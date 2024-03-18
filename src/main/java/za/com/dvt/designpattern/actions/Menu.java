package za.com.dvt.designpattern.actions;

import java.util.Scanner;

import za.com.dvt.designpattern.members.Roles;

import static za.com.dvt.designpattern.library.FileHandler.getIdFromFile;
import static za.com.dvt.designpattern.library.FileHandler.login;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Library Management System");
        while (true) {
            System.out.println("Are you a");
            System.out.println("1. Borrower");
            System.out.println("2. Librarian");
            System.out.println("3. Administrator");
            System.out.println("4. Exit");
            String roleInput = scanner.nextLine();

            if (roleInput.equalsIgnoreCase("exit") || roleInput.equals("4")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            Roles role = getRoleFromInput(roleInput);
            if (role == null) {
                System.out.println("Invalid input! Please enter 1, 2, 3, or 'exit' to exit.");
                continue;
            }

            System.out.println("Enter your ID:");
            String id = scanner.nextLine();

            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            if (login(role, id, password)) {
                System.out.println("Login Successful");

                String userId = getIdFromFile(role);
                if (userId != null) {
                    System.out.println("User ID: " + userId);
                }

                switch (role) {
                    case BORROWER:
                        BorrowerActions.borrowerActions(scanner);
                        break;
                    case LIBRARIAN:
                        LibrarianActions.librarianActions(scanner);
                        break;
                    case ADMINISTRATOR:
                        AdminActions.adminActions(scanner);
                        break;
                }
            }
        }
        scanner.close();
    }

    private Roles getRoleFromInput(String roleInput) {
        return switch (roleInput) {
            case "1" -> Roles.BORROWER;
            case "2" -> Roles.LIBRARIAN;
            case "3" -> Roles.ADMINISTRATOR;
            default -> null;
        };
    }
}
