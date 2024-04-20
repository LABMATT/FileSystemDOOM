package fileManger;

import JobFunctions.Job;
import JobFunctions.JobHandeler;

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

        boolean runJob = true;

        try {

            while (runJob) {
                // Start Avrg Timer | Set job status Running.
                long startTime = System.currentTimeMillis();
                jobHandeler.getJob(jobName).running = true;

                // preform task
                Crawler crawler = new Crawler();
                crawler.crawlRoot(jobHandeler.getJob(jobName).root);

                // Stop Timer | stop running in jobhandler.
                long endTime = System.currentTimeMillis();
                long totalTime = Math.subtractExact(endTime, startTime);
                jobHandeler.getJob(jobName).jobRuntime.add(totalTime);

                jobHandeler.getJob(jobName).running = false;

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

    private boolean running() {

        boolean run = true;

        for (Message recivedMessage : msg.ReadMessages(jobName))
        {
            if (recivedMessage.message.equalsIgnoreCase("stop"))
            {
                msg.RemoveMessage(recivedMessage.signatureID);
                run = false;

            } else if(recivedMessage.message.equalsIgnoreCase("start")) {

                msg.RemoveMessage(recivedMessage.signatureID);
                run = true;
            }
        }

        return run;
    }
}
