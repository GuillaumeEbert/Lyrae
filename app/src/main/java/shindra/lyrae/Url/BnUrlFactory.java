package shindra.lyrae.Url;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.MalformedURLException;
import java.net.URL;

import shindra.lyrae.Url.UlrBuilder.IdUrl;

/**
 * Created by Guillaume on 26/08/2017.
 * Factory for the Battle Net api Urls.
 *
 */

public class BnUrlFactory {

    private static final String API_KEY = "&apikey=v9ged4jkvp97kfgujgsztnqvsts5ndac";
    private static String userLanguageString;
    private static String userZoneString;




   public URL getIdUrl(@IdUrlType int urlType, int bnId) throws MalformedURLException {
       String bnObjectUrlPart = null;
       switch (urlType){
           case ITEM_URL:
               bnObjectUrlPart = new IdUrl(IdUrl.ITEM_ENDPOINT, bnId).getBnUrlPart();
               break;

           case ZONE_URL:
               bnObjectUrlPart = new IdUrl(IdUrl.ZONE_ENDPOINT, bnId).getBnUrlPart();
               break;
       }

       return new URL(userZoneString + bnObjectUrlPart + userLanguageString + API_KEY);
   }


    public static void setUserLanguage(@UrlFactoryLanguageOptions int userLanguage){
        userLanguageString = getLanguageConstant(userLanguage);
        userZoneString = getZoneUrlPart(userLanguage);
    }


    /**
     * Get the url for the zone corresponding to the user language
     * @param userLanguage user language
     * @return zone url
     */
    private static String getZoneUrlPart(@UrlFactoryLanguageOptions int userLanguage){

        switch (userLanguage) {

           //todo add the rest of zones available thought the api
            case ENG_LANGUAGE:
            case FR_LANGUAGE:
                return "https://eu.api.battle.net/wow/";

            default:  return "https://eu.api.battle.net/wow/";
        }

    }


    /**
     * get the language constance corresponding the user choice
     * @param userLanguage user language
     * @return language constance
     */
    private static String getLanguageConstant(@UrlFactoryLanguageOptions int userLanguage){
        String langConstant = "locale=";

        switch (userLanguage) {

            //todo add the rest of languages available thought the api
            case ENG_LANGUAGE:
                 return langConstant + "en_GB";

            case FR_LANGUAGE:
                return langConstant + "fr_FR";

            default:
                return langConstant+ "en_GB";
        }
    }



    public static final int ITEM_URL = 1;
    public static final int ZONE_URL = 2;
    public static final int CHARACTER_URL = 3;
    public static final int USER_CHARACTER_URL = 4;


    @IntDef({ITEM_URL, ZONE_URL})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IdUrlType {
    }


    public static final int ENG_LANGUAGE = 100;
    public static final int  FR_LANGUAGE = 110;

    @IntDef({ENG_LANGUAGE, FR_LANGUAGE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UrlFactoryLanguageOptions {
    }



}




