package com.fsoft.sonnm6.phonemanagerapp.codebase;

import android.content.Context;

import java.lang.reflect.Constructor;

/**
 * Created by tiennguyen on 6/30/16.
 */
public class Utils {

    public static Object newClassInstance(String classname, Context context){
        Object object = null;
        try{
            Class<BasePresenter> c = (Class<BasePresenter>) Class.forName(classname);
            Constructor<BasePresenter> cons = c.getConstructor(Context.class);
            object = cons.newInstance(context);
        }catch (Exception e){
            throw new IllegalStateException("You must override Constructor(Context context) in class "+ classname);
        }
        return object;
    }
    public static Object newClassInstance(String classname){
        Object object = null;
        try{
            Class<BasePresenter> c = (Class<BasePresenter>) Class.forName(classname);
            Constructor<BasePresenter> cons = c.getConstructor();
            object = cons.newInstance();
        }catch (Exception e){
            throw new IllegalStateException("You must override Constructor(Context context) in class "+ classname);
        }
        return object;
    }
}
