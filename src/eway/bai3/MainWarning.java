package eway.bai3;

public class MainWarning {

    public static void main(String[] args) {
        String pathArea = "src/eway/bai3/filetxt/area.txt";
        String pathPositions = "src/eway/bai3/filetxt/positions.txt";

        DataShare dataShare = new DataShare();
        ThreadPosition threadPosition = new ThreadPosition(pathPositions,dataShare);
        ThreadArea threadArea = new ThreadArea(pathArea, dataShare);
        threadArea.start();
        threadPosition.start();
    }
}
