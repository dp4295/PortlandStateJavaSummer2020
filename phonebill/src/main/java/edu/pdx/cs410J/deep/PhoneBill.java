package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class PhoneBill extends AbstractPhoneBill{

    private String customer;
    List<PhoneCall> callcollection;

    /**
     * Creates a new <code>PhoneBill</code>
     * @param customer    customer name
     */
    public PhoneBill(String customer) {
        this.customer = customer;
        callcollection = new ArrayList<PhoneCall>();
    }

    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        callcollection.add((PhoneCall) call);
    }

//    @Override
//    public void addPhoneCall(PhoneCall call) {
//        callcollection.add(call);
//    }

    @Override
    public List<PhoneCall> getPhoneCalls() {

      Collections.sort(callcollection);

       return  callcollection;
    }


    /**
     *
     * This method is used to create a file at destination <code>CreateFile</code>
     * @return Success, failure or file already exist message
     */
      public void CreateFile(String filename) throws IOException {



              File newfile = new File( filename);

              if(newfile.createNewFile())
              {
                  System.out.println("File created");

              }else{
                  System.out.println("File already exist");
              }



//          try{
//
//              File newfile = new File("*deep/" + filename);
//
//              if(newfile.createNewFile())
//              {
//                  System.out.println("File created");
//              }else{
//                  System.out.println("File already exist");
//              }
//
//          } catch (IOException e) {
//              System.out.println("Something is wrong, can not create file this time");
//              e.printStackTrace();
//          }
      }

    /**
     * This method write to destination file  <code>WriteToFile</code>
     * @return success message
     */
      public void WriteToFile(String  filelocation, PhoneBill writeobj){

          try{
              FileWriter write = new FileWriter(filelocation);

//              System.out.println("Write out into file successfully");

              TextDumper obj = new TextDumper(filelocation);
              obj.dump(writeobj);


          } catch (IOException e) {
              System.out.println("Unable to write out");
              e.printStackTrace();
          }
      }


    /**
     * This method is used by PrettyPrinter class to write in to PrettyPrinter
     */
    public static String prettyPrint(PhoneCall phonecall) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date startdate = format.parse(phonecall.getStartTimeString());
        Date enddate = format.parse(phonecall.getEndTimeString());
        long phonecallduaration = enddate.getTime() - startdate.getTime() ;

        return phonecall.getPhoneCallToWrite() + "," + phonecallduaration;

    }



    /**
     *  Setter function customer
     */
    public void setCustomer(String customer)
    {
          this.customer = customer;
    }


    /**
     *  Display function will display all call records
     */
    public void display()
    {
        System.out.println();
        System.out.println("Phone Bill");
        System.out.println("_________________________________");
        System.out.println(toString());
        System.out.println(getPhoneCalls().toString());
    }

    /**
     * Display function for displaying from file
     */
    public void DisplayFromFile()
    {
        System.out.println();
        System.out.println(toString());
        System.out.println(getPhoneCalls().toString());
    }





}
