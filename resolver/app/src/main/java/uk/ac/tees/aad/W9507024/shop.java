package uk.ac.tees.aad.W9507024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        findViewById(R.id.buy1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.stoptopps.com/"));
                startActivity(httpIntent);
            }
        });
        findViewById(R.id.buy2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.drinkstuff.com/products/drink-safety.asp"));
                startActivity(httpIntent);
            }
        });
        findViewById(R.id.buy3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://www.amazon.co.uk/drink-spiking-prevention/s?k=drink+spiking+prevention"));
                startActivity(httpIntent);
            }
        });
    }
}