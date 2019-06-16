package com.slashlearn.tennisstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SyncAccountActivity extends AppCompatActivity {

    private Button uploadMatchesBtn;
    private Button downloadMatchesBtn;

    private StorageReference mStorageRef;
    FirebaseAuth firebaseAuth;

    private void downloadMatch(String matchTitle) {
        String userID = firebaseAuth.getCurrentUser().getUid();
        StorageReference matchListRef = mStorageRef.child(userID).child(matchTitle);
        //load the match list file
        final File localFile = new File(getFilesDir(), matchTitle);

        matchListRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                System.out.println("download Stage 2");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_account);

        //Firebase
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        uploadMatchesBtn = (Button) findViewById(R.id.uploadMatchesButton);
        downloadMatchesBtn = (Button) findViewById(R.id.downloadMatchesButton);

        //Upload Matches On Click
        uploadMatchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File filePath = getFilesDir();
                File[] fileList = filePath.listFiles();
                int i = 1;

                String userID = firebaseAuth.getCurrentUser().getUid();

                for (File f : fileList) {
                    Uri fileUri = Uri.fromFile(f);
                    StorageReference matchRef = mStorageRef.child(userID).child(f.getName());
                    UploadTask uploadTask = matchRef.putFile(fileUri);
                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(SyncAccountActivity.this, "Error: An Upload Failed", Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...
                        }
                    });
                }
            }
        });

        //download on click listener
        downloadMatchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = firebaseAuth.getCurrentUser().getUid();
                StorageReference matchListRef = mStorageRef.child(userID).child("MatchTitleList");
                //load the match list file
                    final File localFile = new File(getFilesDir(), "MatchTitleList");

                    matchListRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            FileInputStream fileIS = null;
                            String outputStr = "";
                            try {
                                fileIS = openFileInput("MatchTitleList");
                                InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                StringBuilder sb = new StringBuilder();
                                String text;

                                while ((text = bufferedReader.readLine()) != null) {
                                    sb.append(text);
                                }
                                outputStr = sb.toString();
                            } catch (FileNotFoundException e ) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (fileIS != null) {
                                    try {
                                        fileIS.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            String[] tokens = outputStr.split(",");
                            for (String s: tokens) {
                                downloadMatch(s);
                            }
                            Toast.makeText(SyncAccountActivity.this, "All Matches Downloaded", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(SyncAccountActivity.this, "Error Downloading Matches", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        });
    }
}
