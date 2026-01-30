import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyCalculator extends JFrame implements ActionListener {

    JTextField display;
    JButton[] numbers = new JButton[10];
    JButton add, sub, mul, div, clear, equal;

    double firstNum = 0;
    String operator = "";

    MyCalculator() {
        setTitle("Calculator");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        // Number buttons
        for (int i = 0; i <= 9; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].setFont(new Font("Arial", Font.BOLD, 18));
            numbers[i].addActionListener(this);
        }

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        clear = new JButton("C");
        equal = new JButton("=");

        JButton[] ops = { add, sub, mul, div, clear, equal };
        for (JButton b : ops) {
            b.setFont(new Font("Arial", Font.BOLD, 18));
            b.addActionListener(this);
        }

        // Layout order
        panel.add(numbers[7]);
        panel.add(numbers[8]);
        panel.add(numbers[9]);
        panel.add(clear);

        panel.add(numbers[4]);
        panel.add(numbers[5]);
        panel.add(numbers[6]);
        panel.add(add);

        panel.add(numbers[1]);
        panel.add(numbers[2]);
        panel.add(numbers[3]);
        panel.add(sub);

        panel.add(numbers[0]);
        panel.add(mul);
        panel.add(div);
        panel.add(equal);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Number buttons
        for (int i = 0; i <= 9; i++) {
            if (e.getSource() == numbers[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        // Clear
        if (e.getSource() == clear) {
            display.setText("");
            firstNum = 0;
            operator = "";
            return;
        }

        // Operator buttons
        if (e.getSource() == add || e.getSource() == sub ||
            e.getSource() == mul || e.getSource() == div) {

            firstNum = Double.parseDouble(display.getText());
            operator = ((JButton) e.getSource()).getText();
            display.setText("");
            return;
        }

        // Equal
        if (e.getSource() == equal) {
            double secondNum = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+": result = firstNum + secondNum; break;
                case "-": result = firstNum - secondNum; break;
                case "*": result = firstNum * secondNum; break;
                case "/": result = secondNum != 0 ? firstNum / secondNum : 0; break;
            }

            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new MyCalculator();
    }
}
