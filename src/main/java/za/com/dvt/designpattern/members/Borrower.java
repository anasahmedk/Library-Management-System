package za.com.dvt.designpattern.members;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Borrower {

    private static final String BORROWER_INFORMATION_FILE_PATH = "src/main/java/resources/borrower_information.txt";
    private static final String USER_INFORMATION_FILE_PATH = "src/main/java/resources/user_information.txt";
    private final String id;

    public Borrower(final String id) {
        this.id = id;
    }

    public static void addBorrower(final String id, final String password, final String name, final String title, final String surname, final String address) {

        String borrowerInfoLine = "BORROWER," + id + "," + password + "," + name + "," + title + "," + surname + "," + address + "\n";

        try (BufferedWriter borrower = new BufferedWriter(new FileWriter(BORROWER_INFORMATION_FILE_PATH, true));
             BufferedWriter login = new BufferedWriter(new FileWriter(USER_INFORMATION_FILE_PATH, true))) {
            borrower.newLine();
            borrower.write(borrowerInfoLine);
            login.newLine();
            login.write(borrowerInfoLine);
            System.out.println("Borrower added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the Borrower: " + e.getMessage());
        }
    }

    public void viewPersonalInformation() {
        PersonalInformationUtils.viewPersonalInformation(String.valueOf(id));
    }
}