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

public class Profile extends AppCompatActivity {
    int q=0;
    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i=getIntent();
        final String username=i.getStringExtra("uname");

        TextView tv1 =(TextView)findViewById(R.id.p_tv1);
        final TextView tv2 =(TextView)findViewById(R.id.p_tv2);
        final TextView tv3=(TextView)findViewById(R.id.p_tv3);
        final TextView tv4=(TextView)findViewById(R.id.p_tv4);

        tv1.setText("Username is : "+username);

        DatabaseReference uref= FirebaseDatabase.getInstance().getReference("users");

        uref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot users:dataSnapshot.getChildren())
                {
                    User user = users.getValue(User.class);
                    if(user.getUsername().compareTo(username)==0)
                    {
                        tv2.setText("Password is : "+user.getPassword());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* DatabaseReference qref=FirebaseDatabase.getInstance().getReference("questions");
        qref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot questions:dataSnapshot.getChildren())
                {
                    Questions question = questions.getValue(Questions.class);
                    //Toast.makeText(getApplicationContext(),question.getUname(),Toast.LENGTH_SHORT).show();
                    if(question.getUname().compareTo(username)==0)
                    {
                        q++;
                    }
                }
                String ques=Integer.toString(q);
                tv3.setText("Total Questions Posted "+ques);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/

      /*  DatabaseReference aref =FirebaseDatabase.getInstance().getReference("answers");
        aref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot answers:dataSnapshot.getChildren())
                {
                    Answer answer=answers.getValue(Answer.class);
                    //Toast.makeText(getApplicationContext(),answer.getUid(),Toast.LENGTH_SHORT).show();
                    if(answer.getUid().compareTo(username)==0)
                    {
                        a++;
                    }
                }
                String ans=Integer.toString(a);

                tv4.setText("Total Answers Posted "+ans);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/








        Button b=(Button)findViewById(R.id.p_b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                i.putExtra("uname",username);
                startActivity(i);

            }
        });


    }



}
