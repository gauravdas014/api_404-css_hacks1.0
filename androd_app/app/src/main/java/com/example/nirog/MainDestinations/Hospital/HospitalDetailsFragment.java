package com.example.nirog.MainDestinations.Hospital;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.databinding.FragmentHospitalDetailsBinding;
import com.google.android.material.transition.MaterialContainerTransform;
import com.google.android.material.transition.MaterialElevationScale;
import com.google.gson.TypeAdapterFactory;


public class HospitalDetailsFragment extends Fragment{


    private static final String HOSPITAL_NAME = "hospital_name";
    private static final String ADDRESS = "address";
    private static final String CONTACT = "contact";
    private static final String POSITION = "position";
    private static final String ID = "id";

    //view binding
    private FragmentHospitalDetailsBinding binding;
    //initializing view model for getting hospital data
    private HospitalViewModel viewModel;

    private DoctorListAdapter doctorListAdapter;


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
            mContact = getArguments().getString(CONTACT);
            mPosition = getArguments().getInt(POSITION);

        }

        //creating an instance of view model
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HospitalViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHospitalDetailsBinding.inflate(inflater, container, false);

        setEnterTransition(new MaterialElevationScale(true));
        setReenterTransition(new MaterialContainerTransform());
        //custom font for collapsing toolbar layout
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.poppins_bold);
        binding.collapsingToolbar.setExpandedTitleTypeface(typeface);
        binding.collapsingToolbar.setCollapsedTitleTypeface(typeface);

        //setting the views
        binding.collapsingToolbar.setTitle(mHospitalName);
        binding.addtionalInfo.setText("Phone: " + mContact + "\n" + "Address: " + mAddress);

        //getting the data of particular hospital through id
        viewModel.getHospital(mId);
        //getting the response
        viewModel.getHospitalResponse().observe(this, data->{
            if(data != null){
                Log.d("Hospital", data.getHospitalDetails().getImage() + " : Image Url");
                //setting image from the url
                Glide.with(getContext())
                        .load(data.getHospitalDetails().getImage())
                        .placeholder(R.drawable.placeholder_drawable)
                        .centerCrop()
                        .into(binding.hospitalImageDetail);
                binding.description.setText(data.getHospitalDetails().getDescription());
            }else{
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getAllDoctors(mId);
        viewModel.getAllDoctorsRes().observe(this,data->{
            if(data!=null)
            {
                doctorListAdapter = new DoctorListAdapter(getContext(),data.getDoctorDetails());
                binding.doctorsList.setAdapter(doctorListAdapter);
            }
            else
            {
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
        });





        return binding.getRoot();
    }
}