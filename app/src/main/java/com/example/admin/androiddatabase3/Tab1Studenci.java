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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Tab1Studenci extends Fragment implements OnClickListener {

    ListView studenciListView;
    MyListAdaper studenciListaAdapter;

    EditText imie;
    EditText nazwisko;
    EditText wiek;
    Spinner grupa;
    long id = 0;

    Button dodaj;
    Button aktualizuj;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

//        Grupa.deleteAll(Grupa.class);
////        Student.deleteAll(Student.class);
//
        if(Grupa.listAll(Grupa.class).isEmpty())
        {
            Grupa grupa = new Grupa("13INF-SP");
            Grupa grupa2 = new Grupa("14INF-SP");
            Grupa grupa3 = new Grupa("15INF-SP");

            grupa.save();
            grupa2.save();
            grupa3.save();
        }

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


        studenciListView = (ListView) getView().findViewById(R.id.lista);
        List<Student> studenci = Student.listAll(Student.class);

//        studenciListaAdapter = new ArrayAdapter(getActivity(), R.layout.studenci_list_view_layout, R.id.textView ,studenci);
        studenciListaAdapter = new MyListAdaper(getContext(), R.layout.studenci_list_view_layout, studenci, this);

        studenciListView.setAdapter(studenciListaAdapter);

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

        dodaj = (Button) view.findViewById(R.id.dodaj);
        dodaj.setOnClickListener(this);
        aktualizuj = (Button) view.findViewById(R.id.aktualizuj);
        aktualizuj.setOnClickListener(this);

        aktualizuj.setClickable(false);
        aktualizuj.setAlpha(0);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dodaj:
                Grupa grupka = Grupa.find(Grupa.class,"nazwa = ?", grupa.getSelectedItem().toString()).get(0);
                Student student = new Student(
                        imie.getText().toString(),
                        nazwisko.getText().toString(),
                        Integer.parseInt(wiek.getText().toString()),
                        grupka
                );
                student.save();

                imie.setText("");
                nazwisko.setText("");
                wiek.setText("");

                studenciListaAdapter.notifyDataSetChanged();
                studenciListView.invalidateViews();
                break;
            case R.id.aktualizuj:
                Grupa grupkaa = Grupa.find(Grupa.class,"nazwa = ?", grupa.getSelectedItem().toString()).get(0);
                Student studentt = Student.findById(Student.class, id);
                studentt.imie = imie.getText().toString();
                studentt.nazwisko = nazwisko.getText().toString();
                studentt.wiek = Integer.parseInt(wiek.getText().toString());
                studentt.grupa = grupkaa;
                studentt.save();

                imie.setText("");
                nazwisko.setText("");
                wiek.setText("");

//                studenciListaAdapter = new MyListAdaper(getContext(), R.layout.studenci_list_view_layout, Student.listAll(Student.class), this);
//                studenciListView.setAdapter(studenciListaAdapter);

                dodaj.setClickable(true);
                dodaj.setAlpha(1);

                aktualizuj.setClickable(false);
                aktualizuj.setAlpha(0);

                studenciListaAdapter.notifyDataSetChanged();
                studenciListView.invalidateViews();
                break;
            case R.id.usun:
                System.out.println("usuwa");
                break;
        }

    }

    public void refresh()
    {
        studenciListaAdapter.notifyDataSetChanged();
        studenciListView.invalidateViews();
    }



    private class MyListAdaper extends ArrayAdapter<Student> {
        private int layout;
        private List<Student> mObjects;
        private Tab1Studenci tab;
        private MyListAdaper(Context context, int resource, List<Student> objects, Tab1Studenci tab1) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
            tab = tab1;
        }

        @Override
        public void notifyDataSetChanged() {
            mObjects.clear();
            mObjects.addAll(Student.listAll(Student.class));
            super.notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.textView);
                viewHolder.usun = (Button) convertView.findViewById(R.id.usun);
                viewHolder.edytuj = (Button) convertView.findViewById(R.id.edytuj);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.usun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Student studento = Student.findById(Student.class, mObjects.get(position).getId());
                    studento.delete();

                    tab.refresh();


//                    System.out.println("Oke");
//                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });
            mainViewholder.edytuj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Student studento = Student.findById(Student.class, mObjects.get(position).getId());
                    tab.imie.setText(studento.imie);
                    tab.nazwisko.setText(studento.nazwisko);
                    tab.wiek.setText(String.valueOf(studento.wiek));

                    tab.id = studento.getId();

                    tab.dodaj.setClickable(false);
                    tab.dodaj.setAlpha(0);

                    tab.aktualizuj.setClickable(true);
                    tab.aktualizuj.setAlpha(1);

                    tab.refresh();
                }
            });
            mainViewholder.title.setText(mObjects.get(position).toString());
            mainViewholder.id = mObjects.get(position).getId();
            mainViewholder.imie = mObjects.get(position).imie;
            mainViewholder.nazwisko = mObjects.get(position).nazwisko;
            mainViewholder.wiek = mObjects.get(position).wiek;
            mainViewholder.grupa = mObjects.get(position).grupa;

            return convertView;
        }
    }
    public class ViewHolder {

        TextView title;
        Button usun;
        Button edytuj;
        long id;
        String imie;
        String nazwisko;
        int wiek;
        Grupa grupa;
    }
}