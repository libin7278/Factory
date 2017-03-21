package com.libin.factory.ndk;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class Student {
    private String name;
    private int age;
    private List<People> peopleList;
    public Student(){}
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name --- >" + name + "  age --->" + age ;
    }
}
