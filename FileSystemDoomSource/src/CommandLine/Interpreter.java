package CommandLine;

import fileManger.MessageHandeler;
import fileManger.Shutdown;
import json.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interpreter {

    private MessageHandeler messageHandeler;
    private Scanner input;
    private List<Thread> runningJobs;
    private List<String> errors;
    private List<Job> jobList;


    public Interpreter(MessageHandeler mh, List<Thread> runningJobs, List<String> errors, List<Job> jobList) {
        this.messageHandeler = mh;
        this.input = new Scanner(System.in);
        this.runningJobs = runningJobs;
        this.errors = errors;
        this.jobList = jobList;
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
                new Stop(runningJobs, messageHandeler, keys);
                break;

            case "start":
                new Start(runningJobs, messageHandeler, keys, jobList);
                break;

            case "list":
                new Status(runningJobs);
                break;

            case "errors":
                new Errors(errors);
                break;

            case "msg":
                messageHandeler.printMessages();
                break;

            default:
                System.out.println("Unknown Command. Type help for help.");
        }
    }
}
