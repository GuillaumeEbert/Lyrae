package shindra.lyrae.BattleNetApi.BnObjectFactory;

import java.util.ArrayList;
import shindra.lyrae.BattleNetApi.BnObject.BnObject;

/**
 * Created by Guillaume on 26/08/2017.
 */

public class BnObjectFactoryReminder {
    private static int REQUEST_ID;
    private int id;
    private @BnObject.BnObjectType  int bnObjectToBuild;
    private BnObject resultObject;
    private ArrayList<BnObject> lResultBnobject;

    public BnObjectFactoryReminder(@BnObject.BnObjectType int bnObjectToBuild) {
        super();
        this.bnObjectToBuild = bnObjectToBuild;
       id = REQUEST_ID++;

    }

    public @BnObject.BnObjectType int getBnObjectToBuild() {
        return bnObjectToBuild;
    }



    public void setResultObject(BnObject resultObject) {
        this.resultObject = resultObject;
    }

    public void resultObject(ArrayList<BnObject> arrayBnObject) {
        this.lResultBnobject = arrayBnObject;
    }



    public BnObject getResultObject() {
        return resultObject;
    }

    public ArrayList<BnObject> getListBnObject() {
        return lResultBnobject;
    }

    public int getId() {
        return id;
    }
}
