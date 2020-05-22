package com.example.admin_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBooks extends AppCompatActivity {

    DatabaseReference deff;
Books books;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_books_add);
        Intent i = getIntent();
        //final String username = i.getStringExtra("uname");
        final EditText ed1 = (EditText) findViewById(R.id.bid);
        final EditText ed2 = (EditText) findViewById(R.id.tid);
        final EditText ed3 = (EditText) findViewById(R.id.cid);
        final EditText ed4 = (EditText) findViewById(R.id.aid);
        deff = FirebaseDatabase.getInstance().getReference().child("books");
        books =new Books();
        Button b1 = (Button) findViewById(R.id.addb);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= ed1.getText().toString();
                String title=ed2.getText().toString();
                String category=ed3.getText().toString();
                String author =ed4.getText().toString();
                books.setTitle(title);
                books.setBookId(id);
                books.setAuthor(author);
                books.setCategory(category);
                books.setIssue_status("False");
                deff.push().setValue(books);
                Toast.makeText(AddBooks.this,"books added",Toast.LENGTH_LONG).show();
            }
        });
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");


    }
}

