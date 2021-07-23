package eway.bai1.example2;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class MainBai2 {
    public static void main(String[] args) {
        String pathStr = "C:\\Users\\LaptopAZ.vn\\Desktop\\input";
        List<String> listFileAllTxtIP = MainBai2.getpathTxt(pathStr);
        List<String> listTxtIp = new ArrayList<>();
        if (listFileAllTxtIP != null) {
            for (String strPat : listFileAllTxtIP
            ) {
                if (MainBai2.getNameTxtIPPath(strPat)) {
                    listTxtIp.add(strPat);
                }
            }
        }
        System.out.println("\n");
        String pathStrOutPut = "C:\\Users\\LaptopAZ.vn\\Desktop\\output";
        List<String> listAllTxtOut = MainBai2.getpathTxt(pathStrOutPut);
        List<String> listTxtOP = new ArrayList<>();
        if (listAllTxtOut != null) {
            for (String strTxtOP : listAllTxtOut) {
                if (MainBai2.getNameTxtOPPath(strTxtOP)) {
                    listTxtOP.add(strTxtOP);
                }
            }
        }
        List<String> listPhonesIp;
        List<String> listPhonesOp;
        List<String> listPhonesIPPrintf = new ArrayList<>();
        List<String> listPhonesOPPrintf = new ArrayList<>();
        boolean isDate;
        for (String strOP : listTxtOP) {
            for (String strIP : listTxtIp) {
                isDate = MainBai2.isDuplicateDate(strOP, strIP);
                if (isDate) {
                    listPhonesIp = MainBai2.getListPhone(strIP);
                    listPhonesOp = MainBai2.getListPhone(strOP);
                    List<String> listIP = MainBai2.listNotDuplicatePhone(listPhonesIp, listPhonesOp);
                    List<String> listOP = MainBai2.listNotDuplicatePhone(listPhonesOp, listPhonesIp);
                    if (listIP.size() > 0) {
                        listPhonesIPPrintf.addAll(listIP);
                    }
                    if (listOP.size() > 0) {
                        listPhonesOPPrintf.addAll(listOP);
                    }
                }
            }
        }
        String inputTxt = "src/eway/bai1/example2/input_co.txt";
        String outputTxt = "src/eway/bai1/example2/output_co.txt";
        boolean isSuccess = MainBai2.isprintfTxt(listPhonesIPPrintf, inputTxt);
        boolean isSuccess1 = MainBai2.isprintfTxt(listPhonesOPPrintf, outputTxt);
        if (isSuccess & isSuccess1){
            System.out.println("ok");
        }
    }

    public static List<String> getpathTxt(String path) {
        List<String> listFile = new ArrayList<>();
        File file = new File(path);
        File[] arrFile = file.listFiles();
        boolean isArrFile = (arrFile != null);
        if (isArrFile) {
            for (File f : arrFile) {
                File fileTxt = new File(f.toString());
                File[] arrFileTxt = fileTxt.listFiles();
                if (arrFileTxt != null) {
                    for (File fTxt : arrFileTxt) {
                        listFile.add(fTxt.toString());
                    }
                }
            }
            return listFile;
        }
        return null;
    }

    public static Boolean getNameTxtIPPath(String path) {
        File file = new File(path);
        String name = file.getName();
        String regexTxt = "^cdr_[0-9]{8}_8x56_[0-9].txt";
        return name.matches(regexTxt);
    }

    public static Boolean getNameTxtOPPath(String path) {
        File file = new File(path);
        String name = file.getName();
        String regexTxt = "^[0-9]{8}_8x56_[0-9].txt";
        return name.matches(regexTxt);
    }


    public static String getDate(String filePath) {
        File file = new File(filePath);
        String fileNameTxt = file.getName();
        String date = null;
        Pattern patternDate = Pattern.compile("[0-9]{8}");
        Matcher matcher = patternDate.matcher(fileNameTxt);
        while (matcher.find()) {
            date = fileNameTxt.substring(matcher.start(), matcher.end());
        }
        return date;
    }

    public static Boolean isDuplicateDate(String PathIp, String PathOp) {
        String dateIP = getDate(PathIp);
        String dateOP = getDate(PathOp);
        return dateIP.equals(dateOP);
    }

    public static List<String> getListPhone(String path) {
        List<String> list = new ArrayList<>();
        try {
            String regexPhone = "849[0-9]{7}";
            String phone;
            boolean isPhone;
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                phone = scanner.nextLine();
                isPhone = phone.matches(regexPhone);
                if (isPhone) list.add(phone);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> listNotDuplicatePhone(List<String> list1, List<String> list2) {
        List<String> list = new ArrayList<>();
        boolean isList = list1.size() > 0 && list2.size() > 0;
        boolean isPhone;
        if (isList) {
            for (String phone1 : list1) {
                int count = 0;
                for (String phone2 : list2) {
                    isPhone = phone1.equals(phone2);
                    if (isPhone) break;
                    else count++;
                }
                if (count == list2.size()) {
                    list.add(phone1);
                }
            }
        }
        return list;
    }

    public static boolean isprintfTxt(List<String> list, String path){
        List<String> listWithoutDuplicateElements = list
                .stream()
                .distinct()
                .collect(Collectors.toList());
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (String phone: listWithoutDuplicateElements) {
                fileWriter.write(phone + "\n");
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

