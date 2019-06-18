package com.slashlearn.tennisstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button loginRegBtn;
    private Button loginloginBtn;
    private EditText loginEmail;
    private EditText loginPassword;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        loginEmail = (EditText) findViewById(R.id.loginEmailInput);
        loginPassword = (EditText) findViewById(R.id.loginPasswordInput);
        loginloginBtn = (Button) findViewById(R.id.loginloginButton);
        loginRegBtn = (Button) findViewById(R.id.loginRegButton);

        loginloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmailStr = loginEmail.getText().toString();
                String loginPasswordStr = loginPassword.getText().toString();

                //if both the inputs are not empty then proceed to login
                if(!TextUtils.isEmpty(loginEmailStr) && !TextUtils.isEmpty(loginPasswordStr)) {
                    mAuth.signInWithEmailAndPassword(loginEmailStr, loginPasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent syncIntent = new Intent(LoginActivity.this, SyncAccountActivity.class);
                                startActivity(syncIntent);
                                finish();
                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error" + errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Fill in email and password", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //if the user is already logged In
        if (currentUser != null) {
            //Take them to SyncAccountActivity
            Toast.makeText(LoginActivity.this, "Already Logged In", Toast.LENGTH_LONG).show();
            Intent SyncAccountIntent = new Intent(LoginActivity.this, SyncAccountActivity.class);
            startActivity(SyncAccountIntent);
            finish();
        }
    }
}
