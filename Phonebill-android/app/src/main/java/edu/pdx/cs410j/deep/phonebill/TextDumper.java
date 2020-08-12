package edu.pdx.cs410j.deep.phonebill;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TextDumper{

//    private PrintWriter pw;
//    private final String DIR = "deep";
//    private final String  FILENAME = "deep.txt";

    private File file;

    /**
     * Constructor
     * @param file
     */
    public TextDumper(File file) {
        this.file = file;
    }


    /**
     * @param bill
     * @throws IOException
     */
    public void saveDataToFile(PhoneBill bill) throws IOException {
        try{
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);


            bufferedWriter.write("");
            List<PhoneCall> phoneCallList = bill.getPhoneCalls();
            Collections.sort(phoneCallList);


            for(int i =0;  i< phoneCallList.size(); i++)
            {
                bufferedWriter.append(bill.getCustomer()+","+ ((PhoneBill)bill).getPhoneCalls().get(i).getPhoneCallToWrite());
                if(i<bill.getPhoneCalls().size()-1) {
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
        }catch(IOException e){
            Log.e("error while writing a file", e.getMessage());
        }

    }
}
