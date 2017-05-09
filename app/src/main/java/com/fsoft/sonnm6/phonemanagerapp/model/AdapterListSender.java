package com.fsoft.sonnm6.phonemanagerapp.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fsoft.sonnm6.phonemanagerapp.R;
import com.fsoft.sonnm6.phonemanagerapp.data.MySharedPreferences;
import com.fsoft.sonnm6.phonemanagerapp.view.DialogCustom;

import java.util.ArrayList;

/**
 * Created by SonNM6 on 12/22/2016.
 */
public class AdapterListSender extends RecyclerView.Adapter<AdapterListSender.MyViewHolder> {
    private static final String TAG = "AdapterListSender";
    private ArrayList<String> mListSender = new ArrayList<>();
    private Context mContext;

    public AdapterListSender(Context context, ArrayList<String> strings) {
        if (strings != null) {
            this.mListSender = strings;
        }
        mContext = context;

    }

    public ArrayList<String> getmListSender() {
        return mListSender;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sender, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.title.setText(mListSender.get(position));
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "+ " + position);
                DialogCustom dialogCustom = new DialogCustom(mContext, mContext.getString(R.string.key_delete), "Bạn muốn xóa:\"" + mListSender.get(position) + "\"", mContext.getString(R.string.key_cancel), mContext.getString(R.string.key_ok), new DialogCustom.IOnclickMultiButtonDialog() {
                    @Override
                    public void onClickBtnCancelOrLeft() {

                    }

                    @Override
                    public void onClickBtnRight() {
                        getmListSender().remove(position);
                       notifyDataSetChanged();
                        MySharedPreferences.getInstance(mContext).saveListSender(getmListSender());

                    }
                });
                dialogCustom.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mListSender.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public Button btnRemove;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_name_sender);
            btnRemove = (Button) view.findViewById(R.id.btn_remove);
        }
    }
}
