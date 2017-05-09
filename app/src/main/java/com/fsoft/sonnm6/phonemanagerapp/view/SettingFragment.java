package com.fsoft.sonnm6.phonemanagerapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fsoft.sonnm6.phonemanagerapp.R;
import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;
import com.fsoft.sonnm6.phonemanagerapp.model.APISevice;
import com.fsoft.sonnm6.phonemanagerapp.model.AdapterListSender;
import com.fsoft.sonnm6.phonemanagerapp.model.RequestObject;
import com.fsoft.sonnm6.phonemanagerapp.model.SmsObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private Button mBtnSave;
    private TextView mTvChangePass;
    private EditText mEdtApi;
    private EditText mEdtToken;
    private CheckBox mCheckStatusPass;
    private ImageButton mBtnAdd;
    private RecyclerView mRecyclerView;
    private AdapterListSender adapterListSender;
    private ArrayList<String> mListSender = new ArrayList<>();

    @Override
    public void onDestroy() {
        mView = null;
        mBtnSave = null;
        mEdtApi = null;
        mListSender = null;
        adapterListSender = null;
        mBtnAdd = null;
        mEdtToken = null;
        mBtnAdd = null;
        mRecyclerView = null;
        super.onDestroy();
    }

    public SettingFragment() {
        super();
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.setting_fragment, null);
        initView();
        return mView;
    }


    private void initView() {
//        HideSoftKeyBoard.setupParent(SettingFragment.this, getView());
        mListSender = MySharedPreferences.getInstance(getContext()).getListSender();
        mBtnAdd = (ImageButton) mView.findViewById(R.id.btn_add);
        mBtnSave = (Button) mView.findViewById(R.id.btn_save);
        mEdtApi = (EditText) mView.findViewById(R.id.edt_api);
        mEdtToken = (EditText) mView.findViewById(R.id.edt_token);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.lv_sender);
        mTvChangePass = (TextView) mView.findViewById(R.id.btn_change_pass);
        adapterListSender = new AdapterListSender(getContext(), mListSender);
        mCheckStatusPass = (CheckBox) mView.findViewById(R.id.cb_status_pass);
        mCheckStatusPass.setChecked(MySharedPreferences.getInstance(getActivity()).getStatePass());
        mCheckStatusPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MySharedPreferences.getInstance(getActivity()).saveStatePass(isChecked);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapterListSender);
        mBtnSave.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mTvChangePass.setOnClickListener(this);
        getDataFromLocal();

    }

    private void getDataFromLocal() {

        String api = MySharedPreferences.getInstance(getContext()).getAPI();
        String token = MySharedPreferences.getInstance(getContext()).getToken();
        if (api != null) {
            mEdtApi.setText(api);
        }
        if (token != null) {
            mEdtToken.setText(token);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                DialogCustom dialogCustom = new DialogCustom(getContext(), "Add sender", getString(R.string.key_cancel), getString(R.string.key_ok), new DialogCustom.IOnclickMultiButtonDialogEdittext() {
                    @Override
                    public void onClickBtnCancelOrLeft() {

                    }

                    @Override
                    public void onClickBtnRight(String txt) {
                        if (txt != null && txt.trim().length() > 0) {
                            adapterListSender.getmListSender().add(txt);
                            adapterListSender.notifyItemInserted(adapterListSender.getmListSender().size() - 1);
                            MySharedPreferences.getInstance(getContext()).saveListSender(adapterListSender.getmListSender());
                        }
                    }
                }

                );
                dialogCustom.show();
                break;
            case R.id.btn_save:
                if (mEdtToken.getText() != null) {
                    MySharedPreferences.getInstance(getContext()).saveToken(mEdtToken.getText().toString());
                }
                if (mEdtApi.getText() != null) {
                    MySharedPreferences.getInstance(getContext()).saveAPI(mEdtApi.getText().toString());
                }

                MySharedPreferences.getInstance(getContext()).saveListSender(mListSender);
                Toast.makeText(getContext(), getString(R.string.key_notice_save_success), Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_change_pass:
//                ArrayList<SmsObject> smsObjects = MySharedPreferences.getInstance(getActivity()).getListSMS();
//                String api = MySharedPreferences.getInstance(getActivity()).getAPI();
//                String token = MySharedPreferences.getInstance(getActivity()).getToken();
//                sendToService(api, new RequestObject("TOKEn", "0123116445", "VIETTED", "Hello world"), new SmsObject("VIETTED", "Hello world"), false);
                startActivity(new Intent(getActivity(), DialogVerification.class));
                break;
        }
    }

    public void sendToService(String api, RequestObject requestObject, final SmsObject smsObject, final boolean positionEnd) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl("https://your.api.url/").addConverterFactory(GsonConverterFactory.create()).build();
        APISevice service = retrofit.create(APISevice.class);
        Call<ResponseBody> requestObjectCall = service.sendService(api, requestObject);
        requestObjectCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
