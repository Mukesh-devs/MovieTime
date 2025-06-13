package util;

import java.util.Scanner;

public class Util {
    public final String RED = "\u001B[31m";
    public final String GREEN = "\u001B[32m";
    public final String RESET = "\u001B[0m";
    public static final Scanner scanner = new Scanner(System.in);

    public static void printError(String error) {
        System.out.println("Error : " + error);
    }

    public static String readLine() {
        return scanner.nextLine();
    }
    public static int readInt() {
        return scanner.nextInt();
    }

    public static void prompt(String prompt) {
        System.out.println(prompt);
    }
    public static void message(String msg) {
        System.out.println(msg);
    }

    public static int choice() {
        System.out.println("Enter Your Choice : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
