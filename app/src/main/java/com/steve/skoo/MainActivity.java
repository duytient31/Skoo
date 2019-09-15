package com.steve.skoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Button signout = findViewById(R.id.SignOut);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                updateUI(mAuth.getCurrentUser());
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        //TextView greet = findViewById(R.id.greetings);
        //greet.setText(currentUser.getUid());

    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, FirstLoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if (currentUser.isEmailVerified()) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            finish();
        }

    }


}
