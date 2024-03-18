package za.com.dvt.designpattern.actions;

import java.util.Scanner;

import za.com.dvt.designpattern.library.Catalog;
import za.com.dvt.designpattern.Main;
import za.com.dvt.designpattern.library.FileHandler;
import za.com.dvt.designpattern.library.WishListManager;
import za.com.dvt.designpattern.books.BookProxy;
import za.com.dvt.designpattern.books.Books;
import za.com.dvt.designpattern.members.Borrower;
import za.com.dvt.designpattern.library.Library;
import za.com.dvt.designpattern.members.Librarian;
import za.com.dvt.designpattern.members.Roles;

public class LibrarianActions {

    public static void librarianActions(final Scanner scanner) {
        String librarianId = FileHandler.getIdFromFile(Roles.LIBRARIAN);

        if (librarianId != null) {
            Librarian librarian = new Librarian(librarianId);
            boolean logout = false;
            while (!logout) {
                String menuString = """
                        Welcome to the Librarian Menu
                        What would you like to do?
                        1. Search item by title, author, or ISBN
                        2. View All Books
                        3. View Personal information
                        4. Check out an item for a Member
                        5. Check in an item that has been returned
                        6. Add a new Member
                        7. Add a new item to the collection
                        8. Delete an item from the collection
                        9. View All Books added to wish list
                        10. Logout
                        """;
                System.out.println(menuString);
                String choiceStr = scanner.nextLine();

                try {
                    int choice = Integer.parseInt(choiceStr);
                    switch (choice) {
                        case 1:
                            Catalog.searchBooks(scanner);
                            break;
                        case 2:
                            Catalog.viewAllBooks();
                            break;
                        case 3:
                            librarian.viewPersonalInformation();
                            break;
                        case 4:
                            System.out.println("Enter the ISBN of the book to check out:");
                            String isbn = scanner.nextLine();

                            System.out.println("Enter the ID of the Borrower:");
                            String borrowerID = scanner.nextLine();

                            BookProxy bookProxy = new BookProxy(isbn);
                            Library.checkOutBook(bookProxy, borrowerID);
                            break;
                        case 5:
                            System.out.println("Enter the ISBN of the book you want to check in:");
                            String isbnToCheckIn = scanner.nextLine();
                            BookProxy bookProxyToCheckIn = new BookProxy(isbnToCheckIn);
                            Library.checkInBook(bookProxyToCheckIn);
                            break;
                        case 6:
                            addBorrower(scanner);
                            break;
                        case 7:
                            addBook(scanner);
                            break;
                        case 8:
                            deleteBook(scanner);
                            break;
                        case 9:
                            WishListManager.viewBooksAddedToWishList();
                            break; // Add a break statement here
                        case 10:
                            logout = true;
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a number.");
                }
            }
        } else {
            System.out.println("Error: Librarian ID not found.");
        }
    }

    private static void addBorrower(final Scanner scanner) {
        System.out.println("Enter Borrower ID:");
        String id = scanner.nextLine();
        System.out.println("Enter Borrower Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Borrower Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Borrower Surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter Borrower Address:");
        String address = scanner.nextLine();
        System.out.println("Enter a password for the Borrower");
        String password = scanner.nextLine();

        Borrower.addBorrower(id, password, name, title, surname, address);
    }

    public static void addBook(final Scanner scanner) {
        System.out.println("Enter the Book Name");
        String name = scanner.nextLine();
        System.out.println("Enter the Authors Name");
        String author = scanner.nextLine();
        System.out.println("Enter the ISBN of the Book");
        String ISBN = scanner.nextLine();

        Books.addBook(name, author, ISBN);
    }

    public static void deleteBook(final Scanner scanner) {
        System.out.println("Enter the ISBN of the book to delete:");
        String ISBN = scanner.nextLine();

        Books.deleteBook(ISBN);
    }
}
