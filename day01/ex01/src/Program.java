
public class Program {
    private static final int USER_MAXIMUM = 10;

    public static void main(String[] args) {
        User sender = new User("JimTheRich");
        User recipient = new User("TomThePoor", 500);
        printUserStatus(sender);
        printUserStatus(recipient);
        sender.setBalance(15000);
        printUserStatus(sender);

        User[] array = new User[USER_MAXIMUM];
        for (int i = 0; i < USER_MAXIMUM; i++) {
            array[i] = new User("Person" + i);
        }

        for (int i = 0; i < USER_MAXIMUM; i++) {
            printUserStatus(array[i]);
        }
    }

    private static void printUserStatus(User user1) {
        System.out.println("Sender: " + user1.getName() + " " + user1.getBalance() + " " + user1.getIdentifier());
    }
}
