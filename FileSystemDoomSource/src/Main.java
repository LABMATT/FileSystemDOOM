// FILE SYSTEM DOOM

import CommandLine.Interpreter;
import CommandLine.Status;
import fileManger.JobThread;
import fileManger.MessageHandeler;
import fileManger.Shutdown;
import json.Job;
import json.ReadJobs;
import json.ReadSettings;
import json.VerifyJob;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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



        ReadJobs readJobs = new ReadJobs();
        List<Job> jobList = readJobs.loadJobs();
        List<Thread> runningJobs = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        MessageHandeler mh = new MessageHandeler();

        // Goes though list of jobs, Verifes the job is ok, then runs job in new thread.
        for (Job job : jobList) {

            VerifyJob verifyJob = new VerifyJob();
            List<String> vj = verifyJob.verifyJob(job);

            // if the jobs ok and had no errors then run a new thread and store it in the list.
            if(vj.isEmpty())
            {
                JobThread j1 = new JobThread(job, mh);
                Thread thread = new Thread(j1);
                thread.setName(job.name);
                thread.start();

                runningJobs.add(thread);
            } else {
                errors.addAll(vj);
            }
        }


        Interpreter interpreter = new Interpreter(mh, runningJobs, errors, jobList);
        System.out.println("Enter Command OR help: ");

        while (running) {

            interpreter.read();

        }
    }
}