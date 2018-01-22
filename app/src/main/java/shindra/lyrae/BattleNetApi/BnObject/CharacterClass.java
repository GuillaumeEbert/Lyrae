package shindra.lyrae.BattleNetApi.BnObject;

/**
 * Created by Guillaume on 10/08/2017.
 */

public class CharacterClass {
    private int id;
    private int mask;
    private String powerType;
    private String iconName;

    public int getId() {
        return id;
    }

    public int getMask() {
        return mask;
    }

    public String getPowerType() {
        return powerType;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }


}
