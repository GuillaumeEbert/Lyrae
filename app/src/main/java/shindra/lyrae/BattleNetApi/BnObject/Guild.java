package shindra.lyrae.BattleNetApi.BnObject;

/**
 * Created by Guillaume on 11/08/2017.
 */

public class Guild extends BnObject {

    private String name;
    private String realm;
    private String battlegroup;
    private int members;
    private int achievementPoints;
    //private Emblem emblem;


    public String getName() {
        return name;
    }

    public String getRealm() {
        return realm;
    }

    public String getBattlegroup() {
        return battlegroup;
    }

    public int getMembers() {
        return members;
    }

    public int getAchievementPoints() {
        return achievementPoints;
    }
}
