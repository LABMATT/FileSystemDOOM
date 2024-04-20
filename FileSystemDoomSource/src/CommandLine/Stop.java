package CommandLine;

import JobFunctions.Job;
import JobFunctions.JobHandeler;
import fileManger.MessageHandeler;

import java.util.List;

public class Stop {
    public Stop(JobHandeler jobHandeler, MessageHandeler messageHandeler, List<String> keys) {

        if(keys.size() != 2){
            System.out.println("Invalid Syntax: Try stop <JOB NAME>.");
        } else {

            Job foundJob = null;

            for (Job job : jobHandeler.getJobList()) {

                if (job.name.equalsIgnoreCase(keys.get(1))) {

                    foundJob = job;
                }
            }

            if(foundJob != null && foundJob.running) {

                System.out.println("Sending Stop Command To <" + foundJob.name + ">");
                //messageHandeler.SendMessage(foundJob.name, "core", "stop");
                jobHandeler.getJob(foundJob.name).isAlive = false;
            } else if(foundJob != null && foundJob.thread != null){

                System.out.println("Stopping <" + foundJob.name + ">");
                //messageHandeler.SendMessage(foundJob.name, "core", "stop");
                jobHandeler.getJob(foundJob.name).isAlive = false;
                foundJob.thread.interrupt();
            } else {

                System.out.println("Job Not Running.");
            }

            if(foundJob == null) {
                System.out.println("No Job Found Named: " + keys.get(1));
            }
        }
    }
}
