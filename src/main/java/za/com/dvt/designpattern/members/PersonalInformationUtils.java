package za.com.dvt.designpattern.members;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PersonalInformationUtils {

    public static void viewPersonalInformation(final String id) {
        String filePath = "src/main/resources/user_information.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String storedId = parts[1];
                    if (id.equals(storedId)) {
                        String role = parts[0];
                        String name = parts[3];
                        String title = parts[4];
                        String surname = parts[5];
                        String address = parts[6];
                        System.out.println("Role: " + role + ", ID: " + id
                                + ", Name: " + name + ", Title: " + title
                                + ", Surname: " + surname + ", Address: " + address);
                        return;
                    }
                } else {
                    System.out.println("Invalid data format in the file.");
                }
            }
            System.out.println("User information not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading user information: " + e.getMessage());
        }
    }
}
