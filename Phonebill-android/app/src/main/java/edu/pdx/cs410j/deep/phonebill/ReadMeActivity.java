package edu.pdx.cs410j.deep.phonebill;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadMeActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        TextView textview = findViewById(R.id.);
//        textview.setText("ReadMe");
//    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readme);
//
        TextView textview = findViewById(R.id.textView);
        textview.setText("Phone Bill Application\n" + "\n@author: Deep Patel\n" + "\n@features: Create a Phone bill, Entering phone call, pretty printing phone calls, searching phone call for username\n");
    }

}
