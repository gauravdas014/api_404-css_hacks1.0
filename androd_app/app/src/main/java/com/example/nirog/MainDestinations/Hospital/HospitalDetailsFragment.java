package com.example.nirog.MainDestinations.Hospital;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nirog.R;
import com.example.nirog.databinding.FragmentHospitalDetailsBinding;
import com.google.gson.TypeAdapterFactory;


public class HospitalDetailsFragment extends Fragment{


    private static final String HOSPITAL_NAME = "hospital_name";
    private static final String ADDRESS = "address";
    private static final String CONTACT = "contact";
    private static final String POSITION = "position";
    private static final String ID = "id";

    //view binding
    private FragmentHospitalDetailsBinding binding;


    private String mHospitalName;
    private String mAddress;
    private String mContact;
    private String mId;
    private int mPosition;


    public HospitalDetailsFragment() {
        // Required empty public constructor
    }


    public static HospitalDetailsFragment newInstance(String hospitalName, String address, String contact, String id, int position) {
        HospitalDetailsFragment fragment = new HospitalDetailsFragment();
        Bundle args = new Bundle();
        args.putString(HOSPITAL_NAME, hospitalName);
        args.putString(ADDRESS, address);
        args.putString(CONTACT, contact);
        args.putString(ID, id);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHospitalName = getArguments().getString(HOSPITAL_NAME);
            mAddress = getArguments().getString(ADDRESS);
            mId = getArguments().getString(ID);
            mPosition = getArguments().getInt(POSITION);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHospitalDetailsBinding.inflate(inflater, container, false);

        //custom font for collapsing toolbar layout
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.poppins_bold);
        binding.collapsingToolbar.setExpandedTitleTypeface(typeface);
        binding.collapsingToolbar.setCollapsedTitleTypeface(typeface);


        return binding.getRoot();
    }
}