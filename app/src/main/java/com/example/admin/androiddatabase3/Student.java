package com.example.admin.androiddatabase3;

import com.orm.SugarRecord;

public class Student extends SugarRecord<Student> {

    String imie;
    String nazwisko;
    int wiek;
    Grupa grupa;

    public Student(String imie, String nazwisko, int wiek, Grupa grupa)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.grupa = grupa;
    }

    public Student(){
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko + " - " + this.wiek + " lat | " + this.grupa;
    }
}