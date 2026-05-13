package tools.main.de;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private JTextField input1;
    private JTextField input2;
    private JLabel resultLabel;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 1));

        input1 = new JTextField();
        input2 = new JTextField();
        resultLabel = new JLabel("Result: ");

        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton mul = new JButton("*");
        JButton div = new JButton("/");

        add(input1);
        add(input2);

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(sub);
        buttons.add(mul);
        buttons.add(div);

        add(buttons);
        add(resultLabel);

        add.addActionListener(e -> calculate('+'));
        sub.addActionListener(e -> calculate('-'));
        mul.addActionListener(e -> calculate('*'));
        div.addActionListener(e -> calculate('/'));

        setVisible(true);
    }

    private void calculate(char op) {

        String text1 = input1.getText();
        String text2 = input2.getText();

        // CHECK EMPTY FIELDS
        if (text1.isEmpty() || text2.isEmpty()) {
            resultLabel.setText("Error: Please enter both numbers");
            return;
        }

        double x;
        double y;

        try {
            x = Double.parseDouble(text1);
            y = Double.parseDouble(text2);
        } catch (NumberFormatException e) {
            resultLabel.setText("Error: Invalid number");
            return;
        }

        double result = 0;

        switch (op) {
            case '+': result = x + y; break;
            case '-': result = x - y; break;
            case '*': result = x * y; break;
            case '/':
                if (y == 0) {
                    resultLabel.setText("Error: Division by 0 not allowed");
                    return;
                }
                result = x / y;
                break;
        }

        resultLabel.setText("Result: " + result);
    }
}