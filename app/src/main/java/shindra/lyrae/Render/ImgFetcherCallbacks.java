package shindra.lyrae.Render;

import android.graphics.Bitmap;

/**
 * Created by Guillaume on 08/08/2017.
 */

public interface ImgFetcherCallbacks {

    void onImgFetched(Bitmap img, int size, String name);

}
