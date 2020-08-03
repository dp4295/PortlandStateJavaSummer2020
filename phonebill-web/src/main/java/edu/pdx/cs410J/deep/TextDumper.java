package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TextDumper implements PhoneBillDumper {

   // private final SimpleDateFormat  dateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm aa", Locale.ENGLISH);
   private PrintWriter pw;
    private final String DIR = "deep";
    private final String  FILENAME = "deep.txt";

    public TextDumper(PrintWriter pw) {
        this.pw = pw;
    }


//    /**
//     * Constructor <code>TextDumper</code>
//     */
//    TextDumper(PrintWriter pw){
//        this.pw = pw;
//    }

    /**
     * @param bill
     * @throws IOException
     */
    @Override
//    public void dump(AbstractPhoneBill bill) throws IOException {
//
//        // Create a Directory
//        File directory = new File(DIR);
//        if(!directory.exists()){
//            directory.mkdir();
//        }
//
//        try{
//            FileWriter writer = new FileWriter(DIR + "/" + FILENAME, true);
//
//            BufferedWriter bw = new BufferedWriter(writer);
//        //   bw.write("");
//
//            for(int i =0;  i< bill.getPhoneCalls().size(); i++)
//            {
//                bw.append(bill.getCustomer()+","+ ((PhoneBill)bill).getPhoneCalls().get(i).getPhoneCallToWrite());
//                if(i<bill.getPhoneCalls().size()-1) {
//                    bw.newLine();
//                }
//
//
//                bw.close();
//            }
//        }catch(IOException e){
//            System.out.println("error while writing a file");
//            System.exit(1);
//        }
//
//    }



    public void dump(AbstractPhoneBill bill) throws IOException {

        // Create a Directory


        try{
            File directory = new File(DIR+ "/" + FILENAME);
            if(!directory.exists()){
                directory.mkdir();
            }

            FileWriter writer = new FileWriter(DIR + "/" + FILENAME, true);

            BufferedWriter bw = new BufferedWriter(writer);
              for(int i =0;  i< bill.getPhoneCalls().size(); i++)
              {

                bw.append(bill.getCustomer()+","+ ((PhoneBill)bill).getPhoneCalls().get(i).getPhoneCallToWrite());
                if(i<bill.getPhoneCalls().size()) {
                    bw.newLine();
                }

                bw.close();
            }

        }catch(IOException e){
            System.out.println("error while writing a file");
            System.exit(1);
        }

    }


}
