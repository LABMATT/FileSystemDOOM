package CommandLine;

import JobFunctions.Job;
import JobFunctions.JobHandeler;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    public Errors(JobHandeler jobHandeler) {

        System.out.println("Errors: ");
        List<String> errors = new ArrayList<>();
        List<String> errorSource = new ArrayList<>();

        for (Job job : jobHandeler.getJobList()) {
            errors.addAll(job.errors);
            errorSource.add(job.name);
        }

        int index = 0;
        for (String error : errors) {

            System.out.println("- <" + errorSource.get(index) +">" + error);
            index++;
        }
    }
}
