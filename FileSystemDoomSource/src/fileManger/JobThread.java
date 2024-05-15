
// JobThread Is what will run as a "job".
//

package fileManger;

import JobFunctions.Job;
import JobFunctions.JobHandeler;
import JobType.SimpleTasks.DeleteTask;

import java.util.ArrayList;
import java.util.List;

public class JobThread implements Runnable {

    private MessageHandeler msg;
    private JobHandeler jobHandeler;
    private String jobName = null;
    private List<String> jobErrors = new ArrayList<>();

    public JobThread(MessageHandeler regmsg, JobHandeler jobHandeler) {
        this.msg = regmsg;
        this.jobHandeler = jobHandeler;
    }

    public void run() {

        jobName = Thread.currentThread().getName();
        String mode = jobHandeler.getJob(jobName).mode;
        Job thisJob = jobHandeler.getJob(jobName);

        boolean runJob = true;

        try {

            while (runJob) {
                // Start Avrg Timer | Set job status Running.
                long startTime = System.currentTimeMillis();
                jobHandeler.getJob(jobName).running = true;

                // preform task
                switch (mode) {
                    case "version":            // Backs up everything in version control.
                    case "versionChange":
                    case "backup":             // Copys the whole struture over.
                    case "backupChange":       // only replaces files that changed. Backsup on firle change
                    case "copy":                   // Mirrors the fs elsewhere if one item gets deleted then so does the other.
                    case "delete":
                        new DeleteTask(thisJob);
                        break;

                    case "cut":
                }

                Crawler crawler = new Crawler();
                crawler.crawlRoot(jobHandeler.getJob(jobName).root);

                // Stop Timer | stop running in jobhandler.
                long endTime = System.currentTimeMillis();
                long totalTime = Math.subtractExact(endTime, startTime);
                jobHandeler.getJob(jobName).jobRuntime.add(totalTime);

                jobHandeler.getJob(jobName).running = false;
                jobHandeler.getJob(jobName).lastActivation = System.currentTimeMillis();

                runJob = jobHandeler.getJob(jobName).isAlive;

                if(runJob) {
                    Thread.sleep(jobHandeler.getJob(jobName).period);
                    runJob = jobHandeler.getJob(jobName).isAlive;
                }
            }

        } catch (Exception exception) {

            jobHandeler.getJob(jobName).errors.add(exception.getMessage());
        }

            System.out.println("<" + jobName + "> Stopped.");
    }
}
