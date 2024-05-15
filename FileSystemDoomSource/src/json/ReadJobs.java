package json;

import JobFunctions.Job;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// Reads the JSON file for what backup jobs there are.
// FORMAT FOLLOWS:
// JOBNAME
// enabled - If the job is currently running.
// name - name of the job
// root - the root folder this job is for.
// mode - Mode shows what type of process this should be.
//      - Backup - Stright copy per period.
//      - Backup if change - Only backs up when the file changes checks every period.
//      - Backup on change - Backs up when a change occurs. Checks for change every period.
//      - Version - version control schema.
//      - Clean - Deletes all files in this folder every period.
// period - Is How offer the mode is triggered.

public class ReadJobs {


    public List<Job> loadJobs() {

        String FSDjson = "E:\\Projects\\FSDgit\\FileSystemDOOM\\JSON\\FSD_Jobs.json";
        JSONParser parser = new JSONParser();

        try {

            List<Job> jobs = new ArrayList<>();

            Object fileObject = parser.parse(new FileReader(FSDjson));
            JSONObject json = (JSONObject) fileObject;

            JSONObject jobsJSON = (JSONObject) json.get("Jobs");

            for (Object object : jobsJSON.keySet()) {

                JSONObject jobData = (JSONObject) jobsJSON.get(object);

                // Creates a job object for use later.
                jobs.add(new Job(
                        jobData.get("enabled"),
                        jobData.get("name"),
                        jobData.get("root"),
                        jobData.get("target"),
                        jobData.get("mode"),
                        jobData.get("period")
                ));
            }


            return jobs;


        } catch (Exception e) {
            System.out.println("Error Reading Settings: " + e);
        }

        return null;
    }
}
