package com.syafii.formvalidation.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.syafii.formvalidation.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdRegisterFragment extends Fragment {

    View view;
//  EditText
    @BindView(R.id.et_agama)
    EditText eAgama;
    @BindView(R.id.et_status)
    EditText eStatus;
    @BindView(R.id.et_kewarganegaraan)
    EditText eKewarga;
    @BindView(R.id.et_berlaku)
    EditText eBerlaku;

//  Button
    @BindView(R.id.btn_nextThird)
    Button btnThird;



    public ThirdRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_third_register, container, false);
        ButterKnife.bind(this, view);

        onClick();
        return view;
    }

    private void onClick() {
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        eBerlaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateValid();

            }
        });
    }


    void subValidation(){
        validAgama();
        validStatus();
        validKew();
        validBerlaku();
    }

    private boolean validAgama() {
        String agama = eAgama.getText().toString();
        String pAgama = ".*[A-Z].*";

        if (!agama.isEmpty() && Pattern.compile(pAgama).matcher(agama).matches()) {
            eAgama.setSelection(eAgama.getText().length());
        } else {
            eAgama.setError(getString(R.string.error_cap));
            return false;
        }
        return true;
    }

    private boolean validStatus() {
        String status = eStatus.getText().toString();
        if (!status.isEmpty()) {
            eStatus.setSelection(eStatus.getText().length());
        } else {
            eStatus.setError(getString(R.string.error_status));
            return false;
        }
        return true;
    }

    private boolean validKew() {
        String kewag = eKewarga.getText().toString();
        String pKew = ".*[A-Z].*";
        if (!kewag.isEmpty() && Pattern.compile(pKew).matcher(kewag).matches()) {
            eKewarga.setSelection(eKewarga.getText().length());
        } else {
            eKewarga.setError(getString(R.string.error_kewarga));
            return false;
        }
        return true;
    }

    private boolean validBerlaku() {
        String berlaku = eBerlaku.getText().toString();
////        String regBerlaku = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Log.e("Berlaku = ", berlaku);
        if (!berlaku.isEmpty() /*&& Pattern.compile(regBerlaku).matcher(berlaku).matches()*/) {
////            etBerlaku.setSelection(etBerlaku.getText().length());
            eBerlaku.setError(null);
        } else {
            eBerlaku.setError("Isi masa berlaku | ex. DD-MM-YYYY");
////            etBerlaku.requestFocus();
            return false;
        }
        return true;
    }

    private void showDateValid() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                eBerlaku.setText(simpleDateFormat.format(newDate.getTime()));

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    private void moveResultFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
        ft.replace(R.id.frameActivity, new ThirdRegisterFragment()).commit();
    }
}
