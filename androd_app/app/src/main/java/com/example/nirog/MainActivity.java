package com.example.nirog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.nirog.Account.ChildInputDetailsFragment;
import com.example.nirog.MainDestinations.BottomNavFragment;
import com.example.nirog.Splash.SplashFragment;
import com.example.nirog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up view binding for main activity
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setFragment(new ChildInputDetailsFragment());

        setContentView(mainBinding.getRoot());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}