package za.com.dvt.designpattern.members;

public class Admin {

    private final String id;

    public Admin(final String id) {
        this.id = id;
    }

    public void viewPersonalInformation() {
        PersonalInformationUtils.viewPersonalInformation(String.valueOf(id));
    }

}
