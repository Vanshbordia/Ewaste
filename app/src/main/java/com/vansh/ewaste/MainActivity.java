package com.vansh.ewaste;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{
    Button SubmitButton ;

    EditText NameEditText, PhoneNumberEditText, AddressEditText;

    public static final String Firebase_Server_URL = "https://ewaste-a89f2.firebaseio.com/ ";

    // Declaring String variables to store name & phone number get from EditText.
    String NameHolder, NumberHolder, AddressHolder, OptionHolder, OptionHolder1;

    Firebase firebase;

    DatabaseReference databaseReference;
    View view;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "ewaste-a89f2";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContex(MainActivity.this);

        firebase = new Firebase();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        SubmitButton = findViewById(R.id.finish);

        NameEditText = findViewById(R.id.name);

        PhoneNumberEditText = findViewById(R.id.phonenumber);

        AddressEditText = findViewById(R.id.address);

        PhoneNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    if (PhoneNumberEditText.getText().toString().length() <=10 ) {
                        PhoneNumberEditText.setError("Please enter your number correctly");
                    } else {
                        // your code here
                        PhoneNumberEditText.setError(null);
                    }
                }

            }
        });



        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StudentDetails studentDetails = new StudentDetails();
                Selection selection = new Selection();

                GetDataFromEditText();
                Intent intent = getIntent();
                String TypeHolder = intent.getStringExtra(Intent.EXTRA_TEXT);
                Intent intent1 = getIntent();
                String TypeHolder1 = intent.getStringExtra(Intent.EXTRA_TEXT);

                // Adding name into class function object.
                studentDetails.setName(NameHolder);

                // Adding phone number into class function object.
                studentDetails.setPhoneNumber(NumberHolder);

                studentDetails.setAddress(AddressHolder);
                studentDetails.setOption(OptionHolder);
                studentDetails.setOption(OptionHolder1);

                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(StudentRecordIDFromServer).setValue(studentDetails);


                // Showing Toast message after successfully data submit.
                Toast.makeText(MainActivity.this,"Successfully sent request", Toast.LENGTH_LONG).show();
                Intent F = new Intent(MainActivity.this, Finish.class);
                startActivity(F);

            }
        });


    }



    public void GetDataFromEditText() {
        Intent intent = getIntent();
        String TypeHolder = intent.getStringExtra(Intent.EXTRA_TEXT);
        Intent intent1 = getIntent();
        String TypeHolder1 = intent1.getStringExtra(Intent.EXTRA_TEXT);
        NameHolder = NameEditText.getText().toString().trim();

        NumberHolder = PhoneNumberEditText.getText().toString().trim();

        AddressHolder = AddressEditText.getText().toString().trim();
        OptionHolder = TypeHolder;
        OptionHolder1= TypeHolder1;





    }


}