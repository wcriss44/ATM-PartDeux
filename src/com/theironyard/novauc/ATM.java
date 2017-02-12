package com.theironyard.novauc;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    public static HashMap<String, Users> users = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner scannerDbl = new Scanner(System.in);
    public static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        AtmMenu menu = new AtmMenu();
        menu.bootUp();
    }
}