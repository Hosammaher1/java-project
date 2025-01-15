import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static final String USERS_FILE = "users.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public static ArrayList<UserAccount> loadUsers() {
        ArrayList<UserAccount> users = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(USERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String accountNumber = data[0];
                String fullName = data[1];
                String phoneNumber = data[2];
                String password = data[3];
                double balance = Double.parseDouble(data[4]);

                UserAccount user = new UserAccount(accountNumber, fullName, phoneNumber, password, balance);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No users found. Starting fresh.");
        }

        return users;
    }

    public static void saveUsers(ArrayList<UserAccount> users)throws IOException {
        try (PrintWriter writer = new PrintWriter((USERS_FILE))) {
            for (UserAccount user : users) {
                writer.println(user.getAccountNumber() + "," +
                        user.getFullName() + "," +
                        user.getPhoneNumber() + "," +
                        user.getPassword() + "," +
                        user.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error writing to users file: " + e.getMessage());
        }
    }

    public static void logTransaction(String transaction) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(TRANSACTIONS_FILE, true))) {
            writer.println(transaction);
        } catch (IOException e) {
            System.out.println("Error writing to transactions file: " + e.getMessage());
        }
    }
    public static void loadTransactionHistory(ArrayList<UserAccount> users) {
        try (Scanner scanner = new Scanner(new File(TRANSACTIONS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" " , 2);
                String accountNumber = parts[0];
                String transaction = parts[1];

                for (UserAccount user : users) {
                    if (user.getAccountNumber().equals(accountNumber)) {
                        user.addTransaction(transaction);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transaction history found.");
        }
    }
}




