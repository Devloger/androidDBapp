package com.example.admin.androiddatabase3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class Tab1Studenci extends Fragment implements OnClickListener {

    ListView studenciListView;
    ArrayAdapter<String> studenciListaAdapter;

    EditText imie;
    EditText nazwisko;
    EditText wiek;
    Spinner grupa;

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

        setSpinner();

        imie = (EditText) getView().findViewById(R.id.imie);
        nazwisko = (EditText) getView().findViewById(R.id.nazwisko);
        wiek = (EditText) getView().findViewById(R.id.wiek);
        grupa = (Spinner) getView().findViewById(R.id.grupa);





        ListView studenciListView = (ListView) getView().findViewById(R.id.lista);
        List<Student> studenci = Student.listAll(Student.class);

//        studenciListaAdapter = new ArrayAdapter(getActivity(), R.layout.studenci_list_view_layout, R.id.textView ,studenci);

        studenciListView.setAdapter(studenciListaAdapter);



//        Button bur = (Button) studenciListView.findViewById(R.id.lista);
//
//        bur.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                                            System.out.println("asd");
//
//            }
//        });

//        studenciListView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                            System.out.println("asd");
//            }
//        });
    }

    private void setSpinner() {
        List<Grupa> grupy = Grupa.listAll(Grupa.class);

        Spinner grupySpinner = (Spinner) getView().findViewById(R.id.grupa);
        ArrayAdapter<String> grupyArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, grupy);
        grupyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grupySpinner.setAdapter(grupyArrayAdapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.studenci, container, false);

        Button dodaj = (Button) view.findViewById(R.id.dodaj);
        dodaj.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dodaj:
                Grupa grupka = Grupa.find(Grupa.class,"nazwa = ?", grupa.getSelectedItem().toString()).get(1);
                Student student = new Student(
                        imie.getText().toString(),
                        nazwisko.getText().toString(),
                        Integer.parseInt(wiek.getText().toString()),
                        grupka
                );
                student.save();
                studenciListaAdapter.notifyDataSetChanged();
                studenciListView.invalidateViews();
                break;
            case R.id.usun:
                System.out.println("usuwa");
                break;
        }
    }
}