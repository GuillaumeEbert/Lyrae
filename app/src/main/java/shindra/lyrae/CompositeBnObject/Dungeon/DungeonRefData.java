package shindra.lyrae.CompositeBnObject.Dungeon;

import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Guillaume on 06/09/2017.
 */

public class DungeonRefData extends AsyncTask<InputStream, InputStream, ArrayList<DungeonV2>>{

    private DungeonDeserializerCallback mCallback;


    public DungeonRefData(DungeonDeserializerCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected ArrayList<DungeonV2> doInBackground(InputStream... inputStreams) {
        JsonParser jP = new JsonParser();

        ArrayList<DungeonV2> lDungeons = new ArrayList<>();

        //Convert InputStream to Json
        JsonObject jDungeon = jP.parse(new InputStreamReader(inputStreams[0])).getAsJsonObject();

        //Deserialize Json
        JsonArray jsonDungeonArray = jDungeon.getAsJsonArray("dungeons");

        for(int i = 0; i< jsonDungeonArray.size(); i++){
            ArrayList<DungeonV2.Loot> lootTable = new ArrayList<>();

            //Deserialize zone Id
            JsonObject jsonDungeon = jsonDungeonArray.get(i).getAsJsonObject();
            int zoneID = jsonDungeon.get("zoneId").getAsInt();

            //Deserialize boss Id
            JsonArray jsonBossArray = jsonDungeon.getAsJsonArray("bosses");
            for(int y = 0; y< jsonBossArray.size(); y++){

                JsonObject jsonBosses = jsonBossArray.get(y).getAsJsonObject();
                int bossId = jsonBosses.get("bossId").getAsByte();

                //Deserialize loot table
                JsonArray jsonLootArray = jsonBosses.getAsJsonArray("loots");
               for(int x = 0; x< jsonLootArray.size(); x++){
                   int lootId = jsonLootArray.get(x).getAsJsonObject().get("lootId").getAsInt();

                   DungeonV2.Loot aLoot = new DungeonV2.Loot(lootId,bossId);
                   lootTable.add(aLoot);

               }
            }

            //Build the dungeon
            DungeonV2 d  = new DungeonV2(zoneID,lootTable);
            lDungeons.add(d);
        }

        return lDungeons;
    }


    @Override
    protected void onPostExecute(ArrayList<DungeonV2> dungeonV2s) {
        super.onPostExecute(dungeonV2s);
        mCallback.onDungeonsRefDataLoaded(dungeonV2s);

    }



    public interface DungeonDeserializerCallback{
        void onDungeonsRefDataLoaded(ArrayList<DungeonV2> dungeonV2s);

    }






}
