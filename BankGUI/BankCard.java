/**
 * A class that represents a Bank card.
 * 
 * @author Kushal Dhakal
 * @version 1.0
 */

public class BankCard
{
    // attribute declaration
    private final int cardID;
    private String clientName;
    private final String issuerBank;
    private final String bankAccount;
    private int balanceAmount;

    /**
     * This is the constructor of the Kushal_Dhakal.BankCard class
     * @param cardID An integer that is the card identification number of the client
     * @param issuerBank The name of the issuer bank as a String
     * @param bankAccount The bank account number of the client stored as a String
     * @param balanceAmount The integer that is the balance amount of the client
     */
    BankCard(int cardID, String issuerBank, String bankAccount, int balanceAmount )
    {
        this.cardID = cardID;
        this.clientName = "";
        this.issuerBank = issuerBank;
        this.bankAccount = bankAccount;
        this.balanceAmount = balanceAmount;
    }

    /**
     * This is the mutator method for the clientName attribute
     * @param newClientName The name of the client as a String
     */
    protected void setClientName(String newClientName)
    {
        this.clientName = newClientName;
    }

    /**
     * This is the mutator method for the balanceAmount attribute
     * @param newBalanceAmount The new integer value of balance amount 
     */
    protected void setBalanceAmount(int newBalanceAmount)
    {
        this.balanceAmount = newBalanceAmount;
    }

    /**
     * This is the accessor method for the clientName attribute
     * @return The name of the client as a String
     */
    protected String getClientName()
    {
        return this.clientName;
    }

    /**
     * This is the accessor method for the cardID attribute
     * @return The integer value of card identification number
     */
    protected int getCardID()
    {
        return this.cardID;
    }

    /**
     * This is the accessor method for the bankAccount attribute
     * @return The bank account number as a String
     */
    protected String getBankAccount()
    {
        return this.bankAccount;
    }

    /**
     * This is the accessor method for the balanceAmount attribute
     * @return The integer value of balance amount
     */
    protected int getBalanceAmount()
    {
        return this.balanceAmount;
    }

    /**
     * This is the accessor method for the issuerBank attribute
     * @return The issuer bank as a String
     */
    protected String getIssuerBank()
    {
        return this.issuerBank;
    }

    /**
     * The displayDetails method prints the bank card details to the system output
     */
    protected void displayDetails()
    {
        System.out.println("Card ID: " + getCardID());
        if (getClientName().equals("")) {
            System.out.println("Client name has not been assigned.");
        } else {
            System.out.println("Client name: " + this.clientName);
        }
        System.out.println("Issuer bank: " + this.issuerBank);
        System.out.println("Bank account: " + this.bankAccount);
        System.out.println("Balance amount: " + this.balanceAmount);
    }
}