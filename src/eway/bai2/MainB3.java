package eway.bai2;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainB3 {

    public static final String INFOR_MESSAGE = "(\\+84[0-9]{9,10})\\((.+)\\|(.+)\\|(.+)\\)";
    public static final String TIME = "(0[0-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-([0-9]{4})";
    public static final String INFORMATION_VIETTEL = "([0-9]{3,4})(.+)\\((([^)]+))\\)";

    public static void main(String[] args) {
        String structPath = "bai2/input/struct.txt";
        List<String> listInforStruct = MainB3.readFile(structPath);
        String messagePath = "bai2/input/message.txt";
        List<String> listInforMess = MainB3.readFile(messagePath);
        //danh sách mesage theo yeu cầu 1,2
        List<String> listMessageInvalid = MainB3.getListMessageByRequest(listInforMess, listInforStruct);
        //danh sách các đầu số tổng đài
        List<String> listPhoneViettelInMessage = MainB3.getPhoneViettelInMessage(listMessageInvalid);
        //lọc messeage theo đầu số tổng đài viettel
        HashMap<String, String> listMessageByPhoneViettel = MainB3.getMessageByPhoneViettel(listPhoneViettelInMessage, listMessageInvalid);
        
        LinkedList<String> linkedListSortMessageDuplicatePhoneByDate = new LinkedList<>();
        for (String key : listMessageByPhoneViettel.keySet()) {
            String messageByPhoneViettel = listMessageByPhoneViettel.get(key);
            List<String> listMessage = MainB3.convertStringToList(messageByPhoneViettel);
            //sap xep message theo thời gian tăng dần
            MainB3.sortMessageByDate(listMessage);
            //danh sách message theo yêu cầu bài toán
            linkedListSortMessageDuplicatePhoneByDate.addAll(MainB3.sortMessageDuplicatePhoneByDate(listMessage));
        }

        HashMap<String,String> hashMap = MainB3.getMessageByPhoneViettel(listPhoneViettelInMessage,linkedListSortMessageDuplicatePhoneByDate);
        try {
            String path = "bai2/output/";
            for (String key: hashMap.keySet()) {
                String pathMessage = path + key + ".txt";
                FileWriter fw = new FileWriter(pathMessage);
                fw.write(hashMap.get(key));
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String path) {
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
    // lấy ra thông tin cú pháp viettel
    public static String getInforViettel(String inforViettel, int number) {
        Pattern pattern = Pattern.compile(INFORMATION_VIETTEL);
        Matcher matcher = pattern.matcher(inforViettel);
        String result = null;
        while (matcher.find()) {
            if (number == 1) {
                //get phone Viettel
                result = matcher.group(1);
            }
            if (number == 2) {
                //get struct Vt
                result = matcher.group(3);
            }
        }

        return result;
    }
    // lấy ra thông tin Message
    public static String getInforMessage(String messInfor, int choice) {
        Pattern pattern = Pattern.compile(INFOR_MESSAGE);
        Matcher matcher = pattern.matcher(messInfor);
        String inforMess = null;

        if (matcher.find()) {
            switch (choice) {
                case 1:
                    //get PhoneUser
                    inforMess = matcher.group(1);
                    break;
                case 2:
                    //get Struct
                    inforMess = matcher.group(2);
                    break;
                case 3:
                    //get Time
                    inforMess = matcher.group(3);
                    break;
                case 4:
                    //get phone Viettel
                    inforMess = matcher.group(4);
                    break;
            }

        }
        return inforMess;
    }
    // sắp xếp ngày tăng dần theo ngày tháng
    public static void sortMessageByDate(List<String> listMesseage) {
        listMesseage.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String o1Date = MainB3.getDateMessInformation(o1).toString();
                String o2Date = MainB3.getDateMessInformation(o2).toString();
                return o1Date.compareTo(o2Date);
            }
        });
    }
    // kiểm tra cú pháp trong message có đúng ko?
    public static boolean checkStruct(String messInfor, String structInfor) {
        String structMess = MainB3.getInforMessage(messInfor, 2);
        String phoneMess = MainB3.getInforMessage(messInfor, 4);
        String structViettel = MainB3.getInforViettel(structInfor, 2);
        String phoneViettel = MainB3.getInforViettel(structInfor, 1);
        boolean checkPhone = false;
        boolean checkMess = false;
        if (phoneMess != null && phoneViettel != null) {
            checkPhone = phoneMess.equals(phoneViettel);
        }
        if (structViettel != null && structMess != null) {
            checkMess = structViettel.toUpperCase().contains(structMess.toUpperCase());
        }

        return checkMess && checkPhone;
    }
    // kiểm tra ngày tháng năm trong message có lớn hơn so với ngày hiện tại
    public static boolean checkTime(String messInfor) {
        String timeMessage = getInforMessage(messInfor, 3);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateNow = LocalDate.now();

        Pattern pattern = Pattern.compile(TIME);
        Matcher matcher = pattern.matcher(timeMessage);
        if (matcher.find()) {
            LocalDate dateMessage = LocalDate.from(dateFormat.parse(matcher.group()));
            return dateNow.compareTo(dateMessage) > 0;
        }
        return false;
    }
    //lấy ra ngày tháng năm trong mesage
    static LocalDate getDateMessInformation(String messInfor) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Pattern pattern = Pattern.compile(TIME);
        boolean checkinforMessage = MainB3.getInforMessage(messInfor, 3) != null;
        if (checkinforMessage) {
            String timeMessage = MainB3.getInforMessage(messInfor, 3);
            Matcher matcher = pattern.matcher(timeMessage);
            if (matcher.find()) {
                return LocalDate.from(dateFormat.parse(matcher.group()));
            }
        }
        return null;
    }
    // danh sách message theo yêu cầu 1 và 2
    static List<String> getListMessageByRequest(List<String> listMess, List<String> listVietel) {
        List<String> list = new ArrayList<>();
        boolean checkStructAndDate;
        for (String messInfor : listMess) {
            for (String inforViettel : listVietel) {
                checkStructAndDate = MainB3.checkStruct(messInfor, inforViettel) && MainB3.checkTime(messInfor);
                if (checkStructAndDate) {
                    list.add(messInfor);
                }
            }
        }
        return list;
    }
    // khoảng cách giữa 2 ngày
    public static int getDayBetween(LocalDate timeStart, LocalDate timeEnd) {
        int betweenTwoYears = (timeStart.getYear() - timeEnd.getYear()) * 365;
        int betweenTwoMonth = (timeStart.getMonthValue() - timeEnd.getMonthValue()) * 30;
        int betweenTwoDate = timeStart.getDayOfMonth() - timeEnd.getDayOfMonth();
        if (betweenTwoYears < 0) {
            betweenTwoYears *= -1;
        }
        if (betweenTwoMonth < 0) {
            betweenTwoMonth *= -1;
        }
        if (betweenTwoDate < 0) {
            betweenTwoDate *= -1;
        }
        return betweenTwoDate + betweenTwoYears + betweenTwoMonth;
    }
    // lấy ra đầu số tổng đài có trong Message
    static List<String> getPhoneViettelInMessage(List<String> listMessage) {
        List<String> listPhoneViettelInMessage = new ArrayList<>();
        for (String messValid : listMessage) {
            String phoneViettel = MainB3.getInforMessage(messValid, 4);
            int count = 0;
            if (listPhoneViettelInMessage.size() > 0) {
                for (String phoneVT : listPhoneViettelInMessage) {
                    if (!phoneViettel.equals(phoneVT)) {
                        count++;
                    }
                }
                if (count == listPhoneViettelInMessage.size()) {
                    listPhoneViettelInMessage.add(phoneViettel);
                }
            } else {
                listPhoneViettelInMessage.add(phoneViettel);
            }
        }
        return listPhoneViettelInMessage;
    }
    /*
    lấy ra các danh sách message theo đầu số tổng đài
    key: đầu số tổng đài
    values: chuỗi chứa danh sách các message cách nhau bởi " /// "
    */
    static HashMap<String, String> getMessageByPhoneViettel(List<String> listPhoneVT, List<String> listMessage) {
        HashMap<String, String> list = new HashMap<>();
        for (String phoneViettel : listPhoneVT) {
            for (String message : listMessage) {
                String phoneMessage = MainB3.getInforMessage(message, 4);
                boolean chekKey = list.containsKey(phoneViettel);
                if (chekKey && phoneMessage.equals(phoneViettel)) {
                    String str = list.get(phoneViettel).concat(" /// " + message);
                    list.put(phoneViettel, str);
                } else if (!chekKey && phoneMessage.equals(phoneViettel)) {
                    list.put(phoneViettel, message);
                }
            }
        }
        return list;
    }

    static List<String> convertStringToList(String text) {
        String[] array = text.split(" /// ");
        return new ArrayList<>(Arrays.asList(array));
    }
//lấy ra các message trùng sđt và sđt tổng đài cách nhau 1 tháng
    static LinkedList<String> sortMessageDuplicatePhoneByDate(List<String> list) {
        LinkedList<String> linkedList = new LinkedList<>();
        MainB3.sortMessageByDate(list);
        for (String element : list) {
            LocalDate date105 = MainB3.getDateMessInformation(element);
            if (linkedList.size() > 0) {
                String lastElement = linkedList.getLast();
                LocalDate lastDate = MainB3.getDateMessInformation(lastElement);
                if (date105 != null && lastDate != null) {
                    boolean check = MainB3.getDayBetween(date105, lastDate) > 30;
                    if (check) {
                        linkedList.add(element);
                    }
                }
            } else {
                linkedList.add(element);
            }
        }
        return linkedList;
    }

}
