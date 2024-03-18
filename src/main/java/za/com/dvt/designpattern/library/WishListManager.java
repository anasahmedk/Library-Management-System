package za.com.dvt.designpattern.library;

public class WishListManager {

    private static FileHandler fileHandler = null;

    public WishListManager() {
        fileHandler = new FileHandler();
    }

    public static void addToWishList(String bookName, String author) {
        fileHandler.writeToWishList(bookName + "," + author);
        System.out.println("Book added to wish list: " + bookName + " by " + author);
    }

    public static void viewBooksAddedToWishList() {
        fileHandler.readFromWishList();
    }

}
