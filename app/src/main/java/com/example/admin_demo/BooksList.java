package com.example.admin_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BooksList extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        final TextView ed1 = (TextView) findViewById(R.id.book);
        Button b1 = (Button) findViewById(R.id.search);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ed1.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please Enter Book Name ", Toast.LENGTH_SHORT).show();

                else {

                    DatabaseReference booksref = FirebaseDatabase.getInstance().getReference("Books");

                    booksref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(ed1.getText().toString())) {
                                Books book = dataSnapshot.child(ed1.getText().toString()).getValue(Books.class);
                                if (book.getTitle().equalsIgnoreCase(ed1.getText().toString())) {
                                    String[] b={ed1.getText().toString()};
                                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_books_list,b);
                                    ListView listView = (ListView) findViewById(R.id.bookItemList);
                                        listView.setAdapter(adapter);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "No matching book found", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}


