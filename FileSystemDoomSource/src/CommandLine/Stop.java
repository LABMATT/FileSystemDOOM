package CommandLine;

import fileManger.MessageHandeler;

import java.util.List;

public class Stop {
    public Stop(List<Thread> runningJobs, MessageHandeler messageHandeler, List<String> keys) {

        if(keys.size() != 2){
            System.out.println("Invalid Syntax: Try stop <JOB NAME>.");
        } else {
            messageHandeler.SendMessage(keys.get(1), "core", "stop");

            boolean jobFound = false;
            int index = 0;
            for (Thread thread : runningJobs) {

                if(thread.getName().equalsIgnoreCase(keys.get(1))) {
                    runningJobs.remove(index);
                    jobFound = true;
                    break;
                }

                index++;
            }

            if(!jobFound) {
                System.out.println("No Job Found Named: " + keys.get(1));
            }
        }
    }
}
