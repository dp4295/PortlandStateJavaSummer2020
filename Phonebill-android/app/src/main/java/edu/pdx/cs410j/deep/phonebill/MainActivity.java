package edu.pdx.cs410j.deep.phonebill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void launchReadMeView(View view){
        Intent intent = new Intent(this, ReadMeActivity.class);
        startActivity(intent);
    }

    public void launchAddPhoneCall(View view){
        Intent intent= new Intent(this, AddPhoneCallActivity.class);
        startActivity(intent);
    }

    public void launchSearchPhoneCall(View view){
        Intent intent = new Intent(this, SearchPhoneCallActivity.class );
        startActivity(intent);
    }

    public void launchAllPhoneCalls(View view){
        Intent intent = new Intent(this, PrittyPrintActivity.class);
        startActivity(intent);
    }

    public void launchSearchCustomer(View view){
        Intent intent = new Intent(this, SearchCustomerPhoneCallsActivity.class);
        startActivity(intent);
    }

}