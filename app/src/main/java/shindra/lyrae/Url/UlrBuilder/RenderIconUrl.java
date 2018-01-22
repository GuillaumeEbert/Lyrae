package shindra.lyrae.Url.UlrBuilder;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Guillaume on 07/08/2017.
 *
 * Exemple of an render url for icons http://render-us.worldofwarcraft.com/icons/size/name.jpg
 *
 */

public class RenderIconUrl extends BnUrlBuilder {


    private int iconSize;
    private String iconName;

    public RenderIconUrl(int iconSize, String iconName) {
        super();
        this.iconSize = iconSize;
        this.iconName = iconName;
    }

    @Override
    protected String getEndpoint() {
        return Integer.toString(iconSize);
    }

    @Override
    protected String getQuery() {
        return iconName;
    }

    @Override
    protected String getFields() {
        return "";
    }


}
