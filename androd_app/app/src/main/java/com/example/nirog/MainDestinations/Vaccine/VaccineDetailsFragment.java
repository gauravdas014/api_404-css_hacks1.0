package com.example.nirog.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nirog.R;
import com.example.nirog.databinding.FragmentVaccineDetailsBinding;


public class VaccineDetailsFragment extends Fragment {


    private static final String VACCINE_NAME = "param1";
    private static final String VACCINE_ID = "param2";
    private static final String WHEN_TO_GIVE = "param2";
    private static final String POSITION = "0";

    //view binding
    private FragmentVaccineDetailsBinding binding;


    private String mVaccineName;
    private String mVaccineId;
    private String mWhenToGive;
    private int mPosition;

    public VaccineDetailsFragment() {
        // Required empty public constructor
    }


    public static VaccineDetailsFragment newInstance(String vaccineName, String vaccineId, String whenToGive, int position) {
        VaccineDetailsFragment fragment = new VaccineDetailsFragment();
        Bundle args = new Bundle();
        args.putString(VACCINE_NAME, vaccineName);
        args.putString(VACCINE_ID, vaccineId);
        args.putString(WHEN_TO_GIVE, whenToGive);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVaccineId = getArguments().getString(VACCINE_ID);
            mVaccineName = getArguments().getString(VACCINE_NAME);
            mWhenToGive = getArguments().getString(WHEN_TO_GIVE);
            mPosition = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineDetailsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}