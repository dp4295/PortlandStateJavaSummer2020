package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;
import java.io.BufferedWriter;

import java.io.IOException;

import java.io.*;
import java.util.ArrayList;



public class TextDumper implements PhoneBillDumper<AbstractPhoneBill> {


    private String filename;
    private PhoneBill phoneBill;


    public TextDumper(String filename) {
        this.filename = filename;
    }




    /**
     *
     * @param bill PhoneBill object
     * @throws IOException Throw input output exception
     */
    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {

        this.phoneBill = (PhoneBill) bill;
        BufferedWriter bufferedWriter;
        try {

            OutputStream writetofile = new FileOutputStream(filename);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(writetofile));
            bufferedWriter.write(this.phoneBill.getCustomer());
            bufferedWriter.newLine();

            ArrayList<PhoneCall> phoneCalls = (ArrayList<PhoneCall>) this.phoneBill.getPhoneCalls();
            for (PhoneCall phoneCall : phoneCalls) {

                String callernumber = phoneCall.getCaller();
                bufferedWriter.write(callernumber);
                bufferedWriter.newLine();

                String calleenumber = phoneCall.getCallee();
                bufferedWriter.write(calleenumber);
                bufferedWriter.newLine();

                String start = phoneCall.getStartTimeString();
                bufferedWriter.write(start);
                bufferedWriter.newLine();

                String end = phoneCall.getEndTimeString();
                bufferedWriter.write(end);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception err) {
            throw new IOException("Can't input to file");
        }
    }
}




