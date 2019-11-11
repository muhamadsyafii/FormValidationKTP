package com.syafii.formvalidation.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.syafii.formvalidation.Model.User;
import com.syafii.formvalidation.R;

import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondRegisterFragment extends Fragment {

    View view;

    User user;

    // EditText
    @BindView(R.id.et_alamat)
    EditText eAlamat;
    @BindView(R.id.et_rt)
    EditText etRt;
    @BindView(R.id.et_rw)
    EditText eRw;
    @BindView(R.id.et_kelurahan)
    EditText eKelurahan;
    @BindView(R.id.et_kecamatan)
    EditText eKecamatan;

    //  Button
    @BindView(R.id.btn_nextSecond)
    Button btnNextSecond;


    public SecondRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second_register, container, false);
        ButterKnife.bind(this, view);

        onClick();
        return view;
    }

    private void onClick() {
        btnNextSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();


//                subValidation();
                moveThirdFragment();
            }
        });

    }
    private  boolean validAlamat(){
        String alamat = eAlamat.getText().toString();
        if (!alamat.isEmpty()){
            eAlamat.setSelection(eAlamat.getText().length());
        }else {
            eAlamat.setError(getString(R.string.error_alamat));
            return false;
        }
        return true;
    }
    private boolean validRt() {
        String rt = etRt.getText().toString();
        if (!rt.isEmpty() && rt.length() == 3) {
            etRt.setSelection(etRt.getText().length());
        } else {
            etRt.setError(getString(R.string.error_rt));
            return false;
        }
        return true;
    }

    private boolean validRw() {
        String rw = eRw.getText().toString();
        if (!rw.isEmpty() && rw.length() == 3) {
            eRw.setSelection(eRw.getText().length());
        } else {
            eRw.setError("Insert character length 3 digits");
            eRw.setError(getString(R.string.error_rt));
            return false;
        }
        return true;
    }

    private boolean validKelDes() {
        String kelDes = eKelurahan.getText().toString();
        String patternKaldes = ".*[A-Z].*";
        if (!kelDes.isEmpty() && Pattern.compile(patternKaldes).matcher(kelDes).matches()) {
            eKelurahan.setSelection(eKelurahan.getText().length());
        } else {
            eKelurahan.setError(getString(R.string.error_kel));
            eKelurahan.setError(getString(R.string.error_cap));
            eKelurahan.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validKec() {
        String kec = eKecamatan.getText().toString().trim();
        String patternKec = ".*[A-Z].*";

        if (!kec.isEmpty() && Pattern.compile(patternKec).matcher(kec).matches()) {
            eKecamatan.setSelection(eKecamatan.getText().length());
        } else {
            eKecamatan.setError(getString(R.string.error_kec));
            return false;

        }
        return true;
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void moveThirdFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
        ft.replace(R.id.frameActivity, new ThirdRegisterFragment()).commit();
    }

}
