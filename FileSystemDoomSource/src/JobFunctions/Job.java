package JobFunctions;

import java.util.ArrayList;
import java.util.List;

public class Job {

    // Vars that define and change during a jobs lifetime.
    public Thread thread;
    public boolean enabled = false;   // Is the job currently running.
    public boolean running = false;   // Is the job currently running in a thread. False=Idle | true=Running
    public String status = "";
    public List<Long> jobRuntime = new ArrayList<>();
    public List<String> errors = new ArrayList<>();
    public String name = "";
    public String root = "";
    public String target = "";
    public String mode = "";
    public long period = 0;
    public int lastActivation = 0; // Last time the task was preformed.
    public Exception exception;


    // Job is an object that contains the info about a job from the json file.
    public Job(Object enabled, Object name, Object root, Object mode, Object period) {

        try {

            this.enabled = (boolean) enabled;
            this.name = name.toString();
            this.root = root.toString();
            this.mode = mode.toString();
            this.period = (long) period;
        } catch (Exception e) {
            exception = e;
        }
    }


    public void activation() {

    }
}
