package fileManger;

import JobFunctions.Job;
import JobFunctions.JobHandeler;

import java.util.List;

public class Shutdown {
    public Shutdown(JobHandeler jobHandeler) {


        List<Job> jobs = jobHandeler.getJobList();
        int activeJob = 0;


        for (Job job : jobs) {

            if (job.thread != null && job.thread.isAlive()) {

                activeJob++;
            }
        }


        System.out.println("Shutting Down Running Jobs (" + activeJob + ")");


        for (Job job : jobs) {

            job.isAlive = false;

            if(job.thread != null) {
                if(!job.running && job.thread.getState() != Thread.State.TERMINATED) {

                    job.thread.interrupt();
                    System.out.println("Job <" + job.name + "> Was Asleep And Has Been Reaped.");

                } else {

                    System.out.println("Job <" + job.name + "> Still Running. Stop Sent, Awaiting Finish. Average run time (" + job.avg + ")s.");
                }
            }
        }

        try {

            int runningJobs = 0;

            while (runningJobs != jobs.size()) {

                runningJobs = 0;

                for (Job job : jobs) {

                    if (job.thread == null || !job.thread.isAlive()) {

                        runningJobs++;
                    }
                }

                Thread.sleep(1000);

                System.out.print("...");
            }

        } catch (Exception e) {

            System.out.println("Error Shutting Down Programs Safely." + e.getMessage());
        }
    }
}
