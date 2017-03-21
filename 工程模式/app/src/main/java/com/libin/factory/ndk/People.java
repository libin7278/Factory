package com.libin.factory.ndk;

/**
 * Created by Administrator on 2016/4/12.
 */
public class People {
    private String name;
    private Student student;

    public People(){}

    public People(String name, Student student) {
        this.name = name;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
