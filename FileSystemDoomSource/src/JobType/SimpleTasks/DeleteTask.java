package JobType.SimpleTasks;

import JobFunctions.Job;
import fileManger.Crawler;
import fileManger.IndexObject;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DeleteTask {

    // Delete Task will preform a delete operation once activated.
    // 1: Check if file/folder exits
    // 2: Checks if its readable
    // 3: Checks if is folder.
    // 4: If folder then crawl and elete each element one by one.
    // 5: If file then delete.
    // 5: spits errors if found.

    public DeleteTask(Job thisJob) {

        List<String> deletedFiles = new ArrayList<>();
        List<String> suvivorFiles = new ArrayList<>();

        boolean flagIssue = false;
        boolean dirContentsOnly = false;

        File file = new File(thisJob.target);

        if(thisJob.target.isEmpty()) {

            flagIssue = true;
            thisJob.errors.add("DeleteTask: The Target File Or Folder Is Emptry. <" + thisJob.target + ">.");
        } else {

            if(thisJob.target.endsWith("\\") || thisJob.target.endsWith("/")) {

                dirContentsOnly = true;
                System.out.println("CONTENTS ONLY");
            }
        }


        boolean isReadable = Files.isReadable(file.toPath());
        if(!isReadable) {

            flagIssue = true;
            removedAlreadyError(thisJob);
        }


        boolean isDIR = file.isDirectory();
        IndexObject indexObject = null;
        if(isDIR) {

            Crawler crawler = new Crawler();
            indexObject = crawler.crawlRoot(thisJob.target);

            if (indexObject == null)
            {

                flagIssue = true;
                thisJob.errors.add("DeleteTask: Failed To Crawl Target Folder. <" + thisJob.target + ">.");
            }
        }




        boolean isDelete = false;
        if(!flagIssue) {

            if(isDIR){

                for(File index : indexObject.indexedFiles) {

                    boolean outcome = false;
                    if(index.isFile())
                    {

                        outcome = index.delete();
                        System.out.println("Deleting: " + index.getPath() + "/" + index.getName());
                    }

                    if (outcome) {

                        deletedFiles.add(index.getPath() + "/" + index.getName());
                    } else {

                        suvivorFiles.add(index.getPath() + "/" + index.getName());
                    }
                }

                boolean dirDeleteOutcome = false;
                if (suvivorFiles.isEmpty()) {

                    if(!dirContentsOnly)
                    {
                        System.out.println("Contents ERASED");
                        dirDeleteOutcome = file.delete();
                    } else {

                        System.out.println("Contents PRESERCED");
                        dirDeleteOutcome = true;
                    }
                }

                if (!dirDeleteOutcome) {

                    suvivorFiles.add(file.getPath() + "/" + file.getName());
                }
            } else {
                System.out.println("It was a file.");

                isDelete = file.delete();
            }
        }

        System.out.println("FlaggedIssue: " + flagIssue);
    }


    private void removedAlreadyError(Job thisJob) {

        if(!thisJob.errors.contains("DeleteTask: File Is Not Readable Or Does Not Exist <" + thisJob.target + ">.")) {

            thisJob.errors.add("DeleteTask: File Is Not Readable Or Does Not Exist <" + thisJob.target + ">.");
        }
    }
}
