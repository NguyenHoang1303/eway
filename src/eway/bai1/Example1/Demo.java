package eway.bai1.Example1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {

        String input = "src/eway/bai1/Example1/input.txt";


        try {
            FileInputStream fileInputStream = new FileInputStream(input);
            Scanner scanner = new Scanner(fileInputStream);

            List<String> listStr = new ArrayList<>();
            while (scanner.hasNextLine()) {
                listStr.add(scanner.nextLine());
            }

            // tao 1 chuoi duy nhat tu mang listStr
            String str = String.join(" ", listStr);

            //in ra ki tu in hoa
            printUpperCase(str);

            //tong so ki tu
            System.out.println("tong so ki tu: " + sumChar(str));

            // tong so ki tu thuong
            System.out.println("tong so ki tu thuong: " + sumCharUpperCase(str));

            String text = "Toi yeu ha noi pho";
            String output = "src/eway/bai1/Example1/output1.txt";
            FileWriter fileWriter = new FileWriter(output);
            boolean isMatches;
            for (String strElement : listStr
            ) {
                // bo khoang trang thua
                String strNew = strElement.replaceAll("\\s\\s+", " ").trim();
                //kiem tra trong chuoi co ki tu $ ko
                isMatches = strNew.contains("$");
                String strOutput = "o con ga cua toi";
                if (isMatches) {
                    String stringMatches = strNew.concat(" " + strOutput);
                    fileWriter.write(stringMatches);
                } else if (strNew.equals(text)) {
                    fileWriter.write(strNew.toUpperCase());
                } else {
                    fileWriter.write(strNew);
                }
                fileWriter.write("\n");
            }
            fileWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    // tinh tong ki tu
    static int sumChar(String str) {
        int count = 0;
        boolean isUpperCase;
        boolean isLowerCase;
        for (char chr : str.toCharArray()
        ) {
            isUpperCase = (chr >= 'A' && chr <= 'Z');
            isLowerCase = (chr >= 'a' && chr <= 'z');
            if (isLowerCase || isUpperCase) count++;
        }
        return count;
    }

    // tinh tong ki tu thuong
    static int sumCharUpperCase(String str) {
        int count = 0;
        boolean isLowerCase;
        for (char chr : str.toCharArray()) {
            isLowerCase = (chr >= 'a' && chr <= 'z');
            if (isLowerCase) count++;
        }
        return count;
    }

    // In cac ki tu in hoa
    static void printUpperCase(String str) {
        int count = 0;
        for (char chr : str.toCharArray()
        ) {
            if (chr >= 'A' && chr <= 'Z') {
                count++;
                System.out.printf("ki tu in hoa thu %d: %c\n", count, chr);
            }
        }
    }


}
