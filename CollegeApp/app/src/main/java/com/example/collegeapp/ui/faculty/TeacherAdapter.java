package com.example.collegeapp.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewAdapter> {
    private final List<TeacherData> list;
    private final Context context;


    public TeacherAdapter(List<TeacherData> list, Context context, String category) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public TeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.faculty_item_layout, parent, false );
        return new TeacherViewAdapter( view );
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewAdapter holder, int position) {
        TeacherData item = list.get( position );
        holder.name.setText( item.getName() );
        holder.email.setText( item.getEmail() );
        holder.post.setText( item.getPost() );
        try {
            Picasso.get().load( item.getImage() ).into( holder.imageView );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherViewAdapter extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView email;
        private final TextView post;

        private final ImageView imageView;

        public TeacherViewAdapter(@NonNull View itemView) {
            super( itemView );
            name = itemView.findViewById( R.id.teacherName );
            email = itemView.findViewById( R.id.teacherEmail );
            post = itemView.findViewById( R.id.teacherPost );
            imageView = itemView.findViewById( R.id.teacherImage );
        }
    }
}
