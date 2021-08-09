package eway.bai3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadPosition extends Thread{
    private final String pathFile;
    private final DataShare dataShare;

    @Override
    public void run() {
        readFilePositon();
    }

    public ThreadPosition(String pathFile, DataShare dataShare) {
        this.pathFile = pathFile;
        this.dataShare = dataShare;
    }

    public void readFilePositon(){
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            Scanner read = new Scanner(fileInputStream);
            while (read.hasNextLine()){
                Position position = getPositionToFile(read.nextLine());
                dataShare.listData.put(position);
            }
            Position position = new Position();
            position.setMmsi("End");
            dataShare.listData.put(position);
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Position getPositionToFile(String informationPosition) {
        Position position = new Position();
        position.setMmsi(getInformationPosition(informationPosition, 1));
        position.setLongitude(Integer.parseInt(getInformationPosition(informationPosition, 2)));
        position.setLatitude(Integer.parseInt(getInformationPosition(informationPosition, 3)));
        position.setTime(getInformationPosition(informationPosition, 4));
        return position;
    }

    static String getInformationPosition(String position, int choice) {
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(position);
        String inforSearch = null;
        if (matcher.find()) {
            switch (choice) {
                case 1:
                    //Mmsi
                    inforSearch = matcher.group(1);
                    break;
                case 2:
                    //longitude
                    inforSearch = matcher.group(2);
                    break;
                case 3:
                    //latitude
                    inforSearch = matcher.group(3);
                    break;
                case 4:
                    //thời gian (dd/MM/yyyy HH:mm:ss)
                    inforSearch = matcher.group(4);
                    break;
                default:
                    System.out.println("chon sai!");
                    break;
            }
        }
        return inforSearch;
    }
}
