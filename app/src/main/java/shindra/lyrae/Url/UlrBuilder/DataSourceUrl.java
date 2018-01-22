package shindra.lyrae.Url.UlrBuilder;

/**
 * Created by Guillaume on 10/08/2017.
 */

public class DataSourceUrl extends BnUrlBuilder {

    public static final String EP_CHARACTER_DATA_SOURCE = "data/character";

    public static final String EP_DATA_SOURCE = "data";

    public static final String QUERY_CHARACTER_EP_CLASSES = "classes";
    public static final String QUERY_DATA_EP_TALENT = "talents";

    public static final int FROM_US_API = 0;
    public static final int FROM_ZONE_API = 1;



    private String dataEndPoints;
    private String query;



    public DataSourceUrl(String dataEndPoints, String query) {
        super();
        this.dataEndPoints = dataEndPoints;
        this.query = query;


    }

    @Override
    protected String getEndpoint() {
        return dataEndPoints;
    }

    @Override
    protected String getQuery() {
        return query;
    }

    @Override
    protected String getFields() {
        return "";
    }


}
