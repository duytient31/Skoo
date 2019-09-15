package com.steve.skoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class FirstLoginActivity extends AppCompatActivity {

    TextView textView, school_terms;
    ArrayAdapter<String> adapter;
    ListView schoolQueries;
    FirebaseAuth mAuth;
    Button confirm_school;
    SearchView school_search;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        mAuth = FirebaseAuth.getInstance();
        mAuth.useAppLanguage();

        textView = findViewById(R.id.textView);
        school_search = findViewById(R.id.school_search);
        confirm_school = findViewById(R.id.confirm_school);
        schoolQueries = findViewById(R.id.schools_query);
        school_terms = findViewById(R.id.school_terms);

        confirm_school.setVisibility(View.GONE);
        schoolQueries.setVisibility(View.INVISIBLE);

        school_search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textView.setVisibility(View.GONE);
                schoolQueries.setVisibility(View.VISIBLE);
            }
        });

        ArrayList<String> arraySchool = new ArrayList<>();
        arraySchool.addAll(Arrays.asList(getResources().getStringArray(R.array.schools)));

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arraySchool
        );


        schoolQueries.setAdapter(adapter);

        schoolQueries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                school_search.setQuery(adapter.getItem(i), true);
                confirm_school.setVisibility(View.VISIBLE);
                schoolQueries.setVisibility(View.GONE);
            }
        });

        school_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        confirm_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (school_search.getQuery() != null) {
                    // School is chosen
                    Intent intent = new Intent(FirstLoginActivity.this, LoginAuthentication.class);
                    intent.putExtra("schoolName", school_search.getQuery());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    
}
