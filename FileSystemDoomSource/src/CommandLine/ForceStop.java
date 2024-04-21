package CommandLine;

import JobFunctions.Job;
import JobFunctions.JobHandeler;

import java.util.List;


// If the job is found and if it has a thread then Exacute order 66 on its ass.
public class ForceStop {
    public ForceStop(JobHandeler jobHandeler, List<String> keys) {

        boolean runCommand = true;

        System.out.println("Keys size" + keys.size());


        if (keys.size() != 3){

            runCommand = false;


        }else if (!keys.get(1).equalsIgnoreCase("stop")) {

            System.out.println();
            runCommand = false;
        }


        if(runCommand){

            Job foundJob = null;

            for (Job job : jobHandeler.getJobList()) {

                if (job.name.equalsIgnoreCase(keys.get(2))) {

                    foundJob = job;
                }
            }

            if (foundJob != null) {
                if (foundJob.thread != null) {

                    foundJob.thread.interrupt();
                }
            } else {

                System.out.println("Unknown Job <" + keys.get(2) + ">.");
            }
        } else {

            System.out.println("Syntax Error. force stop <JobName>");
        }
    }
}
