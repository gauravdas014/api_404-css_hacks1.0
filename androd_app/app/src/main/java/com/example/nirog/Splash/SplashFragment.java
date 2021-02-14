package com.example.nirog.Splash;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nirog.Account.ChildInputDetailsFragment;
import com.example.nirog.Authentication.LoginFragment;
import com.example.nirog.MainDestinations.BottomNavFragment;
import com.example.nirog.R;
import com.example.nirog.databinding.FragmentSplashBinding;


public class SplashFragment extends Fragment {

    //binding
    private FragmentSplashBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String id = sharedPreferences.getString("User_id",null);
                if(id == null)
                    setFragment(new LoginFragment());
                else
                    setFragment(new BottomNavFragment());
            }
        },600);


        return binding.getRoot();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}