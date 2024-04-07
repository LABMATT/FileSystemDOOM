// FILE SYSTEM DOOM

import fileManger.Crawler;
import fileManger.IndexObject;
import json.ReadJobs;
import json.ReadSettings;

public class Main {



    public static void main(String[] args) {

        boolean running = true;

        System.out.println("Hello world!");

        // Read the settings file.
        ReadSettings readJson = new ReadSettings();

        if(!readJson.loadSettings()) {
            System.out.println("FSD Is Disabled In FSD_Settings.json");
            System.exit(0);
        }

        ReadJobs readJobs = new ReadJobs();
        readJobs.loadJobs();


        //
        Crawler crawler = new Crawler();
        IndexObject indexObject = crawler.crawlRoot("C:\\Users\\Matt\\Downloads");

        System.out.println("Total Index Files: " + indexObject.indexedFiles.size());
        System.out.println("Total Failed Files: " + indexObject.failedIndexs.size());


        while(running)
        {
            //System.out.println("test");
        }
    }
}