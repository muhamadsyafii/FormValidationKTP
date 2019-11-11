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
import com.syafii.formvalidation.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstRegisterFragment extends Fragment {
    View view;

    //    EditText
    @BindView(R.id.btn_register)
    Button btnReg;
    @BindView(R.id.et_nik)
    EditText etNik;
    @BindView(R.id.et_tempat)
    EditText etTempat;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.et_name)
    EditText etNama;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.et_rt)
    EditText etRt;
    @BindView(R.id.et_rw)
    EditText etRw;
    @BindView(R.id.et_kelurahan)
    EditText etKelurahan;
    @BindView(R.id.et_kecamatan)
    EditText etKecamatan;
    @BindView(R.id.et_agama)
    EditText etAgama;
    @BindView(R.id.et_status)
    EditText etStatus;
    @BindView(R.id.et_kewarganegaraan)
    EditText etKewarganegaraan;
    @BindView(R.id.et_berlaku)
    EditText etBerlaku;

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


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


    public FirstRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_register, container, false);
        ButterKnife.bind(this, view);


        onClick();

        return view;
    }

    private void onClick() {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                subValidation();
                closeKeyboard();
                moveSecondFragment();
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateBirth();
                closeKeyboard();
            }
        });
        etBerlaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateValid();
                closeKeyboard();
            }
        });
    }

    void subValidation() {
        validateNIK();
        validateNama();
        validAlamat();
        validateTempat();
        validTanggal();
        validKelDes();
        validRt();
        validRw();
        validKec();
        validAgama();
        validStatus();
        validKew();
        validBerlaku();

    }

    //    Membuat Validasinya
    private boolean validateNIK() {
        if (!etNik.getText().toString().trim().isEmpty() && etNik.length() == 16) {
            lyNik.setErrorEnabled(false);
            etNik.setSelection(etNik.getText().length());
            nikErr.setVisibility(View.GONE);
        } else {
            lyNik.setError(getString(R.string.error_nik));
//            etNik.setError("Mohon di isi 16 digits");
            nikErr.setVisibility(View.VISIBLE);
            etNik.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateNama() {
        String nama = etNama.getText().toString().trim();
        String patternName = ".*[A-Z].*";
        if (!nama.isEmpty() && Pattern.compile(patternName).matcher(nama).matches()) {
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
            etTempat.setSelection(etTempat.getText().length());
        } else {
            etTempat.setError(getString(R.string.error_tempat));
            return false;
        }
        return true;
    }

    private boolean validTanggal() {
        String date = etDate.getText().toString();
//        String pDate = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Log.e("Tanggal = ", date);
        if (!date.isEmpty() /*&& Pattern.compile(pDate).matcher(date).matches()*/) {
            etDate.setError(null);
            etDate.setSelection(etDate.getText().length());
        } else {
            etDate.setError(getString(R.string.error_tanggal));
            return false;
        }
        return true;
    }

    private boolean validAlamat() {
        if (!etAlamat.getText().toString().trim().isEmpty()) {
            etAlamat.setSelection(etAlamat.getText().length());

        } else {
            etAlamat.setError(getString(R.string.error_alamat));
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
        String rw = etRw.getText().toString();
        if (!rw.isEmpty() && rw.length() == 3) {
            etRw.setSelection(etRw.getText().length());
        } else {
            etRw.setError("Insert character length 3 digits");
            etRw.setError(getString(R.string.error_rt));
            return false;
        }
        return true;
    }

    private boolean validKelDes() {
        String kelDes = etKelurahan.getText().toString();
        String patternKaldes = ".*[A-Z].*";
        if (!kelDes.isEmpty() && Pattern.compile(patternKaldes).matcher(kelDes).matches()) {
            etKelurahan.setSelection(etKelurahan.getText().length());
        } else {
            etKelurahan.setError(getString(R.string.error_kel));
            etKelurahan.setError(getString(R.string.error_cap));
//            etKelurahan.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validKec() {
        String kec = etKecamatan.getText().toString().trim();
        String patternKec = ".*[A-Z].*";

        if (!kec.isEmpty() && Pattern.compile(patternKec).matcher(kec).matches()) {
            etKecamatan.setSelection(etKecamatan.getText().length());
        } else {
            etKecamatan.setError(getString(R.string.error_kec));
            return false;

        }
        return true;
    }

    private boolean validAgama() {
        String agama = etAgama.getText().toString();
        String pAgama = ".*[A-Z].*";

        if (!agama.isEmpty() && Pattern.compile(pAgama).matcher(agama).matches()) {
            etAgama.setSelection(etAgama.getText().length());
        } else {
            etAgama.setError(getString(R.string.error_cap));
            return false;
        }
        return true;
    }

    private boolean validStatus() {
        String status = etStatus.getText().toString();
        if (!status.isEmpty()) {
            etStatus.setSelection(etStatus.getText().length());
        } else {
            etStatus.setError(getString(R.string.error_status));
            return false;
        }
        return true;
    }

    private boolean validKew() {
        String kewag = etKewarganegaraan.getText().toString();
        String pKew = ".*[A-Z].*";
        if (!kewag.isEmpty() && Pattern.compile(pKew).matcher(kewag).matches()) {
            etKewarganegaraan.setSelection(etKewarganegaraan.getText().length());
        } else {
            etKewarganegaraan.setError(getString(R.string.error_kewarga));
            return false;
        }
        return true;
    }

    private boolean validBerlaku() {
        String berlaku = etBerlaku.getText().toString();
//        String regBerlaku = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        Log.e("Berlaku = ", berlaku);
        if (!berlaku.isEmpty() /*&& Pattern.compile(regBerlaku).matcher(berlaku).matches()*/) {
//            etBerlaku.setSelection(etBerlaku.getText().length());
            etBerlaku.setError(null);
        } else {
            etBerlaku.setError("Isi masa berlaku | ex. DD-MM-YYYY");
//            etBerlaku.requestFocus();
            return false;
        }
        return true;
    }


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


    private void showDateValid() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                etBerlaku.setText(simpleDateFormat.format(newDate.getTime()));

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

    public void moveSecondFragment() {
//        ResultFragment resultFragment = new ResultFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_left_to_right, R.anim.anim_right_to_left);
        ft.replace(R.id.frameActivity, new SecondRegisterFragment()).commit();

    }

}
