package com.example.admin_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        final String uname=i.getStringExtra("uname");

        setContentView(R.layout.activity_admin__home);
        final TextView profile = (TextView) findViewById(R.id.profile);
        Button logout=(Button)findViewById(R.id.logout);
        final TextView AddBook=(TextView)findViewById(R.id.ab);
        final TextView issueBook=(TextView)findViewById(R.id.issueBook);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent profileIntent = new Intent(Admin_Home.this, Profile.class);
                profileIntent.putExtra("uname",uname);
                startActivity(profileIntent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        AddBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent addint = new Intent(Admin_Home.this,AddBooks.class);
                addint.putExtra("uname",uname);
                startActivity(addint);

            }
        });
        issueBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent t = new Intent(Admin_Home.this,BookIssue.class);
                startActivity(t);

            }
        });
        final TextView subBook=(TextView)findViewById(R.id.subBook);
        subBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent t = new Intent(Admin_Home.this,BookSubmission.class);
                startActivity(t);

            }
        });
    }
}
