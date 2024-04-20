package CommandLine;

import JobFunctions.JobHandeler;
import fileManger.MessageHandeler;
import fileManger.Shutdown;
import JobFunctions.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interpreter {

    private MessageHandeler messageHandeler;
    private JobHandeler jobHandeler;
    private Scanner input;
    private List<Thread> runningJobs;
    private List<String> errors;
    private List<Job> jobList;


    public Interpreter(MessageHandeler mh, List<Thread> runningJobs, List<String> errors, List<Job> jobList, JobHandeler jobHandeler) {
        this.messageHandeler = mh;
        this.input = new Scanner(System.in);
        this.runningJobs = runningJobs;
        this.errors = errors;
        this.jobList = jobList;
        this.jobHandeler = jobHandeler;
    }


    public void read() {

        String rawInput = input.nextLine();

            List<String> keys = new ArrayList<>(Arrays.asList(rawInput.split(" ")));

        switch (keys.get(0)) {

            case "help":
                new Help();
                break;

            case "exit":
                System.out.println("Stopping Running Jobs.");
                new Shutdown(runningJobs, messageHandeler);
                System.out.println("Shutdown Complete.");
                System.exit(0);
                break;

            case "stop":
                new Stop(jobHandeler, messageHandeler, keys);
                break;

            case "start":
                new Start(jobHandeler, messageHandeler, keys);
                break;

            case "list":
                new Status(jobHandeler);
                break;

            case "errors":
                new Errors(jobHandeler);
                break;

            case "msg":
                messageHandeler.printMessages();
                break;

            default:
                System.out.println("Unknown Command. Type help for help.");
        }
    }
}
