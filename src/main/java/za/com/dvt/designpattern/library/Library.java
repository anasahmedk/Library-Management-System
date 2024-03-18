package za.com.dvt.designpattern.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import za.com.dvt.designpattern.WishListObserver;
import za.com.dvt.designpattern.books.BookProxy;
import za.com.dvt.designpattern.library.FileHandler;

public class Library implements WishListObserver {

    private static final String CHECKOUT_RECORDS_FILE_PATH = "src/main/java/resources/checkout_records.txt";
    public static final String BOOKS_FILE_PATH = "src/main/java/resources/books.txt";
    private static final String CHECKED_OUT_FILE_PATH = "src/main/java/resources/checked_out.txt";
    private static final double FINE_AMOUNT = 10;
    private final List<WishListObserver> observers;
    private static FileHandler fileHandler;

    public Library() {
        this.observers = new ArrayList<>();
        fileHandler = new FileHandler();
    }

    public static void checkOutBook(final BookProxy book, final String borrowerID) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE_PATH))) {
            String line;
            boolean found = false;

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentISBN = parts[2].trim();
                if (currentISBN.equals(book.getISBN())) {
                    found = true;
                    String updatedLine = line + "," + borrowerID + "," + formattedDateTime + "," + "CHECKOUT";
                    fileHandler.writeCheckedOutBook(updatedLine);
                }
            }

            if (!found) {
                System.out.println("Book with ISBN " + book.getISBN() + " not found.");
            } else {
                System.out.println("Book with ISBN " + book.getISBN() + " checked out successfully by borrower ID: " + borrowerID);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void checkInBook(final BookProxy book) {
        try (BufferedReader br = new BufferedReader(new FileReader(CHECKED_OUT_FILE_PATH))) {
            String line;
            boolean found = false;
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentISBN = parts[2].trim();
                String borrowerID = parts[3].trim();
                String checkoutDateStr = parts[4].trim();
                String status = parts[5].trim();

                if (currentISBN.equals(book.getISBN()) && status.equals("CHECKOUT")) {
                    found = true;
                    LocalDateTime checkoutDate = LocalDateTime.parse(checkoutDateStr, formatter);
                    long duration = ChronoUnit.DAYS.between(checkoutDate, currentTime);

                    if (duration > 3) {
                        double fineAmount = FINE_AMOUNT;
                        String finesRecord = borrowerID + "," + book.getISBN() + "," + fineAmount + "\n";
                        fileHandler.updateCheckedInBook(finesRecord);
                        System.out.println("Fine imposed: R" + fineAmount + " for borrower ID: " + borrowerID);
                    }

                    String checkoutRecord = parts[0] + "," + parts[1] + "," + parts[2] + "," + borrowerID + "," + checkoutDateStr + ",CHECKIN\n";
                    fileHandler.updateCheckedInBook(checkoutRecord);
                }
            }

            if (!found) {
                System.out.println("Book with ISBN " + book.getISBN() + " was not checked out by any borrower.");
            } else {
                System.out.println("Book with ISBN " + book.getISBN() + " checked in successfully.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void viewIssuedBookHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader(CHECKOUT_RECORDS_FILE_PATH))) {
            System.out.println("Issued Book History:");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the issued book history: " + e.getMessage());
        }
    }

    @Override
    public void update(final String bookName, final String author) {
        System.out.println("Book added to wish list: " + bookName + " by " + author);
    }
}
