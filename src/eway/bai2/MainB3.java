package eway.bai2;

import eway.bai1.example2.MainBai2;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainB3 {

    public static final String INFOR_MESSAGE = "(\\+84[0-9]{9,10})\\((.+)\\|(.+)\\|(.+)\\)";
    public static final String TIME = "(0[0-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-([0-9]{4})[ -/]([01][0-9]|2[0-3]):([0-5][0-9]:([0-5][0-9]))";

    public static void main(String[] args) {
        String dateNow = MainB3.getDateNow();
        String structPath = "bai2/input/struct.txt";
        List<String> listInforStruct = MainB3.readFile(structPath);
        List<String> listStruct = MainB3.getListStruct(listInforStruct);
        String messagePath = "bai2/input/message.txt";
        List<String> listInforMess = MainB3.readFile(messagePath);

        boolean check = MainB3.checkTime("");




    }

    public static List<String> readFile(String path){
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner readFile = new Scanner(fileInputStream);
            while (readFile.hasNextLine()){
               list.add(readFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getStruct(String str) {
        Pattern pattern = Pattern.compile("([0-9]{3,4})(.+)\\((([^)]+))\\)");
        Matcher matcher = pattern.matcher(str);
        String phone = null;
        String struct = null;
        String strRs = null;
        while (matcher.find()) {
            phone = matcher.group(1);
            struct = matcher.group(3);
        }
        if (struct!= null && phone != null){
            strRs = phone + " : " + struct;
        }
        return strRs;
    }

    public static List<String> getListStruct(List<String> listStruct){
        List<String> list = new ArrayList<>();
        for (String inforStruct: listStruct) {
            String struct = MainB3.getStruct(inforStruct);
            if (struct != null) list.add(struct);
        }
        return list;
    }

    public static String getStructMessage(String messInfor){
        Pattern pattern = Pattern.compile(INFOR_MESSAGE);
        Matcher matcher = pattern.matcher(messInfor);
        String structMess = null;
        if (matcher.find()){
         structMess = matcher.group(2).concat(" " + matcher.group(4));

        }
        return structMess;
    }

    public static boolean checkTime(String date){
        String s = "17-10-2015 14:23:00";
        String dateNow = MainB3.getDateNow();
        Pattern pattern = Pattern.compile(TIME);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(dateNow);
        }
        return false;
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
