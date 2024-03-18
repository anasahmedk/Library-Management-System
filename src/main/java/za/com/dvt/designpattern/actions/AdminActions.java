package za.com.dvt.designpattern.actions;

import java.util.Scanner;

import za.com.dvt.designpattern.library.Catalog;
import za.com.dvt.designpattern.Main;
import za.com.dvt.designpattern.library.FileHandler;
import za.com.dvt.designpattern.members.Admin;
import za.com.dvt.designpattern.members.Librarian;
import za.com.dvt.designpattern.library.Library;
import za.com.dvt.designpattern.members.Roles;

public class AdminActions {

    public static void adminActions(final Scanner scanner) {
        String adminId = FileHandler.getIdFromFile(Roles.ADMINISTRATOR);

        if (adminId != null) {
            Admin admin = new Admin(adminId);
            boolean logout = false;
            while (!logout) {
                String menuString = """
                        What would you like to do?
                        1. Add Librarian
                        2. View Issued Book History
                        3. View All Books in Library
                        4. View Personal Information
                        5. Logout
                        Enter your choice:\s""";
                System.out.println(menuString);
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addLibrarian(scanner);
                        break;
                    case 2:
                        Library.viewIssuedBookHistory();
                        break;
                    case 3:
                        Catalog.viewAllBooks();
                        break;
                    case 4:
                        admin.viewPersonalInformation();
                        break;
                    case 5:
                        logout = true;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } else {
            System.out.println("Error: Admin ID not found.");
        }
    }

    private static void addLibrarian(final Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String id = scanner.nextLine();
        System.out.println("Enter Librarian Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Librarian Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Librarian Surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter Librarian Address:");
        String address = scanner.nextLine();
        System.out.println("Enter a password for the Librarian");
        String password = scanner.nextLine();

        Librarian.addLibrarian(id, password, name, title, surname, address);
    }
}