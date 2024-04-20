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

        System.out.println("Stared JOB: " + jobName);

        try {

            while (runJob) {
                // Start Avrg Timer | Set job status Running.
                long startTime = System.currentTimeMillis();
                jobHandeler.getJob(jobName).running = true;

                // preform task
                Thread.sleep(1000);

                // Stop Timer | stop running in jobhandler.
                long endTime = System.currentTimeMillis();
                long totalTime = Math.subtractExact(endTime, startTime);
                jobHandeler.getJob(jobName).jobRuntime.add(totalTime);

                jobHandeler.getJob(jobName).running = false;
                Thread.sleep(jobHandeler.getJob(jobName).period);
                runJob = running();
            }

        } catch (Exception exception) {
            System.out.println("error in thread." + exception);
        }

        System.out.println("Thread Stopped.");
    }

    private boolean running() {
        Message command = msg.ReadMessage(jobName);

        for (Message recivedMessage : msg.ReadMessages(jobName))
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
