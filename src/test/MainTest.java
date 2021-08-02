package test;


import eway.bai2.MainViettel;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        list1.add(7);

        List<Integer> list3 = new ArrayList<>();
        list3.addAll(list1);
        list3.addAll(list);

        for (int a: list) {
            int c = 0;
            for (int b: list3) {
                if (a==b) c++;
            }
            if (c == 1) System.out.println(a);

        }



    }

    static void testSortTime(){
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
        for (String s: list
        ) {
            System.out.println(s);
        }
    }
}
