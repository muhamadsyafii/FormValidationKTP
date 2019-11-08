package com.syafii.formvalidation.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syafii.formvalidation.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    View view;

//    BindView
    @BindView(R.id.tv_resultNik)
    TextView rNik;
    @BindView(R.id.tv_resultNama)
    TextView rNama;
    @BindView(R.id.tv_resultTempat)
    TextView rTempat;
    @BindView(R.id.tv_resultTanggal)
    TextView rTanggal;
    @BindView(R.id.tv_resultAlamat)
    TextView rAlamat;
    @BindView(R.id.tv_resultRT)
    TextView rRt;
    @BindView(R.id.tv_resultRW)
    TextView rRw;
    @BindView(R.id.tv_resultKelurahan)
    TextView rKelurahan;
    @BindView(R.id.tv_resultKecamatan)
    TextView rKec;
    @BindView(R.id.tv_resultAgama)
    TextView rAgama;
    @BindView(R.id.tv_resultStatus)
    TextView rStatus;
    @BindView(R.id.tv_resultKewarga)
    TextView rKewarga;
    @BindView(R.id.tv_resultBerlaku)
    TextView rBerlaku;
    @BindView(R.id.btn_Edit)
    Button btnEdit;


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        onClick();
        return view;
    }

    private void onClick() {

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                moveFirstFragment();
//            }
//        });
    }

    private void moveFirstFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new RegisterFragment()).commit();
    }

}
