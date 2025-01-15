package com.example.admincollegeapp.notice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admincollegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteNoticeActivity extends AppCompatActivity {

    private static final String TAG = "DeleteNoticeActivity";

    private RecyclerView deleteNoticeRecyler;
    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);

        deleteNoticeRecyler = findViewById(R.id.deleteNoticeRecyler);
        progressBar = findViewById(R.id.progressBar);

        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        deleteNoticeRecyler.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecyler.setHasFixedSize(true);

        getNotice();
    }

    private void getNotice() {
        progressBar.setVisibility(View.VISIBLE);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NoticeData data = snapshot.getValue(NoticeData.class);
                    if (data != null) {
                        list.add(data);
                    } else {
                        Log.e(TAG, "NoticeData is null for some reason");
                    }
                }

                adapter = new NoticeAdapter(DeleteNoticeActivity.this, list);
                deleteNoticeRecyler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                Log.d(TAG, "Data loaded successfully");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DeleteNoticeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Database error: " + databaseError.getMessage());
            }
        });
    }
}
