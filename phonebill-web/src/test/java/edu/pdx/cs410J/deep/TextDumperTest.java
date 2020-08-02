package edu.pdx.cs410J.deep;

import com.google.inject.internal.cglib.proxy.$UndeclaredThrowableException;
import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TextDumperTest {

    @Test
    public void TestDumper() throws IOException, ParseException {

        PhoneBill bill = new PhoneBill("Deep");
        String caller = "123-123-1221";
        String callee = "222-123-1222";
        String start = "07/2/2020 10:23 am";
        String end = "07/2/2020 10:30 am";

//        Date sd = null;
//        try {
//            sd = Utility.convertDateFromString();
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Date ed = null;
//        try {
//            ed = Utility.convertDateFromString("07/2/2020 10:30 am");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        PhoneCall phoneCall = new PhoneCall(caller, callee, start,end);
        bill.addPhoneCall(phoneCall);


        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter((stringWriter));
        TextDumper textDumper = new TextDumper(pw);
       // textDumper.dump(bill);
        try {
            textDumper.dump(bill);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = checkCustomerInFile();
        assertThat(result, containsString("Deep"));
    }

    protected static String checkCustomerInFile() throws IOException {

        File file = new File("deep/deep.txt");
        String st;
        BufferedReader br;

        if(file.exists() == true){

            br = new BufferedReader(new FileReader(file));
            while((st = br.readLine()) != null){

                String args[] = st.split(",");
                if(args[0] != null) {
                    br.close();
                    return args[0];
                }
            }

        }

        return "Failed TextDump";
    }
}
