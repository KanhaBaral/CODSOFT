import javax.swing.*;
import java.awt.event.*;

public class LoginUI extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField pinField;
    JLabel message;
    JButton loginBtn;

    BankAccount account;

    public LoginUI(BankAccount acc) {
        account = acc;

        setTitle("ATM Login - Aryan Institute Project");
        setSize(300,250);
        setLayout(null);

        JLabel u = new JLabel("Username:");
        u.setBounds(30,30,100,30);
        add(u);

        userField = new JTextField();
        userField.setBounds(130,30,120,30);
        add(userField);

        JLabel p = new JLabel("PIN:");
        p.setBounds(30,80,100,30);
        add(p);

        pinField = new JPasswordField();
        pinField.setBounds(130,80,120,30);
        add(pinField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(90,130,100,30);
        add(loginBtn);

        message = new JLabel("");
        message.setBounds(50,170,200,30);
        add(message);

        loginBtn.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {
            String user = userField.getText();
            int pin = Integer.parseInt(pinField.getText());

            if (account.validateLogin(user, pin)) {
                new ATMUI(account);
                dispose();
            } else {
                message.setText("Wrong Username or PIN!");
            }

        } catch(Exception ex) {
            message.setText("Invalid Input!");
        }
    }
}