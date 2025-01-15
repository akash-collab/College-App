package com.example.admincollegeapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText signupName, signupEmail, signupPassword, signupConfirmPassword;
    private Button signupButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirmPassword = findViewById(R.id.signup_confirm_password);
        signupButton = findViewById(R.id.signup_button);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String confirmPassword = signupConfirmPassword.getText().toString().trim();

                if (validateForm(name, email, password, confirmPassword)) {
                    registerUser(name, email, hashPassword(password));
                }
            }
        });
    }

    private boolean validateForm(String name, String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(name)) {
            signupName.setError("Name is required");
            signupName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Valid email is required");
            signupEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            signupPassword.setError("Password is required");
            signupPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            signupPassword.setError("Password should be at least 6 characters");
            signupPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            signupConfirmPassword.setError("Passwords do not match");
            signupConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void registerUser(String name, String email, String hashedPassword) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("password", hashedPassword);

        databaseReference.push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    Toast.makeText(SignupActivity.this, "Registration failed, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
