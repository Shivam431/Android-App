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

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i=getIntent();
        final String username = i.getStringExtra("uname");
        TextView tv=(TextView)findViewById(R.id.change_username);
        tv.setText(username);
        Toast.makeText(getApplicationContext(),username,Toast.LENGTH_SHORT).show();

        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
     final String s="student";
        final EditText e2=(EditText)findViewById(R.id.change_password);
        final EditText e3=(EditText)findViewById(R.id.change_confirm_password);
        Button b=(Button)findViewById(R.id.change_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (e2.getText().toString().equals(e3.getText().toString())) {
                    if (e2.getText().toString().length() < 8)
                        Toast.makeText(getApplicationContext(), "Password cannot be less than 8 characters", Toast.LENGTH_SHORT).show();

                    else {

                        userRef.child(username).setValue(new User(username, e2.getText().toString(),s));

                        Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), Dashboard.class);

                        i.putExtra("uname",username);

                        startActivity(i);
                    }
                }

                else
                    Toast.makeText(getApplicationContext(),"Password Mismatch", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
