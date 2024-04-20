package CommandLine;

import JobFunctions.JobHandeler;
import fileManger.JobThread;
import fileManger.MessageHandeler;
import JobFunctions.Job;

import java.util.List;

public class Start {
    public Start(JobHandeler jobHandeler, MessageHandeler messageHandeler, List<String> keys) {

        boolean jobFound = false;

        if (keys.size() != 2) {
            System.out.println("Invalid Syntax: Try start <JOB NAME>.");
        } else {

            Job foundJob = null;

            for(Job job : jobHandeler.getJobList()) {

                if (job.name.equalsIgnoreCase(keys.get(1))) {

                    foundJob = job;
                    break;
                }
            }

            if(foundJob != null && foundJob.thread != null) {

                if (foundJob.thread.isAlive()) {

                    System.out.println("Job Already Running.");
                } else {

                    messageHandeler.SendMessage(foundJob.name, "core", "start");

                    JobThread j1 = new JobThread(messageHandeler, jobHandeler);

                    foundJob.thread = new Thread(j1);

                    foundJob.thread.setName(foundJob.name);
                    foundJob.thread.start();
                }
            }

            if (foundJob == null) {
                System.out.println("No Job Found Named: " + keys.get(1));
            }
        }
    }
}
