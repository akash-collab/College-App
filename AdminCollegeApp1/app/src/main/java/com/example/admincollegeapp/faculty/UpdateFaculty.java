package com.example.admincollegeapp.faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admincollegeapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView csDepartmnet, mechanicalDepartmnet, physicsDepartmnet, commerceDepartmnet, artsDepartment;
    private LinearLayout csNoData, mecNoData, physicsNoData, commerceNoData, artsNoData;
    private List<TeacherData> list1, list2, list3, list4, list5;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_faculty);

        artsDepartment = findViewById(R.id.artsDepartment);
        commerceDepartmnet = findViewById(R.id.commerceDepartment);
        physicsDepartmnet = findViewById(R.id.physicsDepartment);
        mechanicalDepartmnet = findViewById(R.id.mechanicalDepartment);
        csDepartmnet = findViewById(R.id.csDepartment);

        artsNoData = findViewById(R.id.artsNoData);
        commerceNoData = findViewById(R.id.commerceNoData);
        physicsNoData = findViewById(R.id.physicsNoData);
        mecNoData = findViewById(R.id.mecNoData);
        csNoData = findViewById(R.id.csNoData);


        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartmnet();
        mechanicalDepartmnet();
        physicsDepartmnet();
        commerceDepartmnet();
        sexDepartmnet();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this, AddTeacher.class));
            }
        });
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
                    csDepartmnet.setLayoutManager( new LinearLayoutManager( UpdateFaculty.this ) );
                    adapter = new TeacherAdapter( list1, UpdateFaculty.this,"BCA");
                    csDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( UpdateFaculty.this, DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
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
                    mechanicalDepartmnet.setLayoutManager( new LinearLayoutManager( UpdateFaculty.this ) );
                    adapter = new TeacherAdapter( list2, UpdateFaculty.this,"B-tech");
                    mechanicalDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( UpdateFaculty.this, DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
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
                    physicsDepartmnet.setLayoutManager( new LinearLayoutManager( UpdateFaculty.this ) );
                    adapter = new TeacherAdapter( list3, UpdateFaculty.this,"BBA");
                    physicsDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( UpdateFaculty.this, DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
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
                    commerceDepartmnet.setLayoutManager( new LinearLayoutManager( UpdateFaculty.this ) );
                    adapter = new TeacherAdapter( list4, UpdateFaculty.this,"B-com");
                    commerceDepartmnet.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( UpdateFaculty.this, DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
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
                    artsDepartment.setLayoutManager( new LinearLayoutManager( UpdateFaculty.this ) );
                    adapter = new TeacherAdapter( list5, UpdateFaculty.this,"Sex Education");
                    artsDepartment.setAdapter( adapter );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( UpdateFaculty.this, DatabaseError.OPERATION_FAILED, Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}