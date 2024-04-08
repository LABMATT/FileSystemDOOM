package fileManger;

import json.Job;

import java.util.ArrayList;
import java.util.List;

public class JobThread implements Runnable {

    private MessageHandeler msg;
    private Job job;
    private List<String> jobErrors = new ArrayList<>();

    public JobThread(Job regJob, MessageHandeler regmsg) {
        this.job = regJob;
        this.msg = regmsg;
    }

    public void run() {

        boolean runJob = true;

        System.out.println("Stared JOB: " + job.name);

        try {

            while (runJob) {
                long startTime = System.currentTimeMillis();

                // preform task
                System.out.println(":)");

                long endTime = System.currentTimeMillis();
                long totalTime = Math.subtractExact(endTime, startTime);
                //msg.SendMessage(job.name, "stats", String.valueOf(totalTime));

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
