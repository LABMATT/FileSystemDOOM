package json;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Reads the JSON file for what backup jobs there are.
public class Jobs {

    public void loadJobs() {

        String FSDjson = "E:\\Projects\\ProjectFSD\\JSON\\FSD_Jobs.json";
        JSONParser parser = new JSONParser();

        try {

            Object fileObject = parser.parse(new FileReader(FSDjson));
            JSONObject json = (JSONObject) fileObject;



        } catch (Exception e) {

        }
    }
}
