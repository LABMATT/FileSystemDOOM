package json;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Reads the JSON file for what backup jobs there are.
public class ReadSettings {



    public boolean loadSettings() {

        String FSDjson = "E:\\Projects\\FSDgit\\FileSystemDOOM\\JSON\\FSD_Settings.json";
        JSONParser parser = new JSONParser();

        try {

            Object fileObject = parser.parse(new FileReader(FSDjson));
            JSONObject json = (JSONObject) fileObject;

            JSONObject jsonSettings = (JSONObject) json.get("settings");

            return (boolean) jsonSettings.get("enable");


        } catch (Exception e) {
            System.out.println("Error Reading Settings: " + e);
        }

        return false;
    }
}
