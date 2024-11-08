import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvertFrame extends JFrame {
    private JTextField currencyInputField, convertedCurrencyField;
    private JButton convertButton, clearButton, exitButton;
    private JRadioButton fromDollarButton, fromPesoButton, fromEuroButton;
    private JRadioButton toDollarButton, toPesoButton, toEuroButton;
    private String fromCurrency = "USD"; // Default "Convert from" currency
    private String toCurrency = "Peso";  // Default "Convert to" currency

    public ConvertFrame() {
        setLayout(new GridLayout(6, 1));

        // Menu bar setup
        JMenu fileMenu = new JMenu("File"); // create file menu
        fileMenu.setMnemonic('F'); // set mnemonic to F

        // Create About menu item
        JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic('A'); // set mnemonic to A
        fileMenu.add(aboutItem); // add about item to file menu
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(ConvertFrame.this,
                        "Currency Conversion Program " +
                                "\n using menus and buttons  " +
                                "\n source:https://www.oanda.com/currency-converter/.",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Create Convert menu item
        JMenuItem convertItem = new JMenuItem("Convert");
        convertItem.setMnemonic('C'); // set mnemonic to C
        fileMenu.add(convertItem); // add convert item to file menu
        convertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                convertCurrency();
            }
        });

        // Create Clear menu item
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setMnemonic('L'); // set mnemonic to L
        fileMenu.add(clearItem); // add clear item to file menu
        clearItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                clearFields();
            }
        });

        // Create Exit menu item
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x'); // set mnemonic to x
        fileMenu.add(exitItem); // add exit item to file menu
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int option = JOptionPane.showConfirmDialog(ConvertFrame.this, "Are you sure you want to quit?",
                        "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // exit application
                }
            }
        });

        JMenuBar menuBar = new JMenuBar(); // create menu bar
        setJMenuBar(menuBar); // add menu bar to the application
        menuBar.add(fileMenu); // add file menu to menu bar

        // Panel for "Convert from" section
        JPanel fromCurrencyPanel = new JPanel(new FlowLayout());
        fromCurrencyPanel.add(new JLabel("Convert from:"));

        // Load and add images for "Convert from" section
        JLabel fromDollarIconLabel = new JLabel(new ImageIcon(getClass().getResource("dollar.jpg")));
        JLabel fromPesoIconLabel = new JLabel(new ImageIcon(getClass().getResource("peso.jpg")));
        JLabel fromEuroIconLabel = new JLabel(new ImageIcon(getClass().getResource("euro.jpg")));

        // Create radio buttons for each "Convert from" currency
        fromDollarButton = new JRadioButton("US Dollar");
        fromPesoButton = new JRadioButton("Peso");
        fromEuroButton = new JRadioButton("Euro");

        // Group "from" buttons so only one can be selected
        ButtonGroup fromGroup = new ButtonGroup();
        fromGroup.add(fromDollarButton);
        fromGroup.add(fromPesoButton);
        fromGroup.add(fromEuroButton);
        fromDollarButton.setSelected(true);  // Default selection

        // Add action listeners for "Convert from" radio buttons
        fromDollarButton.addActionListener(e -> fromCurrency = "USD");
        fromPesoButton.addActionListener(e -> fromCurrency = "Peso");
        fromEuroButton.addActionListener(e -> fromCurrency = "Euro");

        // Add icon labels and radio buttons to the "Convert from" panel
        fromCurrencyPanel.add(fromDollarIconLabel);
        fromCurrencyPanel.add(fromDollarButton);
        fromCurrencyPanel.add(fromPesoIconLabel);
        fromCurrencyPanel.add(fromPesoButton);
        fromCurrencyPanel.add(fromEuroIconLabel);
        fromCurrencyPanel.add(fromEuroButton);

        // Panel for input amount
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Currency:"));
        currencyInputField = new JTextField("0.0", 10);  // Default text
        inputPanel.add(currencyInputField);

        // Panel for "Convert to" section
        JPanel toCurrencyPanel = new JPanel(new FlowLayout());
        toCurrencyPanel.add(new JLabel("Convert to:"));

        // Load and add images for "Convert to" section
        JLabel toDollarIconLabel = new JLabel(new ImageIcon(getClass().getResource("dollar.jpg")));
        JLabel toPesoIconLabel = new JLabel(new ImageIcon(getClass().getResource("peso.jpg")));
        JLabel toEuroIconLabel = new JLabel(new ImageIcon(getClass().getResource("euro.jpg")));

        // Create radio buttons for each "Convert to" currency
        toDollarButton = new JRadioButton("US Dollar");
        toPesoButton = new JRadioButton("Peso");
        toEuroButton = new JRadioButton("Euro");

        // Group "to" buttons
        ButtonGroup toGroup = new ButtonGroup();
        toGroup.add(toDollarButton);
        toGroup.add(toPesoButton);
        toGroup.add(toEuroButton);
        toPesoButton.setSelected(true);  // Default selection

        // Add action listeners for "Convert to" radio buttons
        toDollarButton.addActionListener(e -> toCurrency = "USD");
        toPesoButton.addActionListener(e -> toCurrency = "Peso");
        toEuroButton.addActionListener(e -> toCurrency = "Euro");

        // Add icon labels and radio buttons to the "Convert to" panel
        toCurrencyPanel.add(toDollarIconLabel);
        toCurrencyPanel.add(toDollarButton);
        toCurrencyPanel.add(toPesoIconLabel);
        toCurrencyPanel.add(toPesoButton);
        toCurrencyPanel.add(toEuroIconLabel);
        toCurrencyPanel.add(toEuroButton);

        // Panel for converted currency output
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputPanel.add(new JLabel("Converted Currency:"));
        convertedCurrencyField = new JTextField(10);
        convertedCurrencyField.setEditable(false);
        outputPanel.add(convertedCurrencyField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        // Add action listeners for buttons
        convertButton.addActionListener(e -> convertCurrency());
        clearButton.addActionListener(e -> clearFields());
        exitButton.addActionListener(e -> System.exit(0));

        // Add all panels to the frame
        add(fromCurrencyPanel);
        add(inputPanel);
        add(toCurrencyPanel);
        add(outputPanel);
        add(buttonPanel);

        // Frame settings
        setTitle("Currency Conversion");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(currencyInputField.getText());
            double convertedAmount = calculateConversion(amount, fromCurrency, toCurrency);
            convertedCurrencyField.setText(String.format("%.2f", convertedAmount));
            convertedCurrencyField.setBackground(Color.GREEN);  // Highlight in green after conversion

            // Show a dialog box with the result
            JOptionPane.showMessageDialog(this,
                    String.format("Converted %.2f %s to %.2f %s", amount, fromCurrency, convertedAmount, toCurrency),
                    "Conversion Result",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        currencyInputField.setText("");
        convertedCurrencyField.setText("");
        convertedCurrencyField.setBackground(Color.WHITE);  // Reset background color
    }

    private double calculateConversion(double amount, String from, String to) {
        // Simple conversion logic for example, conversion rates from OANDA
        if (from.equals("USD") && to.equals("Euro")) {
            return amount * 0.92;
        } else if (from.equals("USD") && to.equals("Peso")) {
            return amount * 19.98;
        } else if (from.equals("Euro") && to.equals("USD")) {
            return amount * 1.07;
        } else if (from.equals("Euro") && to.equals("Peso")) {
            return amount * 21.52;
        } else if (from.equals("Peso") && to.equals("USD")) {
            return amount * 0.05;
        } else if (from.equals("Peso") && to.equals("Euro")) {
            return amount * 0.046;
        }
        return amount; // Same currency case
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConvertFrame().setVisible(true));
    }
}
