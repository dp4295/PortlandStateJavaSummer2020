package edu.pdx.cs410j.deep.phonebill;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class PhoneCallListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_result);

        ListView listView  = findViewById(R.id.resultsview);
        ArrayAdapter<PhoneCall> adapter = new PhoneCallAdapter(this);
        listView.setAdapter(adapter);


        try{
            File file = new File(getFilesDir(), "tempfile.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String st;
            List<PhoneCall> result  = new ArrayList<>();

            while((st = bufferedReader.readLine()) != null){
                String [] args = st.split(",");
                String callernumber = args[1];
                String calleenumber = args[2];
                String startdatetime = args[3];
                String enddatetime = args[4];


                PhoneCall phoneCall = new PhoneCall(callernumber, calleenumber, startdatetime, enddatetime);
                adapter.add(phoneCall);
            }

            bufferedReader.close();


        } catch (ParseException | IOException e) {
            e.printStackTrace();


            listView.setAdapter(adapter);
        }

    }

    class PhoneCallAdapter extends ArrayAdapter<PhoneCall> {


       public PhoneCallAdapter(@NonNull Context context) { super(context, R.layout.activity_phoenecallview);}


       @NonNull
       @Override
       public View getView(int position, @Nullable View phonecallview, @NonNull ViewGroup parent){

           if(phonecallview == null) {
               phonecallview = getLayoutInflater().inflate(R.layout.activity_phoenecallview, parent, false);
           }

           PhoneCall phoneCall = getItem(position);

           TextView textView = phonecallview.findViewById(R.id.phonecall_info);
           assert phoneCall != null;

//           textView.setText(phoneCall.prettyPrintCaller());
           textView.setText(phoneCall.printPhoneCall());

           return phonecallview;
       }



    }






}
