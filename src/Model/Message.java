package Model;

import Utility.Scan;

public class Message
{
    //attributes
    private int senderID;
    private int recipientID;
    private boolean read = false;
    private String message;

    //constructor
    public Message(int senderID, int recipientID, boolean read, String message) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.read = read;
        this.message = message;
    }

    //setters and getters
    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(int recipientID) {
        this.recipientID = recipientID;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message from " + this.senderID + Scan.EOL + "To: " + this.recipientID + Scan.EOL +
                "Message: " + this.getMessage() + Scan.EOL;
    }

}

