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

                if(job.thread != null && job.thread.getState() != Thread.State.TERMINATED) {
                    enabledManualyJobs.add(job);
                } else {
                    disabledJobs.add(job);
                }
            }
        }

        System.out.println("### Status Of All Jobs ###");

        System.out.println(" ");
        System.out.println("Disabled:");
        printWithFormatting(disabledJobs, jobHandeler);

        System.out.println(" ");
        System.out.println("Disabled (Manually):");
        printWithFormatting(disabledManualyJobs, jobHandeler);

        System.out.println(" ");
        System.out.println("Enabled (Manually):");
        printWithFormatting(enabledManualyJobs, jobHandeler);

        System.out.println(" ");
        System.out.println("Enabled:");
        printWithFormatting(enabledJobs, jobHandeler);

        System.out.println("### END ###");

    }

    private void printWithFormatting(List<Job> list, JobHandeler jobHandeler) {

        for (Job job : list) {

            String status = "IDLE";
            if (job.running) {
                status = "Running";
            }

            String thd = "";
            if(job.thread != null) {
                thd = String.valueOf(job.thread.isAlive());
            } else  {
                thd = "null";
            }

            System.out.println("- JOB: " + job.name + " Status: " + status + " AvrageRunTime: " + jobHandeler.getAvrage(job.name) + " THREAD: " + thd);
        }
    }
}
