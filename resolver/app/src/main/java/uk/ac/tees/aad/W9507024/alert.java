package uk.ac.tees.aad.W9507024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class alert extends AppCompatActivity {

    FusedLocationProviderClient location ;
    Location loc;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ArrayList<Friend> friends;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("friends");
        mAuth  =FirebaseAuth.getInstance();
        friends = new ArrayList<>();
        location= LocationServices.getFusedLocationProviderClient(this);
        smsManager=SmsManager.getDefault();



        myRef.
                child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        friends.clear();
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            friends.add(postSnapshot.getValue(Friend.class));
                        }

                        checkLocationPermissions();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    void checkLocationPermissions()
    {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);
        }
        location.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location l) {
                if (l != null) {
                    loc = l;
                    sendMessages();
                }
            }
        });
    }

    private void sendMessages() {
        try {
            for (Friend friend : friends) {
                smsManager.sendTextMessage(friend.phonenumber, null, "Emergency !! Iam at " + loc.getLatitude() + " " + loc.getLongitude(), null, null);
            }
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(),"sent success",Toast.LENGTH_LONG).show();


    }
}