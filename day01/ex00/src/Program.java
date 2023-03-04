
public class Program {
    public static void main(String[] args) {
        User sender = new User("JimTheRich");
        User recipient = new User("TomThePoor", 500);

        printUserStatus(sender, recipient);
        sender.setBalance(15000);
        
        Transaction tr1 = new Transaction(recipient, sender, "DEBIT", 200);
        printUserStatus(sender, recipient);
    }

    private static void printUserStatus(User user1, User user2) {
        System.out.println("Sender: " + user1.getName() + " " + user1.getBalance() + " " + user1.getIdentifier());
        System.out.println("Sender: " + user2.getName() + " " + user2.getBalance() + " " + user2.getIdentifier());
    }
}
