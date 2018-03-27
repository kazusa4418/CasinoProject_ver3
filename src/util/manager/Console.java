package util.manager;

import java.io.IOException;

public class Console {
    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.getStackTrace();
        }
    }
}
