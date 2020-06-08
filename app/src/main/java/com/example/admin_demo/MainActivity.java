package com.example.admin_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView TextViewRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextViewRegister = (TextView) findViewById(R.id.login_register);
        TextViewRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                startActivity(registerIntent);

            }
        });

        final EditText ed1=(EditText) findViewById(R.id.login_username);
        final EditText ed2=(EditText) findViewById(R.id.login_password);
        Button b=(Button)findViewById(R.id.login_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* My_DB dbHandler = new My_DB(getApplicationContext(), null, null, 1);
                User user = dbHandler.findHandler(ed1.getText().toString());
                if (user != null) {
                    Log.v("Pass from db",user.getPassword());
                    Log.v("Pass from user",ed2.getText().toString());
                    if(user.getPassword().equals(ed2.getText().toString()))
                    {
                        Intent i=new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"User Not found",Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                    ed2.setText("");
                }*/

                if (ed1.getText().toString().isEmpty() || ed2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter Both username and Password!", Toast.LENGTH_SHORT).show();

                else {

                    DatabaseReference userref = FirebaseDatabase.getInstance().getReference("users");

                    userref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(ed1.getText().toString())) {
                                User user = dataSnapshot.child(ed1.getText().toString()).getValue(User.class);
                                if (user.getPassword().compareTo(ed2.getText().toString()) == 0) {
                                    if(user.getUser_type().equalsIgnoreCase("student")) {
                                        Intent i = new Intent(getApplicationContext(), Dashboard.class);
                                      // Intent i = new Intent(getApplicationContext(), RecyclerViewA.class);
                                        i.putExtra("uname", ed1.getText().toString());
                                        startActivity(i);
                                    }
                                    else if(user.getUser_type().equalsIgnoreCase("admin"))
                                    {
                                        Intent i = new Intent(getApplicationContext(), Admin_Home.class);
                                        i.putExtra("uname", ed1.getText().toString());
                                        startActivity(i);
                                    }
                                } else
                                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();

                            } else  {
                                Toast.makeText(getApplicationContext(), "User Not found", Toast.LENGTH_SHORT).show();

                                ed1.setText("");

                                ed2.setText("");
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }


        });


      /* Button b1=(Button)findViewById(R.id.show_users);
      final  TextView tv=(TextView)findViewById(R.id.tv);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                   My_DB dbHandler = new My_DB(getApplicationContext(), null, null, 1);
                   tv.setText(dbHandler.loadHandler());
                   ed1.setText("");
                   ed2.setText("");
               }
       });*/
    }
}