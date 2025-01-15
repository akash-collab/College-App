package com.example.collegeapp.ui.faculty;

import static com.example.collegeapp.R.layout.fragment_faculty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView csDepartmnet, mechanicalDepartmnet, physicsDepartmnet, commerceDepartmnet, artsDepartment;
    private LinearLayout csNoData, mecNoData, physicsNoData, commerceNoData, artsNoData;
    private List<TeacherData> list1, list2, list3, list4, list5;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate( fragment_faculty, container, false );
        artsDepartment = view.findViewById( R.id.artsDepartment);
        commerceDepartmnet = view.findViewById(R.id.commerceDepartment);
        physicsDepartmnet = view.findViewById(R.id.physicsDepartment);
        mechanicalDepartmnet =view. findViewById(R.id.mechanicalDepartment);
        csDepartmnet = view.findViewById(R.id.csDepartment);

        artsNoData = view.findViewById(R.id.artsNoData);
        commerceNoData = view.findViewById(R.id.commerceNoData);
        physicsNoData = view.findViewById(R.id.physicsNoData);
        mecNoData = view.findViewById(R.id.mecNoData);
        csNoData = view.findViewById(R.id.csNoData);


        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartmnet();
        mechanicalDepartmnet();
        physicsDepartmnet();
        commerceDepartmnet();
        sexDepartmnet();

        return view;
    }

    private void csDepartmnet() {
        dbRef = reference.child("BCA");
        dbRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ;
                    csNoData.setVisibility( View.VISIBLE );
                    csDepartmnet.setVisibility( View.GONE );
                }else {

                    csNoData.setVisibility( View.GONE );
                    csDepartmnet.setVisibility( View.VISIBLE );
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data =snapshot.getValue(TeacherData.class);
                        list1.add( data );
                    }
                    csDepartmnet.setHasFixedSize( true );
                    csDepartmnet.setLayoutManager( new LinearLayoutManager( getContext() ) );
                    adapter = new TeacherAdapter( list1, getContext(),"BCA");

                    csDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void mechanicalDepartmnet() {
        dbRef = reference.child("B-tech");
        dbRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ;
                    mecNoData.setVisibility( View.VISIBLE );
                    mechanicalDepartmnet.setVisibility( View.GONE );
                }else {

                    mecNoData.setVisibility( View.GONE );
                    mechanicalDepartmnet.setVisibility( View.VISIBLE );
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data =snapshot.getValue(TeacherData.class);
                        list2.add( data );
                    }
                    mechanicalDepartmnet.setHasFixedSize( true );
                    mechanicalDepartmnet.setLayoutManager( new LinearLayoutManager( getContext() ) );
                    adapter = new TeacherAdapter( list2, getContext(),"B-tech");
                    mechanicalDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( getContext(), DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    private void physicsDepartmnet() {
        dbRef = reference.child("BBA");
        dbRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ;
                    physicsNoData.setVisibility( View.VISIBLE );
                    physicsDepartmnet.setVisibility( View.GONE );
                }else {

                    physicsNoData.setVisibility( View.GONE );
                    physicsDepartmnet.setVisibility( View.VISIBLE );
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data =snapshot.getValue(TeacherData.class);
                        list3.add( data );
                    }
                    physicsDepartmnet.setHasFixedSize( true );
                    physicsDepartmnet.setLayoutManager( new LinearLayoutManager( getContext() ) );
                    adapter = new TeacherAdapter( list3, getContext(),"BBA");
                    physicsDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( getContext(), DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    private void commerceDepartmnet() {
        dbRef = reference.child("B-com");
        dbRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ;
                    commerceNoData.setVisibility( View.VISIBLE );
                    commerceDepartmnet.setVisibility( View.GONE );
                }else {

                    commerceNoData.setVisibility( View.GONE );
                    commerceDepartmnet.setVisibility( View.VISIBLE );
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data =snapshot.getValue(TeacherData.class);
                        list4.add( data );
                    }
                    commerceDepartmnet.setHasFixedSize( true );
                    commerceDepartmnet.setLayoutManager( new LinearLayoutManager( getContext() ) );
                    adapter = new TeacherAdapter( list4, getContext(),"B-com");
                    commerceDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( getContext(), DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    private void sexDepartmnet() {
        dbRef = reference.child("Sex Education");
        dbRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ;
                    artsNoData.setVisibility( View.VISIBLE );
                    artsDepartment.setVisibility( View.GONE );
                }else {

                    artsNoData.setVisibility( View.GONE );
                    artsDepartment.setVisibility( View.VISIBLE );
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data =snapshot.getValue(TeacherData.class);
                        list5.add( data );
                    }
                    artsDepartment.setHasFixedSize( true );
                    artsDepartment.setLayoutManager( new LinearLayoutManager( getContext() ) );
                    adapter = new TeacherAdapter( list5, getContext(),"Sex Education");
                    artsDepartment.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( getContext(), DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}