package com.samset.recycleranimation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.samset.recycleranimation.R;


public class SecondActivity extends AppCompatActivity {

    public final static String ID = "ID";
    String countryName;
    private  TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        countryName=getIntent().getStringExtra(ID);
        mName = (TextView) findViewById(R.id.DETAILS_name);
        mName.setText(countryName);

    }
}
