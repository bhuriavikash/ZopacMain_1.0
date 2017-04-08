package com.example.ncrsoft.food.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ncrsoft.food.R;

public class Men extends AppCompatActivity {

    String product_id;
    Bundle bundle;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);

        textView = (TextView) findViewById(R.id.textMen);
        /*Intent intent = getIntent();

        product_id = intent.getStringExtra("id");*/

        bundle = getIntent().getExtras();
        product_id = bundle.getString("id");
        textView.setText("Received text:" + product_id);
        /*textView.setText("Received text:" + getIntent().getStringExtra("id"));*/
    }
}
