package eway.bai3;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile extends Thread{
    public final static String PATH_WARNING = "src/eway/bai3/filetxt/alert.txt";
    private StringBuilder messageWarning;
    public WriteFile(StringBuilder messageWarning) {
        this.messageWarning = messageWarning;
    }

    @Override
    public void run() {
        writeFile();
    }

    void writeFile(){
        try {
            FileWriter fileWriter = new FileWriter(PATH_WARNING,true);
            fileWriter.write(String.valueOf(this.messageWarning));
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
