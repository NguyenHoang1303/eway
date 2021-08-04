package eway.bai3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainWarning {
    public final static String PATH_WARNING = "src/eway/bai3/filetxt/alert.txt";
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String pathArea = "src/eway/bai3/filetxt/area.txt";
        String pathPositions = "src/eway/bai3/filetxt/positions.txt";
        List<String> informationAreas = readFile(pathArea);
        List<String> infortmationPositions = readFile(pathPositions);
        FileInputStream fileInputStream = new FileInputStream(pathArea);
        Scanner readFileArea = new Scanner(fileInputStream);
        List<Area> listThreadArea = new ArrayList<>();
        while (readFileArea.hasNextLine()){
            String text = readFileArea.nextLine();
            Area area = new Area(text);
            area.start();
            listThreadArea.add(area);
        }
        for (Area a : listThreadArea) {
            a.join();
        }
        List<Area> listArea = new ArrayList<>();
        for (Area a : listThreadArea) {
            listArea.add(a.getArea());
        }

        FileInputStream filePosition = new FileInputStream(pathPositions);
        Scanner readFilePositom = new Scanner(filePosition);
        List<Warning> list = new ArrayList<>();
        List<Position> listP = new ArrayList<>();
        while (readFilePositom.hasNextLine()){
            String inforPosition = readFilePositom.nextLine();
            if (inforPosition != null){
                Position position = new Position(inforPosition);
                position.start();
                listP.add(position);
            }
        }
        for (Position p : listP) {
            p.join();
        }
        Position position = new Position();
        for (Position p: listP) {
            position = p.getPosition();
            Warning warning = new Warning(listArea,position);
            warning.start();
            list.add(warning);
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).join();
        }

        Warning warning = new Warning();
        for (Warning w: list) {
            warning = w.getWarning();
            System.out.println(warning.toString());
        }








//        List<Area> listArea = getListArea(informationAreas);
//        List<Position> positionList = getListPosition(infortmationPositions);
//        Warning warning = new Warning();
//        List<Warning> warningList = new ArrayList<>();
//        for (Position position : positionList) {
//           warningList.add(warning.getWarning(listArea,position));
//          }
//        writeFile(warningList);
    }

    static List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner readFile = new Scanner(fileInputStream);
            List<Thread> threadList = new ArrayList<>();
            while (readFile.hasNextLine()) {
                String text = readFile.nextLine();
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        list.add(text);
                    }
                };
                thread.start();
                threadList.add(thread);
            }
        } catch (FileNotFoundException  e) {
            e.printStackTrace();
        }
        return list;
    }

    static void writeFile(List<Warning> list){
        try {
            FileWriter fileWriter = new FileWriter(PATH_WARNING,true);
            List<Thread> threadList = new ArrayList<>();
            for (Warning warning: list) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            fileWriter.write(warning.toString()+ "\n");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                threadList.add(thread);
            }

            for (Thread thread: threadList) {
                thread.join();
            }
            fileWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
