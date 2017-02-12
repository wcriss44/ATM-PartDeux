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
        double withdrawAmount = 0;
        double balance = 0;

        int i = 1;
        loop: while(i == 1) {
            printAccount(loginName, yourAccount, yourIntChoice);
            System.out.println("Withdraw amount: ");
            withdrawAmount = ATM.inputDouble.nextDouble();
            balance = (double)accounts.get(loginName.toUpperCase()).get(yourAccount + 1);
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

            int[] transferedAccount = User.getTransferAccount(transferTarget);
            int transferAccount = transferedAccount[0];



            int i = 1;
            loop: while (i == 1){
                System.out.println("How much would you like to transfer?");
                System.out.println("Amount: ");
                double transferAmount = ATM.inputDouble.nextDouble();
                double balance =  (double)accounts.get(loginName.toUpperCase()).get(yourAccount + 1);
            if (transferAmount > balance) { //Loop needs fixing! infinite loop found here!!!!!!!!!!!!!!!!!!
                System.out.println("Transfer exceeds available funds!");
            } else {
                accounts.get(loginName.toUpperCase()).set(yourAccount + 1, balance - transferAmount);
                double oldBalance = (double)accounts.get(transferTarget.toUpperCase()).get(transferAccount + 1);
                accounts.get(transferTarget.toUpperCase()).set(transferAccount + 1, oldBalance + transferAmount);
                break loop;
            }
        }

        }

    }
    public static void removeAccount(String loginName){
        System.out.println("Account Remove Area.");
        int[] selectedAccount = Menu.accountDisplayLoop(loginName, 0);
        int deleteAccount = selectedAccount[0];
        System.out.println("Your account is being deleted...");
        accounts.get(loginName.toUpperCase()).remove(deleteAccount);
        accounts.get(loginName.toUpperCase()).remove(deleteAccount);
        System.out.println("All done!");
    }
    public static void addAccount(String loginName){
        int i = 1;
        loop: while(i == 1) {
            System.out.println("What type of account would you like to add? [Checking][Savings]");
            System.out.println("Your choice: ");
            String accountType = ATM.input.nextLine();
            switch(accountType.toUpperCase()){
                case "CHECKING":
                case "SAVINGS":
                    System.out.println("New " + accountType + ".");
                    System.out.println("Your starting balance is $0.00");
                    accounts.get(loginName.toUpperCase()).add(accountType);
                    accounts.get(loginName.toUpperCase()).add(0.00);
                    break loop;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }

        }

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
    public static int[] getTransferAccount(String transferTarget){
        int yourIntChoice = 0;
        int yourAccount;
        System.out.println("Which account do you want to transfer to? ");
        int[] accountSelection = Menu.accountDisplayLoop(transferTarget, 0);
        return accountSelection;
//        System.out.println("Account Management area.");
//        System.out.println("Which account would you like to manage? Choose a number");
//        int displayNumber = 0;
//        int accountMax = User.accounts.get(transferTarget.toUpperCase()).size() - 1;//verify!!!!!!!!!!!!!!!!!!!!!
//        if (accountMax == 2){
//            accountMax = 1;
//        } else {
//            accountMax = ((accountMax - 1) / 2) + 1;
//        }
//        int i = 1;
//        loop: while (i == 1){
//            for(int x = 1; x <= accountMax; x+=2 ){
//                displayNumber++;
//                System.out.print("(" + User.accounts.get(transferTarget.toUpperCase()).get(x) + " ["+ displayNumber + "]" +")");
//            }
//            System.out.println("\nYour choice: ");
//            yourIntChoice =  ATM.inputInt.nextInt();
//            if ( yourIntChoice > accountMax ){
//                System.out.println("Please enter a valid choice!");
//            } else {
//                break loop;
//            }
//        }
//        if (yourIntChoice == 1){
//            yourAccount = 1;
//        } else {
//            yourAccount = (yourIntChoice * 2) - 1;
//        }
    }


}
