package eway.bai3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWarning {

    public static void main(String[] args) {
        String pathArea = "src/eway/bai3/area.txt";
        String pathPositions = "src/eway/bai3/positions.txt";
        List<String> listArea = MainWarning.readFile(pathArea);
        String str = "Vung 2|11|50|150|100";
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            System.out.println(matcher.group(2));
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
           }
        }
        return null;
    }
}
