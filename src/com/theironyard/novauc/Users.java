package com.theironyard.novauc;

import java.util.ArrayList;
import java.util.HashMap;

public class Users {

    /****************************************
     * Variables
     *
     * One hash for checking accounts, one
     * for savings
     * Access these with:
     * ATM.users.get("Login").checkingORsavings.get(account)
     ***************************************/
    private HashMap<String, Double> checkingAccounts = new HashMap<>();
    private HashMap<String, Double> savingsAccounts = new HashMap<>();
    private String userName;
    private int pin;

    /****************************************
     * Constructors
     ***************************************/

    public Users(){
        pin = 0000;
        userName = "default";
    }

    public Users(String userName, int pin){
        this.userName = userName;
        this.pin = pin;
    }

    /****************************************
     * Setters
     ***************************************/

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPin(int pin){
        this.pin = pin;
    }

    /****************************************
     * Getters
     ***************************************/

    public String getUserName(){
        return userName;
    }

    public int getPin(){
        return pin;
    }
    public ArrayList getAccounts(String accountType){
        ArrayList<String> accountList = new ArrayList<>();
        if (accountType.equalsIgnoreCase("checking")) {
            for (String accountName : checkingAccounts.keySet()) {
                accountList.add(accountName);
            }
            return accountList;
        } else {
            for (String accountName : savingsAccounts.keySet()){
                accountList.add(accountName);
            }
            return accountList;
        }
    }
    public boolean accountExist(String accountType, String accountName){
        if (accountType.equalsIgnoreCase("checking")){
            if (checkingAccounts.containsKey(accountName.toUpperCase())){
                return true;
            } else {
                return false;
            }
        } else {
            if (savingsAccounts.containsKey(accountName.toUpperCase())){
                return true;
            } else {
                return false;
            }
        }
    }
    public double getBalance(String accountType, String accountName){
        if(accountType.equalsIgnoreCase("checking")){
            return checkingAccounts.get(accountName.toUpperCase());
        } else {
            return savingsAccounts.get(accountName.toUpperCase());
        }
    }
    public boolean isEmpty(String accountType){
        if(accountType.equalsIgnoreCase("checking")){
            return checkingAccounts.isEmpty();
        } else {
            return savingsAccounts.isEmpty();
        }
    }

    /****************************************
     * Other methods
     ***************************************/

    public void deposit( String accountType, String accountName, double amount){
        if (accountType.equalsIgnoreCase("checking")){
            checkingAccounts.put(accountName.toUpperCase(), checkingAccounts.get(accountName.toUpperCase()) + amount);
        } else {
            savingsAccounts.put(accountName.toUpperCase(),  savingsAccounts.get(accountName.toUpperCase()) + amount);
        }
    }

    public void withdraw( String accountType, String accountName, double amount){


        if (accountType.equalsIgnoreCase("checking")){
            checkingAccounts.put(accountName.toUpperCase(), checkingAccounts.get(accountName.toUpperCase()) - amount);
        } else {
            savingsAccounts.put(accountName.toUpperCase(), savingsAccounts.get(accountName.toUpperCase()) - amount);
        }
    }

    public void newAccount(String accountType, String accountName){
        if (accountType.equalsIgnoreCase("checking")){
            checkingAccounts.put(accountName.toUpperCase(), 1.01);
        } else {
            savingsAccounts.put(accountName.toUpperCase(), 1.01);
        }
    }

    public void removeAccount(String accountType, String accountName){
        if (accountType.equalsIgnoreCase("checking")){
            checkingAccounts.remove(accountName.toUpperCase());
        } else {
            savingsAccounts.remove(accountName.toUpperCase());
        }
    }


}
