package com.example.admin_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class
Register extends AppCompatActivity {

    DatabaseReference userRef =FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference mRootRef;
    int k=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText e1=(EditText)findViewById(R.id.register_username);
        final EditText e2=(EditText)findViewById(R.id.register_password);
        final EditText e3=(EditText)findViewById(R.id.register_confirm_password);
        Button b=(Button)findViewById(R.id.register_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(e2.getText().toString().equals(e3.getText().toString())) {
                    if (e2.getText().toString().length() < 8)
                        Toast.makeText(getApplicationContext(), "Password cannot be less than 8 characters", Toast.LENGTH_SHORT).show();

                    else {



                        /*My_DB dbHandler = new My_DB(getApplicationContext(), null, null, 1);

                        String username = e1.getText().toString();

                        String password = e2.getText().toString();

                        User user = new User(username, password);

                        dbHandler.addHandler(user);

                        Toast.makeText(getApplicationContext(), "Registration Sucessfull", Toast.LENGTH_SHORT).show();

                        e1.setText("");

                        e2.setText("");

                        e3.setText("");*/
                        if(userRef==null) {
                            mRootRef = FirebaseDatabase.getInstance().getReference();

                            mRootRef.child("users").child(e1.getText().toString()).setValue(new User(e1.getText().toString(), e2.getText().toString()));

                            Toast.makeText(getApplicationContext(), "Registration Sucessfull", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot users:dataSnapshot.getChildren())
                                    {
                                        User user = users.getValue(User.class);
                                        if(user.getUsername().compareTo(e1.getText().toString())==0)
                                        {
                                            k=1;
                                            break;
                                        }

                                    }
                                    if(k==0)
                                    {
                                        userRef.child(e1.getText().toString()).setValue(new User(e1.getText().toString(), e2.getText().toString()));
                                        Toast.makeText(getApplicationContext(), "Registration Sucessfull", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                    }
                                    else if(k==1)
                                    {
                                        Toast.makeText(getApplicationContext(), "User Already exists", Toast.LENGTH_SHORT).show();
                                        e1.setText("");
                                        e2.setText("");
                                        e3.setText("");
                                        k=0;

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }
                }


                else
                    Toast.makeText(getApplicationContext(),"Password Mismatch",Toast.LENGTH_SHORT).show();


            }
        });
    }

}
