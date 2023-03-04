public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("non-existent User ID");
    }

    public UserNotFoundException(String errMessage) {
       super(errMessage);
    }

    public UserNotFoundException(int id) {
        super("non-existent User ID " + id);
    }
}
