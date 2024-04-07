// FILE SYSTEM DOOM

import fileManger.Crawler;
import fileManger.IndexObject;
import json.ReadJson;

public class Main {



    public static void main(String[] args) {

        boolean running = true;

        System.out.println("Hello world!");

        ReadJson readJson = new ReadJson();

        if(!readJson.laodSettings()) {
            System.out.println("FSD Is Dissabled In FSD_Settings.json");
            System.exit(0);
        }

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