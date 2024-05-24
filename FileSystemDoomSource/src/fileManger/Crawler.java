package fileManger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// Crawler crawls a root DIR for its sub folders

public class Crawler {


    // Starts the process of crawling all files.
    public IndexObject crawlRoot(String rootlocation) {

        List<File> auxiliaryCrawl = new ArrayList<>();
        List<File> indexedFiles = new ArrayList<>();
        List<File> failedIndexs = new ArrayList<>();

        boolean validPath = true;

        Path path = Paths.get(rootlocation);
        if (!Files.exists(path)) {

            validPath = false;
        }


        if (validPath) {


            // Preform inital crawl starting from root Directory. If a files found then add to index list, else add to next round.
            File file = new File(rootlocation);
            crawl(file, indexedFiles, auxiliaryCrawl, failedIndexs);

            // Loop though all index files until the list is empty. Crawl each file.
            while (!auxiliaryCrawl.isEmpty()) {

                crawl(auxiliaryCrawl.get(0), indexedFiles, auxiliaryCrawl, failedIndexs);
                auxiliaryCrawl.remove(0);
            }

            // Create an Object and return it. Oject contains all the info from this index.
            return new IndexObject(rootlocation, indexedFiles, failedIndexs);
        }

        // if an error occured then retrun null.
        return null;
    }


    // Crawl a whole folder adding the files to the indexed file list if found else add to second crawl list.
    private void crawl(File file, List<File> indexedFiles, List<File> auxiliaryCrawl, List<File> failedIndexs) {

        File[] fileList = file.listFiles();

        if (fileList != null) {
            for (File scannedItem : fileList) {

                if (scannedItem.isDirectory()) {


                    auxiliaryCrawl.add(scannedItem);
                } else {

                    indexedFiles.add(scannedItem);
                }
            }
        } else {
            failedIndexs.add(file);
        }
    }



    public IndexObject crawlDIR(String rootlocation) {

        List<File> auxiliaryCrawl = new ArrayList<>();
        List<File> indexedFiles = new ArrayList<>();
        List<File> failedIndexs = new ArrayList<>();

        boolean validPath = true;

        Path path = Paths.get(rootlocation);
        if (!Files.exists(path)) {

            validPath = false;
        }


        if (validPath) {


            // Preform inital crawl starting from root Directory. If a files found then add to index list, else add to next round.
            File file = new File(rootlocation);
            crawlSubDirs(file, indexedFiles, auxiliaryCrawl, failedIndexs);

            // Loop though all index files until the list is empty. Crawl each file.
            while (!auxiliaryCrawl.isEmpty()) {

                crawlSubDirs(auxiliaryCrawl.get(0), indexedFiles, auxiliaryCrawl, failedIndexs);
                auxiliaryCrawl.remove(0);
            }

            // Create an Object and return it. Oject contains all the info from this index.
            return new IndexObject(rootlocation, indexedFiles, failedIndexs);
        }

        // if an error occured then retrun null.
        return null;
    }
    // Crawl a whole folder adding the files to the indexed file list if found else add to second crawl list.
    private void crawlSubDirs(File file, List<File> indexedFiles, List<File> auxiliaryCrawl, List<File> failedIndexs) {

        File[] fileList = file.listFiles();

        if (fileList != null) {
            for (File scannedItem : fileList) {

                if (scannedItem.isDirectory()) {


                    auxiliaryCrawl.add(scannedItem);
                } else {

                    indexedFiles.add(scannedItem);
                }
            }
        } else {
            failedIndexs.add(file);
        }
    }
}
