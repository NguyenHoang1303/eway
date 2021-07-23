package test;


import java.io.*;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("test.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            while (scanner.hasNext()) {
                writer.write(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//        try {
//            Scanner scanner = new Scanner(new File("test.txt"));
//            PrintWriter writer = new PrintWriter(new File("testWrite.txt"));
//            while (scanner.hasNext()) {
//                writer.write(scanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}
