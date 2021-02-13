package com.example.nirog.Authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nirog.R;
import com.example.nirog.databinding.FragmentForgotPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordFragment extends Fragment {

    //binding
    private FragmentForgotPasswordBinding binding;
    private FirebaseAuth mAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();
        binding.forgotPasswordProgressBar.setVisibility(View.INVISIBLE);

        binding.forgotPasswordConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.email.getText().toString().length()>0){
                    binding.forgotPasswordProgressBar.setVisibility(View.VISIBLE);
                    SendEmail();
                }
                else{
                    Toast.makeText(getActivity(),"Please enter Email Id",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }

    private void SendEmail() {
        mAuth.sendPasswordResetEmail(binding.email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    binding.forgotPasswordProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Password Reset link sent to your registered email ID",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.forgotPasswordProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),""+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}