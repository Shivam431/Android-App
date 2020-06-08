package com.example.admin_demo.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_demo.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView author,booId,cat,issue_status,title;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        author = (TextView)itemView.findViewById(R.id.au);
      booId = (TextView)itemView.findViewById(R.id.b);
         cat= (TextView)itemView.findViewById(R.id.c);
        issue_status= (TextView)itemView.findViewById(R.id.i);
        title= (TextView)itemView.findViewById(R.id.t);
    }

    @Override
    public void onClick(View v) {

    }
}
