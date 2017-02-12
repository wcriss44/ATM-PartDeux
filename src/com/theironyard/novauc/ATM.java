package com.theironyard.novauc;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    public static Scanner input = new Scanner(System.in);
    public static Scanner inputDouble = new Scanner(System.in);
    public static Scanner inputInt = new Scanner(System.in);
    public static Menu menu = new Menu();
    public static HashMap<String, String> users = new HashMap();

    public static void main(String[] args) {
        //users.put("WILL", "3499");
        menu.ATMOnline();

    }
}
