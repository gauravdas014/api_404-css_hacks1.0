package com.example.nirog.Account;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.nirog.MainDestinations.BottomNavFragment;
import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.data.model.Babydata;
import com.example.nirog.databinding.FragmentChildInputDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChildInputDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    //view binding
    private FragmentChildInputDetailsBinding binding;
    private FirebaseAuth mAuth;
    private int Day=0;
    private int Month=0;
    private int Year=0;
    private String gender = "";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private HospitalViewModel viewModel;
    private SharedPreferences sharedPrefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildInputDetailsBinding.inflate(inflater, container, false);

        sharedPrefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(HospitalViewModel.class);

        // date picker for the child on clicking the edit text
        binding.selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrievedate();
            }
        });

        /*Bundle Retrival
        Bundle bundle = this.getArguments();
        String data = bundle.getString("baby_name");
        binding.babyNameEdittext.setText(data);
        String data2 = bundle.getString("father_name");
        binding.fatherNameEdittext.setText(data2);
        String data3 = bundle.getString("mother_name");
        binding.motherNameEdittext.setText(data3);
        String data4 = bundle.getString("DOB");
        binding.ageInYrsEdittext.setText(data4);
        gender = bundle.getString("gender");
        if(data.length() != 0){
            binding.button.setText("Update");
        }*/

        binding.maleSelectionBabyForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
            }
        });

        binding.femaleSelectioinBabyForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                binding.ageInYrsEdittext.setText(Day+"/"+Month+"/"+Year);
            }
        };

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarChildDetailFragment.setVisibility(View.VISIBLE);
                savedatafirebase();
            }
        });

        return binding.getRoot();
    }

    private void savedatafirebase() {
        FirebaseAuth mAuth;
        DatabaseReference databaseReference;

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby");
        String user = mAuth.getCurrentUser().getUid();

        String name = binding.babyNameEdittext.getText().toString();
        String mother = binding.motherNameEdittext.getText().toString();
        String father = binding.fatherNameEdittext.getText().toString();
        String D = String.valueOf(Day);
        String M = String.valueOf(Month);
        String Y = String.valueOf(Year);

        databaseReference.child(user).child("name").setValue(name);
        databaseReference.child(user).child("father").setValue(father);
        databaseReference.child(user).child("mother").setValue(mother);
        databaseReference.child(user).child("gender").setValue(gender);
        binding.progressBarChildDetailFragment.setVisibility(View.INVISIBLE);
        setFragment(new BottomNavFragment());
    }


    private void savedata() {
        String name = binding.babyNameEdittext.getText().toString();
        String mother = binding.motherNameEdittext.getText().toString();
        String father = binding.fatherNameEdittext.getText().toString();
        String D = String.valueOf(Day);
        String M = String.valueOf(Month);
        String Y = String.valueOf(Year);
        String ID = sharedPrefs.getString("USER_ID","nothing");
        Toast.makeText(getActivity(),""+ID,Toast.LENGTH_SHORT).show();
        Babydata bd = new Babydata(name,D,M,Y,"5",mother,father);
        viewModel.Register_Baby_detail(ID,bd);
        viewModel.getGetbabyResponse().observe(this, data->{
            if(data != null){
                binding.progressBarChildDetailFragment.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),"Details saved",Toast.LENGTH_SHORT).show();
                setFragment(new BottomNavFragment());
                String d = data.getBaby_details().get_id();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("BABY_ID", d);
                editor.apply();
            }else{
                binding.progressBarChildDetailFragment.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private void retrievedate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                mDateSetListener,year,month,date);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfBirth = simpleDateFormat.format(calendar.getTime());

        binding.ageInYrsEdittext.setText(dateOfBirth);
    }
}