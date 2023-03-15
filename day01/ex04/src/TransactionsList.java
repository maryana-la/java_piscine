import java.util.UUID;

interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransaction(UUID str);

}
