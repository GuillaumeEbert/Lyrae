package shindra.lyrae.SaverReader;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import shindra.lyrae.SaverReader.Saver.Saver;

/**
 * Created by Guillaume on 04/09/2017.
 */

public class SaverReaderPool implements RejectedExecutionHandler {
    private ThreadPoolExecutor saverReaderPoolExecutor;
    private static final int QUEUE_SIZE = 500;


    public SaverReaderPool(){
        saverReaderPoolExecutor = new ThreadPoolExecutor(4,8,10, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(QUEUE_SIZE),this);
    }


    public void submitSaver(Saver saver){
        saverReaderPoolExecutor.submit(saver);
    }


    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        //todo
    }
}
