import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * BankGUI is a class that creates a graphical user interface (GUI) for managing
 * bank cards. The GUI allows users to create and manage debit and credit cards,
 * and perform actions such as withdrawing money and setting credit limits.
 */
public class BankGUI implements ActionListener {
    private ArrayList<BankCard> BankCardArrayList;
    private JFrame mainJFrame, debitJFrame, creditJFrame, debitInnerJFrame, creditInnerJFrame;
    private JLabel titleJLabel, subTitleJLabel, titleDebitJLabel, mainTextJLabel, suggestCreationJLabel,
            suggestEnteringJLabel,
            cardIDJLabel,
            clientNameJLabel, interestRateContdJLabel, creditLimitJLabel, gracePeriodJLabel, interestRateJLabel,
            withdrawalAmountJLabel, dateOfWithdrawalJLabel, issuerBankJLabel, bankAccountJLabel, balanceAmountJLabel,
            titleCreditJLabel, pinNumberJLabel, balanceAmountContdJLabel, cvcNumberJLabel, expirationDateJLabel;
    private JButton debitJButton, creditJButton, submitDebitCardDetailsJButton, createNewDebitCardJButton,
            submitDebitCreationJButton, enterDebitCardDetailsJButton, withdrawJButton, displayDebitJButton,
            clearInnerCreditEntryJButton,
            exitToMainMenuFromInnerDebitJButton, enterCreditCardDetailsJButton, createNewCreditCardJButton,
            clearInnerDebitEntryJButton, submitCreditCreationJButton,
            submitCreditCardDetailsJButton, displayCreditJButton, exitToMainMenuFromInnerCreditJButton,
            setCreditLimitJButton, cancelCreditCardJButton, exitToMainMenuFromDebitJButton,
            exitToMainMenuFromCreditJButton, clearDebitCardEntryJButton, clearCreditCardEntryJButton,
            clearDebitCardCreationEntryJButton, clearCreditCardCreationEntryJButton;
    private JPanel titleMainJPanel, titleDebitJPanel, enterDebitCardDetailsJPanel, createDebitCardJPanel,
            withdrawalJPanel,
            displayCreditDetailsJPanel, displayDebitDetailsJPanel, setCreditLimitJPanel, enterCreditCardDetailsJPanel,
            titleCreditJPanel, createCreditCardJPanel;
    private JTextField cardIDJTextField, clientNameJTextField, issuerBankJTextField, balanceAmountJTextField,
            bankAccountJTextField, pinNumberJTextField, withdrawalAmountJTextField, cvcNumberJTextField,
            interestRateJTextField, creditLimitJTextField, gracePeriodJTextField;
    private JComboBox<Integer> dayJComboBox, yearJComboBox;
    private JComboBox<String> monthJComboBox;
    private JTextArea displayDebitDetailsJTextArea, displayCreditDetailsJTextArea;
    private Container mainPane, debitPane, debitInnerPane, creditPane, creditInnerPane;
    private DebitCard myDebitCard;
    private CreditCard myCreditCard;

    private Integer[] daysArray, yearsArray;
    private String[] monthsArray;
    private Font largeText, mediumText;

    /**
     * Constructor method for BankGUI. Initializes class variables and creates the
     * main GUI.
     */
    BankGUI() {
        largeText = new Font("Verdana", Font.BOLD, 40);
        mediumText = new Font("Verdana", Font.PLAIN, 24);
        daysArray = new Integer[31];
        for (int i = 0; i < 31; i++) {
            daysArray[i] = i + 1;
        }
        monthsArray = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        yearsArray = new Integer[200];
        for (int i = 0; i < 200; i++) {
            yearsArray[i] = i + 2000;
        }
        BankCardArrayList = new ArrayList<BankCard>();
        mainGUI();
    }

    /**
     * Main method for BankGUI. Instantiates a new BankGUI object.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new BankGUI();
    }

    /**
     * Method for creating the main GUI. Sets up the JFrame and its components.
     */
    private void mainGUI() {
        mainJFrame = new JFrame("Unified Banking System");
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPane = mainJFrame.getContentPane();
        mainPane.setBackground(Color.decode("#ED8686"));
        mainPane.setLayout(null);
        mainJFrame.setSize(800, 600);
        titleMainJPanel = new JPanel();
        titleMainJPanel.setBackground(Color.decode("#D9D9D9"));
        titleMainJPanel.setLayout(null);
        titleMainJPanel.setBounds(0, 0, 800, 234);
        mainPane.add(titleMainJPanel);
        titleJLabel = new JLabel("Unified Banking System");
        titleJLabel.setFont(largeText);
        titleJLabel.setBounds(131, 88, 537, 59);
        titleMainJPanel.add(titleJLabel);
        subTitleJLabel = new JLabel("By Kushal Dhakal");
        subTitleJLabel.setFont(mediumText);
        subTitleJLabel.setBounds(520, 147, 233, 36);
        titleMainJPanel.add(subTitleJLabel);
        mainTextJLabel = new JLabel("Which type of card do you want to use?");
        mainTextJLabel.setFont(mediumText);
        mainTextJLabel.setBounds(146, 337, 507, 37);
        mainPane.add(mainTextJLabel);
        debitJButton = new JButton("Debit Card");
        debitJButton.setFont(mediumText);
        debitJButton.setBounds(102, 427, 267, 57);
        debitJButton.addActionListener(this);
        mainPane.add(debitJButton);
        creditJButton = new JButton("Credit Card");
        creditJButton.setFont(mediumText);
        creditJButton.setBounds(430, 427, 267, 57);
        creditJButton.addActionListener(this);
        mainPane.add(creditJButton);
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.setVisible(true);
        mainJFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == debitJButton) {
            mainJFrame.dispose();
            debitCardGUI();
        }
        if (e.getSource() == creditJButton) {
            mainJFrame.dispose();
            creditJFrameGUI();
        }
        if (e.getSource() == createNewDebitCardJButton) {
            createDebitCardGUI();
        }
        if (e.getSource() == submitDebitCardDetailsJButton) {
            int indexOfDebitCardInBankCardArrayList = validateDebitCard();
            if (indexOfDebitCardInBankCardArrayList == -1) {
                JOptionPane.showMessageDialog(null, "Invalid credentials! No such debit card exists.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (indexOfDebitCardInBankCardArrayList == -2) {
                    ;
                } else {
                    debitCardInnerGUI(indexOfDebitCardInBankCardArrayList);
                }
            }
        }
        if (e.getSource() == submitCreditCardDetailsJButton) {
            int indexOfCreditCardInBankCardArrayList = validateCreditCard();
            if (indexOfCreditCardInBankCardArrayList == -1) {
                JOptionPane.showMessageDialog(null, "Invalid credentials! No such credit card exists.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (indexOfCreditCardInBankCardArrayList == -2) {
                    ;
                } else {
                    creditCardInnerGUI(indexOfCreditCardInBankCardArrayList);
                }
            }
        }
        if (e.getSource() == enterDebitCardDetailsJButton) {
            createDebitCardJPanel.setVisible(false);
            enterDebitCardDetailsJPanel.setVisible(true);
        }
        if (e.getSource() == enterCreditCardDetailsJButton) {
            createCreditCardJPanel.setVisible(false);
            enterCreditCardDetailsJPanel.setVisible(true);
        }
        if (e.getSource() == submitDebitCreationJButton) {
            createDebitCard();
        }
        if (e.getSource() == submitCreditCreationJButton) {
            createCreditCard();
        }
        if (e.getSource() == withdrawJButton) {
            withdrawGUI();
        }
        if (e.getSource() == displayDebitJButton) {
            String stringToDisplayDebitCard = "Card ID: " + String.valueOf(myDebitCard.getCardID()) + "\n"
                    + "Issuer Bank: " + myDebitCard.getIssuerBank() + "\n"
                    + "Bank Account: " + myDebitCard.getBankAccount() + "\n"
                    + "Balance Amount: " + String.valueOf(myDebitCard.getBalanceAmount());
            if (myDebitCard.getHasWithdrawn()) {
                stringToDisplayDebitCard += "\nWithdrawal amount: " + String.valueOf(myDebitCard.getWithdrawalAmount())
                        + "\n"
                        + "Date of withdrawal:\n" + myDebitCard.getDateOfWithdrawal() + "\n"
                        + "Pin number: " + myDebitCard.getPinNumber();
            }
            displayDebitDetailsJTextArea.setText(stringToDisplayDebitCard);
            myDebitCard.displayDetails();
        }
        if (e.getSource() == displayCreditJButton) {
            String stringToDisplayCreditCard = "Card ID: " + String.valueOf(myCreditCard.getCardID()) + "\n"
                    + "Issuer Bank: " + myCreditCard.getIssuerBank() + "\n"
                    + "Bank Account: " + myCreditCard.getBankAccount() + "\n"
                    + "Balance Amount: " + String.valueOf(myCreditCard.getBalanceAmount()) + "\n"
                    + "CVC Number: " + String.valueOf(myCreditCard.getCvcNumber()) + "\n"
                    + "Interest rate: " + String.valueOf(myCreditCard.getInterestRate()) + "\n"
                    + "Expiration date:" + myCreditCard.getExpirationDate();
            if (myCreditCard.getIsGranted()) {
                stringToDisplayCreditCard += "\nCredit limit: " + String.valueOf(myCreditCard.getCreditLimit()) + "\n"
                        + "Grace period: " + String.valueOf(myCreditCard.getGracePeriod());
            }
            displayCreditDetailsJTextArea.setText(stringToDisplayCreditCard);
            myCreditCard.displayDetails();
        }
        if (e.getSource() == exitToMainMenuFromInnerDebitJButton) {
            debitInnerJFrame.dispose();
            mainGUI();
        }
        if (e.getSource() == exitToMainMenuFromInnerCreditJButton) {
            creditInnerJFrame.dispose();
            mainGUI();
        }

        if (e.getSource() == createNewCreditCardJButton) {
            createCreditCardGUI();
        }
        if (e.getSource() == cancelCreditCardJButton) {
            myCreditCard.cancelCreditCard();
            JOptionPane.showMessageDialog(null,
                    "Successfully cancelled credit card having card ID " + String.valueOf(myCreditCard.getCardID()),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == setCreditLimitJButton) {
            setCreditLimitGUI();
        }
        if (e.getSource() == exitToMainMenuFromDebitJButton) {
            debitJFrame.dispose();
            mainGUI();
        }
        if (e.getSource() == exitToMainMenuFromCreditJButton) {
            creditJFrame.dispose();
            mainGUI();
        }
        if (e.getSource() == clearCreditCardCreationEntryJButton) {
            cardIDJTextField.setText("");
            clientNameJTextField.setText("");
            issuerBankJTextField.setText("");
            balanceAmountJTextField.setText("");
            bankAccountJTextField.setText("");
            cvcNumberJTextField.setText("");
            interestRateJTextField.setText("");
        }
        if (e.getSource() == clearDebitCardEntryJButton) {
            cardIDJTextField.setText("");
            clientNameJTextField.setText("");
            issuerBankJTextField.setText("");
        }
        if (e.getSource() == clearCreditCardEntryJButton) {
            cardIDJTextField.setText("");
            clientNameJTextField.setText("");
            issuerBankJTextField.setText("");
        }
        if (e.getSource() == clearDebitCardCreationEntryJButton) {
            cardIDJTextField.setText("");
            clientNameJTextField.setText("");
            issuerBankJTextField.setText("");
            balanceAmountJTextField.setText("");
            bankAccountJTextField.setText("");
            pinNumberJTextField.setText("");
        }
        if (e.getSource() == clearInnerCreditEntryJButton) {
            gracePeriodJTextField.setText("");
            creditLimitJTextField.setText("");
            displayCreditDetailsJTextArea.setText("");
        }
        if (e.getSource() == clearInnerDebitEntryJButton) {
            withdrawalAmountJTextField.setText("");
            pinNumberJTextField.setText("");
            displayDebitDetailsJTextArea.setText("");
        }
    }

    private void setCreditLimitGUI() {
        try {
            double creditLimit = Double.parseDouble(creditLimitJTextField.getText());
            if (creditLimit <= 0) {
                throw new IllegalArgumentException("Credit limit must be a positive value.");
            }
            int gracePeriod = Integer.parseInt(gracePeriodJTextField.getText());
            if (gracePeriod < 0) {
                throw new IllegalArgumentException("Grace period must be a non-negative integer.");
            }
            myCreditCard.setCreditLimit(creditLimit, gracePeriod);
            if (!myCreditCard.getIsGranted()) {
                JOptionPane.showMessageDialog(null, "The credit cannot be issued", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane
                        .showMessageDialog(
                                null, "Successfully set grace period " + String.valueOf(gracePeriod)
                                        + " and credit limit" + String.valueOf(creditLimit),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Credit limit and grace period must be valid numbers.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid input: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void creditCardInnerGUI(int indexOfCreditCardInBankCardArrayList) {
        creditJFrame.dispose();
        myCreditCard = (CreditCard) BankCardArrayList.get(indexOfCreditCardInBankCardArrayList);
        creditInnerJFrame = new JFrame("Credit Card - " + myCreditCard.getClientName());
        creditInnerPane = creditInnerJFrame.getContentPane();
        creditInnerPane.setLayout(null);
        creditInnerPane.setBackground(Color.decode("#ED8686"));
        titleCreditJPanel = new JPanel();
        titleCreditJLabel = new JLabel("Credit Card");
        titleCreditJLabel.setFont(largeText);
        titleCreditJLabel.setBounds(55, 32, 260, 53);
        titleCreditJPanel.add(titleCreditJLabel);
        titleCreditJPanel.setLayout(null);
        titleCreditJPanel.setBackground(Color.decode("#D9D9D9"));
        titleCreditJPanel.setBounds(0, 0, 800, 117);
        creditInnerPane.add(titleCreditJPanel);
        setCreditLimitJPanel = new JPanel();
        creditLimitJLabel = new JLabel("Credit Limit");
        creditLimitJLabel.setFont(mediumText);
        creditLimitJLabel.setBounds(48, 8, 255, 40);
        setCreditLimitJPanel.add(creditLimitJLabel);
        creditLimitJTextField = new JTextField();
        creditLimitJTextField.setFont(mediumText);
        creditLimitJTextField.setBounds(48, 51, 261, 40);
        setCreditLimitJPanel.add(creditLimitJTextField);
        gracePeriodJLabel = new JLabel("Grace Period");
        gracePeriodJLabel.setFont(mediumText);
        gracePeriodJLabel.setBounds(48, 99, 182, 40);
        setCreditLimitJPanel.add(gracePeriodJLabel);
        gracePeriodJTextField = new JTextField();
        gracePeriodJTextField.setFont(mediumText);
        gracePeriodJTextField.setBounds(48, 139, 261, 40);
        setCreditLimitJPanel.add(gracePeriodJTextField);
        setCreditLimitJButton = new JButton("Set credit limit");
        setCreditLimitJButton.setFont(mediumText);
        setCreditLimitJButton.setBounds(48, 196, 261, 57);
        setCreditLimitJButton.addActionListener(this);
        setCreditLimitJPanel.add(setCreditLimitJButton);
        setCreditLimitJPanel.setBackground(Color.decode("#A87474"));
        setCreditLimitJPanel.setLayout(null);
        setCreditLimitJPanel.setBounds(29, 187, 375, 272);
        creditInnerPane.add(setCreditLimitJPanel);
        cancelCreditCardJButton = new JButton("Cancel Credit Card");
        cancelCreditCardJButton.setFont(mediumText);
        cancelCreditCardJButton.setBounds(55, 487, 313, 57);
        cancelCreditCardJButton.addActionListener(this);
        creditInnerPane.add(cancelCreditCardJButton);
        displayCreditDetailsJPanel = new JPanel();
        displayCreditDetailsJPanel.setLayout(null);
        displayCreditDetailsJPanel.setBounds(424, 187, 348, 368);
        displayCreditDetailsJPanel.setBackground(Color.decode("#A87474"));
        displayCreditJButton = new JButton("Display Details");
        displayCreditJButton.setFont(mediumText);
        displayCreditJButton.setBounds(47, 10, 253, 57);
        displayCreditJButton.addActionListener(this);
        displayCreditDetailsJPanel.add(displayCreditJButton);
        displayCreditDetailsJTextArea = new JTextArea(10, 50);
        displayCreditDetailsJTextArea.setFont(new Font("Verdana", Font.PLAIN, 18));
        displayCreditDetailsJTextArea.setBounds(17, 100, 314, 252);
        displayCreditDetailsJPanel.add(displayCreditDetailsJTextArea);
        creditInnerPane.add(displayCreditDetailsJPanel);
        exitToMainMenuFromInnerCreditJButton = new JButton("Exit to Main Menu");
        exitToMainMenuFromInnerCreditJButton.setFont(mediumText);
        exitToMainMenuFromInnerCreditJButton.setBounds(25, 122, 267, 57);
        exitToMainMenuFromInnerCreditJButton.addActionListener(this);
        creditInnerPane.add(exitToMainMenuFromInnerCreditJButton);
        clearInnerCreditEntryJButton = new JButton("Clear All");
        clearInnerCreditEntryJButton.addActionListener(this);
        clearInnerCreditEntryJButton.setBounds(608, 122, 163, 57);
        clearInnerCreditEntryJButton.setFont(mediumText);
        creditInnerPane.add(clearInnerCreditEntryJButton);
        creditInnerJFrame.setSize(800, 600);
        creditInnerJFrame.setLocationRelativeTo(null);
        creditInnerJFrame.setVisible(true);
    }

    private int validateCreditCard() {
        try {
            int cardID = Integer.parseInt(cardIDJTextField.getText());
            String clientName = clientNameJTextField.getText();
            String issuerBank = issuerBankJTextField.getText();
            for (BankCard element : BankCardArrayList) {
                if (element instanceof CreditCard && cardID == element.getCardID()
                        && clientName.equals(element.getClientName()) && issuerBank.equals(element.getIssuerBank())) {
                    return BankCardArrayList.indexOf(element);
                }
            }
            return -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. The cardID must be a valid integer.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return -2;
        }
    }

    private void createCreditCard() {
        try {
            String cardIDText = cardIDJTextField.getText();
            String clientName = clientNameJTextField.getText();
            String issuerBank = issuerBankJTextField.getText();
            String balanceAmountText = balanceAmountJTextField.getText();
            String bankAccount = bankAccountJTextField.getText();
            String cvcNumberText = cvcNumberJTextField.getText();
            String interestRateText = interestRateJTextField.getText();
            String expirationDate = String.valueOf(yearJComboBox.getSelectedItem()) + monthJComboBox.getSelectedItem()
                    + String.valueOf(dayJComboBox.getSelectedItem());

            // Check for empty fields
            if (cardIDText.isEmpty() || clientName.isEmpty() || issuerBank.isEmpty() || balanceAmountText.isEmpty()
                    || bankAccount.isEmpty() || cvcNumberText.isEmpty() || interestRateText.isEmpty()) {
                throw new NumberFormatException("One or more fields are empty.");
            }

            // Check for alphabetic client name and issuer bank
            if (!clientName.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Client name must contain only alphabetic characters and spaces.");
            }
            if (!issuerBank.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Issuer bank must contain only alphabetic characters and spaces.");
            }

            // Parse integer and double values
            int cardID = Integer.parseInt(cardIDText);
            int balanceAmount = Integer.parseInt(balanceAmountText);
            int cvcNumber = Integer.parseInt(cvcNumberText);
            double interestRate = Double.parseDouble(interestRateText);

            // Check for out-of-range values
            if (cardID == 0 || balanceAmount == 0 || cvcNumber == 0 || interestRate <= 0) {
                throw new IllegalArgumentException(
                        "Card ID, balance amount, CVC number, and interest rate must be non-zero positive values.");
            }
            if (balanceAmount < 0) {
                throw new IllegalArgumentException("Balance amount must be a positive integer.");
            }
            if (cvcNumber < 0) {
                throw new IllegalArgumentException("CVC number must be a non-negative integer.");
            }

            // Create new credit card and add to list
            BankCardArrayList.add(new CreditCard(cardID, clientName, issuerBank, bankAccount, cvcNumber, interestRate,
                    expirationDate, balanceAmount));
            JOptionPane.showMessageDialog(null, "Successfully created a new credit card", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            createCreditCardJPanel.setVisible(false);
            enterCreditCardDetailsJPanel.setVisible(true);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void createCreditCardGUI() {
        enterCreditCardDetailsJPanel.setVisible(false);
        createCreditCardJPanel = new JPanel();
        createCreditCardJPanel.setBounds(0, 0, 800, 600);
        createCreditCardJPanel.setBackground(Color.decode("#ED8686"));
        cardIDJLabel = new JLabel("Card ID:");
        cardIDJLabel.setFont(mediumText);
        cardIDJLabel.setBounds(18, 200, 105, 40);
        createCreditCardJPanel.add(cardIDJLabel);
        cardIDJTextField = new JTextField();
        cardIDJTextField.setFont(mediumText);
        cardIDJTextField.setBounds(144, 200, 128, 40);
        createCreditCardJPanel.add(cardIDJTextField);
        clientNameJLabel = new JLabel("Client Name:");
        clientNameJLabel.setFont(mediumText);
        clientNameJLabel.setBounds(286, 200, 170, 40);
        createCreditCardJPanel.add(clientNameJLabel);
        clientNameJTextField = new JTextField();
        createCreditCardJPanel.add(clientNameJTextField);
        clientNameJTextField.setFont(mediumText);
        clientNameJTextField.setBounds(490, 200, 267, 40);
        issuerBankJLabel = new JLabel("Issuer Bank:");
        issuerBankJLabel.setFont(mediumText);
        issuerBankJLabel.setBounds(286, 255, 160, 40);
        createCreditCardJPanel.add(issuerBankJLabel);
        issuerBankJTextField = new JTextField();
        issuerBankJTextField.setFont(mediumText);
        issuerBankJTextField.setBounds(490, 255, 267, 40);
        createCreditCardJPanel.add(issuerBankJTextField);
        balanceAmountJLabel = new JLabel("Balance");
        balanceAmountJLabel.setFont(mediumText);
        balanceAmountJLabel.setBounds(18, 240, 113, 28);
        balanceAmountContdJLabel = new JLabel("Amount:");
        createCreditCardJPanel.add(balanceAmountContdJLabel);
        balanceAmountContdJLabel.setFont(mediumText);
        balanceAmountContdJLabel.setBounds(18, 268, 113, 28);
        createCreditCardJPanel.add(balanceAmountJLabel);
        balanceAmountJTextField = new JTextField();
        balanceAmountJTextField.setFont(mediumText);
        balanceAmountJTextField.setBounds(144, 252, 128, 40);
        createCreditCardJPanel.add(balanceAmountJTextField);
        bankAccountJLabel = new JLabel("Bank Account:");
        bankAccountJLabel.setFont(mediumText);
        bankAccountJLabel.setBounds(286, 307, 177, 40);
        createCreditCardJPanel.add(bankAccountJLabel);
        bankAccountJTextField = new JTextField();
        bankAccountJTextField.setFont(mediumText);
        bankAccountJTextField.setBounds(490, 307, 267, 40);
        createCreditCardJPanel.add(bankAccountJTextField);
        cvcNumberJLabel = new JLabel("CVC No:");
        cvcNumberJLabel.setFont(mediumText);
        cvcNumberJLabel.setBounds(18, 306, 151, 40);
        createCreditCardJPanel.add(cvcNumberJLabel);
        cvcNumberJTextField = new JTextField();
        cvcNumberJTextField.setFont(mediumText);
        cvcNumberJTextField.setBounds(144, 306, 128, 40);
        createCreditCardJPanel.add(cvcNumberJTextField);
        interestRateJLabel = new JLabel("Interest");
        interestRateJLabel.setFont(mediumText);
        interestRateJLabel.setBounds(18, 350, 113, 28);
        createCreditCardJPanel.add(interestRateJLabel);
        interestRateContdJLabel = new JLabel("Rate:");
        createCreditCardJPanel.add(interestRateContdJLabel);
        interestRateContdJLabel.setFont(mediumText);
        interestRateContdJLabel.setBounds(18, 378, 113, 28);
        interestRateJTextField = new JTextField();
        interestRateJTextField.setFont(mediumText);
        interestRateJTextField.setBounds(144, 359, 128, 40);
        createCreditCardJPanel.add(interestRateJTextField);
        expirationDateJLabel = new JLabel("Expiration Date:");
        expirationDateJLabel.setFont(mediumText);
        expirationDateJLabel.setBounds(286, 359, 198, 40);
        createCreditCardJPanel.add(expirationDateJLabel);
        dayJComboBox = new JComboBox<>(daysArray);
        dayJComboBox.setFont(mediumText);
        dayJComboBox.setBounds(490, 359, 58, 40);
        createCreditCardJPanel.add(dayJComboBox);
        monthJComboBox = new JComboBox<>(monthsArray);
        monthJComboBox.setFont(mediumText);
        monthJComboBox.setBounds(548, 359, 145, 40);
        createCreditCardJPanel.add(monthJComboBox);
        yearJComboBox = new JComboBox<>(yearsArray);
        yearJComboBox.setFont(mediumText);
        yearJComboBox.setBounds(692, 359, 90, 40);
        createCreditCardJPanel.add(yearJComboBox);
        submitCreditCreationJButton = new JButton("Submit");
        submitCreditCreationJButton.setFont(mediumText);
        submitCreditCreationJButton.setBounds(177, 426, 437, 57);
        submitCreditCreationJButton.addActionListener(this);
        createCreditCardJPanel.add(submitCreditCreationJButton);
        suggestEnteringJLabel = new JLabel("Already have a credit card?");
        suggestEnteringJLabel.setFont(mediumText);
        suggestEnteringJLabel.setBounds(140, 514, 332, 37);
        createCreditCardJPanel.add(suggestEnteringJLabel);
        enterCreditCardDetailsJButton = new JButton("Enter details");
        enterCreditCardDetailsJButton.setFont(mediumText);
        enterCreditCardDetailsJButton.setBounds(490, 500, 267, 57);
        enterCreditCardDetailsJButton.addActionListener(this);
        createCreditCardJPanel.add(enterCreditCardDetailsJButton);
        exitToMainMenuFromCreditJButton = new JButton("Exit to Main Menu");
        exitToMainMenuFromCreditJButton.setFont(mediumText);
        exitToMainMenuFromCreditJButton.setBounds(25, 130, 267, 57);
        exitToMainMenuFromCreditJButton.addActionListener(this);
        createCreditCardJPanel.add(exitToMainMenuFromCreditJButton);
        clearCreditCardCreationEntryJButton = new JButton("Clear All");
        clearCreditCardCreationEntryJButton.setFont(mediumText);
        clearCreditCardCreationEntryJButton.addActionListener(this);
        clearCreditCardCreationEntryJButton.setBounds(608, 130, 163, 57);
        createCreditCardJPanel.add(clearCreditCardCreationEntryJButton);
        createCreditCardJPanel.setLayout(null);
        creditPane.add(createCreditCardJPanel);
    }

    private void withdrawGUI() {
        try {
            int withdrawalAmount = Integer.parseInt(withdrawalAmountJTextField.getText());
            int pinNumber = Integer.parseInt(pinNumberJTextField.getText());
            String dateOfWithdrawal = String.valueOf(yearJComboBox.getSelectedItem()) + "-"
                    + monthJComboBox.getSelectedItem() + "-"
                    + String.valueOf(dayJComboBox.getSelectedItem());

            if (withdrawalAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Withdrawal amount cannot be 0 or less.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (pinNumber == myDebitCard.getPinNumber()) {
                if (withdrawalAmount <= myDebitCard.getBalanceAmount()) {
                    myDebitCard.withdraw(withdrawalAmount, dateOfWithdrawal, pinNumber);
                    JOptionPane.showMessageDialog(null, "Withdrawn Rs. " + String.valueOf(withdrawalAmount)
                            + " from card " + String.valueOf(myDebitCard.getCardID()) + " having PIN Number "
                            + String.valueOf(pinNumber) + " on " + dateOfWithdrawal + ". The remaining balance is Rs. "
                            + String.valueOf(myDebitCard.getBalanceAmount()), "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid PIN Number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid integers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void debitCardInnerGUI(int indexOfDebitCardInBankCardArrayList) {
        debitJFrame.dispose();
        myDebitCard = (DebitCard) BankCardArrayList.get(indexOfDebitCardInBankCardArrayList);
        debitInnerJFrame = new JFrame("Debit Card - " + myDebitCard.getClientName());
        debitInnerPane = debitInnerJFrame.getContentPane();
        debitInnerPane.setBackground(Color.decode("#ED8686"));
        titleDebitJPanel = new JPanel();
        titleDebitJLabel = new JLabel("Debit Card");
        titleDebitJLabel.setFont(largeText);
        titleDebitJLabel.setBounds(55, 32, 244, 53);
        titleDebitJPanel.add(titleDebitJLabel);
        titleDebitJPanel.setLayout(null);
        titleDebitJPanel.setBackground(Color.decode("#D9D9D9"));
        titleDebitJPanel.setBounds(0, 0, 800, 117);
        debitInnerPane.add(titleDebitJPanel);
        withdrawalJPanel = new JPanel();
        withdrawalJPanel.setLayout(null);
        withdrawalJPanel.setBounds(29, 187, 375, 368);
        withdrawalJPanel.setBackground(Color.decode("#A87474"));
        withdrawalAmountJLabel = new JLabel("Withdrawal Amount:");
        withdrawalAmountJLabel.setFont(mediumText);
        withdrawalAmountJLabel.setBounds(53, 10, 255, 40);
        withdrawalJPanel.add(withdrawalAmountJLabel);
        withdrawalAmountJTextField = new JTextField();
        withdrawalAmountJTextField.setFont(mediumText);
        withdrawalAmountJTextField.setBounds(53, 54, 267, 40);
        withdrawalJPanel.add(withdrawalAmountJTextField);
        dateOfWithdrawalJLabel = new JLabel("Date of withdrawal:");
        dateOfWithdrawalJLabel.setFont(mediumText);
        dateOfWithdrawalJLabel.setBounds(53, 108, 239, 40);
        withdrawalJPanel.add(dateOfWithdrawalJLabel);
        withdrawalJPanel.add(dateOfWithdrawalJLabel);
        dayJComboBox = new JComboBox<>(daysArray);
        dayJComboBox.setFont(mediumText);
        dayJComboBox.setBounds(53, 152, 58, 40);
        withdrawalJPanel.add(dayJComboBox);
        monthJComboBox = new JComboBox<>(monthsArray);
        monthJComboBox.setFont(mediumText);
        monthJComboBox.setBounds(111, 152, 145, 40);
        withdrawalJPanel.add(monthJComboBox);
        yearJComboBox = new JComboBox<>(yearsArray);
        yearJComboBox.setFont(mediumText);
        yearJComboBox.setBounds(256, 152, 90, 40);
        withdrawalJPanel.add(yearJComboBox);
        pinNumberJLabel = new JLabel("PIN Number:");
        pinNumberJLabel.setFont(mediumText);
        pinNumberJLabel.setBounds(53, 206, 198, 40);
        withdrawalJPanel.add(pinNumberJLabel);
        pinNumberJTextField = new JTextField();
        pinNumberJTextField.setFont(mediumText);
        pinNumberJTextField.setBounds(53, 251, 267, 40);
        withdrawalJPanel.add(pinNumberJTextField);
        withdrawJButton = new JButton("Withdraw");
        withdrawJButton.setFont(mediumText);
        withdrawJButton.setBounds(100, 305, 158, 57);
        withdrawJButton.addActionListener(this);
        withdrawalJPanel.add(withdrawJButton);
        debitInnerPane.add(withdrawalJPanel);
        displayDebitDetailsJPanel = new JPanel();
        displayDebitDetailsJPanel.setLayout(null);
        displayDebitDetailsJPanel.setBounds(424, 187, 348, 368);
        displayDebitDetailsJPanel.setBackground(Color.decode("#A87474"));
        displayDebitJButton = new JButton("Display Details");
        displayDebitJButton.setFont(mediumText);
        displayDebitJButton.setBounds(47, 10, 253, 57);
        displayDebitJButton.addActionListener(this);
        displayDebitDetailsJPanel.add(displayDebitJButton);
        displayDebitDetailsJTextArea = new JTextArea(10, 50);
        displayDebitDetailsJTextArea.setFont(new Font("Verdana", Font.PLAIN, 18));
        displayDebitDetailsJTextArea.setBounds(17, 100, 314, 252);
        displayDebitDetailsJPanel.add(displayDebitDetailsJTextArea);
        debitInnerPane.add(displayDebitDetailsJPanel);
        exitToMainMenuFromInnerDebitJButton = new JButton("Exit to main menu");
        exitToMainMenuFromInnerDebitJButton.setBounds(25, 122, 267, 57);
        exitToMainMenuFromInnerDebitJButton.setFont(mediumText);
        exitToMainMenuFromInnerDebitJButton.addActionListener(this);
        debitInnerPane.add(exitToMainMenuFromInnerDebitJButton);
        clearInnerDebitEntryJButton = new JButton("Clear All");
        clearInnerDebitEntryJButton.addActionListener(this);
        clearInnerDebitEntryJButton.setBounds(608, 122, 163, 57);
        clearInnerDebitEntryJButton.setFont(mediumText);
        debitInnerPane.add(clearInnerDebitEntryJButton);
        debitInnerPane.setLayout(null);
        debitInnerJFrame.setSize(800, 600);
        debitInnerJFrame.setLocationRelativeTo(null);
        debitInnerJFrame.setVisible(true);
    }

    private void debitCardGUI() {
        debitJFrame = new JFrame("Debit Card - Unified Banking System");
        debitJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        debitPane = debitJFrame.getContentPane();
        debitJFrame.setSize(800, 600);
        titleDebitJPanel = new JPanel();
        titleDebitJLabel = new JLabel("Debit Card");
        titleDebitJLabel.setFont(largeText);
        titleDebitJLabel.setBounds(55, 32, 244, 53);
        titleDebitJPanel.add(titleDebitJLabel);
        titleDebitJPanel.setLayout(null);
        titleDebitJPanel.setBackground(Color.decode("#D9D9D9"));
        titleDebitJPanel.setBounds(0, 0, 800, 117);
        debitPane.add(titleDebitJPanel);
        enterDebitCardDetailsGUI();
        debitPane.setLayout(null);
        debitJFrame.setLocationRelativeTo(null);
        debitJFrame.setVisible(true);
    }

    private void enterDebitCardDetailsGUI() {
        enterDebitCardDetailsJPanel = new JPanel();
        enterDebitCardDetailsJPanel.setBounds(0, 0, 800, 600);
        enterDebitCardDetailsJPanel.setBackground(Color.decode("#ED8686"));
        cardIDJLabel = new JLabel("Card ID:");
        cardIDJLabel.setFont(mediumText);
        cardIDJLabel.setBounds(181, 206, 151, 40);
        enterDebitCardDetailsJPanel.add(cardIDJLabel);
        cardIDJTextField = new JTextField();
        cardIDJTextField.setBounds(351, 206, 267, 40);
        cardIDJTextField.setFont(mediumText);
        enterDebitCardDetailsJPanel.add(cardIDJTextField);
        clientNameJLabel = new JLabel("Client Name:");
        clientNameJLabel.setFont(mediumText);
        clientNameJLabel.setBounds(181, 257, 170, 40);
        enterDebitCardDetailsJPanel.add(clientNameJLabel);
        clientNameJTextField = new JTextField();
        clientNameJTextField.setBounds(351, 257, 267, 40);
        clientNameJTextField.setFont(mediumText);
        enterDebitCardDetailsJPanel.add(clientNameJTextField);
        issuerBankJLabel = new JLabel("Issuer Bank:");
        issuerBankJLabel.setBounds(181, 308, 170, 40);
        issuerBankJLabel.setFont(mediumText);
        enterDebitCardDetailsJPanel.add(issuerBankJLabel);
        issuerBankJTextField = new JTextField();
        issuerBankJTextField.setFont(mediumText);
        issuerBankJTextField.setBounds(351, 308, 267, 40);
        enterDebitCardDetailsJPanel.add(issuerBankJTextField);
        submitDebitCardDetailsJButton = new JButton("Submit");
        submitDebitCardDetailsJButton.setFont(mediumText);
        submitDebitCardDetailsJButton.setBounds(181, 366, 437, 57);
        submitDebitCardDetailsJButton.addActionListener(this);
        enterDebitCardDetailsJPanel.add(submitDebitCardDetailsJButton);
        suggestCreationJLabel = new JLabel("Don't have a debit card?");
        suggestCreationJLabel.setFont(mediumText);
        suggestCreationJLabel.setBounds(252, 437, 298, 37);
        enterDebitCardDetailsJPanel.add(suggestCreationJLabel);
        createNewDebitCardJButton = new JButton("Create New!");
        createNewDebitCardJButton.setFont(mediumText);
        createNewDebitCardJButton.setBounds(257, 490, 267, 57);
        createNewDebitCardJButton.addActionListener(this);
        enterDebitCardDetailsJPanel.add(createNewDebitCardJButton);
        exitToMainMenuFromDebitJButton = new JButton("Exit to Main Menu");
        exitToMainMenuFromDebitJButton.setFont(mediumText);
        exitToMainMenuFromDebitJButton.setBounds(25, 130, 267, 57);
        exitToMainMenuFromDebitJButton.addActionListener(this);
        enterDebitCardDetailsJPanel.add(exitToMainMenuFromDebitJButton);
        clearDebitCardEntryJButton = new JButton("Clear All");
        clearDebitCardEntryJButton.addActionListener(this);
        clearDebitCardEntryJButton.setFont(mediumText);
        clearDebitCardEntryJButton.setBounds(608, 130, 163, 57);
        enterDebitCardDetailsJPanel.add(clearDebitCardEntryJButton);
        enterDebitCardDetailsJPanel.setLayout(null);
        debitPane.add(enterDebitCardDetailsJPanel);
    }

    private void createDebitCardGUI() {
        enterDebitCardDetailsJPanel.setVisible(false);
        createDebitCardJPanel = new JPanel();
        createDebitCardJPanel.setBounds(0, 0, 800, 600);
        createDebitCardJPanel.setBackground(Color.decode("#ED8686"));
        cardIDJLabel = new JLabel("Card ID:");
        cardIDJLabel.setFont(mediumText);
        cardIDJLabel.setBounds(18, 200, 105, 40);
        createDebitCardJPanel.add(cardIDJLabel);
        cardIDJTextField = new JTextField();
        cardIDJTextField.setFont(mediumText);
        cardIDJTextField.setBounds(144, 200, 128, 40);
        createDebitCardJPanel.add(cardIDJTextField);
        clientNameJLabel = new JLabel("Client Name:");
        clientNameJLabel.setFont(mediumText);
        clientNameJLabel.setBounds(315, 200, 170, 40);
        createDebitCardJPanel.add(clientNameJLabel);
        clientNameJTextField = new JTextField();
        createDebitCardJPanel.add(clientNameJTextField);
        clientNameJTextField.setFont(mediumText);
        clientNameJTextField.setBounds(505, 200, 267, 40);
        issuerBankJLabel = new JLabel("Issuer Bank:");
        issuerBankJLabel.setFont(mediumText);
        issuerBankJLabel.setBounds(316, 255, 160, 40);
        createDebitCardJPanel.add(issuerBankJLabel);
        issuerBankJTextField = new JTextField();
        issuerBankJTextField.setFont(mediumText);
        issuerBankJTextField.setBounds(505, 255, 267, 40);
        createDebitCardJPanel.add(issuerBankJTextField);
        balanceAmountJLabel = new JLabel("Balance");
        balanceAmountJLabel.setFont(mediumText);
        balanceAmountJLabel.setBounds(18, 240, 113, 28);
        balanceAmountContdJLabel = new JLabel("Amount:");
        createDebitCardJPanel.add(balanceAmountContdJLabel);
        balanceAmountContdJLabel.setFont(mediumText);
        balanceAmountContdJLabel.setBounds(18, 268, 113, 28);
        createDebitCardJPanel.add(balanceAmountJLabel);
        balanceAmountJTextField = new JTextField();
        balanceAmountJTextField.setFont(mediumText);
        balanceAmountJTextField.setBounds(144, 252, 128, 40);
        createDebitCardJPanel.add(balanceAmountJTextField);
        bankAccountJLabel = new JLabel("Bank Account:");
        bankAccountJLabel.setFont(mediumText);
        bankAccountJLabel.setBounds(315, 307, 177, 40);
        createDebitCardJPanel.add(bankAccountJLabel);
        bankAccountJTextField = new JTextField();
        bankAccountJTextField.setFont(mediumText);
        bankAccountJTextField.setBounds(504, 307, 267, 40);
        createDebitCardJPanel.add(bankAccountJTextField);
        pinNumberJLabel = new JLabel("PIN No:");
        pinNumberJLabel.setFont(mediumText);
        pinNumberJLabel.setBounds(18, 306, 151, 40);
        createDebitCardJPanel.add(pinNumberJLabel);
        pinNumberJTextField = new JTextField();
        pinNumberJTextField.setFont(mediumText);
        pinNumberJTextField.setBounds(144, 306, 128, 40);
        createDebitCardJPanel.add(pinNumberJTextField);
        submitDebitCreationJButton = new JButton("Submit");
        submitDebitCreationJButton.setBounds(181, 366, 437, 57);
        submitDebitCreationJButton.setFont(mediumText);
        submitDebitCreationJButton.addActionListener(this);
        createDebitCardJPanel.add(submitDebitCreationJButton);
        suggestEnteringJLabel = new JLabel("Already have a debit card?");
        suggestEnteringJLabel.setFont(mediumText);
        suggestEnteringJLabel.setBounds(231, 437, 330, 37);
        createDebitCardJPanel.add(suggestEnteringJLabel);
        enterDebitCardDetailsJButton = new JButton("Enter details");
        enterDebitCardDetailsJButton.setBounds(257, 490, 267, 57);
        enterDebitCardDetailsJButton.setFont(mediumText);
        enterDebitCardDetailsJButton.addActionListener(this);
        createDebitCardJPanel.add(enterDebitCardDetailsJButton);
        exitToMainMenuFromDebitJButton = new JButton("Exit to Main Menu");
        exitToMainMenuFromDebitJButton.setFont(mediumText);
        exitToMainMenuFromDebitJButton.setBounds(25, 130, 267, 57);
        exitToMainMenuFromDebitJButton.addActionListener(this);
        createDebitCardJPanel.add(exitToMainMenuFromDebitJButton);
        clearDebitCardCreationEntryJButton = new JButton("Clear All");
        clearDebitCardCreationEntryJButton.addActionListener(this);
        clearDebitCardCreationEntryJButton.setFont(mediumText);
        clearDebitCardCreationEntryJButton.setBounds(608, 130, 163, 57);
        createDebitCardJPanel.add(clearDebitCardCreationEntryJButton);
        createDebitCardJPanel.setLayout(null);
        debitPane.add(createDebitCardJPanel);
    }

    private int validateDebitCard() {
        try {
            int cardID = Integer.parseInt(cardIDJTextField.getText());
            String clientName = clientNameJTextField.getText();
            String issuerBank = issuerBankJTextField.getText();

            for (BankCard element : BankCardArrayList) {
                if (element instanceof DebitCard && cardID == element.getCardID()
                        && clientName.equals(element.getClientName()) && issuerBank.equals(element.getIssuerBank())) {
                    return BankCardArrayList.indexOf(element);
                }
            }
            return -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. The cardID must be a valid integer.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return -2;
        }
    }

    private void createDebitCard() {
        try {
            String cardIDText = cardIDJTextField.getText();
            String clientName = clientNameJTextField.getText();
            String issuerBank = issuerBankJTextField.getText();
            String balanceAmountText = balanceAmountJTextField.getText();
            String pinNumberText = pinNumberJTextField.getText();
            String bankAccount = bankAccountJTextField.getText();

            // Check for empty fields
            if (cardIDText.isEmpty() || clientName.isEmpty() || issuerBank.isEmpty() || balanceAmountText.isEmpty()
                    || bankAccount.isEmpty() || pinNumberText.isEmpty()) {
                throw new NumberFormatException("One or more fields are empty.");
            }

            // Check for alphabetic client name and issuer bank
            if (!clientName.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Client name must contain only alphabetic characters.");
            }
            if (!issuerBank.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Issuer bank must contain only alphabetic characters.");
            }

            // Parse integer values
            int cardID = Integer.parseInt(cardIDText);
            int balanceAmount = Integer.parseInt(balanceAmountText);
            int pinNumber = Integer.parseInt(pinNumberText);

            // Check for out-of-range values
            if (balanceAmount <= 0) {
                throw new IllegalArgumentException("Balance amount must be a positive integer.");
            }
            if (pinNumber <= 0) {
                throw new IllegalArgumentException("PIN number must be a positive integer.");
            }

            // Create new debit card and add to list
            BankCardArrayList.add(new DebitCard(balanceAmount, cardID, bankAccount, issuerBank, clientName, pinNumber));
            JOptionPane.showMessageDialog(null, "Successfully created a new debit card", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            createDebitCardJPanel.setVisible(false);
            enterDebitCardDetailsJPanel.setVisible(true);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void creditJFrameGUI() {
        creditJFrame = new JFrame("Credit Card - Unified Banking System");
        creditJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creditPane = creditJFrame.getContentPane();
        titleCreditJPanel = new JPanel();
        titleCreditJLabel = new JLabel("Credit Card");
        titleCreditJLabel.setFont(largeText);
        titleCreditJLabel.setBounds(55, 32, 260, 53);
        titleCreditJPanel.add(titleCreditJLabel);
        titleCreditJPanel.setLayout(null);
        titleCreditJPanel.setBackground(Color.decode("#D9D9D9"));
        titleCreditJPanel.setBounds(0, 0, 800, 117);
        creditPane.add(titleCreditJPanel);
        enterCreditCardDetailsGUI();
        creditJFrame.setSize(800, 600);
        creditJFrame.setLocationRelativeTo(null);
        creditJFrame.setVisible(true);
    }

    private void enterCreditCardDetailsGUI() {
        enterCreditCardDetailsJPanel = new JPanel();
        enterCreditCardDetailsJPanel.setBounds(0, 0, 800, 600);
        enterCreditCardDetailsJPanel.setBackground(Color.decode("#ED8686"));
        cardIDJLabel = new JLabel("Card ID:");
        cardIDJLabel.setFont(mediumText);
        cardIDJLabel.setBounds(181, 206, 151, 40);
        enterCreditCardDetailsJPanel.add(cardIDJLabel);
        cardIDJTextField = new JTextField();
        cardIDJTextField.setBounds(351, 206, 267, 40);
        cardIDJTextField.setFont(mediumText);
        enterCreditCardDetailsJPanel.add(cardIDJTextField);
        clientNameJLabel = new JLabel("Client Name:");
        clientNameJLabel.setFont(mediumText);
        clientNameJLabel.setBounds(181, 257, 170, 40);
        enterCreditCardDetailsJPanel.add(clientNameJLabel);
        clientNameJTextField = new JTextField();
        clientNameJTextField.setBounds(351, 257, 267, 40);
        clientNameJTextField.setFont(mediumText);
        enterCreditCardDetailsJPanel.add(clientNameJTextField);
        issuerBankJLabel = new JLabel("Issuer Bank:");
        issuerBankJLabel.setBounds(181, 308, 170, 40);
        issuerBankJLabel.setFont(mediumText);
        enterCreditCardDetailsJPanel.add(issuerBankJLabel);
        issuerBankJTextField = new JTextField();
        issuerBankJTextField.setFont(mediumText);
        issuerBankJTextField.setBounds(351, 308, 267, 40);
        enterCreditCardDetailsJPanel.add(issuerBankJTextField);
        submitCreditCardDetailsJButton = new JButton("Submit");
        submitCreditCardDetailsJButton.setFont(mediumText);
        submitCreditCardDetailsJButton.setBounds(181, 366, 437, 57);
        submitCreditCardDetailsJButton.addActionListener(this);
        enterCreditCardDetailsJPanel.add(submitCreditCardDetailsJButton);
        suggestCreationJLabel = new JLabel("Don't have a Credit card?");
        suggestCreationJLabel.setFont(mediumText);
        suggestCreationJLabel.setBounds(236, 437, 320, 37);
        enterCreditCardDetailsJPanel.add(suggestCreationJLabel);
        createNewCreditCardJButton = new JButton("Create New!");
        createNewCreditCardJButton.setFont(mediumText);
        createNewCreditCardJButton.setBounds(257, 490, 267, 57);
        createNewCreditCardJButton.addActionListener(this);
        enterCreditCardDetailsJPanel.add(createNewCreditCardJButton);
        exitToMainMenuFromCreditJButton = new JButton("Exit to Main Menu");
        exitToMainMenuFromCreditJButton.setFont(mediumText);
        exitToMainMenuFromCreditJButton.setBounds(25, 130, 267, 57);
        exitToMainMenuFromCreditJButton.addActionListener(this);
        enterCreditCardDetailsJPanel.add(exitToMainMenuFromCreditJButton);
        clearCreditCardEntryJButton = new JButton("Clear All");
        clearCreditCardEntryJButton.addActionListener(this);
        clearCreditCardEntryJButton.setFont(mediumText);
        clearCreditCardEntryJButton.setBounds(608, 130, 163, 57);
        enterCreditCardDetailsJPanel.add(clearCreditCardEntryJButton);
        enterCreditCardDetailsJPanel.setLayout(null);
        creditPane.add(enterCreditCardDetailsJPanel);
    }
}
