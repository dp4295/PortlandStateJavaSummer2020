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
    }else if(args.length == 1) {

      System.out.println("Missing customer name, caller number, callee number, start date, start time, end date and end time");

      // valid options are -README , -print , -textFile only
      OptionValidity optionresult = new OptionValidity();
      if((optionresult.validInvalidOption(args))==true){
        System.out.println("Valid options are only -README , - print , -textFile");
        System.exit(1);
      }

      if (args[0].equals("-README")) {
        InputStream readme = Project1.class.getResourceAsStream("README.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
       // BufferedReader reader = new BufferedReader(new FileReader("README.txt"));5
        String line = reader.readLine();

        while(line != null)
        {
          System.out.println(line);
          line = reader.readLine();
        }
      }
      System.exit(1);

    }else if ((args.length >= 2) && (args.length <= 6)) {
      System.out.println("Invalid command line arguments");
      System.exit(1);
    } else if(args.length == 7) {

      // If passing argument length is 7 then args[0] should not be any option
      OptionValidity optionresult1 = new OptionValidity();
      if ((optionresult1.validInvalidOption(args)) == false) {

        // this is valid case. option is missing but PhoneCall can be added to PhoneBill


        // Validate Date and Time
        CheckDateAndTimeValidity  date_time = new CheckDateAndTimeValidity();
        date_time.validInvalidDateAndTime(args[3], args[4], args[5], args[6]);

        // validate phone number
        CheckNumberValidity number = new CheckNumberValidity();
        number.validInvalidPhoneNumber(args[1],args[2]);

        // Create a new phone call record
        PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4], args[5], args[6]);

        // Create a bill object
        PhoneBill mybill = new PhoneBill(args[0]);

        // Add call record to bill object
        mybill.addPhoneCall(call);

        mybill.display();
      } else {
        System.out.println("Invalid arguments.It should not have any -print, -README or -textFile");
        System.exit(1);
      }
    } else if (args.length == 8) {
        System.out.println("Congratulate you enter 8 arguments");

        // Storing all the arguments in local variable for readablity

        if (args[0].equals("-print")) {



          String option = args[0];
          String customer = args[1];
          String callernumber = args[2];
          String calleenumber = args[3];
          String startdate = args[4];
          String starttime = args[5];
          String enddate = args[6];
          String endtime = args[7];

          CheckDateAndTimeValidity result = new CheckDateAndTimeValidity();
          result.validInvalidDateAndTime(startdate, starttime, enddate, endtime);

          CheckNumberValidity validnumber = new CheckNumberValidity();
          validnumber.validInvalidPhoneNumber(callernumber, calleenumber);

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

        } else {
          System.out.println("Missing -print option");
          System.exit(1);
        }



      } else if (args.length >= 9) {
        System.out.println("Extra arguments");
        System.exit(1);
      }

//       else if (args.length >= 10) {
//        System.out.println("Extra arguments");
//        System.exit(1);
//      }


//    for (String arg : args) {
//      System.out.println(arg);
//    }
//    System.exit(1);



  }
}
