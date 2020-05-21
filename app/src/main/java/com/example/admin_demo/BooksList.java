package com.example.admin_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BooksList extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference fDatabaseRoot= database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        fDatabaseRoot.child("books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> categoryList = new ArrayList<String>();

                for (DataSnapshot catSnapshot: dataSnapshot.getChildren()) {
                    String cat = catSnapshot.child("category").getValue(String.class);
                    if (cat!=null && !categoryList.contains(cat)){
                        categoryList.add(cat);
                    }
                }
                Spinner spinnerCat = (Spinner) findViewById(R.id.spL);
                ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(BooksList.this, android.R.layout.simple_spinner_item, categoryList);
                catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCat.setAdapter(catAdapter);




                spinnerCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Your code here

                        String bk=categoryList.get(i);
                        openList(bk);

                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void openList(String s)
    {
        DatabaseReference book = FirebaseDatabase.getInstance().getReference("books");
        Query query = book.orderByChild("category").equalTo(s);
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> List = new ArrayList<String>();

                for (DataSnapshot catSnapshot: dataSnapshot.getChildren()) {
                    String title = catSnapshot.child("title").getValue(String.class)+" by "+catSnapshot.child("author").getValue(String.class);
                    if (title!=null){
                        List.add(title);
                    }
                }

                ListView titleView=(ListView) findViewById(R.id.tList);
                ArrayAdapter<String> titAdapter = new ArrayAdapter<String>(BooksList.this, android.R.layout.simple_list_item_1, android.R.id.text1, List);
                titleView.setAdapter(titAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}