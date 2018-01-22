package shindra.lyrae.Url.UlrBuilder;

import java.net.MalformedURLException;

import shindra.lyrae.Url.UrlBundleData;

/**
 * Created by Guillaume on 02/09/2017.
 *
 * https://eu.api.battle.net/wow/user/characters?access_token=zrcpjearwnx8mzjd4rgqpvvm
 */

public class UserCharactersUrl extends BnUrlBuilder {

    private String accessToken;


    public UserCharactersUrl(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    protected String getEndpoint() {
        return "user";
    }

    @Override
    protected String getQuery() {
        return "characters?access_token=" + accessToken;
    }

    @Override
    protected String getFields() {
        return "";
    }


    @Override
    public String getBnUrlPart() throws MalformedURLException {
        String sUrl =  getEndpoint() + "/" + getQuery();
        return sUrl;
    }
}
