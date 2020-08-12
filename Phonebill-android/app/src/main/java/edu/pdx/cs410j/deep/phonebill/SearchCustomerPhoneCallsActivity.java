package edu.pdx.cs410j.deep.phonebill;

import android.content.Intent;
import android.os.Bundle;
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

public class SearchCustomerPhoneCallsActivity extends AppCompatActivity {

  @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_searchcustomerphonecalls);
  }

  public void searchCustomerPhoneCall(View view){

      EditText editText = findViewById(R.id.editText_customer_name);
      String cname = editText.getText().toString();
      String message = null;

      if(cname.equals("")){
          message = "Customer name can not be empty";
          popupMenu(view,message);
      }

      File file = new File(getFilesDir(),cname);

      if(file.exists() == false){
          popupMenu(view, "Customer name not found in record");
          return;
      }

      PhoneBill book = new PhoneBill(cname);
      try{
          BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
          String st;
          List<PhoneCall> result = new ArrayList<>();

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
              if((args[0].equalsIgnoreCase(cname))){
                  String caller = args[1];
                  String callee = args[2];
                  String sdt = args[3];
                  String edt = args[4];

                  PhoneCall phoneCall = new PhoneCall(caller, callee, sdt, edt);
                  book.addPhoneCall(phoneCall);
              }
          }    if(book.getPhoneCalls().size() == 0){
              popupMenu(view, "Customer not found");
             return;
          }
          bufferedReader.close();


          File filebook = new File(getFilesDir(), "tempfile.txt");
          TextDumper textDumper = new TextDumper(filebook);
          textDumper.saveDataToFile(book);

          Intent intent = new Intent(this, PhoneCallListActivity.class);
          startActivity(intent);
      } catch (IOException | ParseException e) {
          e.printStackTrace();
      }

  }

  public void popupMenu(View view, String message){
      Snackbar snackbar = Snackbar.make(view, message, 3000);
      snackbar.show();
  }


}
