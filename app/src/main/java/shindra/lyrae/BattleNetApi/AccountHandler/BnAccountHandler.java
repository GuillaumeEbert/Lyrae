package shindra.lyrae.BattleNetApi.AccountHandler;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLDecoder;

/**
 * Created by Guillaume on 02/09/2017.
 * Handle the connection to the user Battle net account
 *
 * https://eu.api.battle.net/wow/user/characters?access_token=exh9cquucybdxtrhgh3v3965
 */

public class BnAccountHandler {


    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;
    private BnAccountHandlerCallback mCallback;
    private SharedPreferences sharedPreferences;

    public BnAccountHandler(SharedPreferences sharedPreferences,BnAccountHandlerCallback callback ){
        this.sharedPreferences = sharedPreferences;
        mCallback = callback;

    }


    public String getAuthorizationUrl(@BnAccountZone int bnAccountZone){
        createBnOAuth2Params(bnAccountZone);

        final String authorizationUrl = bnOAuth2Helper.getAuthorizationUrl();
        Log.i(BnConstants.TAG, "Using authorizationUrl = " + authorizationUrl);

        return authorizationUrl;

    }


    public void processToken(String url){
        new ProcessToken(url).execute();
    }

    private void createBnOAuth2Params(@BnAccountZone int bnAccountZone){

        switch(bnAccountZone){
            case EU_ZONE :
                bnOAuth2Params = new BnOAuth2Params("v9ged4jkvp97kfgujgsztnqvsts5ndac", "Nw24jbeqxDWrGSNSTKtn2Zt8Pk85EcT5", BnConstants.ZONE_EUROPE, "https://localhost", "Roster Management",  BnConstants.SCOPE_WOW);
                break;
            case US_ZONE :
                bnOAuth2Params = new BnOAuth2Params("v9ged4jkvp97kfgujgsztnqvsts5ndac", "Nw24jbeqxDWrGSNSTKtn2Zt8Pk85EcT5", BnConstants.ZONE_UNITED_STATES, "https://localhost", "Roster Management",  BnConstants.SCOPE_WOW);
                break;
        }

        bnOAuth2Helper = new BnOAuth2Helper(sharedPreferences,bnOAuth2Params);
    }




    private class ProcessToken extends AsyncTask<Uri, Void, Void> {

        String url;
        private boolean hasError = false;


        public ProcessToken(String url) {
            this.url = url;
        }

        @Override
        protected Void doInBackground(Uri...params) {
            if (url.startsWith(bnOAuth2Params.getRederictUri())) {
                Log.i(BnConstants.TAG, "Redirect URL found: ".concat(url));
                try {
                    if (url.indexOf("code=") != -1) {
                        String authorizationCode = extractCodeFromUrl(url);

                        Log.i(BnConstants.TAG, "Found code = ".concat(authorizationCode));

                        bnOAuth2Helper.retrieveAndStoreAccessToken(authorizationCode);


                    } else if (url.indexOf("error=") != -1) {

                    }

                } catch (Exception e) {
                    Log.e(BnConstants.TAG, "Error processing token", e);
                    hasError = true;
                }

            } else {
                Log.i(BnConstants.TAG, "Not doing anything for url ".concat(url));
                hasError = true;
            }
            return null;
        }

        private String extractCodeFromUrl(String url) throws Exception {
            final String encodedCode = url.substring(bnOAuth2Params.getRederictUri().length() + 7, url.length());
            return URLDecoder.decode(encodedCode, "UTF-8");
        }


        @Override
        protected void onPostExecute(Void result) {

            if(hasError) mCallback.onBnAccountError();
            else
                mCallback.onBnAccountConnected(bnOAuth2Params);

        }
    }


    public interface BnAccountHandlerCallback {
        void onBnAccountConnected(BnOAuth2Params bnOAuth2Params);
        void onBnAccountError();

    }

    public static final int NO_ZONE = 0;
    public static final int EU_ZONE = 1;
    public static final int US_ZONE = 2;


    @IntDef({NO_ZONE, EU_ZONE, US_ZONE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BnAccountZone {}






}
