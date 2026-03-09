import javax.swing.*;
import java.awt.event.*;

public class ATMUI extends JFrame implements ActionListener {

    BankAccount account;
    JTextField amountField;
    JLabel message;

    JButton withdraw, deposit, balance, exit;

    public ATMUI(BankAccount acc) {
        account = acc;

        setTitle("ATM Machine");
        setSize(400,300);
        setLayout(null);

        JLabel l = new JLabel("Enter Amount:");
        l.setBounds(30,30,120,30);
        add(l);

        amountField = new JTextField();
        amountField.setBounds(160,30,150,30);
        add(amountField);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(30,90,120,40);
        add(withdraw);

        deposit = new JButton("Deposit");
        deposit.setBounds(200,90,120,40);
        add(deposit);

        balance = new JButton("Check Balance");
        balance.setBounds(30,150,140,40);
        add(balance);

        exit = new JButton("Exit");
        exit.setBounds(200,150,120,40);
        add(exit);

        message = new JLabel("");
        message.setBounds(30,210,300,30);
        add(message);

        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        balance.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {
            double amt = 0;
            if (!amountField.getText().isEmpty())
                amt = Double.parseDouble(amountField.getText());

            if (e.getSource() == withdraw) {
                if (account.withdraw(amt))
                    message.setText("Withdrawal Successful!");
                else
                    message.setText("Insufficient Balance!");
            }

            if (e.getSource() == deposit) {
                if (account.deposit(amt))
                    message.setText("Deposit Successful!");
                else
                    message.setText("Invalid Amount!");
            }

            if (e.getSource() == balance) {
                message.setText("Balance: ₹" + account.getBalance());
            }

            if (e.getSource() == exit) {
                System.exit(0);
            }

        } catch(Exception ex) {
            message.setText("Invalid Input!");
        }
    }
}