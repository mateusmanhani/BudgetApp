
package budgedapp;

/**
 *
 * @author Mateus Manhani
 */
public class BudgedApp {


    public static void main(String[] args) {
        Category health = new Category("Health");
        health.deposit(1000, "Initial Deposit");
        health.withdraw(60, "Doctors Apointment");
        
        Category food = new Category("Food");
        food.deposit(500, "Initial Deposit");
        food.withdraw(200, "Groceries");
        food.transfer(100, health);
        
        System.out.println(food);
        System.out.println(health);
    }
    
}
