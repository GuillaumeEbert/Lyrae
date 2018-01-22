package shindra.lyrae.Url.UlrBuilder;

/**
 * Created by Guillaume on 07/08/2017.
 */

public class IdUrl extends BnUrlBuilder {

    public static final String ZONE_ENDPOINT = "zone";
    public static final String ITEM_ENDPOINT = "item";

    private String endpoints;
    private int queryId;

    public IdUrl(String endpoints, int queryId) {
        super();
        this.endpoints = endpoints;
        this.queryId = queryId;
    }

    @Override
    protected String getEndpoint() {
        return endpoints;
    }

    @Override
    protected String getQuery() {
        return Integer.toString(queryId);
    }

    @Override
    protected String getFields() {
        return "";
    }
}
