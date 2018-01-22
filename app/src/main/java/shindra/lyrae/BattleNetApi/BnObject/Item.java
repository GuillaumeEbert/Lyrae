package shindra.lyrae.BattleNetApi.BnObject;

import java.util.ArrayList;

import shindra.lyrae.BattleNetApi.BnObject.BnObject;


/**
 * Created by Guillaume on 10/07/2017.
 */

public class Item extends BnObject {

    private Integer id;
    private String description;
    private String name;
    private String icon;
    private Integer itemBind;
    private ArrayList<BonusStat> bonusStats = null;
    //private ArrayList<ItemSpell> itemSpells = null;
    private Integer itemClass;
    private Integer itemSubClass;
    private WeaponInfo weaponInfo;
    private Integer inventoryType;
    private Integer itemLevel;
    private Integer maxDurability;
    private Integer quality;
    private Integer baseArmor;
    private Integer armor;
    private Integer displayInfoId;
    private String nameDescription;
    private String nameDescriptionColor;
    private Boolean upgradable;
    private ArrayList<Integer> bonusLists = null;
    private ArrayList<String> availableContexts = null;
    private BonusSummary bonusSummary;
    private Integer artifactId;
    private int lootById;
    private int artifactAppearanceId;
    private ArrayList<ArtifactTrait> artifactTraits = null;
    private ArrayList<Relic> relics = null;
    private TooltipParams tooltipParams;
    private String context;
    private String emplacementOnStuff;

    /*protected Item(Integer id, String description, String name, String icon, Integer itemBind, ArrayList<BonusStat> bonusStats, ArrayList<Object> itemSpells, Integer itemClass, Integer itemSubClass,
                   WeaponInfo weaponInfo, Integer inventoryType, Integer itemLevel, Integer maxDurability, Integer quality, Integer baseArmor, Integer armor, Integer displayInfoId, String nameDescription,
                   String nameDescriptionColor, Boolean upgradable, ArrayList<Object> bonusLists, ArrayList<String> availableContexts, BonusSummary bonusSummary, Integer artifactId, int lootById) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.icon = icon;
        this.itemBind = itemBind;
        this.bonusStats = bonusStats;
        this.itemSpells = itemSpells;
        this.itemClass = itemClass;
        this.itemSubClass = itemSubClass;
        this.weaponInfo = weaponInfo;
        this.inventoryType = inventoryType;
        this.itemLevel = itemLevel;
        this.maxDurability = maxDurability;
        this.quality = quality;
        this.baseArmor = baseArmor;
        this.armor = armor;
        this.displayInfoId = displayInfoId;
        this.nameDescription = nameDescription;
        this.nameDescriptionColor = nameDescriptionColor;
        this.upgradable = upgradable;
        this.bonusLists = bonusLists;
        this.availableContexts = availableContexts;
        this.bonusSummary = bonusSummary;
        this.artifactId = artifactId;
        this.lootById = lootById;
    }*/

    public int getLootById() {
        return lootById;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getItemBind() {
        return itemBind;
    }

    public ArrayList<BonusStat> getBonusStats() {
        return bonusStats;
    }

    /*public ArrayList<ItemSpell> getItemSpells() {
        return itemSpells;
    }*/

    public Integer getItemClass() {
        return itemClass;
    }

    public Integer getItemSubClass() {
        return itemSubClass;
    }

    public WeaponInfo getWeaponInfo() {
        return weaponInfo;
    }

    public Integer getInventoryType() {
        return inventoryType;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public Integer getMaxDurability() {
        return maxDurability;
    }

    public Integer getQuality() {
        return quality;
    }

    public Integer getBaseArmor() {
        return baseArmor;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getDisplayInfoId() {
        return displayInfoId;
    }

    public String getNameDescription() {
        return nameDescription;
    }

    public String getNameDescriptionColor() {
        return nameDescriptionColor;
    }

    public Boolean getUpgradable() {
        return upgradable;
    }

   /* public ArrayList<BonusList> getBonusLists() {
        return bonusLists;
    }*/

    public ArrayList<String> getAvailableContexts() {
        return availableContexts;
    }

    public void setAvailableContexts(ArrayList<String> availableContexts) {
        this.availableContexts = availableContexts;
    }

    public BonusSummary getBonusSummary() {
        return bonusSummary;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public class BonusStat {

        private Integer stat;
        private Integer amount;

        public Integer getStat() {
            return stat;
        }

        public void setStat(Integer stat) {
            this.stat = stat;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }


    public class BonusSummary {

        private ArrayList<Object> defaultBonusArrayLists = null;
        private ArrayList<Object> chanceBonusArrayLists = null;
        private ArrayList<Object> bonusChances = null;

        public ArrayList<Object> getDefaultBonusArrayLists() {
            return defaultBonusArrayLists;
        }

        public void setDefaultBonusArrayLists(ArrayList<Object> defaultBonusArrayLists) {
            this.defaultBonusArrayLists = defaultBonusArrayLists;
        }

        public ArrayList<Object> getChanceBonusArrayLists() {
            return chanceBonusArrayLists;
        }

        public void setChanceBonusArrayLists(ArrayList<Object> chanceBonusArrayLists) {
            this.chanceBonusArrayLists = chanceBonusArrayLists;
        }

        public ArrayList<Object> getBonusChances() {
            return bonusChances;
        }

        public void setBonusChances(ArrayList<Object> bonusChances) {
            this.bonusChances = bonusChances;
        }
    }


    public class Damage {

        private Integer min;
        private Integer max;
        private Double exactMin;
        private Double exactMax;

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        public Double getExactMin() {
            return exactMin;
        }

        public void setExactMin(Double exactMin) {
            this.exactMin = exactMin;
        }

        public Double getExactMax() {
            return exactMax;
        }

        public void setExactMax(Double exactMax) {
            this.exactMax = exactMax;
        }
    }


    public class ItemSource {

        private Integer sourceId;
        private String sourceType;

        public Integer getSourceId() {
            return sourceId;
        }

        public void setSourceId(Integer sourceId) {
            this.sourceId = sourceId;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }
    }


    public class WeaponInfo {

        private Damage damage;
        private Double weaponSpeed;
        private Double dps;

        public Damage getDamage() {
            return damage;
        }

        public void setDamage(Damage damage) {
            this.damage = damage;
        }

        public Double getWeaponSpeed() {
            return weaponSpeed;
        }

        public void setWeaponSpeed(Double weaponSpeed) {
            this.weaponSpeed = weaponSpeed;
        }

        public Double getDps() {
            return dps;
        }

        public void setDps(Double dps) {
            this.dps = dps;
        }
    }




    public class TooltipParams {

        private int gem0;
        private int gem1;
        private int gem2;
        private int enchant;
        private int timewalkerLevel;

        public int getGem0() {
            return gem0;
        }

        public void setGem0(int gem0) {
            this.gem0 = gem0;
        }

        public int getGem1() {
            return gem1;
        }

        public void setGem1(int gem1) {
            this.gem1 = gem1;
        }

        public int getGem2() {
            return gem2;
        }

        public void setGem2(int gem2) {
            this.gem2 = gem2;
        }

        public int getEnchant() {
            return enchant;
        }

        public void setEnchant(int enchant) {
            this.enchant = enchant;
        }

        public int getTimewalkerLevel() {
            return timewalkerLevel;
        }

        public void setTimewalkerLevel(int timewalkerLevel) {
            this.timewalkerLevel = timewalkerLevel;
        }

    }


    public class ArtifactTrait {

        private int id;
        private int rank;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

    }

    public class Relic {

        private int socket;
        private int itemId;
        private int context;
        private ArrayList<Integer> bonusLists = null;

        public int getSocket() {
            return socket;
        }

        public void setSocket(int socket) {
            this.socket = socket;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getContext() {
            return context;
        }

        public void setContext(int context) {
            this.context = context;
        }

        public ArrayList<Integer> getBonusLists() {
            return bonusLists;
        }

        public void setBonusLists(ArrayList<Integer> bonusLists) {
            this.bonusLists = bonusLists;
        }

    }

   /*public class ItemSpell {

        private int spellId;
        private Spell spell;
        private int nCharges;
        private boolean consumable;
        private int categoryId;
        private String trigger;

        public int getSpellId() {
            return spellId;
        }

        public Spell getSpell() {
            return spell;
        }

        public int getnCharges() {
            return nCharges;
        }

        public boolean isConsumable() {
            return consumable;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public String getTrigger() {
            return trigger;
        }
    }

    public class BonusList {

        private ArrayList<Integer> bonusLists = null;

        public ArrayList<Integer> getBonusLists() {
            return bonusLists;
        }

        public void setBonusLists(ArrayList<Integer> bonusLists) {
            this.bonusLists = bonusLists;
        }

    }*/

}


/*
//private Integer containerSlots;
    //private Integer minFactionId;
    //private Integer minReputation;
        //private Boolean hasSockets;
    //private Boolean isAuctionable;
        //private Boolean heroicTooltip;
    //private String context;
       // private Integer requiredSkillRank;
    //private ItemSource itemSource;
        //private Integer requiredSkill;
          //private Boolean equippable;
          //private Integer disenchantingSkillRank;
           // private Integer sellPrice;
    //private Integer requiredLevel;
    //private Integer maxCount;
       //private Integer buyPrice;
        //private Integer stackable;

 */
