package shindra.lyrae.Url.UlrBuilder;

/**
 * Created by Guillaume on 11/08/2017.
 *
 * https://eu.api.battle.net/wow/character/Uldaman/Shîndrä?fields=items%2Cguild%2Cstats&locale=en_GB
 */

public class CharacterUrl extends BnUrlBuilder {


    private static final String CHARACTER_FIELD_ITEMS ="items";
    private static final String CHARACTER_FIELD_GUILD ="guild";
    private static final String CHARACTER_FIELD_STATS = "stats";
    private static final String[] CHARACTER_FIELDS = {CHARACTER_FIELD_ITEMS,CHARACTER_FIELD_GUILD,CHARACTER_FIELD_STATS};

    private String serverName;
    private String charName;
    private String[] arrFields;

    public CharacterUrl(String serverName, String charName, String ...fields) {
        super();
        this.serverName = serverName;
        this.charName = charName;
        this.arrFields = fields;
    }

    public CharacterUrl(String serverName, String charName) {
        this.serverName = serverName;
        this.charName = charName;
        this.arrFields = CHARACTER_FIELDS;
    }

    @Override
    protected String getEndpoint() {
        return "character/"+ serverName;
    }

    @Override
    protected String getQuery() {



        return charName;
    }

    @Override
    protected String getFields() {

        if(arrFields == null) return "";

        String fields="fields=";
        int i;

        for(i = 0 ;i<arrFields.length -1 ;i++){
            fields += arrFields[i] + "%2C";
        }

        fields += arrFields[i] +"&";
        return fields;
    }
}
