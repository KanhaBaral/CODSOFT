public class Main {

    public static void main(String[] args) {

        BankAccount user =
                new BankAccount("Sneha", 1234, 000);

        new LoginUI(user);
    }
}