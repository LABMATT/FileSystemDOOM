package CommandLine;

import JobFunctions.Job;
import JobFunctions.JobHandeler;

import java.util.ArrayList;
import java.util.List;

// Prints the current running jobs and there status.
public class Status {
    public Status(JobHandeler jobHandeler) {

        List<Job> disabledJobs = new ArrayList<>();
        List<Job> disabledManualyJobs = new ArrayList<>();
        List<Job> enabledManualyJobs = new ArrayList<>();
        List<Job> enabledJobs = new ArrayList<>();
        List<Job> erroredJobs = new ArrayList<>();




        for (Job job : jobHandeler.getJobList()) {

            if(job.enabled) {
                if (job.thread.getState() == Thread.State.TERMINATED) {
                    disabledManualyJobs.add(job);
                } else {
                    enabledJobs.add(job);
                }

            } else {

                if(job.thread != null && job.thread.getState() == Thread.State.TIMED_WAITING) {
                    enabledManualyJobs.add(job);
                } else {
                    disabledJobs.add(job);
                }
            }
        }

        System.out.println("Status Of All Jobs: ");

        System.out.println(" ");
        System.out.println("Disabled:");
        for (Job job : disabledJobs) {

            String status = "IDLE";
            if (job.running) {
                status = "Running";
            }

            System.out.println("- JOB: " + job.name + " Status: " + status + " AvrageRunTime: " + jobHandeler.getAvrage(job.name));
        }

        System.out.println(" ");
        System.out.println("Disabled (Manually):");
        for (Job job : disabledManualyJobs) {

            String status = "IDLE";
            if (job.running) {
                status = "Running";
            }

            System.out.println("- JOB: " + job.name + " Status: " + status + " AvrageRunTime: " + jobHandeler.getAvrage(job.name));
        }

        System.out.println(" ");
        System.out.println("Enabled (Manually):");
        for (Job job : enabledManualyJobs) {

            String status = "IDLE";
            if (job.running) {
                status = "Running";
            }

            System.out.println("- JOB: " + job.name + " Status: " + status + " AvrageRunTime: " + jobHandeler.getAvrage(job.name));
        }

        System.out.println(" ");
        System.out.println("Enabled:");
        for (Job job : enabledJobs) {

            String status = "IDLE";
            if (job.running) {
                status = "Running";
            }

            System.out.println("- JOB: " + job.name + " Status: " + status + " AvrageRunTime: " + jobHandeler.getAvrage(job.name));
        }
    }
}
