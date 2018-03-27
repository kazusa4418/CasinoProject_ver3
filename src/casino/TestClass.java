package casino;

import java.io.File;

public class TestClass {
    public static void main(String[] args) {
        String fileName = new File("C:\\users\\astar\\desktop\\test.txt").getName();
        int startIdx = fileName.lastIndexOf(".");
        fileName = fileName.substring(0, startIdx);
        System.out.println(fileName);

    }
}
