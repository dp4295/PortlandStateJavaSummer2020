package edu.pdx.cs410j.deep.phonebill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class SearchPhoneCallActivity extends AppCompatActivity {


    public static  final String PASS = "pass";


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_searchphonecalls);
    }

    public void searchPhoneCalls(View view) throws ParseException {

        EditText customername = findViewById(R.id.editText_customer_name);
        EditText startdatetime = findViewById(R.id.editText_startdate);
        EditText enddatetime = findViewById(R.id.editText_enddate);


        String cname = customername.getText().toString();
        String sdt = startdatetime.getText().toString();
//        String sdt = "8/2/2020 12:40 am";
//        String edt = "8/12/2020 12:00 am";
        String edt = enddatetime.getText().toString();

        String result = null;
        String message = null;

        if(cname.equals("")){
            message = "Customer name can not be empty";
            popupMenu(view,message);
        }

        String [] br = sdt.split(" ");
        result = Utility.checkInvalidDateAndTimeFormat(br[0], br[1], br[2]);
        if(!result.equals(PASS)) {
            Snackbar.make(view, "Please enter valid start date and time e.g MM/dd/yyyy 12:00 am", 2000).show();
            return;
        }

        String[] ebr = edt.split(" ");
        result = Utility.checkInvalidDateAndTimeFormat(ebr[0], ebr[1], ebr[2]);
        if(!result.equals(PASS)){
            Snackbar.make(view, "Please enter valid end date and time e.g MM/dd/yyyy 12:00 am", 2000).show();
            return;
        }

        result = Utility.checkForEndTimeBeForStartTime(br[0], br[1], br[2], ebr[0], ebr[1], ebr[2]);
        if(!(result.equals(PASS))){
            Snackbar.make(view, "Phonecall start time can't before end time", 2000).show();
            return;
        }


        PhoneBill book = new PhoneBill(cname);
        File file = new File(getFilesDir(),cname);

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String st;

            while ((st= bufferedReader.readLine()) != null){
                String[] args = st.split(",");
                if(args[0]== null ||  args[0].trim().equals(""))
                    continue;
                if(args.length != 5)
                {
                    bufferedReader.close();
                    popupMenu(view, "File is Malformatted");
                    return;
                }

                String caller = args[1];
                String callee = args[2];
                String startdt = args[3];
                String enddt = args[4];

                // Converting string to date for comparision
                // Parsed from the record

                String[]  par = startdt.split(" ");
                Date cdFromstartdt = Utility.convertDateFromString(par[0] + " 00:00 am");
                par = enddt.split(" ");
                Date cdFromenddt = Utility.convertDateFromString(par[0]+ " 00:00 am");

                // User input
                String[]  parse = sdt.split(" ");
                Date cdsdt = Utility.convertDateFromString(parse[0] + " 00:00 am");
                parse = edt.split(" ");
                Date cdedt = Utility.convertDateFromString(parse[0]+ " 00:00 am");


                if( (args[0].equalsIgnoreCase(cname) && cdsdt.compareTo(cdFromstartdt) >= 0 || cdsdt.compareTo(cdFromenddt)<=0)  || (args[0].equalsIgnoreCase((cname)) && cdedt.compareTo(cdFromstartdt)>=0 || cdedt.compareTo(cdFromenddt) <= 0 )){

                    PhoneCall phoneCall = new PhoneCall(caller, callee, startdt, enddt);
                    book.addPhoneCall(phoneCall);
                }

            }    if(book.getPhoneCalls().size() == 0){
                popupMenu(view, "No phoneCall found");
                return;
            }
            bufferedReader.close();


            File filebook = new File(getFilesDir(), "tempfile.txt");
            TextDumper textDumper = new TextDumper(filebook);
            textDumper.saveDataToFile(book);

            Intent intent = new Intent(this, PhoneCallListActivity.class);
            startActivity(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void popupMenu(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, 3000);
        snackbar.show();
    }




}
