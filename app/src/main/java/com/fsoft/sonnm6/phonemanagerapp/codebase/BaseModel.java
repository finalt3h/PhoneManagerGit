package com.fsoft.sonnm6.phonemanagerapp.codebase;

import android.content.Context;



/**
 * Created by SonNM6 on 2/24/2017.
 */

/**
 * Class extends BaseModel should have a constructor () that not have parameter .
 */
public abstract class BaseModel {
    private static final String TAG = "BaseModel";
    private ICallBackModel mICallBackModel;
    private Context mContext;

    protected Context getContext() {
        return mContext;
    }

    public void setmContext(Context context) {
        mContext = context;
    }

    public BaseModel(Context context) {
        super();
        mContext = context;

    }
//
//    public void doSomeBackgroundWork() {
//        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
//                .execute(new Runnable() {
//                    // Executes the given task sometime in the future.
//                    @Override
//                    public void run() {
//                        // do some background work here.
//                    }
//                });
//    }
//
//    // Using it for Light-Weight Background Tasks
//    public void doSomeLightWeightBackgroundWork() {
//        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
//                .execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        // do some light-weight background work here.
//                    }
//                });
//    }
//
//    // Using it for MainThread Tasks
//    public void doSomeMainThreadWork() {
//        DefaultExecutorSupplier.getInstance().forMainThreadTasks()
//                .execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        // do some Main Thread work here.
//                    }
//                });
//    }

    public void setICallBackModel(ICallBackModel iCallBackPresenter) {
        mICallBackModel = iCallBackPresenter;
    }

    public void setModel(BaseModel model) {
        model.setICallBackModel(mICallBackModel);
    }

    public ICallBackModel getmICallBackPresenter() {
        if (mICallBackModel == null) {
            MyLog.error(TAG, "ICallBackModel is null !!!");
            mICallBackModel = new ICallBackModel() {
                @Override
                public void onCallBackModel(String key, Object data) {
//
                }
            };
        }
        return mICallBackModel;
    }
}
