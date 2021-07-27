package eway.bai1.example2;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class MainBai2 {

    public static final String DATE = "\\[(.+)]";
    public static void main(String[] args) {
        String pathInput = "bai1Example2/input";
        List<String> listAllFileTxtInput = MainBai2.getpathfileTxt(pathInput);
        List<String> fileListByRequestNameInput = new ArrayList<>();
        if (listAllFileTxtInput != null) {
            for (String strPathInput : listAllFileTxtInput) {
                if (MainBai2.getNameOnRequestInput(strPathInput)) {
                    fileListByRequestNameInput.add(strPathInput);
                }
            }
        }
        String pathOutput = "C:\\Users\\LaptopAZ.vn\\Desktop\\output";
        List<String> listAllFileTxtOutput = MainBai2.getpathfileTxt(pathOutput);
        List<String> fileListByRequestNameOutput = new ArrayList<>();
        if (listAllFileTxtOutput != null) {
            for (String strPathOutput : listAllFileTxtOutput) {
                if (MainBai2.getNameOnRequestOutput(strPathOutput)) {
                    fileListByRequestNameOutput.add(strPathOutput);
                }
            }
        }
        List<String> listDate = new ArrayList<>();
        for (String s2 : fileListByRequestNameInput) {
            String nameTxt = MainBai2.getDate(s2);
            listDate.add(nameTxt);
        }
        List<String> listPhoneByDateInput = new ArrayList<>();
        List<String> listPhoneByDateOutput = new ArrayList<>();
        List<String> listDateNotDuplicate = MainBai2.listNotDuplicate(listDate);
        for (String date : listDateNotDuplicate) {
            String stringListPhoneInput = MainBai2.getlistbydate(fileListByRequestNameInput, date);
            String stringListPhoneOutput = MainBai2.getlistbydate(fileListByRequestNameOutput, date);
            listPhoneByDateInput.add(stringListPhoneInput);
            listPhoneByDateOutput.add(stringListPhoneOutput);
        }
        System.out.println("listPhoneByDateOutput: " + listPhoneByDateInput);
        System.out.println("\n");
        System.out.println("listPhoneByDateOutput: " + listPhoneByDateOutput);

        List<String> listPrintInput = new ArrayList<>();
        List<String> listPrintOutput = new ArrayList<>();
        for (String sIp : listPhoneByDateInput) {
            for (String sOp : listPhoneByDateOutput) {
                String strIp = MainBai2.getListPhoneNotDuplicateInput(sIp, sOp);
                String strOp = MainBai2.getListPhoneNotDuplicateOutput(sIp, sOp);
                if (strIp != null) {
                   listPrintInput.add(strIp);
                }
                if (strOp != null) {
                    listPrintOutput.add(strOp);
                }
            }
        }
        String pathPrintInput = "src/eway/bai1/example2/input_co.txt";
        String pathPrintOutput = "src/eway/bai1/example2/output_co.txt";
        MainBai2.isprintfTxt(listPrintInput, pathPrintInput);
        MainBai2.isprintfTxt(listPrintOutput, pathPrintOutput);

    }

    public static List<String> getpathfileTxt(String path) {
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

    public static Boolean getNameOnRequestInput(String path) {
        File file = new File(path);
        String name = file.getName();
        String regexTxt = "^cdr_[0-9]{8}_8x56_[0-9].txt";
        return name.matches(regexTxt);
    }

    public static Boolean getNameOnRequestOutput(String path) {
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
        String phone = "null";
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
                    phone = phone.concat(phone1 + " ");
                }
            }
        }
        return list;
    }

    public static String getlistbydate(List<String> listPath, String date) {
        String phone = "[" + date + "] ";
        boolean isDuplicate;

        List<String> listPhoneDuplicate = new ArrayList<>();
        for (String path : listPath
        ) {
            isDuplicate = MainBai2.getDate(path).equals(date);
            if (isDuplicate) {
                List<String> listPhone = MainBai2.getListPhone(path);
                listPhoneDuplicate.addAll(listPhone);
            }
        }
        List<String> listPhoneNotDuplicate = MainBai2.listNotDuplicate(listPhoneDuplicate);
        String stringPhoneList = String.join(" ", listPhoneNotDuplicate);
        phone = phone.concat(stringPhoneList + " ");
        return phone;
    }

    public static boolean isCheckDate(String stringListIp, String stringListOp){
        Pattern pattern = Pattern.compile(DATE);
        Matcher matcherIP = pattern.matcher(stringListIp);
        Matcher matcherOP = pattern.matcher(stringListOp);
        return matcherIP.find() && matcherOP.find() && matcherIP.group(1).equals(matcherOP.group(1));
    }

    public static String getDateInString(String str){
        Pattern pattern = Pattern.compile(DATE);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) return matcher.group();
        return null;

    }

    public static ArrayList<String> convertArrToArrayList(String[] list){
        return new  ArrayList<>(Arrays.asList(list));
    }

    public static String getListPhoneNotDuplicateOutput(String stringListIp, String stringListOp){
        boolean isCheck = MainBai2.isCheckDate(stringListIp, stringListOp);
        if (isCheck) {
            String[] listIp = stringListIp.split(" ");
            String[] listOp = stringListOp.split(" ");
            ArrayList<String> listIpPhone = MainBai2.convertArrToArrayList(listIp);
            ArrayList<String> listOpPhone = MainBai2.convertArrToArrayList(listOp);
            if (listIp.length > 1 && listOp.length > 1) {
                List<String> listNotDuplicatePhone = MainBai2.listNotDuplicatePhone(listOpPhone, listIpPhone);
                String strNotDuplicatePhone = String.join(" ", listNotDuplicatePhone);
                String nameOp = MainBai2.getDateInString(stringListOp);
                if (nameOp != null) return nameOp.concat(strNotDuplicatePhone);
            }
        }
        return null;
    }

    public static String getListPhoneNotDuplicateInput(String stringListIp, String stringListOp) {
        boolean isCheck = MainBai2.isCheckDate(stringListIp, stringListOp);
        if (isCheck) {
            String[] listIp = stringListIp.split(" ");
            String[] listOp = stringListOp.split(" ");
            if (listIp.length > 1 && listOp.length <= 1) {
                return stringListIp;
            }
            if (listIp.length <= 1 && listOp.length > 1) {
                return stringListOp;
            }
            if (listIp.length > 1) {
                ArrayList<String> listIpPhone = MainBai2.convertArrToArrayList(listIp);
                ArrayList<String> listOpPhone = MainBai2.convertArrToArrayList(listOp);
                List<String> listNotDuplicatePhone = MainBai2.listNotDuplicatePhone(listIpPhone, listOpPhone);
                String str = String.join(" ", listNotDuplicatePhone);
                String nameIp = MainBai2.getDateInString(stringListIp);
                if (nameIp != null) return nameIp.concat(str);
            }
        }
        return null;
    }

    public static List<String> listNotDuplicate(List<String> list) {
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static void isprintfTxt(List<String> list, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (String phone : list) {
                fileWriter.write(phone + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

