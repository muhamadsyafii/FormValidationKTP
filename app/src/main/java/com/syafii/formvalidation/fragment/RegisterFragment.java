package com.syafii.formvalidation.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.syafii.formvalidation.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.xml.validation.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends Fragment {
    View view;

    //    EditText
    @BindView(R.id.btn_register)
    Button btnReg;
    @BindView(R.id.et_nik)
    EditText etNik;
    @BindView(R.id.et_tempat)
    EditText etTtl;
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


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        onClick();

        return view;
    }

    private void onClick() {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subValidation();
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
        } else {
            lyName.setError(getString(R.string.error_name));
            etNama.setError("Huruf harus besar semua");
//            etNik.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateTempat() {
        if (!etTtl.getText().toString().trim().isEmpty()) {
            etTtl.setFocusable(false);
        } else {
            etTtl.setError(getString(R.string.error_tanggal));
//            etDate.requestFocus();
//            etTtl.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validTanggal() {
        String date = etDate.getText().toString().trim();
        String pDate = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        if (date.isEmpty() && Pattern.compile(pDate).matcher(date).matches()) {
            etDate.setFocusable(false);
        } else {
            etDate.setError(getString(R.string.error_tanggal));
            return false;
        }
        return true;
    }

    private boolean validAlamat() {
        if (!etAlamat.getText().toString().trim().isEmpty()) {
            etAlamat.setFocusable(false);
        } else {
            etAlamat.setError(getString(R.string.error_alamat));
            return false;
        }
        return true;
    }

    private boolean validRt() {
        String rt = etRt.getText().toString().trim();
        if (!rt.isEmpty() && rt.length() == 3) {
            etRt.setFocusable(false);
        } else {
            etRt.setError(getString(R.string.error_rtrw));
            return false;
        }
        return true;
    }

    private boolean validRw() {
        String rw = etRw.getText().toString().trim();
        if (!rw.isEmpty() && rw.length() == 3) {
            etRw.setFocusable(false);
        } else {
            etRw.setError("Masukan angkanya 3 digits");
            etRw.setError(getString(R.string.error_rtrw));
            return false;
        }
        return true;
    }

    private boolean validKelDes() {
        String kelDes = etKelurahan.getText().toString().trim();
        String patternKaldes = ".*[A-Z].*";
        if (!kelDes.isEmpty() && Pattern.compile(patternKaldes).matcher(kelDes).matches()) {
            etKelurahan.setFocusable(false);
        } else {
            etKelurahan.setError(getString(R.string.error_kel));
            etKelurahan.setError("Huruf harus besar semua");
//            etKelurahan.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validKec() {
        String kec = etKecamatan.getText().toString().trim();
        String patternKec = ".*[A-Z].*";

        if (!kec.isEmpty() && Pattern.compile(patternKec).matcher(kec).matches()) {
            etKecamatan.setFocusable(false);
        } else {
            etKecamatan.setError("Enter your kecamatan");
//            etKecamatan.requestFocus();
            return false;

        }
        return true;
    }

    private boolean validAgama() {
        String agama = etAgama.getText().toString().trim();
        String pAgama = ".*[A-Z].*";

        if (!agama.isEmpty() && Pattern.compile(pAgama).matcher(agama).matches()) {
            etAgama.setFocusable(false);
        } else {
            etAgama.setError("Huruf diisi dengan kapitas");
//            etAgama.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validStatus() {
        String status = etStatus.getText().toString().trim();
        if (!status.isEmpty()) {
            etStatus.setFocusable(false);
        } else {
            etStatus.setError("Masukan status anda");
//            etStatus.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validKew() {
        String kewag = etKewarganegaraan.getText().toString().trim();
        String pKew = ".*[A-Z].*";
        if (!kewag.isEmpty() && Pattern.compile(pKew).matcher(kewag).matches()) {
            etKewarganegaraan.setFocusable(false);
        } else {
            etKewarganegaraan.setError("Huruf diisi dengan kapital");
//            etKewarganegaraan.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validBerlaku() {
        String berlaku = etBerlaku.getText().toString().trim();
        String regBerlaku = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";
        if (!berlaku.isEmpty() && Pattern.compile(regBerlaku).matcher(berlaku).matches()) {
            etBerlaku.setFocusable(false);
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


}
