package com.example.collegeapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    RecyclerView convo, other;
    GalleryAdapter adapter;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        convo = view.findViewById(R.id.convo);
        other = view.findViewById(R.id.other);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getConvoImage();
        getOtherImage();

        return view;
    }

    private void getOtherImage() {
        reference.child("Other Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                imageList.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Object data = snapshot.getValue();
                    if (data instanceof String) {
                        imageList.add((String) data);
                    }
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                other.setLayoutManager(new GridLayoutManager(getContext(), 3));
                other.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load images: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                imageList.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    Object data = snapshot.getValue();
                    if (data instanceof String) {
                        imageList.add((String) data);
                    }
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                convo.setLayoutManager(new GridLayoutManager(getContext(), 3));
                convo.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load images: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
