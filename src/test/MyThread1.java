package test;


public class MyThread1 extends Thread {
    private ShareData shareData;

    public MyThread1(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        while (true){
            if (shareData.getNumber() == 1 & shareData.listInt.size() == 0){
                break;
            }
            try {
                int number = shareData.listInt.take();
                System.out.println(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
