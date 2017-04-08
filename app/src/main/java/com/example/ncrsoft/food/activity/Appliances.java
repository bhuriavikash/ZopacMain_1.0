package com.example.ncrsoft.food.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ncrsoft.food.R;

public class Appliances extends AppCompatActivity {

    String product_id;
    Bundle bundle;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances);

        textView = (TextView) findViewById(R.id.text);
        bundle = getIntent().getExtras();
        product_id = bundle.getString("id");

        textView.setText(product_id);
    }
}
