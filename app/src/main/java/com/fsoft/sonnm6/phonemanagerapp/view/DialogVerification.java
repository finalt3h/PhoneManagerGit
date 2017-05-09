package com.fsoft.sonnm6.phonemanagerapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fsoft.sonnm6.phonemanagerapp.R;
import com.fsoft.sonnm6.phonemanagerapp.common.HideSoftKeyBoard;
import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;
import com.fsoft.sonnm6.phonemanagerapp.model.ViewFragmentPagerAdapter;
import com.fsoft.sonnm6.phonemanagerapp.viewcustomize.CustomViewPager;

import java.util.ArrayList;



/**
 * Created by SonNM6 on 9/15/2016.
 */
public class DialogVerification extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "DialogVerification";
    public static final String NUMBER_PHONE = "NUMBER_PHONE";
    public static final String BACK_TO_STEP1 = "BACK_TO_STEP1";

    private Button mBtnLeft, mBtnRight;
    private TextView mTvMessage;
    private TextView mTvTitle;
    private ArrayList<Fragment> mListFragments;
    private DialogVerificationStep1 mFragmentStep1;
    private DialogVerificationStep1 mFragmentStep2;
    private DialogVerificationStep1 mFragmentStep3;
    private ViewFragmentPagerAdapter mProviderFragmentPagerAdapter;
    private Button pager1;
    private Button pager2;
    private Button pager3;
    private CustomViewPager mCustomViewPager;
    private static final int TAB1 = 0;
    private static final int TAB2 = 1;
    private static final int TAB3 = 2;

    private String pass;
    private String newPass;


    @Override
    protected void onDestroy() {
        mFragmentStep1 = null;
        mFragmentStep2 = null;
        mFragmentStep3 = null;
        mProviderFragmentPagerAdapter = null;
        mBtnLeft = null;
        mBtnRight = null;

        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_verification);

            getWindow().setBackgroundDrawableResource(R.drawable.border_bg_dialog);
            setFinishOnTouchOutside(false);

            intView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void intView() {
        pass = MySharedPreferences.getInstance(this).getPass();
        HideSoftKeyBoard.setupParent(DialogVerification.this, getWindow().getDecorView().getRootView());
        mListFragments = new ArrayList<>();
        mFragmentStep1 = DialogVerificationStep1.newInstance();
        mFragmentStep2 = DialogVerificationStep1.newInstance();
        mFragmentStep3 = DialogVerificationStep1.newInstance();
        mListFragments.add(mFragmentStep1);
        mListFragments.add(mFragmentStep2);
        mListFragments.add(mFragmentStep3);
        mTvTitle = (TextView) findViewById(R.id.title_dialog);
        mTvMessage = (TextView) findViewById(R.id.tv_message_verification);
        pager1 = (Button) findViewById(R.id.pager_1);
        pager2 = (Button) findViewById(R.id.pager_2);
        pager3 = (Button) findViewById(R.id.pager_3);
        mBtnLeft = (Button) findViewById(R.id.btn_left_verification);
        mCustomViewPager = (CustomViewPager) findViewById(R.id.view_pager_1);
        mBtnRight = (Button) findViewById(R.id.btn_right_verification);
        mBtnLeft.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
//        mBtnClose.setOnClickListener(this);
        mProviderFragmentPagerAdapter = new ViewFragmentPagerAdapter(getSupportFragmentManager(), mListFragments);
        mCustomViewPager.setAdapter(mProviderFragmentPagerAdapter);
        mCustomViewPager.getAdapter().notifyDataSetChanged();

        if (MySharedPreferences.getInstance(this).getPass() == null || MySharedPreferences.getInstance(this).getPass().trim().length() < 1) {
            mCustomViewPager.setCurrentItem(TAB2);
            loadDataInPage(TAB2);
        } else {
            mCustomViewPager.setCurrentItem(TAB1);
            loadDataInPage(TAB1);
        }

        mCustomViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                loadDataInPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void loadDataInPage(int position) {
        mTvMessage.setText("");
        mTvMessage.setVisibility(View.GONE);
        switch (position) {
            case TAB1:
                mTvTitle.setText("Enter your current password");
                pager1.setBackgroundResource(R.drawable.circle_select_true);
                pager2.setBackgroundResource(R.drawable.circle_select_false);
                pager3.setBackgroundResource(R.drawable.circle_select_false);

                mBtnLeft.setText(getString(R.string.key_close));
                mBtnRight.setText(getString(R.string.key_ok));
                Log.d(TAG, "tab1");
                break;
            case TAB2:
                mTvTitle.setText("Enter your new password");
                pager1.setBackgroundResource(R.drawable.circle_select_false);
                pager2.setBackgroundResource(R.drawable.circle_select_true);
                pager3.setBackgroundResource(R.drawable.circle_select_false);

                mBtnRight.setText(getString(R.string.key_ok));

                Log.d(TAG, "tab2");
                break;
            case TAB3:
                mTvTitle.setText("Confirm your new password");
                pager1.setBackgroundResource(R.drawable.circle_select_false);
                pager2.setBackgroundResource(R.drawable.circle_select_false);
                pager3.setBackgroundResource(R.drawable.circle_select_true);
                mBtnLeft.setText(getString(R.string.key_close));
                mBtnRight.setText(getString(R.string.key_confirm));
                Log.d(TAG, "tab3");
                break;
        }

    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "tab1");

        switch (view.getId()) {
            case R.id.btn_right_verification:

                switch (mCustomViewPager.getCurrentItem()) {
                    case TAB1:
                        if (checkEdittext(mFragmentStep1.getTextPass())) {
                            if (pass.equals(mFragmentStep1.getTextPass())) {
                                mCustomViewPager.setCurrentItem(TAB2);
                                mTvMessage.setText("");
                                mTvMessage.setVisibility(View.GONE);
                            } else {
                                mTvMessage.setText(getString(R.string.key_pass_wrong));
                                mTvMessage.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mTvMessage.setText(getString(R.string.key_pass_error));
                            mTvMessage.setVisibility(View.VISIBLE);
                        }

                        break;
                    case TAB2:

                        if (checkEdittext(mFragmentStep2.getTextPass())) {
                            newPass = mFragmentStep2.getTextPass();
                            mCustomViewPager.setCurrentItem(TAB3);
                            mTvMessage.setText("");

                            mTvMessage.setVisibility(View.GONE);
                        } else {
                            mTvMessage.setText(getString(R.string.key_pass_error));
                            mTvMessage.setVisibility(View.VISIBLE);
                        }
                        break;
                    case TAB3:
                        if (checkEdittext(mFragmentStep3.getTextPass())) {
                            if (newPass.equals(mFragmentStep3.getTextPass())) {
                                Toast.makeText(this, getString(R.string.key_pass_change_successfully), Toast.LENGTH_SHORT).show();
                                MySharedPreferences.getInstance(this).savePass(newPass);
                                finish();
                            } else {
                                mTvMessage.setText(getString(R.string.key_pass_confirm_wrong));
                                mTvMessage.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mTvMessage.setText(getString(R.string.key_pass_error));
                            mTvMessage.setVisibility(View.VISIBLE);
                        }
                        break;
                }

                break;
            case R.id.btn_left_verification:
                finish();
                break;
        }
    }

    public static final String NUMBER_PHONE_VERIFY = "NUMBER_PHONE_VERIFY";


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public boolean checkEdittext(String string) {
        if (string != null) {
            if (string.trim().length() < 1) {
                return false;
            } else {
                return true;
            }
        } else {
            Toast.makeText(this, "Edittext null", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
