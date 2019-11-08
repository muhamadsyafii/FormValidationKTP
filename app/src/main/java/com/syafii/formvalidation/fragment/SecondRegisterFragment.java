package com.syafii.formvalidation.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syafii.formvalidation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondRegisterFragment extends Fragment {


    public SecondRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_register, container, false);
    }

}
