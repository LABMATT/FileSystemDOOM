package CommandLine;

import JobFunctions.JobHandeler;

import java.util.List;

// Prints the current running jobs and there status.
public class Status {
    public Status(List<Thread> runningJobs, JobHandeler jobHandeler) {
        System.out.println("Currnet Jobs: ");

        for (Thread thread : runningJobs) {
            System.out.println("-" + thread.getName() +" Status: " + jobHandeler.getJob(thread.getName()).running + " Thread:" + thread.getState() + " AvgRunTime: " + jobHandeler.getAvrage(thread.getName()));
        }
    }
}
