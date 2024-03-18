package za.com.dvt.designpattern.books;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import static za.com.dvt.designpattern.library.FileHandler.LOGGER;
import static za.com.dvt.designpattern.library.Library.BOOKS_FILE_PATH;

public class Books {

    public String name;
    public final String author;
    public String ISBN;

    public Books(String name, String author, String ISBN) {
        this.name = name;
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public static void addBook(final String name, final String author, final String ISBN) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE_PATH, true))) {
            bw.newLine();
            bw.write(name + "," + author + "," + ISBN + "\n");
            LOGGER.info("Book added successfully.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while adding the Book", e);
        }
    }

    public static void deleteBook(final String ISBN) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE_PATH)); BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/resources/deleted_books.txt"))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String currentISBN = parts[2].trim();
                    if (currentISBN.equals(ISBN)) {
                        found = true;
                    } else {
                        bw.write(line);
                        bw.newLine();
                    }
                } else {
                    LOGGER.warning("Invalid line format: " + line);
                }
            }
            if (!found) {
                LOGGER.info("Book with ISBN " + ISBN + " not found.");
            } else {
                File booksFile = new File(BOOKS_FILE_PATH);
                File tempFile = new File("src/main/java/resources/deleted_books.txt");
                if (booksFile.delete()) {
                    if (!tempFile.renameTo(booksFile)) {
                        LOGGER.severe("Failed to rename temp file.");
                    }
                    LOGGER.info("Book with ISBN " + ISBN + " deleted successfully.");
                } else {
                    LOGGER.severe("Failed to delete book file.");
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
        }
    }
}