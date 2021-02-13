package com.example.nirog.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nirog.MainDestinations.Hospital.HospitalListAdapter;
import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.databinding.FragmentVaccineBinding;


public class VaccineFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //view binding
    private FragmentVaccineBinding binding;

    private VaccineListAdapter adapter;

    private HospitalViewModel hospitalViewModel;


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

        hospitalViewModel.getAllVaccines();

        hospitalViewModel.getALLVaccinesRes().observe(this,data->{
            if(data != null){
                adapter = new VaccineListAdapter(data.getVaccineDetails(), getContext());
                binding.upcomingVaccinesRecyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
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