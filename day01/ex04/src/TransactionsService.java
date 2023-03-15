public class TransactionsService {
    private UsersList users;

    void addUser(User person) {
        users.addUser(person);
    }

    long retrieveBalance (User person) {
        return person.getBalance();
    }

    void performTransfer(User recipient, User sender, long amount) {

    }

}
