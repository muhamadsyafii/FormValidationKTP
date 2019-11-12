package com.syafii.formvalidation.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;
import com.syafii.formvalidation.Model.User;
import com.syafii.formvalidation.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstRegisterFragment extends Fragment {
    View view;

    //    EditText
    @BindView(R.id.btn_nextFirst)
    Button btnReg;

//    Button
    @BindView(R.id.et_nik)
    EditText etNik;
    @BindView(R.id.et_tempat)
    EditText etTempat;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.et_name)
    EditText etNama;

    //    TextView
    @BindView(R.id.tv_nik)
    TextView tvNik;
    //    TextInputLayput
    @BindView(R.id.ly_nik)
    TextInputLayout lyNik;
    @BindView(R.id.ly_name)
    TextInputLayout lyName;
    @BindView(R.id.tev_ernik)
    TextView nikErr;

//    String nya, ini yang di sebut global variable

    String nik = "";
    String nama = "";
    String tempat = "";
    String tanggal = "";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");



    public FirstRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_register, container, false);
        ButterKnife.bind(this, view);


        onClick();

        return view;
    }

    private void onClick() {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subValidation();
                if (!nik.isEmpty()){
//                    Log.e("Nik :", nik);
//                    Log.e("Nama :", nama);
//                    Log.e("Tempat :", tempat);
//                    Log.e("Tanggal :", tanggal);

                    ResultFragment result = new ResultFragment();
                    User user = new User();
                    Bundle mbundle = new Bundle();
//                    mbundle.putString("nik", nik);
//                    mbundle.putString("nama", nama);
//                    mbundle.putString("tempat", tempat);
//                    mbundle.putString("tanggal", tanggal);
                    user.setNik(nik);
                    user.setNama(nama);
                    user.setTempat(tempat);
                    user.setTanggal(tanggal);
                    mbundle.putSerializable("user", user);
                    result.setArguments(mbundle);
                    moveSecondFragment(result);
                }
                closeKeyboard();




            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateBirth();
                closeKeyboard();
            }
        });
    }

    private void subValidation() {
        validateNIK();
        validateNama();
        validateTempat();
        validTanggal();

    }

    //    Membuat Validasinya
    private boolean validateNIK() {
        if (!etNik.getText().toString().isEmpty() && etNik.length() == 16) {
            nik = etNik.getText().toString();
            lyNik.setErrorEnabled(false);
            etNik.setSelection(etNik.getText().length());
            nikErr.setVisibility(View.GONE);
        } else {
            lyNik.setError(getString(R.string.error_nik));
            nikErr.setVisibility(View.VISIBLE);
            etNik.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateNama() {
//        String patternName = ".*[A-Z].*";
        if (!etNama.getText().toString().isEmpty()) {
            nama = etNama.getText().toString();
            lyName.setErrorEnabled(false);
            etNama.setSelection(etNama.getText().length());
        } else {
            lyName.setError(getString(R.string.error_name));
            etNama.setError("Huruf harus besar semua");
            return false;
        }
        return true;
    }

    private boolean validateTempat() {
        if (!etTempat.getText().toString().isEmpty()) {
            tempat = etTempat.getText().toString();
            etTempat.setSelection(etTempat.getText().length());
        } else {
            etTempat.setError(getString(R.string.error_tempat));
            return false;
        }
        return true;
    }

    private boolean validTanggal() {
//        String pDate = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Log.e("Tanggal = ", tanggal);
        if (!etDate.getText().toString().isEmpty() /*&& Pattern.compile(pDate).matcher(date).matches()*/) {
            tanggal = etDate.getText().toString();
            etDate.setError(null);
            etDate.setSelection(etDate.getText().length());
        } else {
            etDate.setError(getString(R.string.error_tanggal));
            return false;
        }
        return true;
    }

//    private boolean validAlamat() {
//        if (!etAlamat.getText().toString().trim().isEmpty()) {
//            etAlamat.setSelection(etAlamat.getText().length());
//
//        } else {
//            etAlamat.setError(getString(R.string.error_alamat));
//            return false;
//        }
//        return true;
//    }
    private void showDateBirth() {
//
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                etDate.setText(simpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void moveSecondFragment(ResultFragment resultFragment) {
//        ResultFragment resultFragment = new ResultFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
        ft.replace(R.id.frameActivity, resultFragment).commit();

    }

}
