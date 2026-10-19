import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        
        System.out.println("=== QUICKCHAT REGISTRATION ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter cellphone (+27 format): ");
        String cell = scanner.nextLine();
        
        String regResult = login.registerUser(username, password, cell);
        System.out.println(regResult);
        
        if (!regResult.contains("successfully")) {
            System.out.println("Registration failed. Cannot proceed.");
            return;
        }
        
        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Password: ");
        String loginPass = scanner.nextLine();
        
        boolean loggedIn = login.loginUser(loginUser, loginPass);
        System.out.println(login.returnLoginStatus(loggedIn, loginUser));
        
        if (!loggedIn) {
            System.out.println("Username or password incorrect. Please login successfully before sending messages.");
            return;
        }
        
        // Part 2 - Messaging menu
        System.out.print("\nHow many messages do you want to send? ");
        int numMessages = Integer.parseInt(scanner.nextLine());
        int totalSent = 0;
        
        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Message " + i + " ---");
            System.out.print("Enter recipient cell (+27): ");
            String recipient = scanner.nextLine();
            
            Message tempMsg = new Message(i, recipient, "");
            if (!tempMsg.checkRecipientCell(recipient)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
                System.out.print("Re-enter recipient: ");
                recipient = scanner.nextLine();
            }
            
            System.out.print("Enter your message (max 250 chars): ");
            String text = scanner.nextLine();
            
            Message message = new Message(i, recipient, text);
            String lengthCheck = message.checkMessageLength(text);
            System.out.println(lengthCheck);
            
            if (lengthCheck.contains("exceeds")) {
                continue;
            }
            
            System.out.println("\n1) Send message\n2) Disregard message\n3) Store message to send later");
            System.out.print("Choose option: ");
            String option = scanner.nextLine();
            
            switch (option) {
                case "1":
                    System.out.println("\n" + message.printMessage());
                    System.out.println("Message sent.");
                    totalSent++;
                    break;
                case "2":
                    System.out.println("Press 0 to delete message.");
                    break;
                case "3":
                    message.storeMessage();
                    System.out.println("Message stored.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        
        System.out.println("\nTotal messages sent: " + totalSent);
        scanner.close();
    }
}
