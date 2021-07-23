package eway.bai1.example1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        String input = "src/eway/bai1/example1/input.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(input);
            Scanner readFile = new Scanner(fileInputStream);

            List<String> listStringFileInput = new ArrayList<>();
            while (readFile.hasNextLine()) {
                listStringFileInput.add(readFile.nextLine());
            }

            // tao 1 chuoi duy nhat tu file input
            String strCreatByFileInput = String.join(" ", listStringFileInput);

            //in ra ki tu in hoa
            printUpperCase(strCreatByFileInput);

            //tong so ki tu
            System.out.println("tong so ki tu: " + sumChar(strCreatByFileInput));

            // tong so ki tu thuong
            System.out.println("tong so ki tu thuong: " + sumCharUpperCase(strCreatByFileInput));

            String output = "src/eway/bai1/example1/output1.txt";
            FileWriter fileWriter = new FileWriter(output);
            for (String stringElement : listStringFileInput
            ) {
                // bỏ khoảng trắng thừa và ghi lại vào file output
                String stringRemoveSpace = Demo.removeSpace(stringElement);
                // xử lý thêm chuỗi và toUpperCase chuỗi
                String stringHandle = Demo.handleString(stringRemoveSpace);
                if (stringHandle != null) {
                    fileWriter.write(stringHandle);
                } else {
                    fileWriter.write(stringRemoveSpace);
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
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
    // loại bỏ khoảng trắng thừa
    static String removeSpace(String str) {
        String regexRemoveSpace = "\\s\\s+";
        return str.replaceAll(regexRemoveSpace, " ").trim();
    }
    // xử lí chuỗi theo yêu cầu của đề bài là thêm và đổi chuỗi viết hoa
    static String handleString(String text) {
        String charFilter = "$";
        String textFilter = "Toi yeu ha noi pho";
        String textAddFileOutput1 = "o con ga cua toi";
        if (text.contains(charFilter)) return text.concat(" " + textAddFileOutput1);
        if (text.equals(textFilter)) return text.toUpperCase();
        return null;
    }

}
