 

/**
 * A class that represents the debit card (child of Kushal_Dhakal.BankCard)
 * @author Kushal Dhakal
 * @version 1.0
 */

public class DebitCard extends BankCard
{
    // attributes declaration
    private int pinNumber;
    private int withdrawalAmount;
    private boolean hasWithdrawn;
    private String dateOfWithdrawal;

    /**
     * This is the constructor method of the Kushal_Dhakal.DebitCard class
     * @param balanceAmount The integer that is the balance amount of the client
     * @param cardID An integer that is the card identification number of the client
     * @param bankAccount The bank account number of the client stored as a String
     * @param issuerBank The name of the issuer bank as a String
     * @param clientName The name of the client as a String
     * @param pinNumber The pin number of the debit card as an integer
     */
    DebitCard(int balanceAmount, int cardID, String bankAccount, String issuerBank, String clientName, int pinNumber)
    {
        super(cardID, issuerBank, bankAccount, balanceAmount);
        super.setClientName(clientName);
        this.pinNumber = pinNumber;
        this.hasWithdrawn = false;
    }

    /**
     * This is the accessor method of the pinNumber attribute
     * @return The integer value of the pin number
     */
    protected int getPinNumber() 
    { 
        return this.pinNumber; 
    }

    /**
     * This is the accessor method of the withdrawalAmount attribute
     * @return The integer value of the withdrawal amount
     */
    protected int getWithdrawalAmount() 
    { 
        return this.withdrawalAmount;
    }

    /**
     * This is the accessor method of the dateOfWithdrawal attribute
     * @return The date of withdrawal as a String
     */
    protected String getDateOfWithdrawal() 
    {
         return this.dateOfWithdrawal;
    }

    /**
     * This is the accessor method of the hasWithdrawn attribute
     * @return The true or false value as if any amount has been withdrawn or not
     */
    protected boolean getHasWithdrawn() 
    { 
        return this.hasWithdrawn; 
    }   

    /**
     * This is the mutator method for the withdrawalAmount attribute
     * @param newWithdrawalAmount The new integer value for the withdrawal amount
     */
    protected void setWithdrawalAmount(int newWithdrawalAmount)
    {
        this.withdrawalAmount = newWithdrawalAmount;
    }

    /**
     * This method is used to withdraw money from the debit card only if the client is eligible to
     * @param withdrawalAmount The integer value of the amount to be witdrawn
     * @param dateOfWithdrawal The date of withdrawing the amount as a String
     * @param pinNumber The pin number of the debit card of the client
     */
    protected void withdraw(int withdrawalAmount, String dateOfWithdrawal, int pinNumber)
    {
        if (pinNumber == this.pinNumber && super.getBalanceAmount() >= withdrawalAmount) {
            super.setBalanceAmount(super.getBalanceAmount() - withdrawalAmount);
            this.dateOfWithdrawal = dateOfWithdrawal;
            this.hasWithdrawn = true;
            this.withdrawalAmount = withdrawalAmount;
        } else {
            if (pinNumber == this.pinNumber) {
                System.out.println("Insufficient balance.");
            }
            else if (super.getBalanceAmount() >= withdrawalAmount) {
                System.out.println("Invalid pin number");
            }
            else {
                System.out.println("Invalid pin number and insufficient balance.");
            }

        }
    }

    /**
     * This method overrides the displayDetails method of the Kushal_Dhakal.BankCard parent
     * and also prints the additional attributes in the child
     */
    @Override
    protected void displayDetails() 
    {
        super.displayDetails();
        if (this.hasWithdrawn) {
            System.out.println("Withdrawal amount: " + this.withdrawalAmount);
            System.out.println("Date of withdrawal: " + this.dateOfWithdrawal);
            System.out.println("Pin number: " + this.pinNumber);
        }
    }
}
