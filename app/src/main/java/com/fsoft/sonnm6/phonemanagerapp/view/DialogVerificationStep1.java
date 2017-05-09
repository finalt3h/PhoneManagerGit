package com.fsoft.sonnm6.phonemanagerapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.fsoft.sonnm6.phonemanagerapp.R;


/**
 * Created by SonNM6 on 12/23/2016.
 */
public class DialogVerificationStep1 extends Fragment {
    public DialogVerificationStep1() {
        super();
    }

    private View mView;
    private EditText mEdtPass;

    public static DialogVerificationStep1 newInstance() {
        DialogVerificationStep1 dialogVerificationStep1 = new DialogVerificationStep1();

        return dialogVerificationStep1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.dialog_verification_step1, null);
        mEdtPass = (EditText) mView.findViewById(R.id.edt_pass_verification);

        return mView;
    }

    public String getTextPass() {
        return mEdtPass.getText().toString();
    }
}
