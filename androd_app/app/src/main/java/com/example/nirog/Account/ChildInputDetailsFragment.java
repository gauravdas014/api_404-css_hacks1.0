package com.example.nirog.Account;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.nirog.R;
import com.example.nirog.databinding.FragmentChildInputDetailsBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChildInputDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //view binding
    private FragmentChildInputDetailsBinding binding;


    private String mParam1;
    private String mParam2;

    public ChildInputDetailsFragment() {
        // Required empty public constructor
    }


    public static ChildInputDetailsFragment newInstance(String param1, String param2) {
        ChildInputDetailsFragment fragment = new ChildInputDetailsFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildInputDetailsBinding.inflate(inflater, container, false);


        // date picker for the child on clicking the edit text
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "Date Picker");
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfBirth = simpleDateFormat.format(calendar.getTime());

        binding.ageInYrsEdittext.setText(dateOfBirth);
    }
}