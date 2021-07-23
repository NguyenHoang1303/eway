package com.javaTuan;

import javafx.beans.binding.Bindings;

import java.time.LocalDate;
import java.util.*;

public class Examp {
    public static void main(String[] args) {

//        List<Person> list = new ArrayList<Person>();
//
//        Person p1 = new Person("Nguyen Van Tuan", "Hung Yen", LocalDate.of(1996, 3, 4));
//        Person p2 = new Person("Nguyen Van Hung", "Hung Yen", LocalDate.of(1995, 4, 8));
//        Person p3 = new Person("Nguyen Huu Van", "Yen Bai", LocalDate.of(1996, 4, 7));
//        Person p4 = new Person("Hoang Si Nguyen", "Ha Noi", LocalDate.of(1996, 3, 4));
//        Person p5 = new Person("Vu Thi Thom", "Cao Bang", LocalDate.of(1994, 6, 6));
//
//        list.add(p1);
//        list.add(p2);
//        list.add(p3);
//        list.add(p4);
//        list.add(p5);
//        // Sap xep theo ten
//        Collections.sort(list, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });

//        for (Person a: list
//             ) {
//            System.out.println("name: " + a.name + " addressL: " + a.address + " birdDay: " + a.birdthday);
//
//        }

//        Collections.sort(list, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.birdthday.compareTo(o2.birdthday);
//            }
//        });

//        for (Person day: list
//             ) {
//            System.out.println("name: " + day.name + " addressL: " + day.address + " birdDay: " + day.birdthday);
//        }

//
//        for (Person day: list
//             ) {
//            if(day.name == "Nguyen Van Tuan"){
//                System.out.println("name: " + day.name + " addressL: " + day.address + " birdDay: " + day.birdthday);
//            }
//        }

//        for (Person day: list
//             ) {
//            if(day.birdthday.equals(LocalDate.of(1996, 3, 4))){
//                System.out.println("name: " + day.name + " addressL: " + day.address + " birdDay: " + day.birdthday);
//            }
//        }


        KhachHang khachHang1 = new KhachHang("Nguyen", "Ha Noi");
        KhachHang khachHang2 = new KhachHang("Tuan", "Ha Noi");
        KhachHang khachHang3 = new KhachHang("Thom", "Ha Noi");
        KhachHang khachHang4 = new KhachHang("Dat", "Hung Yen");
        KhachHang khachHang5 = new KhachHang("Minh", "Nha Trang");


        ArrayList<GiaoDich> giaoDiche = new ArrayList<>();
        giaoDiche.add(new GiaoDich(khachHang1, 2019, "iPhone11", 20));
        giaoDiche.add(new GiaoDich(khachHang2, 2019, "iPhoneX", 10));
        giaoDiche.add(new GiaoDich(khachHang3, 2020, "iPhoneXS", 21));
        giaoDiche.add(new GiaoDich(khachHang4, 2021, "iPhone12", 22));
        giaoDiche.add(new GiaoDich(khachHang5, 2021, "iPhone12ProMax", 25));
        giaoDiche.add(new GiaoDich(khachHang1, 2021, "iPhone11ProMax", 21));
        giaoDiche.add(new GiaoDich(khachHang2, 2021, "iPhoneXSMax", 10));
        giaoDiche.add(new GiaoDich(khachHang3, 2020, "iPhone7", 70));
        giaoDiche.add(new GiaoDich(khachHang4, 2020, "iPhone8", 80));
        giaoDiche.add(new GiaoDich(khachHang5, 2021, "iPhone12.5ProMax", 3));

//        giaoDiche.stream()
//                .filter(item -> item.getYear() == 2021)
//                .sorted((s1, s2) -> s2.getAmount() - s1.getAmount())
//                .sorted(Comparator.comparing(s -> s.getAmount()))
//                .forEach(System.out::println);
//        giaoDiche.stream()
//                .map(item -> item.getTrader().getAddress() )
//                .distinct()
//                .forEach(System.out::println);
        giaoDiche.stream()
                .filter(item -> item.getTrader().getAddress() == "Ha Noi")
                .sorted(Comparator.comparing(o-> o.getTrader().getName()))
                .forEach(System.out::println);
//        giaoDiche.stream()
//                .sorted(Comparator.comparing(item -> item.getTrader().getName()))
//                .forEach(System.out::println);

//        System.out.println(giaoDiche.stream()
//                .anyMatch(s -> s.getTrader().getAddress() == "HCM"));

//        System.out.println(giaoDiche.stream()
//                .mapToInt(item -> item.getAmount())
//                .max().getAsInt());
//        System.out.println(giaoDiche.stream()
//                .filter(item -> item.getTrader().getAddress() == "Ha Noi")
//                .mapToInt(item -> item.getAmount())
//                .reduce(0, (a, b) -> a + b));

    }
}
