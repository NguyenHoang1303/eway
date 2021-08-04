package eway.bai2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainViettel {

    public static final String INFOR_MESSAGE = "(\\+84\\d{9,10})\\((.+)\\|(.+)\\|(.+)\\)";
    public static final String TIME = "(0\\d|[12]\\d|3[01])-(0[1-9]|1[012])-(\\d{4})";
    public static final String INFORMATION_VIETTEL = "(\\d{3,4})(.+)\\((.+)\\)";
    static final String STRUCT_PATH = "bai2/input/struct.txt";
    static final String MESSEGA_PATH = "bai2/input/message.txt";

    public static void main(String[] args) {
        List<String> listInforStruct = readFile(STRUCT_PATH);
        List<String> listInforMess = readFile(MESSEGA_PATH);

        //danh sách mesage theo thời gian hợp lí và đúng cú pháp
        List<String> listMessage = getMessagesByRequest(listInforMess, listInforStruct);
        //danh sách các đầu số tổng đài
        HashSet<String> phonesViettel = getPhoneViettelInMessage(listMessage);

        //lấy messeage theo đầu số tổng đài viettel
        //Key: đầu số Viettel
        //Value: message
        HashMap<String, String> listMessages = getMessageByPhoneViettel(phonesViettel, listMessage);
        List<String> listMessagesByPhone = new LinkedList<>();
        for (String key : listMessages.keySet()) {
            String message = listMessages.get(key);
            List<String> messages = convertStringToList(message);
            //sap xep message theo thời gian
            sortMessageByDate(messages);
            //danh sách message theo yêu cầu bài toán
            listMessagesByPhone.addAll(getPhonesMessages(messages));
        }

        //in ra file txt theo đầu số Viettel
        HashMap<String, String> hashMapMessage = getMessageByPhoneViettel(phonesViettel, listMessagesByPhone);
        try {
            String path = "bai2/output/";
            Set<String> listKeys = hashMapMessage.keySet();
            for (String key : listKeys) {
                String value = hashMapMessage.get(key);
                String pathMessage = path + key + ".txt";
                FileWriter fileWriter = new FileWriter(pathMessage);
                int size = convertStringToList(value).size();
                List<String> listMess = convertStringToList(value);
                if (size == 1) {
                    fileWriter.write(value);
                } else {
                    for (String content : listMess) {
                        fileWriter.write(content);
                        fileWriter.write("\n");
                    }
                }
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> readFile(String path) {
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
    static String getInforViettel(String inforViettel, int number) {
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
    static String getInforMessage(String messInfor, int choice) {
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
    static void sortMessageByDate(List<String> listMessage) {
        listMessage.sort(new Comparator<String>() {
            @Override
            public int compare(String text1, String text2) {
                String dateStart = getDateMessInformation(text1).toString();
                String dateEnd = getDateMessInformation(text2).toString();
                return dateStart.compareTo(dateEnd);
            }
        });
    }

    // kiểm tra cú pháp trong message có đúng ko?
    static boolean checkStruct(String messInfor, String structInfor) {
        String structMess = getInforMessage(messInfor, 2);
        String phoneMess = getInforMessage(messInfor, 4);
        String structViettel = getInforViettel(structInfor, 2);
        String phoneViettel = getInforViettel(structInfor, 1);
        boolean checkPhone = false;
        boolean checkMess = false;
        if (phoneMess != null && phoneViettel != null) {
            checkPhone = phoneMess.equals(phoneViettel);
        }
        if (structViettel != null && structMess != null) {
            checkMess = structViettel.equalsIgnoreCase(structMess);
        }

        return checkMess && checkPhone;
    }

    // kiểm tra ngày tháng năm trong message có lớn hơn so với ngày hiện tại
    static boolean checkTime(String messInfor) {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateMessage1 = getDateMessInformation(messInfor);
        if (dateMessage1 != null) return dateNow.compareTo(dateMessage1) > 0;
        return false;
    }

    //lấy ra ngày tháng năm trong mesage
    static LocalDate getDateMessInformation(String messInfor) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Pattern pattern = Pattern.compile(TIME);
        boolean checkinforMessage = getInforMessage(messInfor, 3) != null;
        if (checkinforMessage) {
            String timeMessage = getInforMessage(messInfor, 3);
            Matcher matcher = pattern.matcher(timeMessage);
            if (matcher.find()) {
                return LocalDate.from(dateFormat.parse(matcher.group()));
            }
        }
        return null;
    }

    // danh sách message đúng cú pháp và hợp lí về thời gian
    static List<String> getMessagesByRequest(List<String> listMess, List<String> listVietel) {
        List<String> list = new ArrayList<>();
        boolean checkStructAndTime;
        for (String messInfor : listMess) {
            for (String inforViettel : listVietel) {
                checkStructAndTime = checkStruct(messInfor, inforViettel) && checkTime(messInfor);
                if (checkStructAndTime) {
                    list.add(messInfor);
                }
            }
        }
        return list;
    }

    // khoảng cách giữa 2 khoảng thời gian theo ngày
    static int getDayBetween(LocalDate timeStart, LocalDate timeEnd) {
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
    static HashSet<String> getPhoneViettelInMessage(List<String> listMessage) {
        HashSet<String> list = new HashSet<>();
        for (String messValid : listMessage) {
            String phoneViettel = getInforMessage(messValid, 4);
            list.add(phoneViettel);

        }
        return list;
    }

    static List<String> convertStringToList(String text) {
        String[] array = text.split(" /// ");
        return new ArrayList<>(Arrays.asList(array));
    }

    //lấy ra các message trùng sđt và sđt tổng đài phải cách nhau 1 tháng
    static List<String> getPhonesMessages(List<String> list) {
        LinkedList<String> linkedList = new LinkedList<>();
        sortMessageByDate(list);
        for (String element : list) {
            LocalDate date = getDateMessInformation(element);
            if (linkedList.size() > 0) {
                String lastElement = linkedList.getLast();
                LocalDate lastDate = getDateMessInformation(lastElement);
                if (date != null && lastDate != null) {
                    boolean check = getDayBetween(date, lastDate) > 30;
                    if (check) {
                        linkedList.add(element);
                    }
                }
            } else {
                linkedList.add(element);
            }
        }
        return new ArrayList<String>(linkedList);
    }

    /*
    lấy ra các danh sách message theo đầu số tổng đài
    key: đầu số tổng đài
    values: chuỗi chứa danh sách các message cách nhau bởi " /// "
    */
    static HashMap<String, String> getMessageByPhoneViettel(HashSet<String> listPhoneVT, List<String> listMessage) {
        HashMap<String, String> list = new HashMap<>();
        for (String phoneViettel : listPhoneVT) {
            for (String message : listMessage) {
                String phoneMessage = getInforMessage(message, 4);
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

}
