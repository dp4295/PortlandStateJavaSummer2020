package edu.pdx.cs410j.deep.phonebill;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadMeActivity extends AppCompatActivity {



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readme);

        TextView textview = findViewById(R.id.textView);
        textview.setText("Phone Bill Application\n" + "\n@author: Deep Patel\n" + "\n@features: Create a Phone bill, Entering phone call, pretty printing phone calls, searching phone call for username\n" + "\n 1. ADD PHONECALL: Use to add phone calls to phone bill\n" + "\n 2. SEARCH PHONECALL: Use to search call between two dates. It requires customer name as well \n" + "\n 3. SEARCH CUSTOMER: Use to search customer by name \n " + "\n 4. PRETTY PRINT: Use to print all customers. This function is work in progress \n");

    }

}
