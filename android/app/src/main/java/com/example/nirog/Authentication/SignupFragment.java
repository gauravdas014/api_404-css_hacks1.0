package com.example.nirog.Authentication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nirog.Account.ChildInputDetailsFragment;
import com.example.nirog.MainDestinations.BottomNavFragment;
import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.data.model.NEWSIGNUP;
import com.example.nirog.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.SignatureSpi;


public class SignupFragment extends Fragment {

    //binding
    private FragmentSignupBinding binding;
    private HospitalViewModel viewModel;
    private SharedPreferences sharedPrefs;
    private ChildInputDetailsFragment childInputDetailsFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(HospitalViewModel.class);

        childInputDetailsFragment = new ChildInputDetailsFragment();
        sharedPrefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarSignUp.setVisibility(View.VISIBLE);
                checkdata();
            }
        });

        binding.email.addTextChangedListener(signUpTextWatcher);
        binding.confirmPassword.addTextChangedListener(signUpTextWatcher);
        binding.password.addTextChangedListener(signUpTextWatcher);

        return binding.getRoot();
    }

    private void checkdata() {
        String Email = binding.email.getText().toString();
        String Password = binding.password.getText().toString();
        String ConPassword = binding.confirmPassword.getText().toString();

        if(Email.length()==0){
            Toast.makeText(getActivity(),"please enter email filed",Toast.LENGTH_SHORT).show();
            binding.emailTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(Password.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(ConPassword.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            binding.confirmPasswordTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(Password.length()<6){
            Toast.makeText(getActivity(), "Password length must be greater than 6", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(!Password.equals(ConPassword)){
            Toast.makeText(getActivity(), "Password and Confirm Password doen't match", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("Required Field");
            binding.confirmPasswordTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else{
            binding.progressBarSignUp.setVisibility(View.VISIBLE);
            signupfirebase();
        }
    }

    private void signupfirebase() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String mail = binding.email.getText().toString();
        String pa = binding.password.getText().toString();

        mAuth.createUserWithEmailAndPassword(mail,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(),"Signup completed",Toast.LENGTH_SHORT).show();
                    setFragment(new ChildInputDetailsFragment());
                }
                else{
                    if(task.getException()!=null){
                        Toast.makeText(getActivity(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void signup() {
        String email = binding.email.getText().toString();
        String pass = binding.confirmPassword.getText().toString();
        NEWSIGNUP ss = new NEWSIGNUP("Raj kishan",""+email,"45874521410","Muzaffarpur",""+pass);
        viewModel.SignUp(ss);
        viewModel.getSignUpResponse().observe(this, data->{
            if(data != null){
                binding.progressBarSignUp.setVisibility(View.INVISIBLE);
                String status = data.getStatus();
                if(status.equals("fail")){
                    Toast.makeText(getActivity(),"User already exit",Toast.LENGTH_SHORT).show();
                }
                else {
                    String id = data.getUser_details().get_id();
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString("USER_iD", id);
                    editor.apply();
                    Toast.makeText(getActivity(), "Signup Successfull", Toast.LENGTH_SHORT).show();
                    setFragment(new ChildInputDetailsFragment());
                }
            }else{
                binding.progressBarSignUp.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private TextWatcher signUpTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = binding.email.getText().toString().trim();
            String passwordInput = binding.password.getText().toString().trim();
            String confirmPasswordInput = binding.confirmPassword.getText().toString().trim();

            //enable button whenever there is input in all the fields
            binding.signUpBtn.setEnabled(!emailInput.isEmpty() && !passwordInput.isEmpty() && !confirmPasswordInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

}