package eway.bai3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadAndWrite extends Thread{
    private String text;
    private String pathRead;
    private String pathWirte;
    @Override
    public void run() {
        readFile();
    }

    public ReadAndWrite(String pathRead) {
        this.pathRead = pathRead;
    }

    synchronized void readFile() {
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathRead);
            Scanner readFile = new Scanner(fileInputStream);
            while (readFile.hasNextLine()) {
                list.add(readFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
