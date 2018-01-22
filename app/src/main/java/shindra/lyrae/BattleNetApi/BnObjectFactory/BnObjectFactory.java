package shindra.lyrae.BattleNetApi.BnObjectFactory;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import shindra.lyrae.BattleNetApi.BnObject.BnObject;
import shindra.lyrae.BattleNetApi.BnObject.Character;
import shindra.lyrae.BattleNetApi.BnObject.Guild;
import shindra.lyrae.BattleNetApi.BnObject.Item;
import shindra.lyrae.BattleNetApi.BnObject.Zone;
import shindra.lyrae.BattleNetApi.BnObjectFactory.Builder.BnObjectBuilder;


/**
 * Created by Guillaume on 26/08/2017.
 */

public final class BnObjectFactory   implements BnObjectBuilder.BnBuilderCallBack , Handler.Callback,RejectedExecutionHandler {

    private BnFactoryCallBack bnFactoryCallback;
    private ThreadPoolExecutor poolExecutor;
    private static final int QUEUE_SIZE = 2000;
    private Handler handler;
    private ArrayList<BnObjectFactoryReminder> lReminder;
    private final Object syncKeys;


    public BnObjectFactory(BnFactoryCallBack bnFactoryCallback) {
        this.bnFactoryCallback = bnFactoryCallback;
        poolExecutor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_SIZE), this);
        handler = new Handler(Looper.getMainLooper(), this);
        syncKeys = new Object();
        lReminder = new ArrayList<>();

    }


    public void submit(@BnObject.BnObjectType int bnObjectToBuild, JsonObject jsonObject) throws MalformedURLException {
        BnObjectFactoryReminder reminder = new BnObjectFactoryReminder(bnObjectToBuild);
        lReminder.add(reminder);

        BnObjectBuilder bnBuilder = getBuilderWrapper(bnObjectToBuild, reminder.getId(), jsonObject);
        if (bnBuilder != null) poolExecutor.submit(bnBuilder);

    }


    public <T extends BnObject> T getResult(int requestId, Class<T> resultObject) {

        BnObjectFactoryReminder bnObjectFactoryReminder = getReminder(requestId);
        BnObject requestBnObject;

        requestBnObject = bnObjectFactoryReminder.getResultObject();
        removeFactoryReminderFromList(bnObjectFactoryReminder);

        return resultObject.cast(requestBnObject);

    }


    public <T extends BnObject> ArrayList<T> getArrayResult(int requestId, Class<T> type) {
        BnObjectFactoryReminder bnObjectFactoryReminder = getReminder(requestId);
        ArrayList<BnObject> bnResults = bnObjectFactoryReminder.getListBnObject();
        ArrayList<T> results = new ArrayList<>();
        T result;

        for (BnObject bnObject : bnResults) {
            result = type.cast(bnObject);
            results.add(result);
        }

        removeFactoryReminderFromList(bnObjectFactoryReminder);

        return results;


    }


    private BnObjectBuilder getBuilderWrapper(@BnObject.BnObjectType int bnObjectToBuild, int reminderID, JsonObject jsonObject) {

        switch (bnObjectToBuild) {

            case BnObject.BOSS:
                return null; //todo;

            case BnObject.CHARACTER_CLASS:
                return null; //todo;

            case BnObject.CHARACTER:
                return new BnObjectBuilder(reminderID, jsonObject, Character.class, this);

            case BnObject.GUILD:
                return new BnObjectBuilder(reminderID, jsonObject, Guild.class, this);

            case BnObject.ITEM:
                return new BnObjectBuilder(reminderID, jsonObject, Item.class, this);

            case BnObject.ZONE:
                return new BnObjectBuilder(reminderID, jsonObject, Zone.class, this);

            default:
                return null;

        }

    }


    @Override
    public synchronized void onBnBuilderFinisched(BnObject objectResult, int reminderId) {
        BnObjectFactoryReminder bnObjectFactoryReminder = getReminder(reminderId);
        bnObjectFactoryReminder.setResultObject(objectResult);
        handler.sendEmptyMessage(reminderId);

    }


    @Override
    public synchronized void onBnBuilderFinisched(ArrayList<BnObject> objectResult, int reminderId) {
        BnObjectFactoryReminder bnObjectFactoryReminder = getReminder(reminderId);
        bnObjectFactoryReminder.resultObject(objectResult);

        handler.sendEmptyMessage(reminderId);

    }


    private synchronized BnObjectFactoryReminder getReminder(int factoryRequestId) {
        for (BnObjectFactoryReminder reminder : lReminder) {
            if (reminder.getId() == factoryRequestId) return reminder;
        }

        throw new RuntimeException("BnObjectFactory : Request ID :" + Integer.toString(factoryRequestId) + " not found");
    }


    private void removeFactoryReminderFromList(BnObjectFactoryReminder reminderToRemove) {
        synchronized (syncKeys) {
            lReminder.remove(reminderToRemove);
        }
    }



    @Override
    public boolean handleMessage(Message message) {
        int reminderId = message.what;
        BnObjectFactoryReminder reminderTrig = getReminder(reminderId);
        int bnObjectTypeBuild = reminderTrig.getBnObjectToBuild();

        bnFactoryCallback.onFactoryFinisched(bnObjectTypeBuild, reminderId);
        return true;
    }

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {


    }

    public interface BnFactoryCallBack {
        void onFactoryFinisched(@BnObject.BnObjectType int bnObjectTypeBuild, int reminderId);
    }



}
