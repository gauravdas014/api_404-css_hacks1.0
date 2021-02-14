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
import android.widget.Toast;

import com.example.nirog.Account.ChildInputDetailsFragment;
import com.example.nirog.MainDestinations.BottomNavFragment;
import com.example.nirog.MainDestinations.Hospital.HospitalListAdapter;
import com.example.nirog.R;
import com.example.nirog.ViewModel.HospitalViewModel;
import com.example.nirog.data.model.Login;
import com.example.nirog.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    //binding
    private FragmentLoginBinding binding;

    private SignupFragment signupFragment;
    private ForgotPasswordFragment forgotPasswordFragment;
    private HospitalViewModel viewModel;
    private SharedPreferences sharedPrefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        sharedPrefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(HospitalViewModel.class);

        signupFragment = new SignupFragment();
        forgotPasswordFragment = new ForgotPasswordFragment();

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(signupFragment);
            }
        });

        binding.forgotPasswordFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(forgotPasswordFragment);
            }
        });

        binding.Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progresslogin.setVisibility(View.VISIBLE);
                checkdata();
            }
        });

        binding.email.addTextChangedListener(loginTextWatcher);
        binding.password.addTextChangedListener(loginTextWatcher);


        return binding.getRoot();
    }

    private void checkdata() {
        String E = binding.email.getText().toString();
        String P = binding.password.getText().toString();
        if(E.length()==0){
            binding.emailTxtiplayout.setError("Required Field");
            binding.progresslogin.setVisibility(View.INVISIBLE);
        }
        else if(P.length()==0) {
            binding.passwordTxtiplayout.setError("Required Field");
            binding.progresslogin.setVisibility(View.INVISIBLE);
        }
        else{
            binding.progresslogin.setVisibility(View.VISIBLE);
            logincheck();
        }
    }

    private void logincheck() {
        Login login = new Login(binding.email.getText().toString(),binding.password.getText().toString());
        viewModel.Login(login);
        viewModel.getLoginResponse().observe(this, data->{
            if(data != null){
                binding.progresslogin.setVisibility(View.INVISIBLE);
                String id = data.getUser_details().get_id();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("User_id",id);
                editor.apply();
                Toast.makeText(getActivity(),"Logged in Successfull",Toast.LENGTH_SHORT).show();
                setFragment(new BottomNavFragment());
            }else{
                binding.progresslogin.setVisibility(View.INVISIBLE);
                String error = data.getStatus();
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = binding.email.getText().toString().trim();
            String passwordInput = binding.password.getText().toString().trim();

            //enabling or disabling the button if there is no any text
            binding.Loginbtn.setEnabled(!emailInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}