package CommandLine;

import java.util.List;

// Prints the current running jobs and there status.
public class Status {
    public Status(List<Thread> runningJobs) {
        System.out.println("Currnet Jobs: ");

        for (Thread thread : runningJobs) {
            System.out.println("-" + thread.getName() +" " + thread.getState());
        }
    }
}
