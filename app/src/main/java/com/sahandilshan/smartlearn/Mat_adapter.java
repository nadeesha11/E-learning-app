package com.sahandilshan.smartlearn;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Mat_adapter extends FirebaseRecyclerAdapter<Mat_info,Mat_adapter.myviewholder>
{

    public Mat_adapter(@NonNull FirebaseRecyclerOptions<Mat_info> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, int position, @NonNull final Mat_info Mat_info) {

        holder.header.setText(Mat_info.getFilename());

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.img1.getContext(),Mat_viewpdf.class);
                intent.putExtra("filename",Mat_info.getFilename());
                intent.putExtra("fileurl",Mat_info.getFileurl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img1.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mat_singlelayout,parent,false);
        return new myviewholder(view);
    }
    

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView header;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            img1=itemView.findViewById(R.id.img1);
            header=itemView.findViewById(R.id.header);
        }
    }
}
