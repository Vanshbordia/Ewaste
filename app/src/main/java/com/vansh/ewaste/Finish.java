package com.vansh.ewaste;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Finish extends AppCompatActivity {
Button btn;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        constraintLayout = findViewById(R.id.finishroot);

        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        animationDrawable.setEnterFadeDuration(3000);

        animationDrawable.setExitFadeDuration(4500);

        animationDrawable.start();
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
