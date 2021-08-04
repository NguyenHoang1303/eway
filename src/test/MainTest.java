package test;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        ShareData shareData = new ShareData();
        MyThread1 mt1 = new MyThread1(shareData);
        MyThread mt = new MyThread(shareData);
        mt1.start();
        mt.start();

        List<Integer> listPrint = new ArrayList<>();



    }

    static void testSortTime() {
        List<String> list = new ArrayList<>();
        list.add("2015/12/01 00:12:23");
        list.add("2015/12/01 03:12:23");
        list.add("2015/12/01 04:12:23");
        list.add("2015/12/01 04:10:23");
        list.add("2015/12/01 01:12:23");
        list.add("2015/12/01 02:12:23");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String s : list
        ) {
            System.out.println(s);
        }
    }
}
