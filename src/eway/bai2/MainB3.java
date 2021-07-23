package eway.bai2;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainB3 {
    public static void main(String[] args) {
        String dateNow = MainB3.getDateNow();
        String inputPath = "D:\\eway\\input";
        File file = new File(inputPath);
        File[] arrFile = file.listFiles();
        List<String> listTxtIP = new ArrayList<>();
        if (arrFile != null) {
            for (File f : arrFile) {
                try {
                    String nameStruct = "struct.txt";
                    String messageName = "message.txt";
                    if (nameStruct.equals(f.getName())) {
                        FileInputStream fileInputStream = new FileInputStream(f.toString());
                        Scanner readFile = new Scanner(fileInputStream);
                        while (readFile.hasNextLine()) {
                            String strStruct = MainB3.getStruct(readFile.nextLine());
                            if (strStruct != null){
                                listTxtIP.add(strStruct);
                                System.out.println(strStruct+ "\n");
                            }
                        }
                    }
                    if (messageName.equals(f.getName())){
                        FileInputStream fileInputStream = new FileInputStream(f.toString());
                        Scanner readFile = new Scanner(fileInputStream);
                        while (readFile.hasNextLine()){
                            System.out.println(readFile.nextLine());
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

//        String ts = "src/eway/bai3/test.txt";
//        FileWriter fileWriter;
//        try {
//            fileWriter = new FileWriter(ts);
//            for (String str : listTxtIP
//            ) {
//                if (str != null) {
//                    fileWriter.write(str + "\n");
//                }
//            }
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    public static String getStruct(String strStruct) {
        Pattern patternPhone = Pattern.compile("\\+[0-9]{3,4}");
        Pattern patternStruct = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcherPhone = patternPhone.matcher(strStruct);
        Matcher matcherStruct = patternStruct.matcher(strStruct);
        String strPhone = null;
        String str = null;
        String strRs = null;
        while (matcherPhone.find()) {
            strPhone = matcherPhone.group();
        }
        while (matcherStruct.find()) {
            str = matcherStruct.group();
        }
        if (str!= null && strPhone != null){
            strRs = strPhone + ": " + str;
        }
        return strRs;
    }

    public static String getDateNow(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return day + "-" + (month + 1) + "-" + year + " " + hour + ":" + minute + ":" + second;
    }


}
