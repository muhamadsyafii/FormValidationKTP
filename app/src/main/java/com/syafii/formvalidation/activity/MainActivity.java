package com.syafii.formvalidation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.syafii.formvalidation.R;
import com.syafii.formvalidation.fragment.FirstRegisterFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameActivity, new FirstRegisterFragment()).commit();}
    }
}
