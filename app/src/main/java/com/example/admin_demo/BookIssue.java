package com.example.admin_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookIssue extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference fDatabaseRoot= database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue);
        Button btn=(Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                Spinner sp1= (Spinner)findViewById(R.id.stuL);
                TextView t1=(TextView)findViewById(R.id.bookId);
                String studentName= sp1.getSelectedItem().toString();
                String  bookId=t1.getText().toString();
                String formattedDate = df.format(c);
                String p="pending";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference fDatabaseRoot= database.getReference();
                fDatabaseRoot.child("issue").child(bookId).setValue(new Issue(bookId,formattedDate,p,studentName));


                final DatabaseReference bup = FirebaseDatabase.getInstance().getReference("books");
                Query query = bup.orderByChild("bookId").equalTo(bookId);

                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot child : dataSnapshot.getChildren()) {
                            child.getRef().child("issue_status").setValue("true");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(getApplicationContext(), "Book Issued", Toast.LENGTH_SHORT).show();

                Intent t = new Intent(BookIssue.this,Admin_Home.class);
                startActivity(t);
            }
        });


        DatabaseReference stu = FirebaseDatabase.getInstance().getReference("users");
        Query query = stu.orderByChild("user_type").equalTo("student");

        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> ListS = new ArrayList<String>();

                for (DataSnapshot catSnapshot: dataSnapshot.getChildren()) {

                    String  title = catSnapshot.child("username").getValue(String.class);
                    if (title!=null ){
                        ListS.add(title);
                    }
                }

    Spinner spinnerStu = (Spinner) findViewById(R.id.stuL);
    ArrayAdapter<String> stuAdapter = new ArrayAdapter<String>(BookIssue.this, android.R.layout.simple_spinner_item, ListS);
    stuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerStu.setAdapter(stuAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fDatabaseRoot.child("books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> categoryList = new ArrayList<String>();

                for (DataSnapshot catSnapshot : dataSnapshot.getChildren()) {
                    String cat = catSnapshot.child("category").getValue(String.class);
                    if (cat != null && !categoryList.contains(cat)) {
                        categoryList.add(cat);
                    }
                }

                    Spinner spinnerCat = (Spinner) findViewById(R.id.categ);
                    ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(BookIssue.this, android.R.layout.simple_spinner_item, categoryList);
                    catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCat.setAdapter(catAdapter);


                    spinnerCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            // Your code here

                            String bk = categoryList.get(i);
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
                final List<String> idList = new ArrayList<String>();
                for (DataSnapshot catSnapshot: dataSnapshot.getChildren()) {
                    boolean t= catSnapshot.child("issue_status").getValue(String.class).equalsIgnoreCase("false");
                    String  title = catSnapshot.child("title").getValue(String.class) + " by " + catSnapshot.child("author").getValue(String.class);
                    String title2=catSnapshot.child("bookId").getValue(String.class);
                    if (title!=null && t ){
                        List.add(title);
                        idList.add(title2);
                    }
                }


                Spinner spinnerBok = (Spinner) findViewById(R.id.bokL);
                ArrayAdapter<String> bokAdapter = new ArrayAdapter<String>(BookIssue.this, android.R.layout.simple_spinner_item, List);
                bokAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBok.setAdapter(bokAdapter);

                spinnerBok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Your code here

                        String bi=idList.get(i);
                        TextView t2=(TextView)findViewById(R.id.bookId);
                        t2.setText(bi);
                        t2.setVisibility(View.INVISIBLE);
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
      /*  LinearLayout linearLayout = findViewById(R.id.layout2);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();*/
    }

    }


