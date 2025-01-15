import java.util.ArrayList;



public class UserAccount {

    private String accountNumber;
    private String fullName;
    private String phoneNumber;
    private String password;
    private double balance;
    public ArrayList<String> transaction = new ArrayList<>();
    FileHandler fileHandler = new FileHandler();
    public UserAccount(){
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
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }


    public UserAccount(String accountNumber, String fullName, String phoneNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.balance = balance;
        this.transaction = new ArrayList<>();
    }

    public void deposit(double amount) {
        // FileHandler fileHandler = new FileHandler();
        if (amount > 0) {
            balance = balance + amount;
            transaction.add("Deposit :" + amount);
            //  try {
            //      fileHandler.transactionWriter(transaction);
            //  } catch (IOException ex) {
            //      System.out.println("Error reading file: " + ex.getMessage());
            //  }
        }
    }
    public void withdraw(double amount) {
        // FileHandler fileHandler1 = new FileHandler();
        if (balance >= amount) {
            balance = balance - amount;
            transaction.add("Withdrew: " + amount);

        } else {
            System.out.println("Insufficient Balance");
            {
                //    try {
                //        fileHandler.transactionWriter(transaction);
                //    } catch (IOException ex) {
                //        System.out.println("Error reading file: " + ex.getMessage());
                //    }
            }
        }
    }
    public double getBalance() {
        return balance;
    }
    public String getBalance0() {
        return  "Balance " + balance + "\n";
    }
    public ArrayList<String> getTransactionHistory() {
        return transaction;
    }

    public void addTransaction(String transaction1) {
        transaction.add(transaction1);
       // viewTransactionHistory();
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





