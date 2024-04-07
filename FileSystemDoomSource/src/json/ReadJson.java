package json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Reads the JSON file for what backup jobs there are.
public class ReadJson {

    public boolean laodSettings() {

        String FSDjson = "E:\\Projects\\FSDgit\\FileSystemDOOM\\JSON\\FSD_Settings.json";
        JSONParser parser = new JSONParser();

        try {

            Object fileObject = parser.parse(new FileReader(FSDjson));
            JSONObject json = (JSONObject) fileObject;

            JSONObject jsonSettings = (JSONObject) json.get("settings");



            return jsonSettings.get("enable").toString().equalsIgnoreCase("true");


        } catch (Exception e) {
            System.out.println("Failed" + e);
        }

        return false;
    }
}
