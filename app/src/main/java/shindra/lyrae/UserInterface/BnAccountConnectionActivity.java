package shindra.lyrae.UserInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import shindra.lyrae.BattleNetApi.AccountHandler.BnAccountHandler;
import shindra.lyrae.R;

public class BnAccountConnectionActivity extends AppCompatActivity implements BnAccountHandler.BnAccountHandlerCallback, BnSelectZoneDialog.BnSelectZoneDialogListener{


    private BnAccountHandler bnAccountHandler;
    private SharedPreferences sharedPreferences;
    private WebView bnAccountWebView;
    private static final String SP_KEY_BN_ACCOUNT_ZONE = "bnAccountZone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bn_account_connection);
        sharedPreferences = getSharedPreferences("BnAccount",MODE_PRIVATE);
        bnAccountHandler = new BnAccountHandler(sharedPreferences,this);
        bnAccountWebView =  findViewById(R.id.bnAccountWebView);
        bnAccountWebView.getSettings().setJavaScriptEnabled(true);
        bnAccountWebView.setVisibility(View.INVISIBLE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        @BnAccountHandler.BnAccountZone int accountZone;

        accountZone = sharedPreferences.getInt(SP_KEY_BN_ACCOUNT_ZONE,0);

        if(accountZone == 0){
            confirmFireMissiles();
        }else {
            startBnConnexion(accountZone);
        }

    }

    public void confirmFireMissiles() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        BnSelectZoneDialog newFragment = new BnSelectZoneDialog();
        newFragment.show(fragmentManager,"test");
    }



    private void startBnConnexion(@BnAccountHandler.BnAccountZone int accountZone){



        bnAccountWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bnAccountHandler.processToken(url);
                bnAccountWebView.setVisibility(View.VISIBLE);
            }
        });

        final String authorizationUrl = bnAccountHandler.getAuthorizationUrl(accountZone);
        bnAccountWebView.loadUrl(authorizationUrl);

    }

    @Override
    public void onBnAccountConnected(BnOAuth2Params bnOAuth2Params) {

    }

    @Override
    public void onBnAccountError() {

    }


    @Override
    public void zoneSelected(int accountZoneSelected) {
        startBnConnexion(accountZoneSelected);
    }
}
