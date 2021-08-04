package test;

import java.util.concurrent.ArrayBlockingQueue;

public class ShareData {
    private int[] chan = {0,2,4,6};
    private int[] le = {1,3,5,7};
    private int number;
    static ArrayBlockingQueue<Integer> listInt = new ArrayBlockingQueue<>(5);

    public ShareData(int number) {
        this.number = number;
    }

    public ShareData() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int[] getChan() {
        return chan;
    }

    public void setChan(int[] chan) {
        this.chan = chan;
    }

    public int[] getLe() {
        return le;
    }

    public void setLe(int[] le) {
        this.le = le;
    }
}
