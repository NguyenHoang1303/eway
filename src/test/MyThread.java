package test;



public class MyThread extends Thread {
    private int count = 0;

    @Override
    public void run() {
        System.out.println("shut down hook task completed..");
//        for (int i = 0; i < 5; i++) {
//                count++;
//                System.out.println( this.getName() + " count: " + count);
//        }
    }
}
