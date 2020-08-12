package edu.pdx.cs410j.deep.phonebill;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class PrittyPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prittyprint);


        TextView textview = findViewById(R.id.textview_in_prettyprint);
        textview.setText("\n\n Work in progress... Feature will be available soon\n");
    }


    public void prettyprint(View view){



//        PhoneBill book = null;
//        try{
//
//            File file = null;
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            String st;
//            List<PhoneCall> result = new ArrayList<>();
//
//            while ((st= bufferedReader.readLine()) != null){
//                String[] args = st.split(",");
//                if(args[0]== null ||  args[0].trim().equals(""))
//                    continue;
//
//
//                file = new File(getFilesDir(),args[0]);
//                book = new PhoneBill(args[0]);
//
//                    String caller = args[1];
//                    String callee = args[2];
//                    String sdt = args[3];
//                    String edt = args[4];
//
//                    PhoneCall phoneCall = new PhoneCall(caller, callee, sdt, edt);
//                    book.addPhoneCall(phoneCall);
//
//            }    if(book.getPhoneCalls().size() == 0){
//                popupMenu(view, "Customer not found");
//                return;
//            }
//            bufferedReader.close();
//
//
//            File filebook = new File(getFilesDir(), "tempfile.txt");
//            TextDumper textDumper = new TextDumper(filebook);
//            textDumper.saveDataToFile(book);
//
//            Intent intent = new Intent(this, PhoneCallListActivity.class);
//            startActivity(intent);
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }

    }




    public void popupMenu(View view, String message){
        Snackbar snackbar = Snackbar.make(view, message, 3000);
        snackbar.show();
    }

}
