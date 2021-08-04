package test;



public class MyThread extends Thread {
    private int count = 0;

    @Override
    public void run() {
        getCount();
    }

    public int getCount(){
        return 1;
    }
}
