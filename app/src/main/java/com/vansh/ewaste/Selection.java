package com.vansh.ewaste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Selection extends AppCompatActivity {

    Button button;
     String  a, b;
    EditText Quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        addListenerOnButton();
        Quantity = findViewById(R.id.Quantity);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    a = "Battery";
                break;
            case R.id.radioButton2:
                if (checked)
                    a = "Phone";
                break;
            case R.id.radioButton3:
                if (checked)
                    a = "Computer";
                break;
            case R.id.radioButton4:
                if (checked)
                    a = "Wire";
                break;
            case R.id.radioButton5:
                if (checked)
                    a = "Peripherals";
                break;
            case R.id.radioButton6:
                if (checked)
                    a = "Screen";
                break;
            case R.id.radioButton7:
                if (checked)
                    a = "Home Appliances";
                break;
            case R.id.radioButton8:
                if (checked)
                    a = "Heavy Equipments";
                break;
            case R.id.radioButton9:
                if (checked)
                    a = "Other";
                break;
        }
        b = Quantity.getText().toString().trim();
    }

    public String getA() {
        return a;
    }

    public  void setOption(String option){

        this.a = option;
    }






    public void addListenerOnButton() {



        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(Selection.this, a, Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT,a);

                Intent intent1 = new Intent(getApplicationContext(), Image.class);
                intent1.putExtra(Intent.EXTRA_TEXT,a);
                startActivity(intent);
                startActivity(intent1);


            }

        });
    }

}
