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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class SyncAccountActivity extends AppCompatActivity {

    private Button uploadMatchesBtn;
    private Button downloadMatchesBtn;
    private Button deleteOnlineBtn;
    private Button confirmDeleteBtn;
    private Button cancelDeleteBtn;

    private StorageReference mStorageRef;
    FirebaseAuth firebaseAuth;

    private void deleteMatch(String matchTitle) {
        if(matchTitle != null && !(matchTitle.equals(""))) {
            String userID = firebaseAuth.getCurrentUser().getUid();
            StorageReference matchListRef = mStorageRef.child(userID).child(matchTitle);
            matchListRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // File deleted successfully
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Uh-oh, an error occurred!
                }
            });
        }
    }

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

    private String readFile(String fileName) {
        FileInputStream fileIS = null;
        String result = "";
        try {
            fileIS = openFileInput("MatchTitleList");
            InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                sb.append(text);
            }
            result = sb.toString();
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
            result = "";
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
        return result;
    }
    private void combine(String localList, String firebaseList) {
        String combinedList = localList + firebaseList;
        System.out.println("Combined List: " + combinedList);

        //tokens converted to arraylist
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(combinedList.split(",")));
        Set<String> tokensSet = new LinkedHashSet<>(tokens);
        StringBuilder sb = new StringBuilder();
        for (String s : tokensSet) {
            System.out.println(s);
            sb.append(s + ",");
        }
        System.out.println("The toString: " + sb.toString());
        //rewrite file
        FileOutputStream fileOS = null;
        try {
            fileOS = openFileOutput("MatchTitleList", MODE_PRIVATE);
            fileOS.write(sb.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOS != null) {
                try {
                    fileOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void mergeMatchTitleList() {
        //save the string of local MatchTitleList
        final String localListStr = readFile("MatchTitleList");

        //download MatchTitleList from the cloud and override local
        String userID = firebaseAuth.getCurrentUser().getUid();
        StorageReference matchListRef = mStorageRef.child(userID).child("MatchTitleList");
        //load the match list file
        File localFile = new File(getFilesDir(), "MatchTitleList");

        matchListRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                String firebaseListStr = readFile("MatchTitleList");
                combine(localListStr, firebaseListStr);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //failure will happen
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
        deleteOnlineBtn = (Button) findViewById(R.id.deleteOnlineButton);
        confirmDeleteBtn = (Button) findViewById(R.id.confirmDeleteCloud);
        cancelDeleteBtn = (Button) findViewById(R.id.cancelDeleteCloud);


        //Upload Matches On Click
        uploadMatchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteBtn.setVisibility(View.INVISIBLE);
                cancelDeleteBtn.setVisibility(View.INVISIBLE);
                mergeMatchTitleList();
                File filePath = getFilesDir();
                File[] fileList = filePath.listFiles();
                int i = 1;

                String userID = firebaseAuth.getCurrentUser().getUid();
                Toast.makeText(SyncAccountActivity.this, "Please wait Uploading Matches...", Toast.LENGTH_LONG).show();
                for (File f : fileList) {
                    Uri fileUri = Uri.fromFile(f);
                    StorageReference matchRef = mStorageRef.child(userID).child(f.getName());
                    UploadTask uploadTask = matchRef.putFile(fileUri);
                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    });
                }
                Toast.makeText(SyncAccountActivity.this, "Upload Finished", Toast.LENGTH_LONG).show();

            }
        });

        //download on click listener
        downloadMatchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteBtn.setVisibility(View.INVISIBLE);
                cancelDeleteBtn.setVisibility(View.INVISIBLE);
                mergeMatchTitleList();
                String userID = firebaseAuth.getCurrentUser().getUid();
                StorageReference matchListRef = mStorageRef.child(userID).child("MatchTitleList");
                //load the match list file
                final File localFile = new File(getFilesDir(), "MatchTitleList");

                matchListRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(SyncAccountActivity.this, "Please wait downloading matches...", Toast.LENGTH_LONG).show();
                        String outputStr = "";
                        outputStr = readFile("MatchTitleList");
                        String[] tokens = outputStr.split(",");
                        for (String s: tokens) {
                            downloadMatch(s);
                        }
                        Toast.makeText(SyncAccountActivity.this, "Finished Downloading Matches", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(SyncAccountActivity.this, "Error Downloading Matches", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        //deletebutton
        deleteOnlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteBtn.setVisibility(View.VISIBLE);
                cancelDeleteBtn.setVisibility(View.VISIBLE);
            }
        });

        //confirm delete button
        confirmDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = firebaseAuth.getCurrentUser().getUid();
                StorageReference matchListRef = mStorageRef.child(userID).child("MatchTitleList");
                final File localFile = new File(getFilesDir(), "MatchTitleListCloud");

                matchListRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(SyncAccountActivity.this, "Please wait clearing online account...", Toast.LENGTH_LONG).show();
                        String outputStr = "";
                        outputStr = readFile("MatchTitleListCloud");
                        String[] tokens = outputStr.split(",");
                        for (String s: tokens) {
                            deleteMatch(s);
                        }
                        deleteMatch("MatchTitleList");
                        localFile.delete();
                        Toast.makeText(SyncAccountActivity.this, "Finished Clearing Account", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(SyncAccountActivity.this, "Error Clearing Account", Toast.LENGTH_LONG).show();
                    }
                });
                confirmDeleteBtn.setVisibility(View.INVISIBLE);
                cancelDeleteBtn.setVisibility(View.INVISIBLE);
            }
        });

        //cancel button
        cancelDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteBtn.setVisibility(View.INVISIBLE);
                cancelDeleteBtn.setVisibility(View.INVISIBLE);
            }
        });
    }
}