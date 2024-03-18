package za.com.dvt.designpattern.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.com.dvt.designpattern.members.Roles;

public class FileHandler {

    private static final String FINES_PAID_FILE_PATH = "src/main/resources/wish_list.txt";
    private static final String CHECKOUT_RECORDS_FILE_PATH = "src/main/resources/checkout_records.txt";
    public static final String BOOKS_FILE_PATH = "src/main/resources/books.txt";
    private static final String CHECKED_OUT_FILE_PATH = "src/main/resources/checked_out.txt";
    private static final String WISH_LIST_FILE_PATH = "src/main/resources/wish_list.txt";
    private static final String USER_INFO_FILE_PATH = "src/main/resources/user_information.txt";
    public static final Logger LOGGER = Logger.getLogger(FileHandler.class.getName());

    public void writeToWishList(final String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(WISH_LIST_FILE_PATH, true))) {
            bw.newLine();
            bw.write(data + "\n");
            LOGGER.info("Data written to wish list file.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to wish list file: " + e.getMessage(), e);
        }
    }

    public void readFromWishList() {
        try (BufferedReader br = new BufferedReader(new FileReader(WISH_LIST_FILE_PATH))) {
            String line;
            LOGGER.info("Wish List:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading from wish list file: " + e.getMessage(), e);
        }
    }

    public void writeCheckedOutBook(final String data) {
        try (BufferedWriter checkedOutWriter = new BufferedWriter(new FileWriter(CHECKED_OUT_FILE_PATH, true)); BufferedWriter checkoutRecordsWriter = new BufferedWriter(new FileWriter(CHECKOUT_RECORDS_FILE_PATH, true))) {
            checkedOutWriter.newLine();
            checkedOutWriter.write(data + "\n");
            checkoutRecordsWriter.newLine();
            checkoutRecordsWriter.write(data + "\n");
            LOGGER.info("Book checked out and recorded.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to files: " + e.getMessage(), e);
        }
    }

    public void updateCheckedInBook(final String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE_PATH, true)); BufferedWriter finesPaidWriter = new BufferedWriter(new FileWriter(FINES_PAID_FILE_PATH, true)); BufferedWriter checkoutRecordsWriter = new BufferedWriter(new FileWriter(CHECKOUT_RECORDS_FILE_PATH, true))) {
            bw.newLine();
            bw.write(data + "\n");
            finesPaidWriter.write(data + "\n");
            checkoutRecordsWriter.write(data + "\n");
            LOGGER.info("Book checked in and records updated.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to files: " + e.getMessage(), e);
        }
    }

    public static String getIdFromFile(final Roles role) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_INFO_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[0].trim().equalsIgnoreCase(role.name())) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while reading user credentials file", e);
        }

        LOGGER.warning(role.name() + " ID not found in file.");
        return null;
    }

    public static boolean login(final Roles role, final String id, final String password) {

        try (BufferedReader br = new BufferedReader(new FileReader(USER_INFO_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String storedRole = parts[0].trim();
                    String storedId = parts[1].trim();
                    String storedPassword = parts[2].trim();

                    if (role.name().equalsIgnoreCase(storedRole) && id.equals(storedId) && password.equals(storedPassword)) {
                        System.out.println("Logged in as: " + parts[3].trim());
                        return true;
                    }
                } else {
                    LOGGER.warning("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while reading user credentials file", e);
        }

        System.out.println("Login failed. Invalid username or password.");
        return false;
    }
}
