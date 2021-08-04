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
        ThreadPosition threadPosition = new ThreadPosition(pathPositions);
        ThreadArea threadArea = new ThreadArea(pathArea);
        threadArea.start();
        threadPosition.start();
    }
}
