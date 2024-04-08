package fileManger;

import java.util.List;

public class Shutdown {
    public Shutdown(List<Thread> runningJobs, MessageHandeler messageHandeler) {

        for (Thread thread : runningJobs) {
            messageHandeler.SendMessage(thread.getName(), "core", "stop");

            if(thread.getState() == Thread.State.TIMED_WAITING)
            {
                thread.interrupt();
            }
        }
    }
}
