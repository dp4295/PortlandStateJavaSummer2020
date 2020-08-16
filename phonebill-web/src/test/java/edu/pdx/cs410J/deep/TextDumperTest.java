package edu.pdx.cs410J.deep;

import com.google.inject.internal.cglib.proxy.$UndeclaredThrowableException;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TextDumperTest {

    @Test
    public void TestDumper() {
        String writer = "test";
        TextDumper dumper =  new TextDumper(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020 12:30 am", "01/10/2020 12:45 am");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Test
    public void TestDumperFailed() {
        String writer = "test";
        TextDumper dumper =  new TextDumper(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020 12:30 am", "01/10/2020 12:45 am");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void TestDumperFailed1() {
        String writer = "test";
        TextDumper dumper =  new TextDumper(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020 12:30 am", "01/10/2020 12:45 am");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
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
