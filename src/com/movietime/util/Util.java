package com.movietime.util;

import com.movietime.dto.User;

import java.util.Scanner;

public class Util {
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final Scanner scanner = new Scanner(System.in);

    public static int loggedInUserId = -1;
    public static User loggedInUser;

    public static void printError(String error) {
        System.out.println(RED + "❌ Error : " + error + RESET);
    }

    public static String readLine() {
        String input = scanner.nextLine();
        if ( input.trim().equalsIgnoreCase("exit")) {
            return null;
        }
        return input;
    }
    public static int readInt() {
        while (true ) {
            String input = readLine();
            if ( input == null) {
                return Integer.MIN_VALUE;
            }
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                printError("Invalid input. Please enter a number.");
                prompt("Please try again");
            }
        }
    }

    public static void prompt(String prompt) {
        System.out.println(prompt);
    }
    public static void message(String msg) {
        System.out.println(BLUE + msg + RESET);
    }

    public static int choice() {
        System.out.println("Enter Your Choice : ");
        return readInt();
    }

    public static void printSuccess(String success) {
        System.out.println(GREEN + "✅ " + success + RESET);
    }
}
