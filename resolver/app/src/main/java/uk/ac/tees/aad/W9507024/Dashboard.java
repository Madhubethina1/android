package uk.ac.tees.aad.W9507024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();

        Button logout  = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"Logout Successful", Toast.LENGTH_LONG).show();
                startActivity(new Mover(getApplicationContext(),Login.class));
            }
        });
        View help = findViewById(R.id.constraintLayout6);
        View taxi= findViewById(R.id.constraintLayout4);
        View add = findViewById(R.id.constraintLayout);
        View alert = findViewById(R.id.constraintLayout2);
        View news = findViewById(R.id.constraintLayout5);
        View shop = findViewById(R.id.constraintLayout3);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(), help.class));
            }
        });

        taxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(), taxi.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(), add.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(), news.class));
            }
        });

       alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(), alert.class));
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(), shop.class));
            }
        });









    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            startActivity(new Mover(getApplicationContext(),Login.class));
        }
    }

    @Override
    public void onBackPressed() {
       finishAffinity();
    }
}