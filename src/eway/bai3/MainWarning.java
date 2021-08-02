package eway.bai3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWarning {
    public final static String PATH_WARNING = "src/eway/bai3/filetxt/alert.txt";

    public static void main(String[] args) {
        String pathArea = "src/eway/bai3/filetxt/area.txt";
        String pathPositions = "src/eway/bai3/filetxt/positions.txt";
        List<String> informationAreas = readFile(pathArea);
        List<String> infortmationPositions = readFile(pathPositions);
        List<Area> listArea = getListArea(informationAreas);
        List<Position> positionList = getListPosition(infortmationPositions);
        Warning warning = new Warning();
        List<Warning> warningList = new ArrayList<>();
        for (Position position : positionList) {
           warningList.add(warning.getWarning(listArea,position));
          }
        writeFile(warningList);
    }

    static List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner readFile = new Scanner(fileInputStream);
            while (readFile.hasNextLine()) {
                String text = readFile.nextLine();
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        list.add(text);
                    }
                };
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    static void writeFile(List<Warning> list){
        try {
            FileWriter fileWriter = new FileWriter(PATH_WARNING,true);
            for (Warning warning: list) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            fileWriter.write(warning.toString());
                            fileWriter.write("\n");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                thread.join();
            }
            fileWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String getInformationArea(String area, int choice) {
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(area);
        String inforSearch = null;
        if (matcher.find()) {
            switch (choice) {
                case 1:
                    //Ten vung
                    inforSearch = matcher.group(1);
                    break;
                case 2:
                    //longitude_left
                    inforSearch = matcher.group(2);
                    break;
                case 3:
                    //longitude_right
                    inforSearch = matcher.group(3);
                    break;
                case 4:
                    //latitude_top
                    inforSearch = matcher.group(4);
                    break;
                case 5:
                    //latitude_bottom
                    inforSearch = matcher.group(5);
                    break;
                default:
                    System.out.println("chon sai!");
                    break;
            }
        }
        return inforSearch;
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

    static List<Area> getListArea(List<String> listText) {
        List<Area> list = new ArrayList<>();
        for (String area : listText) {
            String nameArea = getInformationArea(area, 1);
            int longitudeLeft = Integer.parseInt(getInformationArea(area, 2));
            int longitudeRight = Integer.parseInt(getInformationArea(area, 3));
            int latitudeTop = Integer.parseInt(getInformationArea(area, 4));
            int latitudeBottom = Integer.parseInt(getInformationArea(area, 5));
            list.add(new Area(nameArea, longitudeLeft, longitudeRight, latitudeTop, latitudeBottom));
        }
        return list;
    }

    static List<Position> getListPosition(List<String> listText) {
        List<Position> list = new ArrayList<>();
        for (String position : listText) {
            String mmsi = getInformationPosition(position, 1);
            int longitude = Integer.parseInt(getInformationPosition(position, 2));
            int latitude = Integer.parseInt(getInformationPosition(position, 3));
            String date = getInformationPosition(position, 4);
            list.add(new Position(mmsi, longitude, latitude, date));
        }
        return list;
    }


}
