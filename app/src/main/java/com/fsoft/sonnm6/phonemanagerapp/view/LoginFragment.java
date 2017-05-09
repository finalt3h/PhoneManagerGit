package com.fsoft.sonnm6.phonemanagerapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fsoft.sonnm6.phonemanagerapp.MainActivity;
import com.fsoft.sonnm6.phonemanagerapp.R;
import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private Button btnLogin;
    private EditText edtPass;
    private String myPass;

    @Override
    public void onDestroy() {
        mView = null;
        btnLogin = null;
        edtPass = null;
        myPass = null;
        super.onDestroy();
    }

    public LoginFragment() {
        super();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.login_fragment, null);
        initView();
        return mView;
    }

    private void initView() {
        myPass = MySharedPreferences.getInstance(getContext()).getPass();
        btnLogin = (Button) mView.findViewById(R.id.btn_login);
        edtPass = (EditText) mView.findViewById(R.id.edt_pass_login);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (edtPass.getText().toString().equals(myPass)) {
                    getActivity().sendBroadcast(new Intent(MainActivity.ACTION_LOGIN_SUCCESS));
                } else {
                    edtPass.setText("");
                    Toast.makeText(getContext(), getString(R.string.key_notice_login_fail), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
