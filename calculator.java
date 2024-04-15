import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class calculator implements ActionListener {

    JFrame frame;
    JTextField textField;

    JButton[] numberbuttons = new JButton[10];
    JButton[] functionbuttons = new JButton[9];
    JButton addbutton, subbutton, mulbutton, divbutton;
    JButton decibutton, equbutton, delbutton, clrbutton, negbutton;
    JPanel panel;
    Font myfont = new Font("Jersey 25", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myfont);
        textField.setEditable(false);

        addbutton = new JButton("+");
        subbutton = new JButton("-");
        mulbutton = new JButton("*");
        divbutton = new JButton("/");
        decibutton = new JButton(".");
        equbutton = new JButton("=");
        delbutton = new JButton("Del");
        clrbutton = new JButton("Clr");
        negbutton = new JButton("(-)");

        functionbuttons[0] = addbutton;
        functionbuttons[1] = subbutton;
        functionbuttons[2] = mulbutton;
        functionbuttons[3] = divbutton;
        functionbuttons[4] = decibutton;
        functionbuttons[5] = equbutton;
        functionbuttons[6] = delbutton;
        functionbuttons[7] = clrbutton;
        functionbuttons[8] = negbutton;

        for (int i = 0; i<9; i++) {
            functionbuttons[i].addActionListener(this);
            functionbuttons[i].setFont(myfont);
            functionbuttons[i].setFocusable(false);

        }

        for (int i = 0; i < 10; i++) {
            numberbuttons[i] = new JButton(String.valueOf(i));
            numberbuttons[i].addActionListener(this);
            numberbuttons[i].setFont(myfont);
            numberbuttons[i].setFocusable(false);
        }
        negbutton.setBounds(50, 430, 100, 50);
        delbutton.setBounds(150, 430, 100, 50);
        clrbutton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberbuttons[1]);
        panel.add(numberbuttons[2]);
        panel.add(numberbuttons[3]);
        panel.add(addbutton);
        panel.add(numberbuttons[4]);
        panel.add(numberbuttons[5]);
        panel.add(numberbuttons[6]);
        panel.add(subbutton);
        panel.add(numberbuttons[7]);
        panel.add(numberbuttons[8]);
        panel.add(numberbuttons[9]);
        panel.add(mulbutton);
        panel.add(decibutton);
        panel.add(numberbuttons[0]);
        panel.add(equbutton);
        panel.add(divbutton);

        frame.add(panel);
        frame.add(negbutton);
        frame.add(delbutton);
        frame.add(clrbutton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        calculator calc;
        calc = new calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberbuttons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));

            }
        }

        if (e.getSource() == decibutton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addbutton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subbutton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

            if (e.getSource() == mulbutton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            if (e.getSource() == divbutton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            if (e.getSource() == equbutton) {
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            }
            if (e.getSource() == clrbutton) {
                textField.setText("");
            }
            if (e.getSource() == delbutton) {
                String string = textField.getText();
                textField.setText("");
                for (int i = 0; i < string.length() - 1; i++) {
                    textField.setText(textField.getText()+string.charAt(i));
                }
            }
            if (e.getSource() == negbutton) {
                double temp = Double.parseDouble(textField.getText());
                temp *= -1;
                textField.setText(String.valueOf(temp));
            }
    }

}
