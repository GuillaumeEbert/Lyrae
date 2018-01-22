package shindra.lyrae.BattleNetApi.BnObject;

import java.util.ArrayList;

import shindra.lyrae.BattleNetApi.BnObject.BnObject;

/**
 * Created by Guillaume on 27/08/2017.
 */

public class Zone extends BnObject {

    private int id;
    private String name;
    private String urlSlug;
    private String description;
    private Location location;
    private int expansionId;
    private String patch;
    private String numPlayers;
    private boolean isDungeon;
    private boolean isRaid;
    private int advisedMinLevel;
    private int advisedMaxLevel;
    private int advisedHeroicMinLevel;
    private int advisedHeroicMaxLevel;
    private ArrayList<String> availableModes;
    private int lfgNormalMinGearLevel;
    private int lfgHeroicMinGearLevel;
    private int floors;
    ArrayList<Boss> bosses;

    public Zone(){
        availableModes = new ArrayList<>();
    }



    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getExpansionId() {
        return expansionId;
    }

    public String getPatch() {
        return patch;
    }

    public String getNumPlayers() {
        return numPlayers;
    }

    public boolean isDungeon() {
        return isDungeon;
    }

    public boolean isRaid() {
        return isRaid;
    }

    public int getAdvisedMinLevel() {
        return advisedMinLevel;
    }

    public int getAdvisedMaxLevel() {
        return advisedMaxLevel;
    }

    public int getAdvisedHeroicMinLevel() {
        return advisedHeroicMinLevel;
    }

    public int getAdvisedHeroicMaxLevel() {
        return advisedHeroicMaxLevel;
    }

    public ArrayList<String> getAvailableModes() {
        return availableModes;
    }

    public int getLfgNormalMinGearLevel() {
        return lfgNormalMinGearLevel;
    }

    public int getLfgHeroicMinGearLevel() {
        return lfgHeroicMinGearLevel;
    }

    public int getFloors() {
        return floors;
    }

    public class Location {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


}
