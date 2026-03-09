public class BankAccount {

    private String username;
    private int pin;
    private double balance;

    public BankAccount(String username, int pin, double balance) {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validateLogin(String user, int enteredPin) {
        return username.equals(user) && pin == enteredPin;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;

        if (amount > balance) return false;

        balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;

        balance += amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }
}