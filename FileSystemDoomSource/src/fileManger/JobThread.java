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


        System.out.println("Job running");

        try {

            while (runJob) {
                runJob = msg.ReadMessage();
                System.out.println("Hello from thread. MSG is: " + runJob);
                Thread.sleep(job.period);
            }

        } catch (Exception exception) {
            System.out.println("error in thread.");
        }

        System.out.println("Thread Stopped.");
    }
}
