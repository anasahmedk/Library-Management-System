package za.com.dvt.designpattern.books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookProxy {

    private final String ISBN;
    private Books realBook;

    public BookProxy(final String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        loadBookIfNeeded();
        return realBook.getName();
    }

    public String getAuthor() {
        loadBookIfNeeded();
        return realBook.getAuthor();
    }

    public String getISBN() {
        return ISBN;
    }

    private void loadBookIfNeeded() {
        if (realBook == null) {
            realBook = loadBookFromFile(ISBN);
        }
    }

    private Books loadBookFromFile(final String ISBN) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentISBN = parts[2].trim();
                if (currentISBN.equals(ISBN)) {
                    return new Books(parts[0].trim(), parts[1].trim(), currentISBN);
                }
            }
            System.out.println("Book with ISBN " + ISBN + " not found.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return null;
    }
}
