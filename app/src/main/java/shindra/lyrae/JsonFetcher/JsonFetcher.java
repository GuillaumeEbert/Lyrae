package shindra.lyrae.JsonFetcher;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Guillaume on 06/08/2017.
 */

public class JsonFetcher implements Runnable {

    private static  int NB_MAX_REPEAT_ON_GATEWAY_TIMEOUT = 10;
    private static final String QPS_ERROR_MESSAGE_FROM_REQUEST = "ERR_403_DEVELOPER_OVER_QPS";
    private static final String MASHERY_ERROR_CODE_FIELD = "X-Mashery-Error-Code";
    private URL url;
    private JsonFetcherCallBack mCallBack;



    public JsonFetcher(URL url, JsonFetcherCallBack callBack) {
        this.url = url;
        this.mCallBack = callBack;

    }

    @Override
    public void run() {
        JsonObject jsonObject;

        Log.i("JsonFetcher", "Loading Json from url : " + url.toString());

        try {
            jsonObject =  fetchJsonFromUrl();

            mCallBack.onJsonFetched(jsonObject);

        } catch (JsonFetcherException e) {
          e.printStackTrace();
        }

    }

    public JsonObject fetchJsonFromUrl() throws JsonFetcherException {
        int responseStatus;
        Throwable t;

        try {
            HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
            request.connect();
            responseStatus = request.getResponseCode();

            switch (responseStatus) {

                case HttpsURLConnection.HTTP_OK: //200 Ok

                    //Convert the input stream to a json element
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

                    request.disconnect();

                    return root.getAsJsonObject();

                case HttpURLConnection.HTTP_FORBIDDEN: //403 forbiden

                    String s = request.getHeaderField(MASHERY_ERROR_CODE_FIELD);

                    t = new Throwable("Connection error on the url : " + url.toString());

                    if (s == null) throw new JsonFetcherException("Error in BNApiJsonFetcher : ", t);

                    if (!s.equals(QPS_ERROR_MESSAGE_FROM_REQUEST))
                        throw new JsonFetcherException("Error in BNApiJsonFetcher : ", t);

                    //QPS overflow, wait a second and retry
                    Log.i("JsonFetcher", "QPS_OVERFLOW on : " + url.toString() + " ==> retry in 1 sec");
                    Thread.sleep(1000);
                    return fetchJsonFromUrl();

                //todo QPH overflow

                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT: //503 Timeout from bli²

                    Log.d("JsonFetcher", "HTTP_GATEWAY_TIMEOUT on : " + url.toString() + "==> retry");

                    //redo the connection
                    if (-- NB_MAX_REPEAT_ON_GATEWAY_TIMEOUT > 0)
                        return fetchJsonFromUrl();

                    t = new Throwable("Failed to fetch data from : " + url.toString() + " after " + Integer.toString(NB_MAX_REPEAT_ON_GATEWAY_TIMEOUT) + "attempts. ");
                    throw new JsonFetcherException("Error in BNApiJsonFetcher : ", t);

                default:
                    t = new Throwable("Reponse Code on : " + url.toString() + "  = " + Integer.toString(responseStatus));
                    throw new JsonFetcherException("Error in BNApiJsonFetcher : ", t);
            }

        } catch (Exception e) {
            throw new JsonFetcherException("Error in BNApiJsonFetcher :", e);
        }

    }


    public interface JsonFetcherCallBack{
        void onJsonFetched(JsonObject jsonObject);
    }


}




//X-Mashery-Error-Code   ====> QPS ERROR




