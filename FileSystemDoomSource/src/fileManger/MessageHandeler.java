package fileManger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageHandeler {

    private int messageID = 0;
    private List<Message> storedMessages = new ArrayList<>();

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


    public synchronized List<Message> ReadMessages(String reciver) {

        List<Message> recivedMessages = new ArrayList<>();

        for(Message message : storedMessages) {
            if(message.to.equalsIgnoreCase(reciver)) {
                recivedMessages.add(message);
            }
        }

        return recivedMessages;
    }


    public synchronized boolean isMessage(String reciver) {

        for(Message message : storedMessages) {
            if(message.to.equalsIgnoreCase(reciver)) {
                return true;
            }
        }

        return false;
    }


    // Given ID remove message from array.
    public synchronized void RemoveMessage(int messageID) {

        int index = 0;
        int removeIndex = -1;

        for(Message message : storedMessages) {

            if(message.signatureID == messageID) {
                removeIndex = index;
                System.out.println("FOUND REMOVE");
                break;
            }

            index++;
        }

        if(removeIndex != -1)
        {
            storedMessages.remove(removeIndex);
        }
    }


    public synchronized void printMessages() {
        for (Message msg : storedMessages) {
            System.out.println(msg.message);
        }
    }
}
