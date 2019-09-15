package com.steve.skoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginAuthentication extends AppCompatActivity {

    EditText school_email, school_password;
    Button send, confirm;
    String email, password;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_authentication);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("kr");

        send = findViewById(R.id.send_email);
        school_email = findViewById(R.id.school_email);
        school_password = findViewById(R.id.password);
        confirm = findViewById(R.id.verify_email);

        confirm.setVisibility(View.GONE);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = school_email.getText().toString();
                password = school_password.getText().toString();
                if (isValid(email)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                currentUser = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(),
                                        "이메일을 확인하여 인증하세요", Toast.LENGTH_LONG).show();

                                currentUser.sendEmailVerification();

                                send.setVisibility(View.GONE);
                                confirm.setVisibility(View.VISIBLE);
                                school_email.setVisibility(View.GONE);
                                school_password.setVisibility(View.GONE);
                            }
                            else {
                                mAuth.signInWithEmailAndPassword(email, password);
                                currentUser = mAuth.getCurrentUser();
                                Intent intent = new Intent(LoginAuthentication.this, MainScreen.class);
                                startActivity(intent);
                                finish();

                            }

                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "학교 이메일을 사용하세요", Toast.LENGTH_SHORT).show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAuthentication.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValid(String mail) {
        String[] school_emails = {"hanyang", "korea", "skku"};

        for (String i: school_emails) {
            if (mail.contains(i))
                return true;
        }
        return false;
    }

}
