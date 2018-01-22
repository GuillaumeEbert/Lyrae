package shindra.lyrae.BattleNetApi.BnObject;


import java.util.ArrayList;


/**
 * Created by Guillaume on 12/07/2017.
 *
 */

public class Boss  {

    private int id;

    private String urlSlug;
    private String description;
    private int zoneId;
    private boolean availableInNormalMode;
    private boolean availableInHeroicMode;
    private int health;
    private int heroicHealth;
    private int level;
    private int heroicLevel;
    private int journalId;
    private ArrayList<Npc> npcs;
    private ArrayList<Integer> lootTable;


    public Boss(){
        lootTable = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public boolean isAvailableInNormalMode() {
        return availableInNormalMode;
    }

    public void setAvailableInNormalMode(boolean availableInNormalMode) {
        this.availableInNormalMode = availableInNormalMode;
    }

    public boolean isAvailableInHeroicMode() {
        return availableInHeroicMode;
    }

    public void setAvailableInHeroicMode(boolean availableInHeroicMode) {
        this.availableInHeroicMode = availableInHeroicMode;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHeroicHealth() {
        return heroicHealth;
    }

    public void setHeroicHealth(int heroicHealth) {
        this.heroicHealth = heroicHealth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHeroicLevel() {
        return heroicLevel;
    }

    public void setHeroicLevel(int heroicLevel) {
        this.heroicLevel = heroicLevel;
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public ArrayList<Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(ArrayList<Npc> npcs) {
        this.npcs = npcs;
    }

    public ArrayList<Integer> getLootTable() {
        return lootTable;
    }

    public void setLoot(int lootId) {
        this.lootTable.add(lootId);
    }

    public void setLootTable(ArrayList<Integer> lootTable) {
        this.lootTable = lootTable;
    }

    public class Npc {

        private int id;
        private String name;
        private String urlSlug;
        private int creatureDisplayId;

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

        public String getUrlSlug() {
            return urlSlug;
        }

        public void setUrlSlug(String urlSlug) {
            this.urlSlug = urlSlug;
        }

        public int getCreatureDisplayId() {
            return creatureDisplayId;
        }

        public void setCreatureDisplayId(int creatureDisplayId) {
            this.creatureDisplayId = creatureDisplayId;
        }

    }

}
