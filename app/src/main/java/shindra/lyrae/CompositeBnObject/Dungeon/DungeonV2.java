package shindra.lyrae.CompositeBnObject.Dungeon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Guillaume on 07/08/2017.
 */

public class DungeonV2 implements Parcelable{

    private int zoneId;
    private ArrayList<Loot> lLoots;




    public DungeonV2(int zoneId, ArrayList<Loot> lLoots) {
        this.zoneId = zoneId;
        this.lLoots = lLoots;
    }

    protected DungeonV2(Parcel in) {
        zoneId = in.readInt();
        lLoots = in.createTypedArrayList(Loot.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(zoneId);
        dest.writeTypedList(lLoots);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public  final Creator<DungeonV2> CREATOR = new Creator<DungeonV2>() {
        @Override
        public DungeonV2 createFromParcel(Parcel in) {
            return new DungeonV2(in);
        }

        @Override
        public DungeonV2[] newArray(int size) {
            return new DungeonV2[size];
        }
    };

    public int getId() {
        return zoneId;
    }

    public ArrayList<Integer> getBossLootId(int bossId) {
        ArrayList<Integer> lootTable = new ArrayList<>();

        for (Loot loot : lLoots) {

            if (loot.getLootOnBossId() == bossId) lootTable.add(loot.getLootId());
        }

        return lootTable;
    }

    public int getNbOfLoots() {
        return lLoots.size();
    }

    public ArrayList<Integer> getAllDungeonLootsId() {
        ArrayList<Integer> lootTable = new ArrayList<>();

        for (Loot loot : lLoots) {
            lootTable.add(loot.getLootId());
        }

        return lootTable;
    }


    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public ArrayList<Loot> getlLoots() {
        return lLoots;
    }

    public void setlLoots(ArrayList<Loot> lLoots) {
        this.lLoots = lLoots;
    }





    public static class Loot  implements  Parcelable{
        private int lootId;
        private int lootOnBossId;
        private boolean isLootUpload;


        public Loot(int lootId, int lootOnBossId) {
            this.lootId = lootId;
            this.lootOnBossId = lootOnBossId;
        }

        protected Loot(Parcel in) {
            lootId = in.readInt();
            lootOnBossId = in.readInt();
        }

        public int getLootId() {
            return lootId;
        }

        public int getLootOnBossId() {
            return lootOnBossId;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(lootId);
            dest.writeInt(lootOnBossId);

        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Loot> CREATOR = new Creator<Loot>() {
            @Override
            public Loot createFromParcel(Parcel in) {
                return new Loot(in);
            }

            @Override
            public Loot[] newArray(int size) {
                return new Loot[size];
            }
        };

    }


}
