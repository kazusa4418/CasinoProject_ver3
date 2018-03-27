package casino;

import util.manager.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Printer {
    private static BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));

    private Printer() {
    }

    public static void print(String msg) {
        System.out.print(msg);
        pressEnter();
    }

    public static void println(String msg) {
        System.out.println(msg);
        pressEnter();
    }

    public static void pleaseEnter() {
        pressEnter();
    }

    public static void dot(int dotNum) {
        for (int i = 0; i < dotNum; i++ ) {
            System.out.print(". ");
            Console.sleep(1000);
        }
        System.out.println();
    }

    private static void pressEnter() {
        try {
            reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
