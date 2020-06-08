package com.example.admin_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin_demo.model.Category;
import com.example.admin_demo.viewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerViewA extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> recyclerAdapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //Intent i=getIntent();
      //  final String uname=i.getStringExtra("uname");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("books");
        recyclerView =(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadData();
    }
    private void loadData()
    {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(databaseReference,Category.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {

                holder.author.setText(model.getAuthor());
                holder.booId.setText(model.getBookId());
                holder.cat.setText(model.getCategory());
                holder.issue_status.setText(model.getIssue_status());
                holder.title.setText((model.getTitle()));


            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_category,viewGroup,false);
                return new CategoryViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }
}

