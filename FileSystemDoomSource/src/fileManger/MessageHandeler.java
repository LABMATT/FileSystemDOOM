package fileManger;

import java.util.ArrayList;
import java.util.List;

public class MessageHandeler {

    //List<Boolean> msg = new ArrayList<>();
    public boolean msg = true;

    public synchronized void SendMessage(boolean msgdata) {
     //msg.add(msgdata);
        msg = msgdata;
    }

    public synchronized boolean ReadMessage() {
        //return msg.get(0);
        return msg;
    }
}
