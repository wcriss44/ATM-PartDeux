package com.theironyard.novauc;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    /*******************
     * Variables
     ******************/
    public static HashMap<String, ArrayList> accounts = new HashMap();



    /*******************
     * Other methods
     ******************/
    public static void printAccount(String loginName, int yourAccount, int yourIntChoice){
        System.out.println("Account: " + User.accounts.get(loginName.toUpperCase()).get(yourAccount) + " [" + yourIntChoice + "]");
    }

    public static void withdraw(String loginName, int yourAccount, int yourIntChoice){
        printAccount(loginName, yourAccount, yourIntChoice);
        System.out.println("Withdraw amount: ");
        double withdrawAmount = ATM.inputDouble.nextDouble();
        double balance =  (double)accounts.get(loginName.toUpperCase()).get(yourAccount + 1);
        int i = 1;
        loop: while(i == 1) {
            if (withdrawAmount > balance) {
                System.out.println("Withdraw exceeds available funds!");
            } else {
                accounts.get(loginName.toUpperCase()).set(yourAccount + 1, balance - withdrawAmount);
                break loop;
            }
        }
        System.out.println("Success!");
        printAccount(loginName, yourAccount, yourIntChoice);

    }
    public static void deposit(String loginName, int yourAccount, int yourIntChoice){
        printAccount(loginName, yourAccount, yourIntChoice);
        System.out.println("Deposit amount: ");
        double depositAmount = ATM.inputDouble.nextDouble();
        double balance = (double)accounts.get(loginName.toUpperCase()).get(yourAccount + 1);
        accounts.get(loginName.toUpperCase()).set(yourAccount + 1, balance + depositAmount);
        System.out.println("Success!");
        printAccount(loginName, yourAccount, yourIntChoice);

    }
    public static void checkBalance(String loginName, int yourAccount, int yourIntChoice){
        printAccount(loginName, yourAccount, yourIntChoice);
        double balance = (double)accounts.get(loginName.toUpperCase()).get(yourAccount + 1);
        System.out.print("Your account balance is: ");
        System.out.printf("%.2f", balance);
        System.out.println("");

    }
    public static void transfer(String loginName, int yourAccount, int yourIntChoice){
        printAccount(loginName, yourAccount, yourIntChoice);
        System.out.println("Transfer funds.");

        System.out.println("How would you like to transfer to?");
        System.out.println("Account user name: ");
        String transferTarget = ATM.input.nextLine();
        if (ATM.users.containsKey(transferTarget.toUpperCase())) {
            System.out.println("Account user found.");
            System.out.println("Which account do you want to transfer to?");

        System.out.println("How much would you like to transfer?");
        System.out.println("Amount: ");

        double transferAmount = ATM.inputDouble.nextDouble();
        double balance =  (double)accounts.get(loginName.toUpperCase()).get(yourAccount + 1);

        int i = 1;
        loop: while (i == 1){
            if (transferAmount > balance) {
                System.out.println("Transfer exceeds available funds!");
            } else {
                accounts.get(loginName.toUpperCase()).set(yourAccount + 1, balance - transferAmount);
                break loop;
            }
        }

        }

    }
    public void removeAccount(){

    }
    public void addAccount(){

    }
    /*******************
     * Static methods
     ******************/
    public static void AddUser(){
        String accountType ="";
        String newName;
        String pin = "";
        double newBalance;
        System.out.println("New User Registration");
        System.out.print("Add a new user: ");
        newName = ATM.input.nextLine();
        System.out.println("Thank you, " + newName + "!");
        int i = 1;
        loop: while (i == 1){
            System.out.println("Enter a 4 character pin: ");
            pin = ATM.input.nextLine();
            if (pin.length() == 4){
                System.out.println("Great! Your pin is set");
                break loop;
            } else {
                System.out.println("Please only enter 4 characters");
            }
        }


       loop: while (i == 1) {
            System.out.println("Account types available: [Savings][Checking]");
            System.out.print("Account type: ");
            accountType = ATM.input.nextLine();
             switch (accountType.toUpperCase()){
                 case "SAVINGS":
                 case "CHECKING":
                     break loop;
                 default:
                     System.out.println("Please enter a valid account type.");
             }
        }
        System.out.println("Great! you are opening a " + accountType +".");
        System.out.println("How much is your initial deposit?");
        System.out.print("Deposit amount: ");
        newBalance = ATM.inputDouble.nextDouble();

        ATM.users.put(newName.toUpperCase(), pin);
        accounts.put(newName.toUpperCase(), new ArrayList());
        accounts.get(newName.toUpperCase()).add(0, newName);
        accounts.get(newName.toUpperCase()).add(1, accountType);
        accounts.get(newName.toUpperCase()).add(2, newBalance);
        System.out.println("Great! You are " + newName + ", and you are opening a new " + accountType + " with a balance of $" + newBalance + "!" );

    }
    public static void getTransferAccount(){
        
    }


}
