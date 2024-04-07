// FILE SYSTEM DOOM

import fileManger.Crawler;
import fileManger.IndexObject;
import json.Job;
import json.ReadJobs;
import json.ReadSettings;

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


        //
        Crawler crawler = new Crawler();
        IndexObject indexObject;
        ReadJobs readJobs = new ReadJobs();
        List<Job> jobList = readJobs.loadJobs();

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
                System.out.println("Indexing (" + job.name + "): " + job.root);
                indexObject = crawler.crawlRoot(job.root);
                System.out.println("Total Index Files: " + indexObject.indexedFiles.size());
                System.out.println("Total Failed Files: " + indexObject.failedIndexs.size());

                //System.out.println(indexObject.indexedFiles);
            }
        }


        //






        while (running) {
            //System.out.println("test");
        }
    }
}