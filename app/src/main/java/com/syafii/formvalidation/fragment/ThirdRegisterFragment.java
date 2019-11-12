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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

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

    //  Global String
    String agama = "";
    String status = "";
    String kewarga = "";
    String berlaku = "";

//    Model
    User data;

    public ThirdRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_third_register, container, false);
        ButterKnife.bind(this, view);

        data = (User)getArguments().getSerializable("user");
        onClick();
        return view;
    }

    private void onClick() {
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subValidation();
                if (!agama.isEmpty()){
                    ResultFragment result = new ResultFragment();
                    Bundle bundle = new Bundle();
                    User user = new User();

                    user.setNik(data.getNik());
                    user.setNama(data.getNama());
                    user.setTempat(data.getTempat());
                    user.setTanggal(data.getTanggal());
                    user.setAlamat(data.getAlamat());
                    user.setRt(data.getRt());
                    user.setRw(data.getRw());
                    user.setKelurahan(data.getKelurahan());
                    user.setKecamatan(data.getKecamatan());
                    user.setAgama(agama);
                    user.setStatus(status);
                    user.setKewarganegaraan(kewarga);
                    user.setBerlaku(berlaku);
                    bundle.putSerializable("user", user);
                    result.setArguments(bundle);
                    moveResultFragment(result);
                    Toasty.success(getActivity(), "Berhasil mengisi data", Toasty.LENGTH_SHORT).show();
                }else{
                    Toasty.info(getContext(), "Harap diisi dahulu", Toast.LENGTH_SHORT, true).show();
                }
                closeKeyboard();
            }
        });

        eBerlaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                showDateValid();

            }
        });
    }


    void subValidation() {
        validAgama();
        validStatus();
        validKew();
        validBerlaku();
    }

    private boolean validAgama() {
        String pAgama = ".*[A-Z].*";
        if (!eAgama.getText().toString().isEmpty() && Pattern.compile(pAgama).matcher(eKewarga.getText().toString()).matches()) {
            agama = eAgama.getText().toString();
            eAgama.setSelection(eAgama.getText().length());
        } else {
            eAgama.setError(getString(R.string.error_cap));
            return false;
        }
        return true;
    }

    private boolean validStatus() {
        if (!eStatus.getText().toString().isEmpty()) {
            status = eStatus.getText().toString();
            eStatus.setSelection(eStatus.getText().length());
        } else {
            eStatus.setError(getString(R.string.error_status));
            return false;
        }
        return true;
    }

    private boolean validKew() {
        String pKew = ".*[A-Z].*";
        if (!eKewarga.getText().toString().isEmpty() && Pattern.compile(pKew).matcher(eKewarga.getText().toString()).matches()) {
            kewarga = eKewarga.getText().toString();
            eKewarga.setSelection(eKewarga.getText().length());
        } else {
            eKewarga.setError(getString(R.string.error_kewarga));
            return false;
        }
        return true;
    }

    private boolean validBerlaku() {
////        String regBerlaku = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Log.e("Berlaku = ", berlaku);
        if (!eBerlaku.getText().toString().isEmpty() /*&& Pattern.compile(regBerlaku).matcher(berlaku).matches()*/) {
            berlaku = eBerlaku.getText().toString();
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

    private void moveResultFragment(ResultFragment resultFragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.setCustomAnimations(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
        ft.replace(R.id.frameActivity, resultFragment).commit();
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
