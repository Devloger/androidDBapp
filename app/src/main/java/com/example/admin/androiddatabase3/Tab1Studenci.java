package com.example.admin.androiddatabase3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Tab1Studenci extends Fragment implements OnClickListener {

    ListView studenciListView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

//        Grupa grupa = new Grupa("32INF-SP");
//        Grupa grupa2 = new Grupa("12INF-SP");
//
//        grupa.save();
//        grupa2.save();
//
//
//        List<Grupa> grupki = Grupa.listAll(Grupa.class);
//
//        for ( Grupa g : grupki)
//        {
//            System.out.println(g);
//        }
//
//
//        Student book = new Student("Adrian", "Stawski", 16, Grupa.findById(Grupa.class, 1L));
//        book.save();
//
//        Student book2 = new Student("Marek", "Wierucki", 24, Grupa.findById(Grupa.class, 2L));
//        book2.save();

        ListView studenciListView = (ListView) getView().findViewById(R.id.lista);
        List<Student> studenci = Student.listAll(Student.class);

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.studenci_list_view_layout, R.id.textView ,studenci);
        studenciListView.setAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.studenci, container, false);

        Button b = (Button) view.findViewById(R.id.dodaj);
        b.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dodaj:
                break;
        }
    }
}