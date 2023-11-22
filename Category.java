
package budgedapp;

import java.util.ArrayList;

/**
 *
 * @author Mateus Manhani
 * It should be able to instantiate objects based on different budget categories
 * like food, clothing, and entertainment. When objects are created, they are 
 * passed in the name of the category. The class should have an instance variable
 * called ledger that is a list.
 */
public class Category {
    
    private final String category;
    private final ArrayList <Transaction> ledger; 
    
    // Constructor for category objects
    public Category(String category) {
        this.category = category;
        this.ledger = new ArrayList<>();//Instantiate ledger for category object
    }
    
    // Deposit method
    public void deposit(double amount, String description){
        ledger.add(new Transaction(amount, description));        
    }
    
    // Withdraw method return boolean
    public boolean withdraw(double amount, String description){
        if(checkFunds(amount)){
            ledger.add(new Transaction(-amount, description));
            return true;
        }
        return false;
    }
    // Get balance
    public double getBalance() {
        double balance = 0;
        
        // Iterate each transaction object in the ledger
        for (Transaction transaction : ledger){
            //add all transactions in the ledger to get the balance
            balance += transaction.getAmount();
        }
        return balance;
    }
    // Transfer boolean
    public boolean transfer(double amount, Category budgetCategory){
        if(checkFunds(amount)){
            this.withdraw(amount,"Transfer to: " + budgetCategory.category);
            budgetCategory.deposit(amount, "Transfer from: " + this.category);
            return true;
        }
        return false;
    }
    
    // Check Funds
    private boolean checkFunds(double amount){
        return amount <= getBalance();
    }
    
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(center(category.toUpperCase(), 30, '*')).append("\n");

        for (Transaction transaction : ledger) {
            String description = transaction.getDescription().length() > 23 ?
                    transaction.getDescription().substring(0, 23) :
                    transaction.getDescription();
            String amount = String.format("%.2f", transaction.getAmount());
            output.append(String.format("%-23s%7s\n", description, amount));
        }

        output.append(String.format("Total: %.2f", getBalance()));
        return output.toString();
    }

    public static String center(String text, int width, char padChar) {
        int textLength = text.length();
        int padding = width - textLength;

        if (padding <= 0) {
            return text;
        }

        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        return new String(new char[leftPadding]).replace('\0', padChar) +
                text +
                new String(new char[rightPadding]).replace('\0', padChar);
    }

    public static String center(String text, int width) {
        return center(text, width, ' ');
    }
}


class Transaction {
    private final double amount;
    private final String description;
    
    public Transaction (double amount, String description){
        this.amount = amount;
        this.description = description;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
}
