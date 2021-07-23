package test;

public class MyRunable implements Runnable{
    private int count = 0;
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            count++;
            System.out.println(Thread.currentThread().getName() + " hello thread " + " count: " + count);

        }


    }
}
