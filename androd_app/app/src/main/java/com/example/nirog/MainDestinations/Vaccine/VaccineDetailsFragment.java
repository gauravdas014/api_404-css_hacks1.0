package com.example.nirog.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.databinding.FragmentVaccineDetailsBinding;


public class VaccineDetailsFragment extends Fragment {


    private static final String VACCINE_NAME = "param1";
    private static final String VACCINE_ID = "param2";
    private static final String WHEN_TO_GIVE = "param2";
    private static final String POSITION = "0";

    //view binding
    private FragmentVaccineDetailsBinding binding;
    //init view model
    private HospitalViewModel viewModel;


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

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HospitalViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineDetailsBinding.inflate(inflater, container, false);

        //setting the text fields from the data of bundle
        binding.vaccineNameDetailed.setText(mVaccineName);
        binding.whenToGiveIt.setText(mWhenToGive);

        //calling view model to get the additional data
        //calling get vaccine api
        viewModel.getVaccine(mVaccineId);

        //getting the response
        viewModel.getVaccineResponse().observe(this, data->{
            if(data != null){
                binding.dose.setText(data.getVacDetails().getDose());
                binding.route.setText(data.getVacDetails().getRoute());
                binding.site.setText(data.getVacDetails().getSite());
                binding.description.setText(data.getVacDetails().getSmallDescription() + "\n" + data.getVacDetails().getDescription());
            }else{
                Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
            }
        });



        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}