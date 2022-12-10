package uk.ac.tees.aad.W9507024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registeration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        mAuth = FirebaseAuth.getInstance();

        TextView textMoveToLogin = findViewById(R.id.register_login_text);
        textMoveToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Mover(getApplicationContext(),Login.class));
            }
        });

        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.reg_eamil);
        EditText pass = findViewById(R.id.reg_pass);

        Button btnReg = findViewById(R.id.button_register_main);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth
                        .createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"Registation Successful",Toast.LENGTH_LONG).show();
                                    startActivity(new Mover(getApplicationContext(),Dashboard.class));

                                }else{
                                    Toast.makeText(getApplicationContext(),"Registation Failed",Toast.LENGTH_LONG).show();

                                }

                            }
                        });

            }
        });

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       if(currentUser != null)
       {
           startActivity(new Mover(getApplicationContext(),Dashboard.class));
       }
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}