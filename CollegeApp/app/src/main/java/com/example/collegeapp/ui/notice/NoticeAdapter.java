package com.example.collegeapp.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.FullImageView;
import com.example.collegeapp.R;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private Context context;
    private List<NoticeData> noticeList;

    public NoticeAdapter(Context context, List<NoticeData> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        NoticeData noticeData = noticeList.get(position);

        holder.title.setText(noticeData.getTitle());
        holder.date.setText(noticeData.getDate());
        holder.time.setText(noticeData.getTime());

        try {
            if (holder.image != null) {
                if (noticeData.getImage() != null && !noticeData.getImage().isEmpty()) {
                    Glide.with(context)
                            .load(noticeData.getImage())
                            .placeholder(R.drawable.avatarprofile)
                            .error(R.drawable.error_image)
                            .into(holder.image);
                } else {
                    holder.image.setImageResource(R.drawable.avatarprofile);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            holder.image.setImageResource(R.drawable.error_image); // set a default error image
        }

        holder.itemView.findViewById(R.id.deleteNoticeImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FullImageView.class );
                intent.putExtra( "image",noticeData.getImage());
                context.startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, time;
        ImageView image;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.deleteNoticeTitle);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.deleteNoticeImage);
        }
    }
}
