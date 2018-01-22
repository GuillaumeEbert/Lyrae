package shindra.lyrae.JsonFetcher;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;

import com.google.gson.JsonObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Guillaume on 03/10/2017.
 */

public class JsonFetcherPool implements RejectedExecutionHandler, JsonFetcher.JsonFetcherCallBack, Handler.Callback {
    private ThreadPoolExecutor poolExecutor;
    private static final int QUEUE_SIZE = 2000;
    private Handler mHandler;
    private ArrayList<JsonObject> jsonObjects;
    private JsonFetcherPoolCallback callback;
    private final Object syncKey;


    public JsonFetcherPool(JsonFetcherPoolCallback callback){
        poolExecutor = new ThreadPoolExecutor(10, 20, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_SIZE), this);
        mHandler = new Handler(Looper.getMainLooper(), this);
        this.callback = callback;
        jsonObjects = new ArrayList<>();
        syncKey = new Object();
    }


    public void submit(URL jsonUrl){
        JsonFetcher jFetcher = new JsonFetcher(jsonUrl,this);
        poolExecutor.submit(jFetcher);
    }


    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

    }

    @Override
    public synchronized void onJsonFetched(JsonObject jsonObject) {
        int jsonObjectIndex;

        synchronized (syncKey){
            jsonObjects.add(jsonObject);
            jsonObjectIndex = jsonObjects.indexOf(jsonObject);
        }

        Message uiMessage = mHandler.obtainMessage();
        uiMessage.what = jsonObjectIndex;

        mHandler.sendMessage(uiMessage);
    }

    @Override
    public boolean handleMessage(Message message) {
        int indexArray = message.what;
        JsonObject jsonObject;

        synchronized (syncKey){
            jsonObject = jsonObjects.get(indexArray);
        }

        callback.jsonFetched(jsonObject);

        return false;
    }

    public interface JsonFetcherPoolCallback{
        void jsonFetched(JsonObject jsonObject);
    }
}
