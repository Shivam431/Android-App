package com.example.admin_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class BookSubmission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_submission);

        Button btn=(Button)findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                Spinner sp1= (Spinner)findViewById(R.id.StudentList);
                Spinner sp2=(Spinner) findViewById(R.id.BookIdList);
                String studentName= sp1.getSelectedItem().toString();
                String  bookId=  sp2.getSelectedItem().toString();
                final String resD = df.format(c);

                final DatabaseReference iss = FirebaseDatabase.getInstance().getReference("issue");
                Query query1 = iss.orderByChild("bookId").equalTo(bookId);

                query1.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot child : dataSnapshot.getChildren()) {
                            child.getRef().child("return_date").setValue(resD);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                final DatabaseReference bup = FirebaseDatabase.getInstance().getReference("books");
                Query query = bup.orderByChild("bookId").equalTo(bookId);

                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot child : dataSnapshot.getChildren()) {
                            child.getRef().child("issue_status").setValue("false");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(getApplicationContext(), "Book Submitted", Toast.LENGTH_SHORT).show();
                Intent t = new Intent(BookSubmission.this,Admin_Home.class);
                startActivity(t);

            }
        });




        DatabaseReference stu = FirebaseDatabase.getInstance().getReference("issue");
        Query query = stu.orderByChild("return_date").equalTo("pending");

        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> ListS = new ArrayList<String>();

                for (DataSnapshot catSnapshot : dataSnapshot.getChildren()) {

                    String title = catSnapshot.child("student").getValue(String.class);
                    if (title != null && !ListS.contains(title)) {
                        ListS.add(title);
                    }
                }

                if (!ListS.isEmpty()) {
                    Spinner spinnerStu = (Spinner) findViewById(R.id.StudentList);
                    ArrayAdapter<String> stuAdapter = new ArrayAdapter<String>(BookSubmission.this, android.R.layout.simple_spinner_item, ListS);
                    stuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerStu.setAdapter(stuAdapter);

                    spinnerStu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            // Your code here

                            String bk = ListS.get(i);
                            openList(bk);

                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });
                }else
                {
                    Toast.makeText(getApplicationContext(), "No student in issues list", Toast.LENGTH_SHORT).show();
                    Intent t = new Intent(BookSubmission.this,Admin_Home.class);
                    startActivity(t);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void openList(String bk)
    {
        DatabaseReference stu = FirebaseDatabase.getInstance().getReference("issue");
        Query query = stu.orderByChild("student").equalTo(bk);

        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> ListS = new ArrayList<String>();

                for (DataSnapshot catSnapshot: dataSnapshot.getChildren()) {

                    String  title = catSnapshot.child("bookId").getValue(String.class);
                    String  title2 = catSnapshot.child("return_date").getValue(String.class);
                    if (title!=null && title2.equalsIgnoreCase("pending")){
                        ListS.add(title);
                    }
                }

                  if(!ListS.isEmpty()) {
                      Spinner spinnerStu = (Spinner) findViewById(R.id.BookIdList);
                      ArrayAdapter<String> stuAdapter = new ArrayAdapter<String>(BookSubmission.this, android.R.layout.simple_spinner_item, ListS);
                      stuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                      spinnerStu.setAdapter(stuAdapter);
                  }else
                  {
                      Toast.makeText(getApplicationContext(), "No student in issues list", Toast.LENGTH_SHORT).show();
                      Intent t = new Intent(BookSubmission.this,Admin_Home.class);
                      startActivity(t);
                  }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
