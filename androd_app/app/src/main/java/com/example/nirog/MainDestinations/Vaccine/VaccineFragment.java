package com.example.nirog.MainDestinations.Vaccine;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.preference.PreferenceManager;
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


public class VaccineFragment extends Fragment {


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



        //logout
        binding.babyNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("User_id",null);
                editor.apply();
                setFragment(new LoginFragment());
            }
        });

        retrieveBabyData();
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

    private void retrieveBabyData() {
        String userId = sharedPrefs.getString("User_id",null);
        hospitalViewModel.Get_Baby_Data(userId);
        //Toast.makeText(getActivity(),""+userId,Toast.LENGTH_LONG).show();
        hospitalViewModel.getGetBabyDataResponse().observe(this, data->{
            if(data != null){
                String name = data.getBaby_details().getName();
                String Father = data.getBaby_details().getFatherName();
                String Mother = data.getBaby_details().getMotherName();
                String Year = data.getBaby_details().getDateOfBirth() + data.getBaby_details().getMonthOfBirth() + data.getBaby_details().getYearOfBirth();
                Toast.makeText(getActivity(),""+name+"/"+Father+"/"+Mother+"/"+Year,Toast.LENGTH_SHORT).show();
                binding.babyNameTv.setText(name);
                binding.babyFatherTv.setText(Father);
                binding.babyMotherTv.setText(Mother);
                binding.babyYearTv.setText(Year);
            }else{
                Toast.makeText(getActivity(), "There is some error", Toast.LENGTH_SHORT).show();
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
}