package test;


public class MyThread extends Thread {
    private ShareData shareData;
    private int[] listNumber ={1,2,3,4,5,6,7};

    public MyThread(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        int count = 0;
        for (int number: listNumber) {
            count++;
            if (count < listNumber.length){
                shareData.setNumber(count);
                try {
                    shareData.listInt.put(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        shareData.setNumber(1);
    }


}
