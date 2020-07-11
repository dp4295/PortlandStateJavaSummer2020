package edu.pdx.cs410J.deep;

import java.io.*;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) throws IOException {

    if (args.length == 0) {
      System.out.println("No command line argument found");
      System.exit(1);

    }
    if (args.length == 1) {
      System.out.println("Missing customer name, caller number, callee number, start date, start time, end date and end time");

      if (args[0].equals("-README")) {
        InputStream readme = Project1.class.getResourceAsStream("README.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
       // BufferedReader reader = new BufferedReader(new FileReader("README.txt"));

        String line = reader.readLine();

        while(line != null)
        {
          System.out.println(line);
          line = reader.readLine();
        }
      }
      else if(args[0].equals("-print")){

        // Create a new phone call object
        PhoneCall call = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "6/7/2020", "12:30");

        // Create a bill object
        PhoneBill mybill = new PhoneBill("Deep Patel");

        // Add call record to bill object
        mybill.addPhoneCall(call);

        // Invoke mybill's display
        mybill.display();


      }


      System.exit(1);
    }

    if (args.length == 2) {
      System.out.println("Missing caller number, callee number, start date, start time, end date and end time");
      System.exit(1);
    }

    if (args.length == 3) {
      System.out.println("Missing callee number, start date, start time, end date and end time");
      System.exit(1);

    }

    if (args.length == 4) {
      System.out.println("Missing start date, start time, end date and end time");
      System.exit(1);

    }

    if (args.length == 5) {
      System.out.println("Missing start time, end date and end time");
      System.exit(1);
    }

    if (args.length == 6) {
      System.out.println("Missing end date and end time");
      System.exit(1);
    }

    if (args.length == 7) {
      System.out.println("Missing end time");
      System.exit(1);
    }

    if (args.length == 8) {
      System.out.println("Congratulate you enter 8 arguments");


      // Storing all the arguments in local variable for readablity

      if (args[0].equals("-print")) {

        CheckDateAndTimeValidity result = new CheckDateAndTimeValidity();
        result.validInvalidDateAndTime(args);

        CheckNumberValidity validnumber = new CheckNumberValidity();
        validnumber.validInvalidPhoneNumber(args);

        String option = args[0];
        String customer = args[1];
        String callernumber = args[2];
        String calleenumber = args[3];
        String startdate = args[4];
        String starttime = args[5];
        String enddate = args[6];
        String endtime = args[7];

        // Create a new phone call record
        PhoneCall call = new PhoneCall(callernumber, calleenumber, startdate, starttime, enddate, endtime);

        // Create a bill object
        PhoneBill mybill = new PhoneBill(customer);

        // Add call record to bill object
        mybill.addPhoneCall(call);

        // Create a new phone call object
        PhoneCall call2 = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "6/7/2020", "12:30");

        // Add call to bill object
        mybill.addPhoneCall(call2);

        // Invoke mybill's display
         mybill.display();



      }else{
        System.out.println("Missing -print option");
        System.exit(1);
      }

    }

    if (args.length >= 9) {
      System.out.println("Extra argument");
      System.exit(1);
    }

//    for (String arg : args) {
//      System.out.println(arg);
//    }
//    System.exit(1);

  }

}
