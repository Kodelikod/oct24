package Utility;

import java.util.Scanner;

public class Scan {

    static Scanner scan = new Scanner(System.in);
    public static final String EOL = System.lineSeparator();

    // Methods for reading input without printing
    public static String scan() {
        return scan.next();
    }
    public static String scanLine() {
        return scan.nextLine();
    }
    public static int scanInt() {
        return scan.nextInt();
    }
    public static double ScanDouble() {
        return scan.nextDouble();
    }
    public static long ScanLong() {
        long aLong = scan.nextLong();
        scan.nextLine();
        return aLong;
    }

    // Methods for output and scanning input
    public static String readLine(String output) {

        System.out.print(output);
        String line = scan.nextLine();
        System.out.println("");
        return line;
    }

    public static int readInt(String output) {
        System.out.println(output);
        int anInt = scan.nextInt();
        scan.nextLine();
        System.out.println("");
        return anInt;
    }

    public static double readDouble(String output) {
        System.out.println(output);
        double aDouble = scan.nextDouble();
        scan.nextLine();
        System.out.println("");
        return aDouble;
    }

    public static long readLong(String output) {
        System.out.println(output);
        long aLong = scan.nextLong();
        scan.nextLine();
        System.out.println("");
        return aLong;
    }


    //system.out.println method
    public static void print(String output){
        System.out.println(output + EOL);
    }


    public static void closeScanner() {
        scan.close();
    }
}


