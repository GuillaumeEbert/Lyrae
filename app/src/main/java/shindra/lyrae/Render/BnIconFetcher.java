package shindra.lyrae.Render;

/**
 * Created by Guillaume on 07/08/2017.
 */

public class BnIconFetcher /*extends ImgFetcher implements Runnable*/{

   /* public static final int ICON_SIZE_18 = 18;
    public static final int ICON_SIZE_36 = 36;
    public static final int ICON_SIZE_56 = 56;

    public static final int[] ARR_ICON_SIZE ={ICON_SIZE_18,ICON_SIZE_36,ICON_SIZE_56};

    private String imgName;
    private int imgSize;

    public BnIconFetcher(File targetFolderPath, int fetchMode, ImgFetcherCallbacks myResultHandler, String imgName, int imgSize) {
        super(targetFolderPath, fetchMode, myResultHandler);
        this.imgName = imgName;
        this.imgSize = imgSize;
    }




    @Override
    protected URL getRenderUrl() throws MalformedURLException {
        return new RenderIconUrl(imgSize,imgName).getUrl();
    }

    @Override
    protected File getImgFile(File iconFolderPath) {
        String iconFileName = imgName +"_" + Integer.toString(imgSize) +".jpg";
        return new File(iconFolderPath,iconFileName);
    }

    @Override
    protected void handleResultCallBack(ImgFetcherCallbacks myResultHandler, Bitmap imgProduce) {
        myResultHandler.onImgFetched(imgProduce,imgSize,imgName);
    }*/


}
