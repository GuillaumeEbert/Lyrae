package shindra.lyrae.BattleNetApi.BnObjectFactory.Builder;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import shindra.lyrae.BattleNetApi.BnObject.BnObject;


/**
 * Created by Guillaume on 07/08/2017.
 */

public final class BnObjectBuilder implements Runnable {
    private BnBuilderCallBack mCallback;
    private JsonObject jsonObject;
    private String jsonArrayName;
    private int reminderId;
    private Class<? extends BnObject> bnObjectClass;
    private Type bnArrayListType;
    private Gson mGson;


    public BnObjectBuilder(int reminderId, JsonObject jsonObject, Class<? extends BnObject> bnObjectClass, BnBuilderCallBack mCallback) {
        this.mCallback = mCallback;
        this.reminderId = reminderId;
        this.bnObjectClass = bnObjectClass;
        this.jsonObject = jsonObject;
        mGson = new Gson();

    }

    public BnObjectBuilder(int reminderId, JsonObject jsonObject, String jsonArrayName, Type bnArrayListType, BnBuilderCallBack mCallback) {
        this.mCallback = mCallback;
        this.jsonArrayName = jsonArrayName;
        this.reminderId = reminderId;
        this.bnArrayListType = bnArrayListType;
        this.jsonObject = jsonObject;
        mGson = new Gson();

    }


    @Override
    public void run() {

        BnObject result;
        ArrayList<BnObject> results;

        Log.d("BnBuilder","Building bnObject on thread " + Thread.currentThread().getName());


        if (jsonArrayName != null) {
            JsonArray jsonArray = jsonObject.getAsJsonArray(jsonArrayName);
            results = build(jsonArray, bnArrayListType);

            mCallback.onBnBuilderFinisched(results, reminderId);
        }

        result = build(jsonObject, bnObjectClass);


        mCallback.onBnBuilderFinisched(result, reminderId);

    }





    private  <T extends BnObject> T build(JsonObject jsonObject, Class<T> objectClass){
        return mGson.fromJson(jsonObject,objectClass);
    }


    private <T extends BnObject> ArrayList<T> build(JsonArray jsonArray, Type typeToken){
        return mGson.fromJson(jsonArray.toString(),typeToken);

    }

    public interface BnBuilderCallBack {

        void onBnBuilderFinisched(BnObject objectResult, int reminderId);
        void onBnBuilderFinisched(ArrayList<BnObject> objectResult, int reminderId);

    }


}
