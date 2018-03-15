package com.example.admin.androiddatabase3;

import com.orm.SugarRecord;

public class Student extends SugarRecord<Student> {
    int id;
    String imie;

    public Student()
    {

    }

    public Student(int id, String imie)
    {
        this.id = id;
        this.imie = imie;
    }
}