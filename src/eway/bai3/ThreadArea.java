package eway.bai3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadArea extends Thread {
    private String pathArea;

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
                listArea.add(Area.getAreaToContent(text));
            }
            FileWriter fileWriter = new FileWriter(padtAlert);
            while (true) {
                if (ThreadPosition.checkThread && ThreadPosition.listPosition.size() == 0) {
                    break;
                }
                Position position = ThreadPosition.listPosition.take();
                Warning warning = Warning.getWarningInform(listArea, position);
                fileWriter.write(warning.toString()+ "\n");
            }
            fileWriter.close();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
