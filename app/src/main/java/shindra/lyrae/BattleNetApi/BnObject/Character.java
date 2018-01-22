package shindra.lyrae.BattleNetApi.BnObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by Guillaume on 02/08/2017.
 */

public class Character extends BnObject{

    private String name;
    private long lastModified;
    private String realm;
    private String battlegroup;
    @SerializedName("class")
    private int _class;
    private int race;
    private int gender;
    private int level;
    private int achievementPoints;
    private String thumbnail;
    private String calcClass;
    private int faction;
    private int totalHonorableKills;
    private int averageItemLvl;
    private int averageItemLvlEquiped;
    @SerializedName("stats")
    private Character.CharStats charStats;
    //private Guild guild = null;
    //private ArrayList<Item> stuffEquiped;

    public long getLastModified() {
        return lastModified;
    }



    public String getRealm() {
        return realm;
    }

    public String getBattlegroup() {
        return battlegroup;
    }

    public int getClass_() {
        return _class;
    }

    public int getRace() {
        return race;
    }

    public int getGender() {
        return gender;
    }

    public int getLevel() {
        return level;
    }

    public int getAchievementPoints() {
        return achievementPoints;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getCalcClass() {
        return calcClass;
    }

    public int getFaction() {
        return faction;
    }

    public int getTotalHonorableKills() {
        return totalHonorableKills;
    }


    /*public void setStuffEquiped(ArrayList<Item> stuffEquiped) {
        this.stuffEquiped = stuffEquiped;
    }

    public void setAverageItemLvl(int averageItemLvl) {
        this.averageItemLvl = averageItemLvl;
    }

    public void setAverageItemLvlEquiped(int averageItemLilEquipped) {
        this.averageItemLvlEquiped = averageItemLilEquipped;
    }*/




    private class CharStats {
        private int health;
        private String powerType;
        private int power;
        private int str;
        private int agi;
        private int _int;
        private int sta;
        private double speedRating;
        private double speedRatingBonus;
        private double crit;
        private int critRating;
        private double haste;
        private int hasteRating;
        private double hasteRatingPercent;
        private double mastery;
        private int masteryRating;
        private double leech;
        private double leechRating;
        private double leechRatingBonus;
        private int versatility;
        private double versatilityDamageDoneBonus;
        private double versatilityHealingDoneBonus;
        private double versatilityDamageTakenBonus;
        private double avoidanceRating;
        private double avoidanceRatingBonus;
        private int spellPen;
        private double spellCrit;
        private int spellCritRating;
        private double mana5;
        private double mana5Combat;
        private int armor;
        private double dodge;
        private int dodgeRating;
        private double parry;
        private int parryRating;
        private double block;
        private int blockRating;
        private double mainHandDmgMin;
        private double mainHandDmgMax;
        private double mainHandSpeed;
        private double mainHandDps;
        private double offHandDmgMin;
        private double offHandDmgMax;
        private double offHandSpeed;
        private double offHandDps;
        private double rangedDmgMin;
        private double rangedDmgMax;
        private double rangedSpeed;
        private double rangedDps;

        public int getHealth() {
            return health;
        }

        public String getPowerType() {
            return powerType;
        }

        public int getPower() {
            return power;
        }

        public int getStr() {
            return str;
        }

        public int getAgi() {
            return agi;
        }

        public int get_int() {
            return _int;
        }

        public int getSta() {
            return sta;
        }

        public double getSpeedRating() {
            return speedRating;
        }

        public double getSpeedRatingBonus() {
            return speedRatingBonus;
        }

        public double getCrit() {
            return crit;
        }

        public int getCritRating() {
            return critRating;
        }

        public double getHaste() {
            return haste;
        }

        public int getHasteRating() {
            return hasteRating;
        }

        public double getHasteRatingPercent() {
            return hasteRatingPercent;
        }

        public double getMastery() {
            return mastery;
        }

        public int getMasteryRating() {
            return masteryRating;
        }

        public double getLeech() {
            return leech;
        }

        public double getLeechRating() {
            return leechRating;
        }

        public double getLeechRatingBonus() {
            return leechRatingBonus;
        }

        public int getVersatility() {
            return versatility;
        }

        public double getVersatilityDamageDoneBonus() {
            return versatilityDamageDoneBonus;
        }

        public double getVersatilityHealingDoneBonus() {
            return versatilityHealingDoneBonus;
        }

        public double getVersatilityDamageTakenBonus() {
            return versatilityDamageTakenBonus;
        }

        public double getAvoidanceRating() {
            return avoidanceRating;
        }

        public double getAvoidanceRatingBonus() {
            return avoidanceRatingBonus;
        }

        public int getSpellPen() {
            return spellPen;
        }

        public double getSpellCrit() {
            return spellCrit;
        }

        public int getSpellCritRating() {
            return spellCritRating;
        }

        public double getMana5() {
            return mana5;
        }

        public double getMana5Combat() {
            return mana5Combat;
        }

        public int getArmor() {
            return armor;
        }

        public double getDodge() {
            return dodge;
        }

        public int getDodgeRating() {
            return dodgeRating;
        }

        public double getParry() {
            return parry;
        }

        public int getParryRating() {
            return parryRating;
        }

        public double getBlock() {
            return block;
        }

        public int getBlockRating() {
            return blockRating;
        }

        public double getMainHandDmgMin() {
            return mainHandDmgMin;
        }

        public double getMainHandDmgMax() {
            return mainHandDmgMax;
        }

        public double getMainHandSpeed() {
            return mainHandSpeed;
        }

        public double getMainHandDps() {
            return mainHandDps;
        }

        public double getOffHandDmgMin() {
            return offHandDmgMin;
        }

        public double getOffHandDmgMax() {
            return offHandDmgMax;
        }

        public double getOffHandSpeed() {
            return offHandSpeed;
        }

        public double getOffHandDps() {
            return offHandDps;
        }

        public double getRangedDmgMin() {
            return rangedDmgMin;
        }

        public double getRangedDmgMax() {
            return rangedDmgMax;
        }

        public double getRangedSpeed() {
            return rangedSpeed;
        }

        public double getRangedDps() {
            return rangedDps;
        }
    }


}
