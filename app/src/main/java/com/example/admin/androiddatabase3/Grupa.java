package com.example.admin.androiddatabase3;

import com.orm.SugarRecord;

public class Grupa extends SugarRecord<Grupa> {

    String nazwa;

    public Grupa(){
    }

    public Grupa(String nazwa)
    {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return this.nazwa;
    }
}