package com.example.nirog.MainDestinations.Account;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nirog.Authentication.LoginFragment;
import com.example.nirog.R;
import com.example.nirog.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {

    //view binding
    private FragmentAccountBinding binding;

    private SharedPreferences sharedPrefs;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        sharedPrefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("USER_ID", null);
                editor.apply();
                setFragment(new LoginFragment());
            }
        });

        return binding.getRoot();
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