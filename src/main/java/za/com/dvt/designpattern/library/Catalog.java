package za.com.dvt.designpattern.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import za.com.dvt.designpattern.books.BookProxy;

public class Catalog {

    public static final String BOOKS_FILE_PATH = "src/main/java/resources/books.txt";

    public static void searchBooks(final Scanner scanner) {
        System.out.println("Search for books by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. ISBN");
        System.out.print("Enter your choice: ");
        int searchBy = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter search query: ");
        String query = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String author = parts[1].trim();
                    String isbn = parts[2].trim();

                    switch (searchBy) {
                        case 1:
                            if (name.equalsIgnoreCase(query)) {
                                System.out.println("Book found: " + line);
                            }
                            break;
                        case 2:
                            if (author.equalsIgnoreCase(query)) {
                                System.out.println("Book found: " + line);
                            }
                            break;
                        case 3:
                            if (isbn.equalsIgnoreCase(query)) {
                                BookProxy bookProxy = new BookProxy(isbn);
                                System.out.println("Book found: " + bookProxy.getName() + " by " + bookProxy.getAuthor());
                            }
                            break;
                        default:
                            System.out.println("Invalid search criteria.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading books data: " + e.getMessage());
        }
    }

    public static void viewAllBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading books data: " + e.getMessage());
        }
    }
}
