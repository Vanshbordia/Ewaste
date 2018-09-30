package com.vansh.ewaste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sec extends AppCompatActivity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        btn = findViewById(R.id.button2);
        Intent intent = getIntent();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent5 = new Intent(com.vansh.ewaste.sec.this, com.vansh.ewaste.ActivityLogin   .class);
                startActivity(intent5);
            }
        });
    }
}
