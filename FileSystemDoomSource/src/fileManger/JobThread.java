package fileManger;

import JobFunctions.Job;
import JobFunctions.JobHandeler;

import java.util.ArrayList;
import java.util.List;

public class JobThread implements Runnable {

    private MessageHandeler msg;
    private JobHandeler jobHandeler;
    private Job job;
    private List<String> jobErrors = new ArrayList<>();

    public JobThread(Job regJob, MessageHandeler regmsg, JobHandeler jobHandeler) {
        this.job = regJob;
        this.msg = regmsg;
        this.jobHandeler = jobHandeler;
    }

    public void run() {

        boolean runJob = true;

        System.out.println("Stared JOB: " + job.name);

        try {

            while (runJob) {
                // Start Avrg Timer | Set job status Running.
                long startTime = System.currentTimeMillis();
                jobHandeler.getJob(job.name).running = true;

                // preform task
                Thread.sleep(1000);

                // Stop Timer | stop running in jobhandler.
                long endTime = System.currentTimeMillis();
                long totalTime = Math.subtractExact(endTime, startTime);
                jobHandeler.getJob(job.name).jobRuntime.add(totalTime);

                jobHandeler.getJob(job.name).running = false;
                Thread.sleep(job.period);
                runJob = running();
            }

        } catch (Exception exception) {
            System.out.println("error in thread." + exception);
        }

        System.out.println("Thread Stopped.");
    }

    private boolean running() {
        Message command = msg.ReadMessage(job.name);

        for (Message recivedMessage : msg.ReadMessages(job.name))
        {
            if (recivedMessage.message.equalsIgnoreCase("stop"))
            {
                msg.RemoveMessage(command.signatureID);
                return false;

            } else if(recivedMessage.message.equalsIgnoreCase("start")) {

                return true;
            }
        }

        return true;
    }
}
