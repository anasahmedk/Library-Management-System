package za.com.dvt.designpattern.actions;

import java.util.Scanner;

import za.com.dvt.designpattern.library.Catalog;
import za.com.dvt.designpattern.Main;
import za.com.dvt.designpattern.library.FileHandler;
import za.com.dvt.designpattern.library.WishListManager;
import za.com.dvt.designpattern.members.Borrower;
import za.com.dvt.designpattern.members.Roles;

public class BorrowerActions {

    public static void borrowerActions(final Scanner scanner) {
        String borrowerId = FileHandler.getIdFromFile(Roles.BORROWER);
        if (borrowerId != null) {
            Borrower borrower = new Borrower(borrowerId);
            boolean logout = false;
            while (!logout) {
                String menuString = """
                        Welcome to the Borrower Menu
                        What would you like to do?
                        1. View All books in the library2. Search item by title, author, or ISBN
                        3. Add a book to your wish list
                        4. View Personal information
                        5. Logout
                        """;
                System.out.println(menuString);

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        Catalog.viewAllBooks();
                        break;
                    case 2:
                        Catalog.searchBooks(scanner);
                        break;
                    case 3:
                        System.out.println("Enter the name of the book:");
                        String bookName = scanner.nextLine();
                        System.out.println("Enter the author of the book:");
                        String author = scanner.nextLine();
                        WishListManager.addToWishList(bookName, author);
                        break;
                    case 4:
                        borrower.viewPersonalInformation();
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
            System.out.println("Error: Borrower ID not found.");
        }
    }
}