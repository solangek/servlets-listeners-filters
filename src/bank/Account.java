package bank;

public class Account {
    private double balance = 0;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * assuming there is one instance per account, all operations on this account
     * are protected against race conditions.
     * @param amount to withdraw
     */
    synchronized public void widthdraw(double amount) {
        this.balance -= amount;
    }
    /** assuming there is one instance per account, all operations on this account
     * are protected against race conditions.
     * @param amount the amount to deposit
     */
    synchronized public void deposit(double amount) {
        this.balance += amount;
    }
}
