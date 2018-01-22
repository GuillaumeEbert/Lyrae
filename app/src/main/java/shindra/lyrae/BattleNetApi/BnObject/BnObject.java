package shindra.lyrae.BattleNetApi.BnObject;

import android.support.annotation.IntDef;

import com.google.gson.JsonObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Guillaume on 26/08/2017.
 */

public abstract class BnObject {



    public static final int BOSS = 0;
    public static final int CHARACTER = 1;
    public static final int CHARACTER_CLASS = 2;
    public static final int GUILD = 3;
    public static final int ITEM = 4;
    public static final int ZONE = 5;

    @IntDef({BOSS, CHARACTER, CHARACTER_CLASS, GUILD, ITEM,ZONE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BnObjectType {
    }




}
