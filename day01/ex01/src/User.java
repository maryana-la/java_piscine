import java.util.UUID;

public class User {
    private int identifier;
    private String name;
    private long balance;

    User(String name, long balance) {
        identifier = UserIdsGenerator.getInstance().generateId();
        setName(name);
        setBalance(balance);
    }

    User(String name) {
        identifier = UserIdsGenerator.getInstance().generateId();
        setName(name);
        setBalance(0);
    }

    /* GETTERS - SETTERS */

    public void setName(String name) {
        if (name == null)
            this.name = "Default";
        else
            this.name = name;
    }

    public void setBalance(long balance) {
        if (balance >= 0)
            this.balance = balance;
        else {
            System.out.println("Balance cannon be negative. Set to 0");
            this.balance = 0;
        }
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public long getBalance() {
        return this.balance;
    }

}
