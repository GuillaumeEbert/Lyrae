package shindra.lyrae.CompositeBnObject.Dungeon;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import shindra.lyrae.JsonFetcher.JsonFetcherPool;
import shindra.lyrae.SaverReader.Saver.JsonSaver;
import shindra.lyrae.SaverReader.Saver.Saver;
import shindra.lyrae.SaverReader.SaverReaderPool;
import shindra.lyrae.Url.BnUrlFactory;

/**
 * Created by Guillaume on 09/10/2017.
 */

public class DungeonLoader implements DungeonRefData.DungeonDeserializerCallback, JsonFetcherPool.JsonFetcherPoolCallback, Saver.SaverCallback {

    private JsonFetcherPool jsonFetcherPool;
    private File dataFolder;
    private DungeonLoaderCallback callback;
    private int nbJsonToSave;
    private SaverReaderPool saverReaderPool;
    private int jsonToFetch;
    private ArrayList<DungeonV2> dungeonsV2RefData;


    public DungeonLoader(File dataFolder, DungeonLoaderCallback callback) {
        this.dataFolder = dataFolder;
        this.callback = callback;
        jsonFetcherPool = new JsonFetcherPool(this);
        saverReaderPool = new SaverReaderPool();

    }

    public void loadDungeons(InputStream dungeonStream) {
        DungeonRefData dungeonDataDes = new DungeonRefData(this);
        dungeonDataDes.execute(dungeonStream);

    }

    @Override
    public void onDungeonsRefDataLoaded(ArrayList<DungeonV2> dungeonV2s) {
        BnUrlFactory urlFactory = new BnUrlFactory();
        dungeonsV2RefData = dungeonV2s;

        try {
            for (DungeonV2 d : dungeonV2s) {

                loadZone(urlFactory, d);
                loadLoot(urlFactory, d);

                if (jsonToFetch == 0) callback.onDungeonsLoaded();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private void loadZone(BnUrlFactory urlFactory, DungeonV2 d) throws MalformedURLException {
        File zoneFile = new File(dataFolder, Integer.toString(d.getZoneId()));

        if (!zoneFile.exists()) {
            //zone
            URL zoneURl = urlFactory.getIdUrl(BnUrlFactory.ZONE_URL, d.getZoneId());
            jsonFetcherPool.submit(zoneURl);
            jsonToFetch++;
        }


    }

    private void loadLoot(BnUrlFactory urlFactory, DungeonV2 d) throws MalformedURLException {
        for (Integer lootId : d.getAllDungeonLootsId()) {

            File lootFile = new File(dataFolder, Integer.toString(lootId));

            if (!lootFile.exists()) {
                URL itemUrl = urlFactory.getIdUrl(BnUrlFactory.ITEM_URL, lootId);
                jsonFetcherPool.submit(itemUrl);
                jsonToFetch++;
            }

        }
    }

    @Override
    public void jsonFetched(JsonObject jsonObject) {
        String jsonName = jsonObject.get("id").getAsString();
        nbJsonToSave++;

        JsonSaver jsonSaver = new JsonSaver(dataFolder, jsonName, jsonObject, this);
        saverReaderPool.submitSaver(jsonSaver);
    }


    @Override
    public void onSaverFinisched() {
        nbJsonToSave--;
        if (nbJsonToSave == 0)
            callback.onDungeonsLoaded();
    }


    public interface DungeonLoaderCallback {
        void onDungeonsLoaded();
    }

    public ArrayList<DungeonV2> getDungeonsV2RefData() {
        return dungeonsV2RefData;
    }
}
