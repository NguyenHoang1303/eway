package com.javaTuan;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    public String name;
    public String address;
    public LocalDate birdthday;

    public Person(String name, String address, LocalDate birdthday) {
        this.name = name;
        this.address = address;
        this.birdthday = birdthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirdthday() {
        return birdthday;
    }

    public void setBirdthday(LocalDate birdthday) {
        this.birdthday = birdthday;
    }
}
