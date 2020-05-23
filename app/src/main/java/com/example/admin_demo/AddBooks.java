package com.example.admin_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddBooks extends AppCompatActivity {
    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("books");
    DatabaseReference mRootRef;
    DatabaseReference deff;

    /* DatabaseReference deff;
     Books books;

     int chck=0;*/
    int k=0;
    long k1=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_books);
        Intent i = getIntent();
        //final String username = i.getStringExtra("uname");
        final EditText ed1 = (EditText) findViewById(R.id.bid);
        final EditText ed2 = (EditText) findViewById(R.id.tid);
        final EditText ed3 = (EditText) findViewById(R.id.cid);
        final EditText ed4 = (EditText) findViewById(R.id.aid);
       deff = FirebaseDatabase.getInstance().getReference().child("books");
        deff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    k1=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      /*  books =new Books();*/
        Button b1 = (Button) findViewById(R.id.addb);
       /* b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ed1.getText().toString();
                String title = ed2.getText().toString();
                String category = ed3.getText().toString();
                String author = ed4.getText().toString();
                books.setTitle(title);
                books.setBookId(id);
                books.setAuthor(author);
                books.setCategory(category);
                books.setIssue_status("False");
                    deff.child(String.valueOf(k + 1)).setValue(books);
                    // deff.push().setValue(books);
                    Toast.makeText(AddBooks.this, "books added", Toast.LENGTH_LONG).show();

            }

        });*/
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(userRef==null)
               {
                   mRootRef = FirebaseDatabase.getInstance().getReference();

                   mRootRef.child("books").child(String.valueOf(k1 + 1)).setValue(new Books(ed1.getText().toString(), ed2.getText().toString(),ed3.getText().toString(),ed4.getText().toString(),"False"));

                   Toast.makeText(getApplicationContext(), "Books Added ", Toast.LENGTH_SHORT).show();
               }
               else
                   {
                       userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               for(DataSnapshot book:dataSnapshot.getChildren())
                               {
                                   Books books = book.getValue(Books.class);
                                   if(books.getBookId().compareTo(ed1.getText().toString())==0)
                                   {
                                       k=1;
                                       break;
                                   }

                               }
                               if(k==0)
                               {
                                   userRef.child(String.valueOf(k1 + 1)).setValue(new Books(ed1.getText().toString(), ed2.getText().toString(),ed3.getText().toString(),ed4.getText().toString(),"False"));
                                   Toast.makeText(getApplicationContext(), "book added", Toast.LENGTH_SHORT).show();
                               }
                               else if (k==1)
                               {
                                   Toast.makeText(getApplicationContext(), "id Already exists", Toast.LENGTH_SHORT).show();
                                   ed1.setText("");
                                   ed2.setText("");
                                   ed3.setText("");
                                   ed4.setText("");
                                   k=0;
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

