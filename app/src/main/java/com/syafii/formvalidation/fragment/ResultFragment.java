package com.syafii.formvalidation.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.syafii.formvalidation.Model.User;
import com.syafii.formvalidation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    private View view;

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

    String nik;
    String nama;
    String tempat;
    String tanggal;



    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);

        Viewundle();
        onClick();
        return view;
    }

    private void Viewundle() {

        User user = (User)getArguments().getSerializable("user");

        rNik.setText(user.getNik());
        rNama.setText(user.getNama());
        rTempat.setText(user.getTempat());
        rTanggal.setText(user.getTanggal());
        rAlamat.setText(user.getAlamat());
        rRt.setText(user.getRt());
        rRw.setText(user.getRw());
        rKelurahan.setText(user.getKelurahan());
        rKec.setText(user.getKecamatan());
        rAgama.setText(user.getAgama());
        rStatus.setText(user.getStatus());
        rKewarga.setText(user.getKewarganegaraan());
        rBerlaku.setText(user.getBerlaku());

    }

    private void onClick() {

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
    }

    private void closeFragment() {
        FirstRegisterFragment registerFragment = new FirstRegisterFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.setCustomAnimations(R.anim.anim_right_to_left, R.anim.anim_left_to_right);
//        ft.replace(R.id.frameActivity, registerFragment).commit();
        ft.remove(this).commit();

    }

}
