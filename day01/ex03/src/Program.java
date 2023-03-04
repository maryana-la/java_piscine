public class Program {
    private static final int USER_MAXIMUM = 10;

    public static void main(String[] args) throws UserNotFoundException, ArrayIndexOutOfBoundsException {
        User sender = new User("JimTheRich");
        User recipient = new User("TomThePoor", 500);
        UsersArrayList array = new UsersArrayList();
        array.addUser(sender);
        array.addUser(recipient);


        User[] arr = new User[USER_MAXIMUM];
        for (int i = 0; i < USER_MAXIMUM; i++) {
            arr[i] = new User("Person" + i);
            array.addUser(arr[i]);
        }

        for (int i = 0; i < array.numberOfUsers(); i++) {
            printUserStatus(array.findUserByIndex(i));
        }

        System.out.println("Array size: " + array.getArraySize());
        System.out.println("Array capacity: " + array.numberOfUsers());


        try {
            System.out.println("User id 3: " + array.findUserById(3).getName());
            System.out.println("User id 30: " + array.findUserById(30).getName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println("User index 1: " + array.findUserByIndex(1).getName());
            System.out.println("User index -2: " + array.findUserByIndex(-2).getName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void printUserStatus(User user1) {
        System.out.println(user1.getName() + " " + user1.getBalance() + " " + user1.getIdentifier());
    }
}
