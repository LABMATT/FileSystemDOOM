// FILE SYSTEM DOOM

import fileManger.Crawler;
import fileManger.IndexObject;

public class Main {



    public static void main(String[] args) {

        boolean running = true;

        System.out.println("Hello world!");

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