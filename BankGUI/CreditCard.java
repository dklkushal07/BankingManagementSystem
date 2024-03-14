/**
 * A class that represents the credit card (child of BankCard)
 * @author Kushal Dhakal
 * @version 1.0
 */

public class CreditCard extends BankCard
{
    // attribute declaration
    private int cvcNumber;
    private double creditLimit;
    private double interestRate;
    private String expirationDate;
    private int gracePeriod;
    private boolean isGranted;

    /**
     * This is the constructor method of CreditCard class
     * @param cardID An integer that is the card identification number of the client
     * @param clientName The name of the client as a String
     * @param issuerBank The name of the issuer bank as a String
     * @param bankAccount The bank account number of the client stored as a String
     * @param cvcNumber The integer that is the cvc number
     * @param interestRate The double that is the interest rate
     * @param expirationDate The expiration date of credit card as a String
     * @param balanceAmount The integer that is the balance amount of the client
     */
    CreditCard(int cardID, String clientName, String issuerBank, String bankAccount, int cvcNumber, double interestRate, String expirationDate, int balanceAmount)
    {
        super(cardID, issuerBank, bankAccount, balanceAmount);
        super.setClientName(clientName);
        this.cvcNumber = cvcNumber;
        this.interestRate = interestRate;
        this.expirationDate = expirationDate;
        this.isGranted = false;
    }

    /**
     * This is the accessor method for the cvcNumber attribute
     * @return The integer value of the cvc number
     */
    protected int getCvcNumber()
    {
     return this.cvcNumber;   
    }

    /**
     * This is the accessor method for the creditLimit attribute
     * @return The double value of the credit limit
     */
    protected double getCreditLimit()
    {
        return this.creditLimit;
    }

    /**
     * This is the accessor method of the interestRate attribute
     * @return The double value of the interest rate
     */
    protected double getInterestRate()
    {
        return this.interestRate;
    }

    /**
     * This is the accessor method of the expirationDate attribute
     * @return The expiration date as a String
     */
    protected String getExpirationDate()
    {
        return this.expirationDate;
    }

    /**
     * This is the accessor method of the gracePeriod attribute
     * @return The integer value of the grace period
     */
    protected int getGracePeriod()
    {
        return this.gracePeriod;
    }

    /**
     * This is the accessor method of the isGranted attribute
     * @return The true or false as if the credit limit is granted or not
     */
    protected boolean getIsGranted()
    {
        return this.isGranted;
    }

    /**
     * This is a method that sets a new creditLimit and a new gracePeriod
     * @param newCreditLimit The new double value of the credit limit
     * @param newGracePeriod The new integer value of the grace period
     */
    protected void setCreditLimit(double newCreditLimit, int newGracePeriod)
    {
        if (newCreditLimit <= (2.5 * super.getBalanceAmount() ) ){
            this.creditLimit = newCreditLimit;
            this.gracePeriod = newGracePeriod;
            this.isGranted = true;
        }
        if (!this.isGranted) {
            System.out.println("The credit cannot be issued");
        }
    }

    /**
     * This is a method to cancel the credit card for a client
     */
    protected void cancelCreditCard()
    {
        this.cvcNumber = 0;
        this.creditLimit = 0;
        this.gracePeriod = 0;
        this.isGranted = false;
    }

    /**
     * This method overrides the displayDetails method of the BankCard parent
     * and also prints the additional attributes in the child
     */
    @Override
    protected void displayDetails()
    {
        super.displayDetails();
        System.out.println("CVC number: " + this.cvcNumber);
        System.out.println("Interest rate: " + this.interestRate);
        System.out.println("Expiration date: " + this.expirationDate);
        if (this.isGranted) {
            System.out.println("Credit limit: " + this.creditLimit);
            System.out.print("Grace period: " + this.gracePeriod);
        }
    }
}