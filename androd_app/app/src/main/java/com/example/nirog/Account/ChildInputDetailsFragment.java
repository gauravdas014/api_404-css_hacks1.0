package com.example.nirog.Account;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.nirog.R;
import com.example.nirog.databinding.FragmentChildInputDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChildInputDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    //view binding
    private FragmentChildInputDetailsBinding binding;
    private FirebaseAuth mAuth;
    private int Day=0;
    private int Month=0;
    private int Year=0;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildInputDetailsBinding.inflate(inflater, container, false);

        binding.ageInYrsEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrievedate();
            }
        });

        // date picker for the child on clicking the edit text
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrievedate();
               // DialogFragment datePicker = new DatePickerFragment();
               // datePicker.show(getFragmentManager(), "Date Picker");
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                Day = dayOfMonth;
                Toast.makeText(getActivity(),""+Day+"/"+Month+"/"+Year,Toast.LENGTH_SHORT).show();
                binding.ageInYrsEdittext.setText(Day+"/"+Month+"/"+Year);
            }
        };

        return binding.getRoot();
    }

    private void retrievedate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(), android.R.style.Theme_Holo_Dialog_MinWidth,
                mDateSetListener,year,month,date);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //for transparent background
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