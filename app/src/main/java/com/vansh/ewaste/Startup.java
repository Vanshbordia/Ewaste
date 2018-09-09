package com.vansh.ewaste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Startup extends AppCompatActivity {
Button login;
Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        login = findViewById(R.id.Login);
        signup = findViewById(R.id.Signup);
        Intent intent = getIntent();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent8 = new Intent(com.vansh.ewaste.Startup.this, com.vansh.ewaste.ActivityLogin.class);
                startActivity(intent8);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent8 = new Intent(com.vansh.ewaste.Startup.this, com.vansh.ewaste.ActivitySignup.class);
                startActivity(intent8);
            }
        });
    }
}
