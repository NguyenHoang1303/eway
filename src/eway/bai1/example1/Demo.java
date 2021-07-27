package eway.bai1.example1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        String input = "src/eway/bai1/example1/input.txt";

            List<String> listStringFileInput = Demo.readFile(input);
            // tao 1 chuoi duy nhat tu file input
            String strCreatByFileInput = String.join(" ", listStringFileInput);

            //in ra ki tu in hoa
            printUpperCase(strCreatByFileInput);

            //tong so ki tu
            System.out.println("tong so ki tu: " + sumChar(strCreatByFileInput));

            // tong so ki tu thuong
            System.out.println("tong so ki tu thuong: " + sumCharUpperCase(strCreatByFileInput));

            String output = "src/eway/bai1/example1/output1.txt";
        try {
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

    static  List<String> readFile(String path){
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner readFile = new Scanner(fileInputStream);
            while (readFile.hasNextLine()) {
                list.add(readFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    // tinh tong ki tu
    static int sumChar(String text) {
        int count = 0;
        boolean isUpperCase;
        boolean isLowerCase;
        for (char characterInText : text.toCharArray()
        ) {
            isUpperCase = (characterInText >= 'A' && characterInText <= 'Z');
            isLowerCase = (characterInText >= 'a' && characterInText <= 'z');
            if (isLowerCase || isUpperCase) count++;
        }
        return count;
    }

    // tinh tong ki tu thuong
    static int sumCharUpperCase(String text) {
        int count = 0;
        boolean isLowerCase;
        for (char characterInText : text.toCharArray()) {
            isLowerCase = (characterInText >= 'a' && characterInText <= 'z');
            if (isLowerCase) count++;
        }
        return count;
    }

    // In cac ki tu in hoa
    static void printUpperCase(String text) {
        int count = 0;
        for (char characterInText : text.toCharArray()
        ) {
            if (characterInText >= 'A' && characterInText <= 'Z') {
                count++;
                System.out.printf("ki tu in hoa thu %d: %c\n", count, text);
            }
        }
    }
    // loại bỏ khoảng trắng thừa
    static String removeSpace(String text) {
        String regexRemoveSpace = "\\s\\s+";
        return text.replaceAll(regexRemoveSpace, " ").trim();
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
