import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to QuickChat - Registration ===");
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Username (must contain _ and <=5 chars): ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password (8 chars, capital, number, special): ");
        String password = scanner.nextLine();
        
        System.out.print("Enter Cell Number (+27...): ");
        String cell = scanner.nextLine();

        Login user = new Login(username, password, cell, firstName, lastName);
        
        String regResult = user.registerUser();
        System.out.println(regResult);

        if (regResult.equals("User registered successfully.")) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();
            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean loggedIn = user.loginUser(loginUser, loginPass);
            System.out.println(user.returnLoginStatus(loggedIn));
        }
        
        scanner.close();
    }
}
