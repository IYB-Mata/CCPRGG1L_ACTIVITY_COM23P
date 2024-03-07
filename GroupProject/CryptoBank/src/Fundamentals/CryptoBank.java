package Fundamentals;

import java.util.Scanner;

public class CryptoBank {
    private static final int MAX_ACCOUNTS = 11; // edited also this from 'MAX_ACCOUNTS = 10' because the creation of accounts in array starts from 1 and skip index 0 but the computer will always count index 0 to solve the problem we just need to add 1 on maxAccount.
    private static String[] accountNames = new String[MAX_ACCOUNTS];
    private static int[] accountNumbers = new int[MAX_ACCOUNTS];
    private static double[] balances = new double[MAX_ACCOUNTS];
    private static int accountCount = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to ALT Crypto Bank!");

        do {
            System.out.println("\t[1.] Create New Account");
            System.out.println("\t[2.] Deposit Money");
            System.out.println("\t[3.] Withdraw Money");
            System.out.println("\t[4.] Check Balance");
            System.out.println("\t[5.] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createNewAccount(scanner);
                    break;
                case 2:
                    performTransaction(scanner, true); // true for deposit
                    break;
                case 3:
                    performTransaction(scanner, false); // false for withdraw
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using ALT Crypto Bank!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
// CASE 1: Creating new account
    private static void createNewAccount(Scanner scanner) {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("Maximum account limit reached.");
            return;
        }

        System.out.print("Enter Account Name: ");
        String accountName = scanner.next();

        accountNames[accountCount] = accountName;
        accountNumbers[accountCount] = accountCount ; 
        balances[accountCount] = 0.0;
       

        System.out.println("Account created successfully. Your account number is: " + accountCount);
        accountCount++;
    }
    // I added a feature here...
    private static void performTransaction(Scanner scanner, boolean isDeposit) {
        System.out.print("Enter Account Number: ");
        int accountNumber; // edited this
        // conditional statement  for scanner to know if the inputted account number is in integer otherwise will prompt again to menu.
        if(scanner.hasNextInt()) {
        	accountNumber = scanner.nextInt();
        } else {
        		System.out.println("Invalid input, please enter integers only.");
        		scanner.next();  // consume the invalid input
                return;
        	}// till here.
        
        if (accountNumber < 0 || accountNumber >= accountCount) {
            System.out.println("Invalid account number.");
            return;
        }
    // also here......
        System.out.print("Enter Amount: ");
        double amount; // edited this
//from here...
        if(scanner.hasNextDouble()) {
        	amount = scanner.nextDouble();
        }else {
        	System.out.println("Invalid input, please enter integers only");
        	scanner.next(); // consume the invalid input
        	return;
        } // to here.....
        
        if (isDeposit) {
            balances[accountNumber] += amount;
            System.out.println("Amount deposited successfully. Current Balance: " + balances[accountNumber]);
        } else {
            if (amount <= balances[accountNumber]) {
                balances[accountNumber] -= amount;
                System.out.println("Amount withdrawn successfully. Remaining Balance: " + balances[accountNumber]);
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber < 0 || accountNumber >= accountCount) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.println("Account Name: " + accountNames[accountNumber] + " - Current Balance: " + balances[accountNumber]);
    }
}