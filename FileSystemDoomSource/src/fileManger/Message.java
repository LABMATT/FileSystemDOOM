package fileManger;

public class Message {

    public String to = "";
    public String from = "";
    public int signatureID = 0;
    public String message = "";

    public Message(int signatureID, String to, String from, String message) {
        this.signatureID = signatureID;
        this.to = to;
        this.from = from;
        this.message = message;
    }
}
