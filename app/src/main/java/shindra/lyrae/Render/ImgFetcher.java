package shindra.lyrae.Render;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import shindra.lyrae.JsonFetcher.JsonFetcherException;


/**
 * Created by Guillaume on 07/08/2017.
 * Mother class for all the img Fetcher
 */

public abstract class ImgFetcher implements Runnable {

    private File targetFolderPath;
    private int fetchMode;
    private ImgFetcherCallbacks myResultHandler;


    public static final int FETCH_FROM_RENDER = 1;
    public static final int FETCH_FROM_APP_FOLDER = 2;


    public ImgFetcher(File targetFolderPath, int fetchMode, ImgFetcherCallbacks myResultHandler) {
        this.targetFolderPath = targetFolderPath;
        this.fetchMode = fetchMode;
        this.myResultHandler = myResultHandler;
    }

    protected abstract URL getRenderUrl() throws MalformedURLException;

    protected abstract File getImgFile(File targetFolderPath);

    protected abstract void handleResultCallBack(ImgFetcherCallbacks myResultHandler, Bitmap imgProduce);

    @Override
    public void run() {
        Bitmap img;
        URL imgUrl;
        File imgFile = getImgFile(targetFolderPath);

        try {

            switch (fetchMode) {

                case FETCH_FROM_RENDER:

                    if (imgFile.exists()) {
                        Log.d("ImgFetcher", "An image already exist at the  file path :" + imgFile.toString() + ". Fetching and saving of the img is aborted");
                        img = null;
                    } else {

                        imgUrl = getRenderUrl();
                        img = getImg(imgUrl);
                        saveImg(imgFile, img);
                        Log.d("ImgFetcher", "Img fetch at : " + imgUrl.toString() + " is saved at : " + imgFile.getPath() + ". Running on thread :" + Thread.currentThread().getName());
                    }

                    handleResultCallBack(myResultHandler, img);
                    break;

                case FETCH_FROM_APP_FOLDER:

                    //From render if no img file found
                    if (!imgFile.exists()) {
                        imgUrl = getRenderUrl();
                        Log.d("ImgFetcher callback", "An image file not found. Fetching from render at url : " + imgUrl.toString());

                        img = getImg(imgUrl);
                        saveImg(imgFile, img);
                        handleResultCallBack(myResultHandler, img);
                    } else {//from saved file
                        img = getImg(imgFile);
                        Log.d("ImgFetcher callback", "img found at path :" + imgFile.getPath());
                        handleResultCallBack(myResultHandler,img);
                    }

                    break;

                default:


            }

        } catch (JsonFetcherException | IOException e) {
            e.printStackTrace();
        }

    }



    protected Bitmap getImg(URL imgUrl) throws JsonFetcherException {

        int responseCode;

        try {

            HttpURLConnection request = (HttpURLConnection) imgUrl.openConnection();
            request.connect();

            responseCode = request.getResponseCode();

            switch (responseCode) {
                case HttpURLConnection.HTTP_OK: // 200

                    InputStream inputStream = request.getInputStream();
                    return BitmapFactory.decodeStream(inputStream);

                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT://504
                    return getImg(imgUrl);

                default:
                    Throwable t = new Throwable("Url : " + imgUrl.toString() + "ended with code :" + Integer.toString(responseCode));
                    throw new JsonFetcherException("ImageFetcher error :", t);
            }

        } catch (IOException e) {
            throw new JsonFetcherException("ImageFetcher error :", e);
        }

    }

    protected Bitmap getImg(File imgFile) {
        return BitmapFactory.decodeFile(imgFile.getPath());
    }


    protected void saveImg(File imgFile, Bitmap imgToSave) throws IOException {
        FileOutputStream outStream;

        outStream = new FileOutputStream(imgFile);
        imgToSave.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.close();

    }


}
