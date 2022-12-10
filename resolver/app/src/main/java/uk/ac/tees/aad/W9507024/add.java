package uk.ac.tees.aad.W9507024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class add extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<Friend> friends;
    ListView listview ;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mAuth  = FirebaseAuth.getInstance();
        friends = new ArrayList<>();
        listview = findViewById(R.id.list_friends);
        adapter = new ArrayAdapter<Friend>(this,android.R.layout.simple_list_item_1,friends);

        DatabaseReference myRef = database.getReference("friends");

        EditText name = findViewById(R.id.fri_name);
        EditText phone = findViewById(R.id.fri_mob);
        Button add = findViewById(R.id.add_fri);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef
                        .child(mAuth.getCurrentUser().getUid())
                        .push()
                        .setValue(new Friend(name.getText().toString(),phone.getText().toString()))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        myRef.
                child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        friends.clear();
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            friends.add(postSnapshot.getValue(Friend.class));
                        }
                       adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        listview.setAdapter( adapter);

    }
}