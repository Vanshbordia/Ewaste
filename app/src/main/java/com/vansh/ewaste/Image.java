package com.vansh.ewaste;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Image extends AppCompatActivity {
    private ImageView imageView;

    private Uri filePath;
    Button btnChoose;
    Button btnUpload;
    Button button1;
    private final int PICK_IMAGE_REQUEST = 71;
    String downloadUrl;
    String OptionHolder2;
    String ImageHolder;


    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        //Initialize Views
        Button btnChoose = findViewById(R.id.btnChoose);
        Button btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imgView);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
                Intent intent1 = getIntent();
                String OptionHolder2 = intent1.getStringExtra(Intent.EXTRA_TEXT);
            }

        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

                Intent intent1 = getIntent();
                String OptionHolder2 = intent1.getStringExtra(Intent.EXTRA_TEXT);

            }
        });
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Internal Class for Choose Image
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);



    }

    //Internal Class for Upload Image
    private void uploadImage() {



        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Uri url = taskSnapshot.getDownloadUrl();
                            /*
                            Intent intent1 = getIntent();
                            String OptionHolder2 = intent1.getStringExtra(Intent.EXTRA_TEXT);
                            intent1.putExtra(Intent.EXTRA_TEXT, OptionHolder2);
                            intent1 = new Intent(Image.this, MainActivity.class);

                            startActivity(intent1);
                            ///startActivity(intent);
                            */
                            Intent intent1 = getIntent();
                            String OptionHolder2 = intent1.getStringExtra(Intent.EXTRA_TEXT);

                            Intent intent = new Intent(com.vansh.ewaste.Image.this, com.vansh.ewaste.MainActivity.class);
                            intent.putExtra(Intent.EXTRA_TEXT,OptionHolder2);
                            startActivity(intent1);
                            startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Image.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");


/*
                            Intent intent3 = new Intent(Image.this, MainActivity.class);
                            intent3.putExtra(Intent.EXTRA_TEXT, downloadUrl);
                            startActivity(intent3);
*/
/*
                            Intent intent1 = getIntent();
                           /// String OptionHolder2 = intent1.getStringExtra(Intent.EXTRA_TEXT);
                            intent1.putExtra(Intent.EXTRA_TEXT, OptionHolder2);
                            intent1 = new Intent(Image.this, MainActivity.class);

                            startActivity(intent1);
                            ///startActivity(intent);
*/
                            Toast.makeText(Image.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }
}


