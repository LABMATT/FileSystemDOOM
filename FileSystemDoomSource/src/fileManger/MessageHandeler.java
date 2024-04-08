package fileManger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageHandeler {

    int messageID = 0;
    List<Message> storedMessages = new ArrayList<>();

    public synchronized void SendMessage(String to, String from, String message) {

        if(storedMessages.isEmpty()) {
            messageID = 0;
        } else {
            messageID++;
        }

        storedMessages.add(new Message(messageID, to, from, message));
    }


    // Recive message for that reciver. If none then return null.
    public synchronized Message ReadMessage(String reciver) {

        for(Message message : storedMessages) {
            if(message.to.equalsIgnoreCase(reciver)) {
                return message;
            }
        }

        return null;
    }


    // Given ID remove message from array.
    public synchronized void RemoveMessage(int messageID) {

        int index = 0;
        int removeIndex = 0;

        for(Message message : storedMessages) {

            if(message.signatureID == messageID) {
                removeIndex = index;
                break;
            }

            index++;
        }

        storedMessages.remove(removeIndex);
    }
}
