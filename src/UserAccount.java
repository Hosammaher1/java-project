import java.util.ArrayList;



public class UserAccount {

    private String accountNumber;
    private String fullName;
    private String phoneNumber;
    private String password;
    private double balance;
    public ArrayList<String> transaction = new ArrayList<>();

    public UserAccount(String accountNumber, String fullName, String phoneNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.balance = balance;
        this.transaction = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getFullName(){
        return fullName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getPassword(){
        return password;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            transaction.add("Deposit :" + amount);
            System.out.println("Deposited :" + amount+"$");
        }
    }
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance = balance - amount;
            transaction.add("Withdrew: " + amount);
            System.out.println("It was withdrawn " + amount+"$");
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(String transaction1) {
        transaction.add(transaction1);
    }
    public void viewTransactionHistory() {
        if (transaction.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction1 : transaction) {
                System.out.println(transaction1);
            }
        }
    }

}





