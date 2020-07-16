package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

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
    public void addPhoneCall(PhoneCall call) {
        callcollection.add(call);
    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return callcollection;
    }


    /**
     *
     * This method is used to create a file at destination <code>CreateFile</code>
     * @return Success, failure or file already exist message
     */
      public void CreateFile(String filename){

          try{
              File newfile = new File("src/main/resources/edu/pdx/cs410J/deep/" + filename);
              if(newfile.createNewFile())
              {
                  System.out.println("File created");
              }else{
                  System.out.println("File already exist");
              }

          } catch (IOException e) {
              System.out.println("Something is wrong, can not create file this time");
              e.printStackTrace();
          }
      }

    /**
     * This method write to destination file  <code>WriteToFile</code>
     * @return success message
     */
      public void WriteToFile(String  filelocation, PhoneBill writeobj){

          try{
              FileWriter write = new FileWriter(filelocation);

              System.out.println("Write out into file successfully");
          } catch (IOException e) {
              System.out.println("Unable to write out");
              e.printStackTrace();
          }
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



}
