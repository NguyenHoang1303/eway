package com.test;

public class Student extends People{
    private String rollNumber;
    private String school;

    @Override
    public void hello() {
        super.hello();
        System.out.println("student");
    }

    public class tieuHoc extends People{
        private String hocsinh;

    }
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
