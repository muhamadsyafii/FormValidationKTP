package com.syafii.formvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTextChangeListener extends AppCompatActivity {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.et_user)
    EditText eUser;
    @BindView(R.id.et_pass)
    EditText ePass;
    @BindView(R.id.btn_submit)
    Button btnSumbit;

    String user;
    String pass;
    String esubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text_change_listener);
        ButterKnife.bind(this);

        eUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!eUser.getText().toString().isEmpty()){
                    user = eUser.getText().toString();
                    Log.e("Hasil", user);
                }else{
                    eUser.setError("Isi dulu dong");
                }

//                user = eUser.getText().toString();
//                tvUser.setText(user);
//                Log.e("user", user);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
