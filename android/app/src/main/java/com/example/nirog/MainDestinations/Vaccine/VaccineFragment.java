package com.example.nirog.MainDestinations.Vaccine;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nirog.Account.ChildInputDetailsFragment;
import com.example.nirog.Authentication.LoginFragment;
import com.example.nirog.MainDestinations.Hospital.HospitalListAdapter;
import com.example.nirog.R;
import com.example.nirog.Splash.SplashFragment;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.databinding.FragmentVaccineBinding;
import com.google.android.material.transition.MaterialElevationScale;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class VaccineFragment extends Fragment implements VaccineListAdapter.OnVaccineCardClick {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //view binding
    private FragmentVaccineBinding binding;

    private VaccineListAdapter adapter;

    private HospitalViewModel hospitalViewModel;
    private SharedPreferences sharedPrefs;


    private String mParam1;
    private String mParam2;

    public VaccineFragment() {
        // Required empty public constructor
    }


    public static VaccineFragment newInstance(String param1, String param2) {
        VaccineFragment fragment = new VaccineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        hospitalViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(HospitalViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineBinding.inflate(inflater, container, false);
        sharedPrefs= PreferenceManager.getDefaultSharedPreferences(getActivity());



        binding.babyNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("User_id",null);
                editor.apply();
                setFragment(new LoginFragment());
            }
        });
        hospitalViewModel.getAllVaccines();
        hospitalViewModel.getALLVaccinesRes().observe(this,data->{
            if(data != null){
                adapter = new VaccineListAdapter(data.getVaccineDetails(), getContext(), this::onClickListener);
                binding.upcomingVaccinesRecyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
        });


        retrieveBabyDataFirebase();

        return binding.getRoot();

    }

    private void retrieveBabyDataFirebase() {
        FirebaseAuth mAuth;
        DatabaseReference databaseReference;

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby");
        String user = mAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child(user).child("name").getValue(String.class);
                    String father = snapshot.child(user).child("father").getValue(String.class);
                    String mother = snapshot.child(user).child("mother").getValue(String.class);
                    binding.babyNameTv.setText(name);
                    binding.babyFatherTv.setText(father);
                    binding.babyMotherTv.setText(mother);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void retrieveBabyData() {
        String userId = sharedPrefs.getString("User_id",null);
        hospitalViewModel.Get_Baby_Data(userId);
        Log.e(userId,"baby user id");
        hospitalViewModel.getGetbabyResponse().observe(this, data->{
            if(data != null){
                String name = data.getBaby_details().getName();
                String Father = data.getBaby_details().getFatherName();
                String Mother = data.getBaby_details().getMotherName();
                String Year = data.getBaby_details().getAge();
                Toast.makeText(getActivity(),""+name+""+Father+""+Mother+""+Year,Toast.LENGTH_SHORT).show();
                binding.babyNameTv.setText(name);
                binding.babyFatherTv.setText(Father);
                binding.babyMotherTv.setText(Mother);
                binding.babyYearTv.setText(Year);
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClickListener(int position, String vaccineId, String vaccineName, String whenToGive, String dose, String route, String site, String description) {

        VaccineDetailsFragment vaccineDetailsFragment = VaccineDetailsFragment.newInstance(vaccineName, vaccineId, whenToGive, position, dose, route, site, description);

        vaccineDetailsFragment.setEnterTransition(new MaterialElevationScale(true));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, vaccineDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}