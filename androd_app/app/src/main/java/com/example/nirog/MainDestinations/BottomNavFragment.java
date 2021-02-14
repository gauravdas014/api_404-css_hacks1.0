package com.example.nirog.MainDestinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nirog.MainDestinations.Account.AccountFragment;
import com.example.nirog.MainDestinations.Hospital.HospitalFragment;
import com.example.nirog.MainDestinations.Vaccine.VaccineFragment;
import com.example.nirog.R;
import com.example.nirog.databinding.FragmentBottomNavBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.MaterialElevationScale;


public class BottomNavFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //binding
    private FragmentBottomNavBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BottomNavFragment() {
        // Required empty public constructor
    }



    public static BottomNavFragment newInstance(String param1, String param2) {
        BottomNavFragment fragment = new BottomNavFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavBinding.inflate(inflater, container, false);


        // attaching the bottom navigation view with listener
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        //setting the default fragment for bottom navigation
        setFragment(new VaccineFragment());

        return binding.getRoot();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.vaccine_destination:
                setFragment(new VaccineFragment());
                break;
            case R.id.hospital_destination:
                setFragment(new HospitalFragment());
                break;
            case R.id.guide_destination:
                //TODO: Make guide fragment
                break;
            case R.id.account_destination:
                //TODO: Make settings fragment
                setFragment(new AccountFragment());
                break;
        }
        return true;
    }


    private void setFragment(Fragment fragment) {
        fragment.setEnterTransition(new MaterialElevationScale(true));
        fragment.setReturnTransition(new MaterialElevationScale(false));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.destination_container,fragment);
        fragmentTransaction.commit();
    }
}