package json;

import JobFunctions.Job;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VerifyJob {

    private List<String> jobErrors = new ArrayList<>();

    // Makes sure the jobs settings are valid.
    public List<String> verifyJob(Job job) {

        try {

            if (!job.enabled) {
                jobErrors.add("Job Is Not Enabled.");
            }

            if (job.name.isEmpty()) {
                jobErrors.add("Job Error: Jobs Name Is Empty In FSD_Jobs.json");
            }

            if (job.root.isEmpty()) {

                jobErrors.add("Job Error: Jobs Root location Is Empty In FSD_Jobs.json");
            }

            File root = new File(job.root);
            if (!root.exists()) {

                jobErrors.add("Job Error: Unable To Access Root File.");
            }

            System.out.println("Job period: " + job.period);
            if(!(job.period > 1)) {

                jobErrors.add("Job Error: Jobs Period Is Invalid (must be greater than 1ms) In FSD_Jobs.json");
            }

            // Checks to make sure the jobs mode is one of the following.
            if(!job.mode.isEmpty()) {

                switch (job.mode) {
                    case "version":
                        break;
                    case "backup if change":
                        break;
                    case "backup on change":
                        break;
                    case "backup":
                        break;
                    case "clean":
                        break;
                    default:

                        jobErrors.add("Job Error: Jobs Mode Invalid In FSD_Jobs.json");
                }
            }

        } catch (Exception exception)
        {
            jobErrors.add("Job Error: " + exception);
        }


        // If no errors were acccounted then run the job.
        System.out.println(jobErrors);
        return jobErrors;
    }
}
