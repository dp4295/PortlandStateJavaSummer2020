package edu.pdx.cs410J.deep;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;

public class TextDumper implements PhoneBillDumper {

    String path;

    /**
     * Constructor
     * @param path File path
     */
     public TextDumper(String path) {
         this.path = path;
     }



    /**
          * @param bill PhoneBill object. It contain PhoneCall details
          * @throws IOException
          */
         @Override
         public void dump(AbstractPhoneBill bill)throws IOException {
             try {
                 FileWriter filewriter = new FileWriter(path);
                 BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
                 bufferedwriter.write("");

                 for (int i = 0; i < bill.getPhoneCalls().size(); i++) {

                    // PhoneCall child = (PhoneCall)phonebill
                     bufferedwriter.append(bill.getCustomer()+","+ ((PhoneBill)bill).getPhoneCalls().get(i).getPhoneCallToWrite());
                     if(i<bill.getPhoneCalls().size()-1) {
                         bufferedwriter.newLine();
                     }

                 }
                 System.out.println("Write out into file successfully");
                 bufferedwriter.close();
             } catch (IOException e) {
                 System.out.println("Can not write out int file");
                 System.exit(1);
             }

         }


}


