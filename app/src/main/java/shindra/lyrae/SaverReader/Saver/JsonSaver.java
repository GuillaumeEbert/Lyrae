package shindra.lyrae.SaverReader.Saver;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Guillaume on 08/10/2017.
 */

public class JsonSaver extends Saver {
    private JsonObject jsonToSave;

    public JsonSaver(File folderToSave, String fileName,JsonObject jsonToSave, SaverCallback callback) {
        super(folderToSave, fileName,callback);
        this.jsonToSave=jsonToSave;

    }

    @Override
    protected void onSave(File file) throws IOException {
        String jsonData = jsonToSave.toString();
        FileOutputStream out = new FileOutputStream(file);
        out.write(jsonData.getBytes());
        out.close();

    }
}
