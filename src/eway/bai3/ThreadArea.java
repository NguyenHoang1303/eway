package eway.bai3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadArea extends Thread {
    private String pathArea;
    private DataShare dataShare;
    public ThreadArea(String pathArea) {
        this.pathArea = pathArea;
    }

    static String padtAlert = "src/eway/bai3/filetxt/alert.txt";

    @Override
    public void run() {
        readFileArea();
    }

    public void readFileArea() {
        try {
            FileInputStream fileInputStream = new FileInputStream(pathArea);
            Scanner readFile = new Scanner(fileInputStream);
            List<Area> listArea = new ArrayList<>();
            while (readFile.hasNextLine()) {
                String text = readFile.nextLine();
                listArea.add(getAreaToContent(text));
            }
            FileWriter fileWriter = new FileWriter(padtAlert);
            while (true) {
                Position position = dataShare.listData.take();
                if (position.getMmsi().equalsIgnoreCase("end")){
                    break;
                }
                Warning warning = Warning.getWarningInform(listArea, position);
                fileWriter.write(warning.toString()+ "\n");
            }
            fileWriter.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public ThreadArea(String pathArea, DataShare dataShare) {
        this.pathArea = pathArea;
        this.dataShare = dataShare;
    }

    public static Area getAreaToContent(String text) {
        Area area = new Area();
        String name = getInformationArea(text, 1);
        area.setNameArea(name);
        area.setLongitudeLeft(Integer.parseInt(getInformationArea(text, 2)));
        area.setLongitudeRight(Integer.parseInt(getInformationArea(text, 3)));
        area.setLatitudeTop(Integer.parseInt(getInformationArea(text, 4)));
        area.setLatitudeBottom(Integer.parseInt(getInformationArea(text, 5)));
        return area;
    }

    public static String getInformationArea(String area, int choice) {
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
}
