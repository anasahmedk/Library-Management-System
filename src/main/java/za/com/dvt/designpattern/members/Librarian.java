package za.com.dvt.designpattern.members;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Librarian {

    private static final String USER_INFORMATION_FILE_PATH = "src/main/java/resources/user_information.txt";
    private final String id;

    public Librarian(final String id) {
        this.id = id;
    }

    public static void addLibrarian(final String id, final String password, final String name, final String title, final String surname, final String address) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_INFORMATION_FILE_PATH, true))) {
            bw.write("LIBRARIAN," + id + "," + password + "," + name + "," + title + "," + surname + "," + address + "\n");
            bw.newLine();
            System.out.println("Librarian added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the librarian: " + e.getMessage());
        }
    }

    public void viewPersonalInformation() {
        PersonalInformationUtils.viewPersonalInformation(String.valueOf(id));
    }

}
