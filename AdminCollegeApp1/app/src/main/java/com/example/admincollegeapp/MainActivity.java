package com.example.admincollegeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.admincollegeapp.faculty.UpdateFaculty;
import com.example.admincollegeapp.notice.DeleteNoticeActivity;
import com.example.admincollegeapp.notice.UploadNotice;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView uploadNotice, addGalleryImage, addEbook, faculty, deleteNotice;
    private Button logoutButton;
    GoogleSignInClient gsc;
    SessionManager sessionManager;
    GoogleSignInAccount acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);
        acc = GoogleSignIn.getLastSignedInAccount( this );
        sessionManager = new SessionManager( this );
        // Initialize CardViews
        uploadNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        deleteNotice = findViewById(R.id.deleteNotice);
        faculty = findViewById(R.id.faculty);
        logoutButton = findViewById(R.id.logout_button);

        // Set onClick listeners
        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        if (view.getId() == R.id.addNotice) {
            intent = new Intent(MainActivity.this, UploadNotice.class);
            startActivity(intent);
        } else if (view.getId() == R.id.addGalleryImage) {
            intent = new Intent(MainActivity.this, Uploadimage.class); // Corrected class name
            startActivity(intent);
        } else if (view.getId() == R.id.addEbook) {
            intent = new Intent(MainActivity.this, UploadPdfActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.faculty) {
            intent = new Intent(MainActivity.this, UpdateFaculty.class);
            startActivity(intent);
        } else if (view.getId() == R.id.deleteNotice) {
            intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.logout_button) {

            if(acc!=null){
                signoutGoogle();
            }
            else{
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                sessionManager.logoutUser();
            }

        }
    }
    private void signoutGoogle() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
