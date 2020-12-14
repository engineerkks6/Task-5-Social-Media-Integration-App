package com.ayush.mymedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView myImage;
    private Button logout;
    private TextView myName,myEmail;
    private  FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        logout = findViewById(R.id.logout);
        myImage = findViewById(R.id.myImage);
        myName = findViewById(R.id.myName);
        myEmail = findViewById(R.id.myEmail);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        if (user!=null)
        {
            Glide.with(this).load(user.getPhotoUrl()).into(myImage);
            myName.setText(user.getDisplayName());
            myEmail.setText(user.getEmail());


        }

    }

    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}