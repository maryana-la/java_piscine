import java.util.UUID;

enum transferStatus {
    DEBIT, CREDIT
}

public class Transaction {

    private String identifier;
    private User recipient;
    private User sender;
    private transferStatus transferCategory ;
    private long transferAmount;

    Transaction(User recipient, User sender, String transferCategory, long transferAmount) {
        generateIdentifier();
        setRecipient(recipient);
        setSender(sender);
        setTransferCategory(transferCategory);
        setTransferAmount(transferAmount);
//
//        if (sender.getBalance() - this.transferAmount < 0) {
//            System.out.println("Not enough money for transfer");
//        } else {
//            sender.setBalance(sender.getBalance() - this.transferAmount);
//            recipient.setBalance(recipient.getBalance() + this.transferAmount);
//        }
    }


    /* GETTERS - SETTERS */

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(String transferCategory) {
        if (transferCategory.equals(transferStatus.DEBIT.name())) {
            this.transferCategory = transferStatus.DEBIT;
        } else if (transferCategory.equals(transferStatus.CREDIT.name())) {
            this.transferCategory = transferStatus.CREDIT;
        } else {
            System.out.println("Available transfer categories: DEBIT, CREDIT");
        }
    }

    public void setTransferAmount(long transferAmount) {
        if (this.transferCategory.equals(transferStatus.DEBIT) && transferAmount > 0) {
            System.out.println("Amount should be negative for DEBIT!");
            this.transferAmount = 0;
        } else if (this.transferCategory.equals(transferStatus.CREDIT) && transferAmount < 0) {
            System.out.println("Amount should be positive for CREDIT!");
            this.transferAmount = 0;
        } else {
            this.transferAmount = transferAmount;
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public String getTransferCategory() {
        return transferCategory.name();
    }

    public long getTransferAmount() {
        return transferAmount;
    }

    private void generateIdentifier() {
        UUID id = UUID.randomUUID();
        this.identifier = id.toString();
    }

}
