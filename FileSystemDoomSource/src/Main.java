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

        MessageHandeler mh = new MessageHandeler();

        // Goes though list of jobs, Verifes the job is ok, then runs job in new thread.
        for (Job job : jobList) {

            VerifyJob verifyJob = new VerifyJob();
            List<String> vj = verifyJob.verifyJob(job);

            // if the jobs ok and had no errors then run a new thread and store it in the list.
            if(vj.isEmpty())
            {
                JobThread j1 = new JobThread(job, mh, jobHandeler);
                Thread thread = new Thread(j1);
                thread.setName(job.name);
                thread.start();

                runningJobs.add(thread);
            } else {
                errors.addAll(vj);
            }
        }


        Interpreter interpreter = new Interpreter(mh, runningJobs, errors, jobList, jobHandeler);
        System.out.println("Enter Command OR help: ");

        while (running) {

            interpreter.read();

        }
    }
}