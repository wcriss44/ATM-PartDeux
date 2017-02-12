package com.theironyard.novauc;

import java.util.ArrayList;
import java.util.Iterator;

public class AtmMenu {

    /********************
     * Variables
     *******************/

    public int i = 1; // menu loop ONLY. break with label

    /********************
     * Other Methods
     *******************/

    public void bootUp() {
        //Stay in this loop until time to exit
        String user;
        loop:
        while (i == 1) {
            starMaker("\t\t\tATM Part Deuce");

            //Switch the return from menuLoop method
            switch (menuLoop("New User", "Login", "Exit")) {
                case "New User":
                    newUser();
                    break;
                case "Login":
                    user = login();
                    if (user.equals("invalid")) {
                        break;
                    } else {
                        accountMenu(user);
                        break;
                    }
                case "Exit":
                    System.exit(0);
                default:
                    System.out.println("*ERROR*\tPlease make a valid choice.\n");
            }
        }
    }

    //Only called from bootUp menu loop
    public void newUser() {
        String newUserChoice;

        loop:
        while (i == 1) {
            starMaker("newUser");

            System.out.println("[Add User][Return]");
            System.out.print("\nYour choice: ");
            newUserChoice = ATM.scanner.nextLine();

            switch (newUserChoice.toUpperCase()) {
                case "ADD USER":
                    addUser();
                    break loop;
                case "RETURN":
                    return; //this return will exit method returning to bootUp menu loop
                default:
                    System.out.println("*ERROR*\tPlease make a valid choice.\n");
            }
        }
    }

    public void addUser() {
        String newUser;
        int newUserPin;
        while (i == 1) {
            starMaker("Add User");
            System.out.println("Please enter a new Username");
            System.out.print("\nUser name: ");
            newUser = ATM.scanner.nextLine();

            if (ATM.users.containsKey(newUser.toUpperCase())) {
                System.out.println("*ERROR*\tUser already exist\n");
                System.out.println("Please enter a NEW user!");
            } else {
                while (i == 1) {
                    System.out.println("Please enter a new four digit pin.");
                    System.out.print("\nYour Pin: ");
                    newUserPin = ATM.scannerInt.nextInt();
                    if (newUserPin > 9999) {
                        System.out.println("Use only four digits");
                    } else {
                        System.out.println("Registration successful!");
                        ATM.users.put(newUser.toUpperCase(), new Users(newUser, newUserPin));
                        return;
                    }
                }
            }

        }

    }

    public String login() {
        String loginName = "";
        int loginPin;
        loop:
        while (i == 1) {
            starMaker("\t\t\tLogin Screen");
            System.out.println("Please enter a user name.");
            System.out.print("\nUser name: ");
            loginName = ATM.scanner.nextLine().toUpperCase();

            if (ATM.users.containsKey(loginName)) {
                System.out.println("\nHello, " + loginName + "! Please enter your pin.");
                System.out.print("\nYour pin: ");
                loginPin = ATM.scannerInt.nextInt();

                if (loginPin == ATM.users.get(loginName).getPin()) {
                    System.out.println("Access verification successful!");
                    System.out.println("Welcome, " + loginName);
                    break loop;
                }

            } else {
                System.out.println("*ERROR*\tPlease enter a valid username.\n");
                System.out.println("If you need one, please create a new user");
                System.out.println("Returning to main menu...");
                return "invalid";
            }

        }
        return loginName;
    }

    public void accountMenu(String loginName) {
        String accountType = "";
        String accountName = "";
        loop:
        while (i == 1) {
            starMaker("Customer Menu\t\t\t\t" + loginName);
            switch (menuLoop("Open Account", "Manage Account", "Logout")) {
                case "Open Account":
                    accountType = accountPicker();
                    accountName = accountNamePicker(loginName, accountType);

                    ATM.users.get(loginName).newAccount(accountType, accountName);
                    System.out.println("Creation successful!");
                    break;
                case "Manage Account":
                    accountType = accountPicker("New Bank Account\t\t\t" + loginName);
                    accountName = chooseAccount(loginName, accountType);
                    if (accountName.equalsIgnoreCase("invalid")){
                        break;
                    }
                    accountManagement(loginName, accountType, accountName);
                    break;
                case "Logout":
                    break loop;
                default:
                    System.out.println("*ERROR*\tPlease make a valid choice.\n");
                    break;
            }
        }
    }

    public void accountManagement(String loginName, String accountType, String accountName) {
        double balance;

        while (i == 1) {
            balance = ATM.users.get(loginName).getBalance(accountType, accountName);
            switch (managementLoop(loginName, accountType, accountName, balance)) {

                case "WITHDRAW": //working
                    withdraw(loginName, accountType, accountName, balance);
                    break;
                case "DEPOSIT": //working
                    deposit(loginName, accountType, accountName, balance);
                    break;
                case "TRANSFER": //working! ^ ^     Cheers!
                    transfer(loginName, accountType, accountName, balance);
                    break;
                case "CLOSE ACCOUNT"://working!
                    if (closeAccount(loginName, accountType, accountName, balance)){
                        ATM.users.get(loginName).removeAccount(accountType, accountName);
                        return;
                    }
                        break;
                case "RETURN":
                    return;
                default:
                    break;

            }
        }
    }

    public void withdraw(String loginName, String accountType, String accountName, double balance) {
        starMaker("Withdraw", loginName, accountType, accountName, balance);
        while (i == 1) {
            System.out.println("\nWithdraw Amount: ");
            double withdrawAmount = ATM.scannerDbl.nextDouble();
            if (withdrawAmount > balance) {
                System.out.println("*ERROR*\tWithdraw exceeds funds!");
                System.out.println("Please enter a valid amount!\n");
                return;
            } else {
                ATM.users.get(loginName).withdraw(accountType, accountName, withdrawAmount);
                return;
            }
        }
    }

    public void deposit(String loginName, String accountType, String accountName, double balance) {
        starMaker("\t  Deposit", loginName, accountType, accountName, balance);
        System.out.print("\nDeposit amount: ");
        double depositAmount = ATM.scannerDbl.nextDouble();
        ATM.users.get(loginName).deposit(accountType, accountName, depositAmount);
    }

    public void transfer(String loginName, String accountType, String accountName, double balance) {
        starMaker("Withdraw", loginName, accountType, accountName, balance);
        String transferTarget;
        String transferTargetAcc;
        String transferTargetAccName;
        double transferAmount;

        while (i == 1) {
            System.out.println("\nWhom would you like to transfer to?");
            System.out.print("User: ");
            transferTarget = ATM.scanner.nextLine().toUpperCase();
            if (ATM.users.containsKey(transferTarget)) {
                transferTargetAcc = accountPicker().toUpperCase();
                transferTargetAccName = chooseAccount(transferTarget, transferTargetAcc).toUpperCase();
                    if (transferTargetAccName.equalsIgnoreCase("invalid")){
                        return;
                    }
                System.out.print("\nTransfer Amount: ");
                transferAmount = ATM.scannerDbl.nextDouble();
                if (transferAmount > balance) {
                    System.out.println("*ERROR*\tTransfer exceeds funds!");
                    System.out.println("Please enter a valid amount!\n");
                    return;
                } else {
                    System.out.println("Transfer is successful!");
                    ATM.users.get(loginName).withdraw(accountType, accountName, transferAmount);
                    ATM.users.get(transferTarget).deposit(transferTargetAcc, transferTargetAccName, transferAmount);
                    return;
                }
            } else {
                System.out.println("*ERROR*\tUser not found. Please try again!\n");
            }
        }
    }

    public boolean closeAccount(String loginName, String accountType, String accountName, double balance) {
        String choice;
        while (i == 1) {
            starMaker("CloseAccount", loginName, accountType, accountName, balance);
            System.out.println("This account is ready for deletion.");
            System.out.println("\n***WARNING!*** \tthis is final!");
            System.out.println("Are you wish to delete it? [Y][N]");
            choice = ATM.scanner.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                System.out.println("Account deletion process started...");
                return true;
            } else if (choice.equals("N")) {
                System.out.println("Returning to previous menu...");
                return false;
            } else {
                System.out.println("*ERROR* Please make a valid choice!\n");
            }

        }
        return false;
    }

    public String chooseAccount(String loginName, String accountType) {
        ArrayList accountNames;
        String account;
        String accountChoice;
        int loopChecker = 0;

        if (ATM.users.get(loginName).isEmpty(accountType)){
            System.out.println("*ERROR*\tNo accounts exist\n");
            return "invalid";
        }

        while (i == 1) {
            System.out.println("Available accounts: ");
            accountNames = ATM.users.get(loginName).getAccounts(accountType);
            Iterator<String> accListIters = accountNames.iterator();
            while (accListIters.hasNext()) {
                loopChecker++;
                if (loopChecker == 5) {
                    System.out.println("");
                    loopChecker = 1;
                }
                account = accListIters.next().toLowerCase();
                System.out.print("[" + account + "]");
            }
            if (loopChecker == 0) {
                System.out.println("No accounts found. Returning to previous menu");
                return "invalid";
            }
            System.out.print("\n\nPlease make a selection: ");
            accountChoice = ATM.scanner.nextLine();
            if (ATM.users.get(loginName).accountExist(accountType, accountChoice)) {
                return accountChoice;
            } else {
                System.out.println("*ERROR*\tNo account found. Please try again.\n");
            }
        }
        return "invalid";
    }

    public String accountNamePicker(String loginName, String accountType) {
        String accountName;
        while (i == 1) {
            System.out.println("Please choose a name for your account.");
            System.out.print("\nYour choice: ");
            accountName = ATM.scanner.nextLine().toUpperCase();
            if (ATM.users.get(loginName).accountExist(accountType, accountName)) {
                System.out.println("*ERROR*\tAccount already exist");
                System.out.println("Please enter a new account.\n");
            } else {
                return accountName;
            }
        }
        return "Invalid";
    }

    public String accountPicker() {
        String accountType;
        while (i == 1) {
            System.out.println("Select account type: [Savings][Checking]");
            System.out.print("\nYour choice: ");
            accountType = ATM.scanner.nextLine().toUpperCase();

            if (accountType.equals("SAVINGS") || accountType.equals("CHECKING")) {
                return accountType;
            } else {
                System.out.println("*ERROR*\tPlease make a valid choice.\n");
            }
        }
        return "invalid";
    }

    public String managementLoop(String loginName, String accountType, String accountName, double balance) {
        String yourChoice;
        while (i == 1) {
            starMaker("Account Management", loginName, accountType, accountName, balance);
            System.out.println("[Withdraw][Deposit][Transfer][Close Account][Return]\n");
            System.out.print("\nYour choice: ");
            yourChoice = ATM.scanner.nextLine().toUpperCase();

            if (yourChoice.equals("WITHDRAW") || yourChoice.equals("DEPOSIT") || yourChoice.equals("TRANSFER") || yourChoice.equals("CLOSE ACCOUNT") || yourChoice.equals("RETURN")) {
                return yourChoice;
            } else {
                System.out.println("*ERROR*\tPlease make a valid choice.\n");
            }

        }
        return "invalid";
    }

    public String menuLoop(String first, String second, String third) {
        String yourChoice;

        loop:
        while (i == 1) {
            System.out.println("Options: " + "[" + first + "]" + "[" + second + "]" + "[" + third + "]");
            System.out.print("\nYour choice: ");
            yourChoice = ATM.scanner.nextLine();
            if (yourChoice.equalsIgnoreCase(first)) {
                return first;
            } else if (yourChoice.equalsIgnoreCase(second)) {
                return second;
            } else if (yourChoice.equalsIgnoreCase(third)) {
                return third;
            } else {
                System.out.println("*ERROR*\tPlease make a valid choice.\n");
            }
        }
        return "Invalid";
    }

    //Display maker with star box for general menus
    public void starMaker(String label) {
        for (int i = 0; i < 54; i++) {
            System.out.print("*");
        }
        System.out.println("\n*  " + label);
        for (int i = 0; i < 54; i++) {
            System.out.print("*");
        }
        System.out.println("");
    }

    //Display maker with star box for account menus
    public void starMaker(String area, String loginName, String accountType, String accountName, double balance) {
        for (int i = 0; i < 54; i++) {
            System.out.print("*");
        }
        System.out.println("\n*\t\t\t" + area);
        System.out.println("*  " + "User: " + loginName.toLowerCase() + "\t\tAccount Name: " + accountName.toLowerCase());
        System.out.print("* " + "Account Type: " + accountType.toLowerCase() + "\t\tBalance: $");
        System.out.printf("%.2f", balance);
        System.out.println("");
        for (int i = 0; i < 54; i++) {
            System.out.print("*");
        }
        System.out.println("");
    }

    public String accountPicker(String area) {
        String accountType;
        starMaker(area);
        while (i == 1) {
            System.out.println("You can select: [Savings][Checking]");
            System.out.print("\nYour choice: ");
            accountType = ATM.scanner.nextLine().toUpperCase();

            if (accountType.equals("SAVINGS") || accountType.equals("CHECKING")) {
                return accountType;
            } else {
                System.out.println("*ERROR*\tPlease make a valid choice.\n");
            }
        }
        return "invalid";
    }
}

