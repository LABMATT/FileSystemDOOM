package JobFunctions;

import java.util.ArrayList;
import java.util.List;

public class JobHandeler {

    List<Job> jobList;

    public synchronized Job getJob(String jobName) {

        for(Job job : jobList) {

            if(job.name.equalsIgnoreCase(jobName)) {

                return job;
            }
        }

        return null;
    }


    // calualtes the amout of time the thread takes to exacute.
    public synchronized long getAvrage(String jobName) {

        long avrage = 0;
        List<Long> values = new ArrayList<>();

        for(Job job : jobList) {

            if(job.name.equalsIgnoreCase(jobName)) {

                values = job.jobRuntime;
            }
        }

        for (Long value : values) {
            avrage = avrage + value;
        }

        if(avrage != 0) {
            avrage = avrage / values.size();
        }

        // Cut down the avrage aray so it doest get to big.
        if (values.size() > 1000) {

            // See how many values we must remove.
            int removeAmount = values.size() - 1000;

            while (removeAmount != 0) {

                values.remove(0);
                removeAmount--;
            }

            getJob(jobName).jobRuntime = values;

        }

        return avrage;
    }


    // Sets the joblist to inputted list.
    public synchronized void setJobList(List<Job> joblist) {

        this.jobList = joblist;
    }


    // Returns the current list of jobs.
    public synchronized List<Job> getJobList() {

        return jobList;
    }

}
