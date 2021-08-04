package eway.bai3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class ThreadPosition extends Thread{
    private String pathFile;
    static boolean checkThread = false;
    public static ArrayBlockingQueue<Position> listPosition = new ArrayBlockingQueue(5);

    public ThreadPosition(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public void run() {
        readFilePositon();
    }

    public void readFilePositon(){
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            Scanner read = new Scanner(fileInputStream);
            while (read.hasNextLine()){
                Position position = Position.getPositionToFile(read.nextLine());
                listPosition.put(position);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        checkThread = true;
    }
}
