import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class CurrencyConverter extends JFrame implements ActionListener {


    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountTextField, resultTextField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton jButton;

    private final String[] CURRENCIES = {"INR", "USD", "EUR", "GBP", "CAD", "AUD", "JPY", "CHF", "CNY", "HKD", "KRW", "MXN", "NOK", "NZD", "SEK", "SGD", "THB", "TRY", "ZAR"};

    private final double[] EXCHANGE_RATES = {1, 82.24, 89.57, 97.75, 62.45, 56.33, 0.523, 90.53, 13.52, 12.21, 0.07, 5.78, 7.35, 2.44, 8.07, 59.78, 3.09, 19.85, 5.54};


    public CurrencyConverter() {
        // Create the labels first
        amountLabel = new JLabel("Amount:");
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        resultLabel = new JLabel("Result:");

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 20, 40));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(new Color(240, 240, 240));
        Font boldFont = new Font("Tahoma", Font.BOLD, 18);
        amountLabel.setFont(boldFont);
        fromLabel.setFont(boldFont);
        toLabel.setFont(boldFont);
        resultLabel.setFont(boldFont);

        amountTextField = new JTextField();
        
     // Set a thicker border for the amountTextField
     Border thickBorder = new LineBorder(Color.BLACK, 2);
     amountTextField.setBorder(thickBorder);

     resultTextField = new JTextField();
     resultTextField.setEditable(false);

     // Set a thicker border for the resultTextField
     resultTextField.setBorder(thickBorder);

     fromComboBox = new JComboBox<>(CURRENCIES);
     toComboBox = new JComboBox<>(CURRENCIES);

     // Set a thicker border for JComboBoxes
     fromComboBox.setBorder(thickBorder);
     toComboBox.setBorder(thickBorder);

     jButton = new JButton("Convert Currency");
     jButton.addActionListener(this);

     jButton.setPreferredSize(new Dimension(jButton.getPreferredSize().width, 50));

     // Make the text of the button big and bold
     Font buttonFont = new Font("Tahoma", Font.BOLD, 24);
     jButton.setFont(buttonFont);

     // Set a thicker border for the jButton
     jButton.setBorder(thickBorder);

     panel.add(amountLabel);
     panel.add(amountTextField);
     panel.add(fromLabel);
     panel.add(fromComboBox);
     panel.add(toLabel);
     panel.add(toComboBox);
     panel.add(resultLabel);
     panel.add(resultTextField);
     add(panel, BorderLayout.CENTER);
     add(jButton, BorderLayout.SOUTH);
     

 }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton) {
            try {
                double amount = Double.parseDouble(amountTextField.getText());
                int fromIndex = fromComboBox.getSelectedIndex();
                int toIndex = toComboBox.getSelectedIndex();

                if (fromIndex == toIndex) {
                    JOptionPane.showMessageDialog(this, "Please choose different currencies.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double result = amount * EXCHANGE_RATES[fromIndex] / EXCHANGE_RATES[toIndex];
                DecimalFormat df = new DecimalFormat("#.##");
                resultTextField.setText(df.format(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter currencyConverter = new CurrencyConverter();
            currencyConverter.setTitle("Currency Converter App");
            currencyConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            currencyConverter.setSize(500, 400);
            currencyConverter.setLocationRelativeTo(null);
            currencyConverter.setVisible(true);
        });
    }
}
