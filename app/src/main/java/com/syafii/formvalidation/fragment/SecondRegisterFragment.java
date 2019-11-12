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
import android.widget.Toast;

import com.syafii.formvalidation.Model.User;
import com.syafii.formvalidation.R;

import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

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

//    Variable Global

    String alamat = "";
    String rt = "";
    String rw = "";
    String kelurahan = "";
    String kecamatan = "";

    User data;


    public SecondRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second_register, container, false);
        ButterKnife.bind(this, view);

        data = (User)getArguments().getSerializable("user");

        onClick();
        return view;
    }

    private void onClick() {
        btnNextSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subValidation();
                if (!alamat.isEmpty()
                        && !rt.isEmpty() && !rw.isEmpty()
                        && !kelurahan.isEmpty() && !kecamatan.isEmpty()) {

//                    Cek apakah sudah masuk yang di masukan ke dalam edittext
                    Log.e("alamat", alamat);
                    Log.e("rt", rt);
                    Log.e("rw", rw);
                    Log.e("kelurahan", kelurahan);
                    Log.e("kecamatan", kecamatan);


                    Bundle bundle = new Bundle();
                    ThirdRegisterFragment third = new ThirdRegisterFragment();
                    User user = new User();

                    user.setNik(data.getNik());
                    user.setNama(data.getNama());
                    user.setTempat(data.getTempat());
                    user.setTanggal(data.getTanggal());
                    user.setAlamat(alamat);
                    user.setRt(rt);
                    user.setRw(rw);
                    user.setKelurahan(kelurahan);
                    user.setKecamatan(kecamatan);
                    bundle.putSerializable("user", user);
                    third.setArguments(bundle);
                    moveThirdFragment(third);
                }else{
                    Toasty.info(getContext(), "Harap diisi dahulu", Toast.LENGTH_SHORT, true).show();
                }
                closeKeyboard();
            }
        });
    }

    private void subValidation() {
        validAlamat();
        validRt();
        validRw();
        validKelDes();
        validKec();
    }

    private  boolean validAlamat(){
        if (!eAlamat.getText().toString().isEmpty()){
            alamat = eAlamat.getText().toString();
            eAlamat.setSelection(eAlamat.getText().length());
        }else {
            eAlamat.setError(getString(R.string.error_alamat));
            return false;
        }
        return true;
    }
    private boolean validRt() {
        if (!etRt.getText().toString().isEmpty() && etRt.getText().toString().length() == 3) {
            rt = etRt.getText().toString();
            etRt.setSelection(etRt.getText().length());
        } else {
            etRt.setError(getString(R.string.error_rt));
            return false;
        }
        return true;
    }

    private boolean validRw() {
        if (!eRw.getText().toString().isEmpty() && eRw.getText().toString().length() == 3) {
            rw = eRw.getText().toString();
            eRw.setSelection(eRw.getText().length());
        } else {
            eRw.setError("Insert character length 3 digits");
            eRw.setError(getString(R.string.error_rt));
            return false;
        }
        return true;
    }

    private boolean validKelDes() {
        String patternKaldes = ".*[A-Z].*";
        if (!eKelurahan.getText().toString().isEmpty() && Pattern.compile(patternKaldes).matcher(eKelurahan.getText().toString()).matches()) {
            kelurahan = eKelurahan.getText().toString();
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
        String patternKec = ".*[A-Z].*";

        if (!eKecamatan.getText().toString().isEmpty() && Pattern.compile(patternKec).matcher(eKecamatan.getText().toString()).matches()) {
            kecamatan = eKecamatan.getText().toString();
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

    private void moveThirdFragment(ThirdRegisterFragment thirdRegisterFragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.setCustomAnimations(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
        ft.replace(R.id.frameActivity, thirdRegisterFragment).commit();
    }



}
