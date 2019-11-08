package com.syafii.formvalidation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.syafii.formvalidation.R;
import com.syafii.formvalidation.fragment.RegisterFragment;
import com.syafii.formvalidation.fragment.ResultFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new RegisterFragment()).commit();}
    }
}
