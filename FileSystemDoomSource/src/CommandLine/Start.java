package CommandLine;

import fileManger.JobThread;
import fileManger.MessageHandeler;
import json.Job;

import java.util.List;

public class Start {
    public Start(List<Thread> runningJobs, MessageHandeler messageHandeler, List<String> jobName, List<Job> jobList) {

        boolean jobFound = false;

        if (jobName.size() != 2) {
            System.out.println("Invalid Syntax: Try start <JOB NAME>.");
        } else {
            for (Job job : jobList) {

                if (job.name.equalsIgnoreCase(jobName.get(1))) {

                    JobThread j1 = new JobThread(job, messageHandeler);
                    Thread thread = new Thread(j1);
                    thread.setName(job.name);
                    thread.start();

                    runningJobs.add(thread);

                    jobFound = true;
                    break;
                }
            }

            if (!jobFound) {
                System.out.println("No Job Found Named: " + jobName.get(1));
            }
        }
    }
}
