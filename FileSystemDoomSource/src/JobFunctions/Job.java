package JobFunctions;

import java.util.ArrayList;
import java.util.List;

public class Job {

    // Vars that define and change during a jobs lifetime.
    public Thread thread = null;
    public boolean isAlive = false;   // If false=kills thread | if true= keeps thread running
    public boolean enabled = false;   // Is the job currently Enabled in config.
    public boolean running = false;   // Is the job currently running in a thread. False=Idle | true=Running
    public String status = "";
    public List<Long> jobRuntime = new ArrayList<>();
    public float avg = 0;
    public List<String> errors = new ArrayList<>();
    public String name = "";
    public String root = "";
    public String target = "";
    public String mode = "";
    public long period = 0;
    public long lastActivation = 0; // Last time the task was preformed.


    // Job is an object that contains the info about a job from the json file.
    public Job(Object enabled, Object name, Object root, Object target, Object mode, Object period) {

        try {

            this.enabled = (boolean) enabled;
            this.name = name.toString();
            this.root = root.toString();
            this.target = target.toString();
            this.mode = mode.toString();
            this.period = (long) period;
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }


    public void activation() {

    }
}
