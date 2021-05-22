package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {
    Place city = new Place("Beijing", 80, 70);
    DatabaseReference dbRef;
    final String CHILD = "myplace123";
    final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText latText= findViewById(R.id.latitude);
        EditText lonText= findViewById(R.id.longtitude);

        String lat = latText.getText().toString();
        String lon = lonText.getText().toString();


        // получаем ссылку на облачную БД

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("coordinates").child("lat").setValue(10);

        db.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("mytag", "count: " + snapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("mytag", "error");
            }
        });


        //changePlace(city);
        //Log.d("mytag", "city changed");
    }

    public void changePlace(Place p) {
        dbRef.child(CHILD).setValue(p);
        dbRef.child(CHILD).push().setValue(new Place("SPb", 11, 22));
        dbRef.child(CHILD).push().setValue(new Place("Moscow", 33, 44));
        dbRef.child("myplace");
        dbRef.child("myplace").child("anotherplace").setValue(p);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Place place = snapshot.getValue(Place.class);

        Log.d("mytag", "place: " + place);
        /*
       for (DataSnapshot s: snapshot.getChildren() ) {
           Log.d("mytag", "key: " + s.getKey());
           Place place = s.getValue(Place.class);
           Log.d("mytag", "place: " + place);
       }
         */

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
