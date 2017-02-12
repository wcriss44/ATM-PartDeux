package com.theironyard.novauc;

/**
 * Created by willc on 2/9/17.
 */
public class Menu {
    int yourIntChoice;
    int yourAccount;
    String yourChoice;
    String loginName;
    String pin;

    public void ATMOnline() {
        System.out.println("ATM now online!");
        int i = 1;
        while (i == 1) {
            System.out.println("Please choose: [Add User][Login][Exit]");
            System.out.print("your choice: ");
            yourChoice = ATM.input.nextLine();

            switch (yourChoice.toUpperCase()) {
                case "ADD USER":
                case "ADDUSER":
                    addUser();
                    break;
                case "LOGIN":
                    login();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }
    }
    public void addUser(){
        System.out.println("Add a user menu");
        int i = 1;
        while (i == 1){
            System.out.println("Please choose: [Add New][Last Menu]");
            System.out.print("Your choice: ");
            yourChoice = ATM.input.nextLine();

            switch(yourChoice.toUpperCase()){
                case "ADD NEW":
                case "ADDNEW":
                    System.out.println("Added a new user");
                    User.AddUser();
                    break;
                case "LAST MENU":
                case "LASTMENU":
                    System.out.println("\nReturning to last menu...");
                    return;
                default:
                    System.out.println("Please make a correct selection: ");
                    break;
            }
        }
    }
    public void login(){
        System.out.println("Login screen");
        System.out.print("User: ");
        loginName = ATM.input.nextLine();
        if (ATM.users.containsKey(loginName.toUpperCase())){
            System.out.println("Welcome, " + loginName + "!");
            System.out.print("Please enter your pin: ");
            pin = ATM.input.nextLine();
            if (pin.equalsIgnoreCase(ATM.users.get(loginName.toUpperCase()))){
                loginMenu();
            } else {
                System.out.println("Invalid pin!");
            }
        } else {
            System.out.println("User does not exist!");


            int i = 1;
            loop: while(i == 1) {
                System.out.println("Would you like to create a new user? [Y][N]");
                System.out.print("Your choice: ");
                yourChoice = ATM.input.nextLine();
                switch (yourChoice.toUpperCase()) {

                    case "Y":
                        addUser();
                        break;
                    case "N":
                        break loop;
                    default:
                        System.out.println("Please enter a valid choice.");

                }
            }
        }

    }
    public void loginMenu(){
        System.out.println("Welcome to your Account menu!");
        int i = 1;
        while(i == 1) {
            System.out.println("You can: [Admin][Manage Account][Logout]");
            System.out.print("Your choice: ");
            yourChoice = ATM.input.nextLine();
            switch (yourChoice.toUpperCase()){
                case "ADMIN":
                    adminMenu(loginName);
                    break;
                case "MANAGE ACCOUNT":
                case "MANAGEACCOUNT":
                    manageAccount();
                    break;
                case "LOGOUT":
                    return;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        }


    }
    public  void manageAccount(){
        //System.out.println(accounts.get(loginName.toUpperCase()).get(0));
        int[] accountSelection = accountDisplayLoop(loginName, yourIntChoice);
        yourAccount = accountSelection[0];
        yourIntChoice = accountSelection[1];
        int i = 1;
        loop: while (i == 1){
            System.out.println("You are managing: " + User.accounts.get(loginName.toUpperCase()).get(yourAccount) + " [" + yourIntChoice + "]");
            System.out.println("What would you like to do?");
            System.out.println("[Withdraw][Deposit][Transfer][Check Balance][Last Menu]");
            yourChoice = ATM.input.nextLine();
            switch(yourChoice.toUpperCase()){
                case "WITHDRAW":
                    User.withdraw(loginName, yourAccount, yourIntChoice);
                    break;
                case "DEPOSIT":
                    User.deposit(loginName, yourAccount, yourIntChoice);
                    break;
                case "TRANSFER":
                    User.transfer(loginName, yourAccount, yourIntChoice);
                    break;
                case "CHECK BALANCE":
                    User.checkBalance(loginName, yourAccount, yourIntChoice);
                    break;
                case "LAST MENU":
                case "LASTMENU":
                    break loop;
                default:
                    System.out.println("Please put in a correct value.");
            }

        }
    }
    public static int[] accountDisplayLoop(String loginName, int yourIntChoices){
        int yourIntChoice = yourIntChoices;
        //System.out.println("Account Management area.");
        System.out.println("Your accounts are listed by number. Please Choose a number");
        int yourAccount = 0;
        int displayNumber = 0;
        int accountMax = User.accounts.get(loginName.toUpperCase()).size() - 1;//verify!!!!!!!!!!!!!!!!!!!!!
        if (accountMax == 2){
            accountMax = 1;
        } else {
            accountMax = ((accountMax - 1) / 2) + 1;
        }
        int i = 1;
        loop: while (i == 1){
            for(int x = 1; x <= accountMax; x+=2 ){
                displayNumber++; //FIX! LOOP displays the wrong amount!!!!!!!!!!!!!!!!!!!!!!!!!!!
                System.out.print("(" + User.accounts.get(loginName.toUpperCase()).get(x) + " ["+ displayNumber + "]" +")");
            }
            System.out.println("\nYour choice: ");
            yourIntChoice =  ATM.inputInt.nextInt();
            if ( yourIntChoice > accountMax ){
                System.out.println("Please enter a valid choice!");
            } else {
                break loop;
            }
        }
        if (yourIntChoice == 1){
            yourAccount = 1;
        } else {
            yourAccount = (yourIntChoice * 2) - 1;
        }
         int[]accountSelection = {yourAccount, yourIntChoice};
        return accountSelection;
    }
    public void exit(){

    }
    public void adminMenu(String loginName){
        System.out.println("Welcome to Account Administration!");
        int i = 1;
        while(i == 1) {
            System.out.println("You can: [Add Account][Remove Account][Last menu]");
            System.out.print("Your choice: ");
            yourChoice = ATM.input.nextLine();
            switch (yourChoice.toUpperCase()){
                case "ADD ACCOUNT":
                case "ADDACCOUNT":
                    User.addAccount(loginName);
                    break;
                case "REMOVE ACCOUNT":
                case "REMOVEACCOUNT":
                    User.removeAccount(loginName);
                    break;
                case "LAST MENU":
                case "LASTMENU":
                    return;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        }


    }
}
