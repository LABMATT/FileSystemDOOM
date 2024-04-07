package json;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Reads the JSON file for what backup jobs there are.
// FORMAT FOLLOWS:
// JOBNAME
// enabled - If the job is currently running.
// name - name of the job
// root - the root folder this job is for.
// mode - Mode shows what type of process this should be. Backup is a stright copy. Version is a version control scheem.
// period - Is How offen the backup occurs.

public class ReadJobs {



    public boolean loadJobs() {

        String FSDjson = "E:\\Projects\\FSDgit\\FileSystemDOOM\\JSON\\FSD_Jobs.json";
        JSONParser parser = new JSONParser();

        try {

            Object fileObject = parser.parse(new FileReader(FSDjson));
            JSONObject json = (JSONObject) fileObject;

            JSONObject jsonSettings = (JSONObject) json.get("Jobs");

            return (boolean) jsonSettings.get("enable");


        } catch (Exception e) {
            System.out.println("Error Reading Settings: " + e);
        }

        return false;
    }
}
