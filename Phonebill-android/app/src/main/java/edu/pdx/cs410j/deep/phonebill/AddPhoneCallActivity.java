package edu.pdx.cs410j.deep.phonebill;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AddPhoneCallActivity extends AppCompatActivity {


    public static final String PASS   = "pass";
  //  final View viewPos = findViewById(R.id.myCoordinatorLayout);

    /**
     *
     * @param savedInstance
     */
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_phonecall);
    }


    public void addPhoneCall(View view)throws IOException, ParseException{

        String result;
        File dir = getFilesDir();
        EditText customername = findViewById(R.id.customername);
        EditText callernumber = findViewById(R.id.callernumber);
        EditText calleenumber = findViewById(R.id.calleenumber);
        EditText startdatetime = findViewById(R.id.startcalldatetime);
        EditText enddatetime = findViewById(R.id.endcalldatetime);

//        String cname = customername.getText().toString();
//        String can = callernumber.getText().toString();
//        String cel = calleenumber.getText().toString();
//        String sdt = startdatetime.getText().toString();
//        String edt = enddatetime.getText().toString();


        String cname = "deep";
        String can = "123-123-1111";
        String cel = "111-333-3333";
        String sdt = "8/8/2020 12:00 am";
        String edt = "8/8/2020 12:30 am";

        // Validating input
        if(cname.equals("")){
            Snackbar.make(view, "Customer can not be null", 2000).show();
            return;
        }

        result = Utility.checkInvalidPhoneNumber(can);
        if(!(result.equals(PASS))){
            Snackbar.make(view, "Please enter valid caller number e.g 123-122-1234", 2000).show();
            return;
        }

        result = Utility.checkInvalidPhoneNumber(cel);
        if(!(result.equals(PASS))){
            Snackbar.make(view, "Please enter valid callee number e.g 123-123-1222", 2000).show();
            return;
        }

        String [] br = sdt.split(" ");
        String startdate = br[0];
        String starttime = br[1] + " " + br[2];

        String[] ebr = edt.split(" ");
        String enddate = ebr[0];
        String endtime = ebr[1] + " " + ebr[2];


        result = Utility.checkInvalidDateAndTimeFormat(br[0], br[1], br[2]);
        if(!result.equals(PASS)){
            Snackbar.make(view, "Please enter valid start date and time e.g MM/dd/yyyy 12:00 am", 2000).show();
            return;
        }

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
        // End of the validation input



        PhoneBill phoneBill = new PhoneBill(cname);
        List<PhoneCall> phoneCallList = new ArrayList<>();

        PhoneCall phoneCall = new PhoneCall(can,cel, sdt, edt);
        phoneCallList.add(phoneCall);
        phoneBill.setPhoneCall(phoneCallList);

        //phoneBill.addPhoneCall(phoneCall);



        // Create a file to internal storage
        File file = new File(getFilesDir(), cname);
        String string;
        BufferedReader bufferedReader;

        try{
            if((file.exists() == false)){
                file.createNewFile();
                Log.e("AddPhoneCall", "File created");
                TextDumper textDumper = new TextDumper(file);
                textDumper.saveDataToFile(phoneBill);
            }else{
                bufferedReader= new BufferedReader(new FileReader(file));

                while((string = bufferedReader.readLine()) != null) {
                    String[] args = string.split(",");
                    if (args[0] == null || args[0].trim().equals("")) {
                        TextDumper textDumper = new TextDumper(file);
                        textDumper.saveDataToFile(phoneBill);
                        return;
                    }

                    if (!phoneBill.getCustomer().equalsIgnoreCase(args[0])) {
                        bufferedReader.close();
                        popUp(view, "Customer name not match with the file");
                        return;
                    }


                    phoneCall = new PhoneCall(can, cel, sdt, edt);
            //        phoneCall = new PhoneCall(args[1],args[2],args[3],args[4]);
                    phoneBill.addPhoneCall(phoneCall);
                }

                bufferedReader.close();
                TextDumper textDumper = new TextDumper(file);
                textDumper.saveDataToFile(phoneBill);

            }

            Snackbar snackbar = Snackbar.make(view, "Customer " + cname + " Added successfully ", 3000);
            snackbar.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void popUp(View view, String mes){
        Snackbar snackbar = Snackbar.make(view, mes, 3000);
        snackbar.show();
    }




}
