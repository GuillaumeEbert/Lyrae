package shindra.lyrae.Url;

import android.os.Bundle;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by Guillaume on 27/08/2017.
 * Wrapper for android bundle. The keys name are limited by the tupeDef annotation.
 * The data put in it is needed for the construction of any object that implement the BnObject interface
 */

public final class UrlBundleData {

    private Bundle dataBundler;

    public UrlBundleData() {
        dataBundler = new Bundle();
    }


    public void putInt(@BundleIntKeys String key, int value) {
        dataBundler.putInt(key,value);
    }


    public void putString(@BundleStringKeys String key, String value){
        dataBundler.putString(key,value);
    }


    public int getInt(@BundleIntKeys String key){
      return  dataBundler.getInt(key);
    }

    public String getString(@BundleStringKeys String key){
        return dataBundler.getString(key);
    }


    public static final String CHARACTER_SERVER = "character_server";
    public static final String CHARACTER_NAME = "character_name";
    public static final String ITEM_ID = "item_id";
    public static final String ZONE_ID = "zone_id";
    public static final String ACCESS_TOKEN = "access_token";

    @StringDef({ITEM_ID, ZONE_ID})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BundleIntKeys {
    }



    @StringDef({CHARACTER_SERVER,CHARACTER_NAME,ACCESS_TOKEN })
    @Retention(RetentionPolicy.SOURCE)
    public @interface BundleStringKeys {
    }


}
