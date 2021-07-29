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

    public static void main(String[] args) {
        String pathArea = "src/eway/bai3/area.txt";
        String pathPositions = "src/eway/bai3/positions.txt";
        String pathWarning = "src/eway/bai3/alert.txt";
        List<String> listArea = MainWarning.readFile(pathArea);
        List<String> listPostitons = MainWarning.readFile(pathPositions);
        List<StringBuilder> listWarning = MainWarning.getListWarning(listArea,listPostitons);
        if (listWarning.size() > 0){
            for (StringBuilder stringWarning: listWarning
                 ) {
                MainWarning.writeFile(stringWarning,pathWarning);
            }
        }
    }

    static List<String> readFile(String path){
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

    static void writeFile(StringBuilder text, String path){
        try {
            FileWriter fileWriter = new FileWriter(path,true);
            fileWriter.write(String.valueOf(text));
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getInformationArea(String area,int choice){
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(area);
        String inforSearch = null;
        if (matcher.find()){
           switch (choice){
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

    static String getInformationPosition(String position,int choice){
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(position);
        String inforSearch = null;
        if (matcher.find()){
            switch (choice){
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

    static List<StringBuilder> getListWarning(List<String> listArea, List<String> listPositions){
        List<StringBuilder> list = new ArrayList<>();
        for (String area: listArea) {
            String nameArea = MainWarning.getInformationArea(area,1);
            int longitudeLeftArea = Integer.parseInt(MainWarning.getInformationArea(area,2));
            int longitudeRightArea = Integer.parseInt(MainWarning.getInformationArea(area,3));
            int latitudeTopArea =  Integer.parseInt(MainWarning.getInformationArea(area,4));
            int latitudeBottomArea = Integer.parseInt(MainWarning.getInformationArea(area,5));
            for (String position: listPositions) {
                String mmsiPosition = MainWarning.getInformationPosition(position,1);
                int longitudePosition = Integer.parseInt(MainWarning.getInformationPosition(position,2));
                int latitudePosition = Integer.parseInt(MainWarning.getInformationPosition(position,3));
                String timePosition = MainWarning.getInformationPosition(position,4);
                //Longitude_left <= longitude <= longitude_right
                //Và latitude_bottom <= latitude <= latitude_top
                boolean checkLongitude = longitudeLeftArea <= longitudePosition && longitudePosition <= longitudeRightArea;
                boolean checkLatitude = latitudeBottomArea <= latitudePosition && latitudePosition <= latitudeTopArea;
                if (checkLatitude & checkLongitude){
//                   //Mmsi|Canh bao xam nhap vung| Tên vùng | Longitude|Latitude| thời gian
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(mmsiPosition);
                    stringBuilder.append("| ");
                    stringBuilder.append("Canh bao xam nhap vung");
                    stringBuilder.append(" | ");
                    stringBuilder.append(nameArea);
                    stringBuilder.append(" | ");
                    stringBuilder.append(latitudePosition);
                    stringBuilder.append(" | ");
                    stringBuilder.append(latitudePosition);
                    stringBuilder.append(" | ");
                    stringBuilder.append(timePosition);
                    list.add(stringBuilder);

                }
            }
        }
        return list;
    }

}
