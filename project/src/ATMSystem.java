import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class ATMSystem {
    private ArrayList<UserAccount> userAccount = new ArrayList<>();

    public ATMSystem() {
        userAccount = FileHandler.loadUsers();
        FileHandler.loadTransactionHistory(userAccount);

    }
    public void start (){
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Enter one option ");
                System.out.println("1 . Login ");
                System.out.println("2 . Create Account ");
                System.out.println("3 . Exit ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        login();
                        break;
                    case 2:
                        newAccount();
                        break;
                    case 3:
                        System.out.println("Exiting ...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid entered option !");
                }

            } catch (InputMismatchException ex) {
                System.out.println("Invalid option " + ex.getMessage());
            }
        }while (true);

    }
    public void login() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();


            UserAccount user = findUser(accountNumber);


            if (user != null) {
                if (user.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    transactions(user);
                } else {

                    System.out.println("Incorrect password. Try again.");
                    start();
                }
            } else {
                System.out.println("Account not found. Try again.");
                start();
            }

        }while (true);
    }


    public void newAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Name : ");
        String fullName = scanner.nextLine();
        System.out.print("Enter your phone number : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        while (password.length() < 6){
                System.out.println("Password must be at least 6 characters . Try again :");
                password = scanner.nextLine();
        }
        String accountNumber = generateAccountNumber();
        UserAccount users = new UserAccount(accountNumber,fullName,phoneNumber,password,0);
        userAccount.add(users);
        try {
            FileHandler.saveUsers(userAccount);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        System.out.println("Account created successfully! Your Account Number is: " + accountNumber);
        do {
            try {
                System.out.println("choice: ");
                System.out.println("1 . Login ");
                System.out.println("2 . Exit ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        transactions(users);
                        break;
                    case 2:
                        System.out.println("Exiting ...");
                        start();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid option " + ex.getMessage());
            }
        }
        while (true);
        //transactions(users);
    }
    public void transactions(UserAccount userAccount1) {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("************************");
                System.out.println("Choice :");
                System.out.println("1 . Deposit  ");
                System.out.println("2 . Withdraw  ");
                System.out.println("3 . View Transactions  ");
                System.out.println("4 . View Balance  ");
                System.out.println("5 . Exit Account ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double amountdeposit = scanner.nextInt();
                        userAccount1.deposit(amountdeposit);
                        try {
                            FileHandler.logTransaction(userAccount1.getAccountNumber()+ " Deposit: " + amountdeposit);
                            FileHandler.saveUsers(userAccount);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + ex.getMessage());
                        }

                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double amountwithdraw = scanner.nextInt();
                        userAccount1.withdraw(amountwithdraw);
                        try {
                            FileHandler.logTransaction(userAccount1.getAccountNumber() + " Withdrew: " + amountwithdraw);
                            FileHandler.saveUsers(userAccount);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + ex.getMessage());
                        }

                        break;
                    case 3:
                        System.out.println("View transactions : ");
                        userAccount1.viewTransactionHistory();

                        break;
                    case 4:
                        System.out.println("Current Balance: " + userAccount1.getBalance() + "$");
                        break;
                    case 5:
                        System.out.println("Exiting Account...");
                        start();
                        break;
                    default:
                        System.out.println("Invalid option");

                }

            }
            catch (InputMismatchException ex) {
                System.out.println("Invalid input "+ex.getMessage());
            }
            scanner.nextLine();
        }while (true);
    }

    public String generateAccountNumber() {
        return String.valueOf((int) (Math.random() * 9000000) + 1000000);
    }

    private UserAccount findUser(String accountNumber) {
        for (UserAccount user : userAccount) {
            if (user.getAccountNumber().equals(accountNumber)) {
                return user;
            }
        }
        return null;
    }
}