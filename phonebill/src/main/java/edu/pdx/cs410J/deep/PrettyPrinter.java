package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class PrettyPrinter implements PhoneBillDumper {

    String path;

    /**
     * @param bill PhoneBill object to pretty print
     * @throws IOException
     */
    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {
        if(path != null){
            try{
                FileWriter filewriter = new FileWriter(path);
                BufferedWriter bufferedwriter = new BufferedWriter(filewriter);

                bufferedwriter.write("");
                bufferedwriter.append("PhoneBill info for " + bill.getCustomer());
                bufferedwriter.newLine();

                for(PhoneCall phonecall : ((PhoneBill) bill).getPhoneCalls()){
                    bufferedwriter.append(((PhoneBill) bill).prettyPrint(phonecall));
                    bufferedwriter.newLine();
                }

                System.out.println("pretty printed successfully");
                bufferedwriter.close();

            } catch (ParseException e){
                e.printStackTrace();
                System.exit(1);
            }
        }
        else {
            System.out.println("Bill for Phonecall for " + bill.getCustomer());
            for(PhoneCall phoncall : ((PhoneBill) bill).getPhoneCalls()){
                try {
                    System.out.println(((PhoneBill) bill).prettyPrint(phoncall));
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.exit(1);

                }
            }
            System.out.println();
        }
    }

    /**
     * Constructor
     * @param path pretty print to this path
     */
    public PrettyPrinter(String path){
        this.path = path;
    }
}
