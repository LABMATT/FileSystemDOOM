// FILE SYSTEM DOOM

import fileManger.JobThread;
import fileManger.MessageHandeler;
import json.Job;
import json.ReadJobs;
import json.ReadSettings;

import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        Date date = new Date();
        long startTime = System.currentTimeMillis();

        boolean running = true;

        System.out.println("Hello world!");

        // Read the settings file.
        ReadSettings readJson = new ReadSettings();

        if (!readJson.loadSettings()) {
            System.out.println("FSD Is Disabled In FSD_Settings.json");
            System.exit(0);
        }

        /*
        Crawler crawler = new Crawler();
        IndexObject indexObject;

         */
        ReadJobs readJobs = new ReadJobs();
        List<Job> jobList = readJobs.loadJobs();

        MessageHandeler mh = new MessageHandeler();

        for (Job job : jobList) {
            boolean validJob = true;

            if(job.root.isEmpty())
            {
                validJob = false;
            }

            if (!job.enabled)
            {
                validJob = false;
            }

            if(validJob) {

                // Register Jobs by creating threads.
                /*
                JobThread t1 = new JobThread();
                t1.run(job, mh);

                 */

                JobThread j1 = new JobThread(job, mh);
                Thread t1 = new Thread(j1);
                t1.start();

                System.out.println("Is this blocking?");
                Thread.sleep(10000);
                mh.SendMessage("LM FIRE","core","stop");

                /*
                System.out.println("Indexing (" + job.name + "): " + job.root);
                indexObject = crawler.crawlRoot(job.root);
                System.out.println("Total Index Files: " + indexObject.indexedFiles.size());
                System.out.println("Total Failed Files: " + indexObject.failedIndexs.size());

                 */

                //System.out.println(indexObject.indexedFiles);
            }
        }

        long endTime = System.currentTimeMillis();
        long totalTime = Math.subtractExact(endTime, startTime);
        System.out.println("Runtime was: " + totalTime + "ms OR " + (totalTime/1000f) + "s");
        System.out.println("FSD Core Stopped.");


        while (running) {
            //System.out.println("test");
        }
    }
}