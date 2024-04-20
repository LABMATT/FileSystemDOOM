// FILE SYSTEM DOOM

import CommandLine.Interpreter;
import JobFunctions.Job;
import JobFunctions.JobHandeler;
import fileManger.JobThread;
import fileManger.MessageHandeler;
import json.*;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        boolean running = true;

        System.out.println("Hello world!");

        // Read the settings file.
        ReadSettings readJson = new ReadSettings();

        if (!readJson.loadSettings()) {
            System.out.println("FSD Is Disabled In FSD_Settings.json");
            System.exit(0);
        }


        // Lists to know what threads are running.
        ReadJobs readJobs = new ReadJobs();
        List<Thread> runningJobs = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        // Job handler cotains all the jobs and there status. Set and Get the list.
        JobHandeler jobHandeler = new JobHandeler();
        jobHandeler.setJobList(readJobs.loadJobs());
        List<Job> jobList = jobHandeler.getJobList();

        MessageHandeler messageHandler = new MessageHandeler();

        for (Job job : jobHandeler.getJobList()) {

            boolean runJob = true;


            if(!job.enabled) {
                runJob = false;
            }

            if (!job.errors.isEmpty()) {
                runJob = false;
            }

            if (runJob) {
                JobThread j1 = new JobThread(messageHandler, jobHandeler);

                job.thread = new Thread(j1);

                job.thread.setName(job.name);
                job.thread.start();
            }
        }


        Interpreter interpreter = new Interpreter(messageHandler, runningJobs, errors, jobList, jobHandeler);
        System.out.println("Enter Command OR help: ");

        while (running) {

            interpreter.read();

        }
    }
}