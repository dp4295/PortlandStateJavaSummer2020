package edu.pdx.cs410j.deep.phonebill;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SearchPhoneCallActivity extends AppCompatActivity {

    String customer_name;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_searchcustomerphonecalls);

        Intent intent = getIntent();
        customer_name = intent.getStringExtra("customer name");

      //  ListView listView = findViewById(R.id.)



    }



}
