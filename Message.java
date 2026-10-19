import java.util.Random;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageID;
    private int messageNo;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNo, String recipient, String messageText) {
        this.messageNo = messageNo;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates random 10-digit ID
    public String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Q1 - Check recipient cellphone number
    public boolean checkRecipientCell(String cellNumber) {
        // Must start with +27 and be max 13 chars (e.g., +27831234567 = 12)
        if (cellNumber.startsWith("+27") && cellNumber.length() <= 13) {
            return true;
        }
        return false;
    }

    // Q2 - Check message length (max 250 chars)
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    // Create message hash: FIRST TWO:ID: LAST TWO WORDS uppercase
    public String createMessageHash() {
        String[] words = messageText.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        String hash = firstTwo + ":" + messageNo + ":" + (firstWord + lastWord).toUpperCase();
        return hash;
    }

    // Return full message details
    public String printMessage() {
        return "MessageID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    // Store message in JSON file
    public void storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("messageID", messageID);
        obj.put("messageNo", messageNo);
        obj.put("recipient", recipient);
        obj.put("messageText", messageText);
        obj.put("messageHash", messageHash);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(obj.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    public String getMessageHash() {
        return messageHash;
    }
    
    public String getRecipient() {
        return recipient;
    }
}
