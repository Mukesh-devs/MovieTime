package util;

import java.util.Scanner;

public class Util {

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

    public static int choice() {
        System.out.println("Enter Your Choice : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
