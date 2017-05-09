package com.fsoft.sonnm6.phonemanagerapp.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fsoft.sonnm6.phonemanagerapp.R;

import java.util.ArrayList;

/**
 * Created by SonNM6 on 8/23/2016.
 */
public class DialogCustom extends Dialog {
    public static final String ERROR = "ERROR";
    public static final String WARNING = "WARNING";
    public static final String DEFAULT = "DEFAULT";
    private LinearLayout mllMultiButton, mllTitle;
    private RelativeLayout mRltMainMsg;
    private Button mBtnOk;
    private Button mBtnRight;
    private Button mBtnLeft;
    private String mStrMsg, mStrTitle, mStrBtnLeft, mStrBtnRight, mStrBtnOk;
    private TextView mTvMsg;
    private TextView mTvTitle;
    private EditText mEdtSender;
    private String mStateDialog;
    private boolean mIsErrorUser = false;
    private Context mContext;

    private IOnclickMultiButtonDialog mIOnclickMultiButtonDialog = null;
    private IOnclickMultiButtonDialogEdittext mIOnclickMultiButtonDialogEdittext = null;
    private static DialogCustom sDialogCustom = null;

    public static ArrayList<DialogCustom> getListDialog() {
        return listDialog;
    }

    private static ArrayList<DialogCustom> listDialog = new ArrayList<>();

    public static DialogCustom Instance(Context context) {
        if (sDialogCustom == null) {
            sDialogCustom = new DialogCustom(context);
        }
        return sDialogCustom;
    }

    public void addDialog(DialogCustom dialogCustom) {
        listDialog.add(dialogCustom);
    }

    public void destroy() {
        if (sDialogCustom != null) {
            sDialogCustom.cancel();
        }
    }

    public DialogCustom(Context context) {
        super(context);
    }

    public void setColorTitle() {
        mTvTitle.setTextColor(Color.BLUE);
    }


    public DialogCustom(Context context, String title, String msg, String nameBtnLeft, String nameBtnRight, IOnclickMultiButtonDialog iOnclickMultiButtonDialog) {
        super(context);
        mContext = context;
        this.mStrMsg = msg;
        this.mStrTitle = title;
        this.mStrBtnLeft = nameBtnLeft;
        this.mStrBtnRight = nameBtnRight;
        this.mIOnclickMultiButtonDialog = iOnclickMultiButtonDialog;
        DialogCustom.Instance(context).addDialog(this);
    }

    public DialogCustom(Context context, String title, String nameBtnLeft, String nameBtnRight, IOnclickMultiButtonDialogEdittext iOnclickMultiButtonDialog) {
        super(context);
        mContext = context;
        this.mStrTitle = title;
        this.mStrBtnLeft = nameBtnLeft;
        this.mStrBtnRight = nameBtnRight;
        this.mIOnclickMultiButtonDialogEdittext = iOnclickMultiButtonDialog;
        DialogCustom.Instance(context).addDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.drawable.border_bg_dialog);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_custom);
        intView();
    }

    private View mNewViewContent;


    public void setStateDialog(String stateDialog) {
        this.mStateDialog = stateDialog;
    }

    private void setState(String state) {
        if (state == null) {
            return;
        }
        switch (state) {
            case ERROR:
                mTvTitle.setTextColor(getContext().getResources().getColor(R.color.colorError));
                break;
            case WARNING:
                mTvTitle.setTextColor(getContext().getResources().getColor(R.color.colorWarning));
                break;
            case DEFAULT:
                mTvTitle.setTextColor(getContext().getResources().getColor(R.color.text_normal));
                break;
            default:
                break;
        }
    }

    private void intView() {

        mEdtSender = (EditText) findViewById(R.id.edt_name_sender);

        mRltMainMsg = (RelativeLayout) findViewById(R.id.rlt_main_msg);
        mBtnLeft = (Button) findViewById(R.id.btn_left_of_cancel);
        mBtnRight = (Button) findViewById(R.id.btn_right_confirm);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mTvMsg = (TextView) findViewById(R.id.tv_message);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mllMultiButton = (LinearLayout) findViewById(R.id.ll_two_button);
        mllTitle = (LinearLayout) findViewById(R.id.ll_title);
        if (mStrTitle == null) {
            mTvTitle.setText(getContext().getResources().getString(R.string.app_name));
            // mllTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setText(mStrTitle);
            //  mllTitle.setVisibility(View.VISIBLE);
        }
        if (mStrMsg != null) {
            mTvMsg.setText(mStrMsg);
        }

        if (mNewViewContent != null) {
            mTvMsg.setVisibility(View.GONE);
            mRltMainMsg.addView(mNewViewContent);
        }
        if (mStrMsg == null) {
            mTvMsg.setVisibility(View.GONE);
            mEdtSender.setVisibility(View.VISIBLE);

        } else {
            mTvMsg.setVisibility(View.VISIBLE);
            mEdtSender.setVisibility(View.GONE);
        }
        setState(mStateDialog);
        if (mIOnclickMultiButtonDialog != null) {
            mllMultiButton.setVisibility(View.VISIBLE);
            mBtnOk.setVisibility(View.GONE);
            mBtnLeft.setText(mStrBtnLeft);
            mBtnRight.setText(mStrBtnRight);
            mBtnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIOnclickMultiButtonDialog.onClickBtnCancelOrLeft();
                    dismiss();
                }
            });
            mBtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIOnclickMultiButtonDialog.onClickBtnRight();
                    dismiss();
                }
            });

        }
        if (mIOnclickMultiButtonDialogEdittext != null) {
            mllMultiButton.setVisibility(View.VISIBLE);
            mBtnOk.setVisibility(View.GONE);
            mBtnLeft.setText(mStrBtnLeft);
            mBtnRight.setText(mStrBtnRight);
            mBtnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIOnclickMultiButtonDialogEdittext.onClickBtnCancelOrLeft();
                    dismiss();
                }
            });
            mBtnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIOnclickMultiButtonDialogEdittext.onClickBtnRight(mEdtSender.getText().toString());
                    dismiss();
                }
            });

        }

    }


    @Override
    public void onBackPressed() {
        onBackPressed();
    }

    @Override
    public void dismiss() {
        listDialog.clear();
        super.dismiss();
    }



    public interface IOnclickMultiButtonDialog {
        public void onClickBtnCancelOrLeft();

        public void onClickBtnRight();
    }


    public interface IOnclickMultiButtonDialogEdittext {
        public void onClickBtnCancelOrLeft();

        public void onClickBtnRight(String txt);
    }
}
