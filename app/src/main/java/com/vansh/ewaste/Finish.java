package com.vansh.ewaste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Finish extends AppCompatActivity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        btn = findViewById(R.id.button3);
        Intent intent = getIntent();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent5 = new Intent(com.vansh.ewaste.Finish.this, com.vansh.ewaste.Selection.class);
                startActivity(intent5);
            }
            });
}
}
