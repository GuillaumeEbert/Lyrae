package shindra.lyrae.SaverReader.Saver;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.File;
import java.io.IOException;

/**
 * Created by Guillaume on 08/10/2017.
 */

public abstract class Saver implements Runnable, Handler.Callback{
    private File folderToSave;
    private Handler uiHandler;
    private SaverCallback saverCallback;
    private String fileName;


    public Saver(File folderToSave,String fileName ,SaverCallback callback) {
        this.folderToSave = folderToSave;
        uiHandler = new Handler(Looper.getMainLooper(), this);
        saverCallback = callback;
        this.fileName = fileName;
    }

    protected abstract void onSave(File file) throws IOException;


    @Override
    public void run() {

        File file = new File(folderToSave,fileName);

        try{
            onSave(file);
            uiHandler.sendEmptyMessage(0);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean handleMessage(Message message) {
        saverCallback.onSaverFinisched();
        return false;
    }


    public interface SaverCallback{
        void onSaverFinisched();
    }
}
