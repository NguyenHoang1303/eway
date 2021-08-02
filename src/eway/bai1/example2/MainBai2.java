package eway.bai1.example2;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class MainBai2 {
    static final String PATH_INPUT = "bai1Example2/input";
    static final String PATH_OUTPUT = "bai1Example2/output";

    public static void main(String[] args) {
        //Lấy ra các file theo đúng yêu cầu
        List<String> listFileInput = getListFileByName(PATH_INPUT);
        List<String> listFileOutput = getListFileByName(PATH_OUTPUT);
        //lấy ra các ngày có trong file
        HashSet<String> listDate = new HashSet<>();
        for (String s2 : listFileInput) {
            String nameTxt = getDate(s2);
            listDate.add(nameTxt);
        }

        HashMap<String, String> listPhoneIpByDate = new HashMap<>();
        HashMap<String, String> listPhoneOpByDate = new HashMap<>();
        //lấy ra các số điện thoại theo
        // Key: ngày, Values: chuỗi chứa các sđt
        // của cả thư mục input và output
        for (String date : listDate) {
            HashMap<String, String> listInput = getlistPhoneByDate(listFileInput, date);
            listPhoneIpByDate.putAll(listInput);
            HashMap<String, String> listOutput = getlistPhoneByDate(listFileOutput, date);
            listPhoneOpByDate.putAll(listOutput);
        }

        HashMap<String, String> listPhoneByRequestInput = new HashMap<>();
        HashMap<String, String> listPhoneRequestOutput = new HashMap<>();
        //tạo Set chứa các key của Hasmap
        Set<String> listDateInput = listPhoneIpByDate.keySet();
        Set<String> listDateOutput = listPhoneOpByDate.keySet();
        //Lấy ra các sdt trong cùng 1 ngày của 2 thư mục output và input không giống nhau
        for (String dateIp : listDateInput) {
            String phoneInput = listPhoneIpByDate.get(dateIp);
            for (String dateOp : listDateOutput) {
                String listPhoneOp = listPhoneOpByDate.get(dateOp);
                boolean checkDate = dateIp.equals(dateOp);
                if (checkDate) {
                    String phonesInput = getPhones(phoneInput, listPhoneOp, 1);
                    listPhoneByRequestInput.put(dateIp, phonesInput);
                    String phonesOutput = getPhones(phoneInput, listPhoneOp, 2);
                    listPhoneRequestOutput.put(dateIp, phonesOutput);
                }
            }
        }

        String pathPrintInput = "src/eway/bai1/example2/input_co.txt";
        String pathPrintOutput = "src/eway/bai1/example2/output_co.txt";
        isprintfTxt(listPhoneByRequestInput, pathPrintInput);
        isprintfTxt(listPhoneRequestOutput, pathPrintOutput);
    }

    //lấy ra tất cả các file txt trong thư mục
    static List<String> getpathfileTxt(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
        File[] arrFile = file.listFiles();
        boolean isArrFile = (arrFile != null);
        if (isArrFile) {
            for (File fileName : arrFile) {
                File fileTxt = new File(fileName.toString());
                File[] arrFileTxt = fileTxt.listFiles();
                if (arrFileTxt != null) {
                    for (File fTxt : arrFileTxt) {
                        list.add(fTxt.toString());
                    }
                }
            }
            return list;
        }
        return null;
    }

    // kiểm tra tên theo đúng định dạng file txt trong Input
    static boolean getNameOnRequestInput(String path) {
        File file = new File(path);
        String name = file.getName();
        String regexTxt = "cdr_\\d{8}_8x56_\\d.txt";
        return name.matches(regexTxt);
    }

    // kiểm tra tên theo đúng định dạng file txt trong Output
    static boolean getNameOnRequestOutput(String path) {
        File file = new File(path);
        String name = file.getName();
        String regexTxt = "^\\d{8}_8x56_\\d.txt";
        return name.matches(regexTxt);
    }

    // lấy ra các file Txt theo yêu cầu của đề
    static List<String> getListFileByName(String path) {
        List<String> allFileTxtInput = getpathfileTxt(path);
        List<String> list = new ArrayList<>();
        String pathInput = "bai1Example2/input";
        String pathOutput = "bai1Example2/output";
        if (allFileTxtInput != null) {
            for (String pathTxt : allFileTxtInput) {
                boolean checkpathInput = path.equals(pathInput) && getNameOnRequestInput(pathTxt);
                boolean checkpathOutput = path.equals(pathOutput) && getNameOnRequestOutput(pathTxt);
                if (checkpathInput) {
                    list.add(pathTxt);
                }
                if (checkpathOutput) {
                    list.add(pathTxt);
                }
            }
        }
        return list;
    }

    // lấy ra ngày trong tên của các file txt
    static String getDate(String filePath) {
        File file = new File(filePath);
        String fileNameTxt = file.getName();
        String date = null;
//        Pattern patternDate = Pattern.compile("[0-9]{8}");
//        Pattern patternDate = Pattern.compile("\\d{4}\\d{2}\\d{2}");
        Pattern patternDate = Pattern.compile("\\d{4}((0\\d)|(1[0-2]))(([0-2]\\d)|3[01])");
        Matcher matcher = patternDate.matcher(fileNameTxt);
        while (matcher.find()) {
            date = matcher.group();
        }
        return date;
    }

    // lấy ra sdt đúng định dạng trong file txt
    static HashSet<String> getListPhone(String path) {
        HashSet<String> list = new HashSet<>();
        try {
            String regexPhone = "849\\d{7}";
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

    // láy ra những số điện thoại không trùng lặp trong cùng 1 ngày
    static List<String> listNotDuplicatePhone(List<String> list1, List<String> list2) {
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

    // lấy ra tất cả sdt trong 1 ngày và tạo thành 1 chuỗi
    static HashMap<String, String> getlistPhoneByDate(List<String> listPath, String date) {
        HashMap<String, String> list = new HashMap<>();
        boolean isDuplicate;
        HashSet<String> listPhone = new HashSet<>();
        for (String path : listPath
        ) {
            isDuplicate = MainBai2.getDate(path).equals(date);
            if (isDuplicate) {
                listPhone.addAll(MainBai2.getListPhone(path));
            }
        }
        String stringPhoneList = String.join(" ", listPhone);
        list.put(date, stringPhoneList);
        return list;
    }

    static ArrayList<String> convertTextToList(String text) {
        String[] list = text.split("\\s");
        return new ArrayList<>(Arrays.asList(list));
    }

    //Lấy ra các sđt không trùng lặp trong 2 ngày
    // choice = 1: fileInput có Output ko có,
    // choice = 2: fileOutput có Input ko có
    static String getPhones(String textIp, String textOp, int choice) {
        List<String> listIp = convertTextToList(textIp);
        List<String> listOp = convertTextToList(textOp);
        if (choice == 1) {
            if (textOp.isEmpty()) return textIp;
            List<String> list = MainBai2.listNotDuplicatePhone(listIp, listOp);
            return String.join(" ", list);
        }

        if (choice == 2) {
            if (textIp.isEmpty()) return textOp;
            List<String> list = MainBai2.listNotDuplicatePhone(listOp, listIp);
            return String.join(" ", list);
        }
        return null;
    }

    //in ra file
    static void isprintfTxt(HashMap<String, String> list, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (String date : list.keySet()) {
                if (list.get(date).length() > 0) {
                    String str = date + ": " + list.get(date);
                    fileWriter.write(str + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

