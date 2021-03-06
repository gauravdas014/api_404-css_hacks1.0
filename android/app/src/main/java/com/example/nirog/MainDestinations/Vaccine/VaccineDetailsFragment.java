package com.example.nirog.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nirog.MainDestinations.Hospital.DoctorListAdapter;
import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.data.model.VTaken;
import com.example.nirog.databinding.FragmentVaccineDetailsBinding;


public class VaccineDetailsFragment extends Fragment {


    private static final String VACCINE_NAME = "param1";
    private static final String VACCINE_ID = "param2";
    private static final String WHEN_TO_GIVE = "param2";
    private static final String DOSE = "dose";
    private static final String ROUTE = "route";
    private static final String SITE = "site";
    private static final String DESC = "description";
    private static final String POSITION = "0";

    //view binding
    private FragmentVaccineDetailsBinding binding;
    //init view model
    private HospitalViewModel viewModel;


    private String mVaccineName;
    private String mVaccineId;
    private String mWhenToGive;
    private String mDose;
    private String mRoute;
    private String mSite;
    private String mDescription;
    private int mPosition;

    public VaccineDetailsFragment() {
        // Required empty public constructor
    }


    public static VaccineDetailsFragment newInstance(
            String vaccineName,
            String vaccineId,
            String whenToGive,
            int position,
            String dose,
            String route,
            String site,
            String description) {


        VaccineDetailsFragment fragment = new VaccineDetailsFragment();
        Bundle args = new Bundle();
        args.putString(VACCINE_NAME, vaccineName);
        args.putString(VACCINE_ID, vaccineId);
        args.putString(WHEN_TO_GIVE, whenToGive);
        args.putString(DOSE, dose);
        args.putString(ROUTE, route);
        args.putString(SITE, site);
        args.putString(DESC, description);
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
            mDose = getArguments().getString(DOSE);
            mRoute = getArguments().getString(ROUTE);
            mSite = getArguments().getString(SITE);
            mDescription = getArguments().getString(DESC);
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
        binding.site.setText(mSite);
        binding.route.setText(mRoute);
        binding.description.setText(mDescription);
        binding.dose.setText(mDose);

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendDataToServer();
            }
        });

        return binding.getRoot();
    }

    public void SendDataToServer()
    {
        VTaken vTaken = new VTaken("6028db558817ff0015d855b8",mVaccineId);
        viewModel.AddVaccinesTaken(vTaken);
        viewModel.AddVaccinesTakenRes().observe(this,data->{
            if(data!=null)
            {
                Toast.makeText(getContext(), "SuccessFull!!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}