package shindra.lyrae.Url.UlrBuilder;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Guillaume on 07/08/2017.
 * Bn API URL : "startUrl/endpoint/query?language&API_KEY" : ex "https://us.api.battle.net/wow/item/18803?locale=en_US&apikey=xxxxxxxxxx"
 * Render URL : "startUrl/endpoint/query  ex: http://render-us.worldofwarcraft.com/character/lightbringer/110/115539310-avatar.jpg
 *
 */

public abstract class BnUrlBuilder {



    protected BnUrlBuilder() {
    }

    public String getBnUrlPart() throws MalformedURLException {

        String sUrl =  getEndpoint() + "/" + getQuery() +  "?" + getFields();
        return sUrl;

    }

    protected abstract String getEndpoint();
    protected abstract String getQuery();
    protected abstract String getFields();



}
