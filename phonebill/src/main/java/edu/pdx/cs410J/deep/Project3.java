package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project3 {

  public static void main(String[] args) throws IOException, ParserException, ParseException {

    // Do you want to read from file
    Scanner in = new Scanner(System.in);



    if (args.length == 0) {
      System.out.println("No command line argument found");
      System.exit(1);

    }else if(args.length == 1) {

      System.out.println("Only one argument found");

      // valid options are -README , -print , -textFile only
      ArgumentValidationCheck validationCheck = new ArgumentValidationCheck();
      if((validationCheck.validInvalidOption(args))==true){
        System.out.println("Valid options are only -README, - print , -textFile");
        System.exit(1);
      }

      if (args[0].equals("-README")) {
        InputStream readme = Project3.class.getResourceAsStream("README.txt");
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

    }else if ((args.length >= 2) && (args.length <= 8)) {


      System.out.println("Invalid command line arguments");
      System.exit(1);
    }

    else if(args.length == 9) {



      // If passing argument length is 7 then args[0] should not be any option
      ArgumentValidationCheck validationCheck = new ArgumentValidationCheck();


        // Validate Date and Time
        ArgumentValidationCheck validationcheck = new ArgumentValidationCheck();
        validationcheck.validInvalidDateAndTime(args[3], args[4], args[5] ,args[6], args[7], args[8]);

        // validate phone number
        validationcheck.validInvalidPhoneNumber(args[1],args[2]);

        // return false, if the start date is before the end
        if(!(validationcheck.checkForEndTimeBeForStartTime(args[3], args[4], args[5] ,args[6], args[7], args[8]))){
          System.out.println("phone call's end time is before its start time");
          System.exit(1);
        }

        // Create a new phone call record
        PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);

        // Create a bill object
        PhoneBill mybill = new PhoneBill(args[0]);

        // Add call record to bill object
        mybill.addPhoneCall(call);

    }
    else if (args.length == 10) {
        System.out.println("Congratulate you enter 9 arguments");

        // Storing all the arguments in local variable for readablity

        if (args[0].equals("-print")) {



          String option = args[0];
          String customer = args[1];
          String callernumber = args[2];
          String calleenumber = args[3];
          String startdate = args[4];
          String starttime = args[5];
          String startaampm = args[6];
          String enddate = args[7];
          String endtime = args[8];
          String endampm = args[9];

          String path = args[1]; // saving path into local variable

          // Split the entire string with "/" token
          String breaks[] = path.split("/");
          String filename = breaks[1];

          ArgumentValidationCheck validationcheck  = new ArgumentValidationCheck();
          validationcheck.validInvalidDateAndTime(startdate, starttime, startaampm, enddate, endtime, endampm);
          validationcheck.validInvalidPhoneNumber(callernumber, calleenumber);




          // Create a new phone call record
          PhoneCall call = new PhoneCall(callernumber, calleenumber, startdate, starttime, startaampm, enddate, endtime, endampm);

          // Create a bill object
          PhoneBill mybill = new PhoneBill(customer);

          // Add call record to bill object
          mybill.addPhoneCall(call);

//          // Create a new phone call object
//          PhoneCall call2 = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "6/7/2020", "12:30");
//
//          // Add call to bill object
//          mybill.addPhoneCall(call2);

          // Invoke mybill's display
          mybill.display();

        } else {
          System.out.println("Missing -print option");
          System.exit(1);
        }



      } else if (args.length == 11) {

      System.out.println("Passed eleven arguments");


      // Check if path first argument is -textFile
      if (args[0].equals("-textFile")) {

        // ArgumentValidationCheck class contain methods to argument validation
        ArgumentValidationCheck validationobj = new ArgumentValidationCheck();
        if (validationobj.isvalidfilename(args[1])) {

          String path = args[1]; // saving path into local variable

          // Split the entire string with "/" token
          String breaks[] = path.split("/");
          String filename = breaks[1];




          // Check validation for all command line arguments
          ArgumentValidationCheck validationcheck  = new ArgumentValidationCheck();

          // Check for valid date format
          validationcheck.validInvalidDateAndTime(args[5], args[6],args[7], args[8], args[9], args[10]);

          // Check for end time is before starts time
          String start = args[5];
          String end = args[8];


          // return false, if the start date is before the end
//          System.out.print((validationcheck.checkForEndTimeBeForStartTime(start,args[6], args[7],end, args[9], args[10])));
            if(!(validationcheck.checkForEndTimeBeForStartTime(start,args[6], args[7],end, args[9], args[10]))){
            System.out.println("phone call's end time is before its start time");
            System.exit(1);
          }


          validationcheck.validInvalidPhoneNumber(args[3], args[4]);



          // Create a new phone call record
          PhoneCall call = new PhoneCall(args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10]);

          // Create a bill object
          PhoneBill mybill = new PhoneBill(args[2]);
          mybill.addPhoneCall(call);


//          // ---> Second call <------
//          // Create a new phone call object
//          PhoneCall call2 = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "6/7/2020", "12:30");
//          // Add call to bill object
//          mybill.addPhoneCall(call2);
//          // ----> Added second call to call object <----

          // Display mybill
          mybill.display();

          // before creating a file make sure to valid isPathCorrect and parse file name we already did it earlier
          mybill.CreateFile(filename);

          // Here the first argument is path and second argument is PhoneBill object
          mybill.WriteToFile(filename, mybill);


          // Create a newline in output
          for(int i=0; i<=3; ++i)
          {
            System.out.println();
          }

            System.out.println("Read from file");
            System.out.println("_______________");
            // READING FROM FILE
            TextParser textparser = new TextParser(filename, mybill,args[2]);
            System.out.println(textparser.parse());
        }
      }


//     // --> Reading from file <----



//    // ----> End of reading from file <---

    } else if (args.length == 12) {


      // getting file name
      String path = args[1]; // saving path into local variable
      // Split the entire string with "/" token
      String breaks[] = path.split("/");
      String filename = breaks[1];

      String options1 = args[0];
      String file = filename;
      String option2 = args[2];
      String customername = args[3];
      String callernumber = args[4];
      String calleenumber= args[5];
      String startdate = args[6];
      String starttime = args[7];
      String startampm = args[8];
      String enddate = args[9];
      String endtime = args[10];
      String endampm = args[11];




      ArgumentValidationCheck validationcheck = new ArgumentValidationCheck();
      validationcheck.validInvalidDateAndTime(startdate, starttime, startampm, enddate, endtime, endampm);
      validationcheck.validInvalidPhoneNumber(callernumber, calleenumber);

      if(!(validationcheck.checkForEndTimeBeForStartTime(startdate,starttime, startampm,enddate, endtime, endampm))){
        System.out.println("phone call's end time is before its start time");
        System.exit(1);
      }




      // In this case we would like to print PhoneBill object if file is not exist
      if ((args[0].equals("-textFile") && args[2].equals("-pretty") && !(validationcheck.isFileExist(filename)) && validationcheck.isvalidfilename(args[1]))) {
//      if ((args[0].equals("-textFile") && args[2].equals("-pretty"))) {

         System.out.println("File not exist");


         PhoneCall call = new PhoneCall(callernumber, calleenumber, startdate, starttime, startampm, enddate, endtime, endampm);
         PhoneBill bill = new PhoneBill(customername);
         bill.addPhoneCall(call);



        // before creating a file make sure to valid isPathCorrect and parse file name we already did it earlier
        bill.CreateFile(filename);

        // Here the first argument is path and second argument is PhoneBill object
        bill.WriteToFile(filename, bill);

        PrettyPrinter pp = new PrettyPrinter(null);
        pp.dump(bill);


//        // Create a newline in output
//        for(int i=0; i<=3; ++i)
//        {
//          System.out.println();
//        }
//
//        System.out.println("Read from file");
//        System.out.println("_______________");
//        // READING FROM FILE
//        TextParser textparser = new TextParser(filename, bill, customername);
//        System.out.println(textparser.parse());


        System.exit(0);
      }else {

        if(validationcheck.isFileExist(filename) && (args[0].equals("-textFile")) && args[2].equals("-pretty") ) {

          PhoneCall call = new PhoneCall(callernumber, calleenumber, startdate, starttime, startampm, enddate, endtime, endampm);
          PhoneBill bill = new PhoneBill(customername);
          bill.addPhoneCall(call);


          // Create a newline in output
          for(int i=0; i<=3; ++i)
          {
            System.out.println();
          }
          System.out.println("Pretty printing");
          System.out.println("_______________");
          // READING FROM FILE

          // Print to pretty file in sorted order by start time and include duration
          PrettyPrinter pp = new PrettyPrinter(null);
          pp.dump(bill);


          System.exit(0);



        }
        else{
        //  System.out.println("Extra arguments");
          System.exit(1);
        }

      }


    }else if(args.length == 13){


      // getting file name
      String textpath = args[1]; // saving path into local variable
      // Split the entire string with "/" token
      String breaks[] = textpath.split("/");
      String filename = breaks[1];

      String prettyfilepath = args[3];
      // split the enire string with "/" token
      String br[] = prettyfilepath.split("/");
      String prettyfilename = br[1];

      String textfilepath = args[0];
      String file = filename;
      String  prettypath = args[3];
      String prettyfile = prettyfilename;
      String customername = args[4];
      String callernumber = args[5];
      String calleenumber= args[6];
      String startdate = args[7];
      String starttime = args[8];
      String startampm = args[9];
      String enddate = args[10];
      String endtime = args[11];
      String endampm = args[12];


      ArgumentValidationCheck validationcheck = new ArgumentValidationCheck();
      validationcheck.validInvalidDateAndTime(startdate, starttime, startampm, enddate, endtime, endampm);
      validationcheck.validInvalidPhoneNumber(callernumber, calleenumber);

      if(!(validationcheck.checkForEndTimeBeForStartTime(startdate,starttime, startampm,enddate, endtime, endampm))){
        System.out.println("phone call's end time is before its start time");
        System.exit(1);
      }

      // In this case we would like to print PhoneBill object if file is not exist
      if ((args[0].equals("-textFile") && args[2].equals("-pretty") && validationcheck.isvalidfilename(prettyfile))) {
    //  if ((args[0].equals("-textFile") && args[2].equals("-pretty"))) {

        System.out.println("File not exist");




        PhoneCall call = new PhoneCall(callernumber, calleenumber, startdate, starttime, startampm, enddate, endtime, endampm);
        PhoneBill bill = new PhoneBill(customername);
        bill.addPhoneCall(call);



        // before creating a file make sure to valid isPathCorrect and parse file name we already did it earlier
        bill.CreateFile(filename);

        // Here the first argument is path and second argument is PhoneBill object
        bill.WriteToFile(filename, bill);


        // ---> Second call <------
        // Create a new phone call object
        PhoneCall call3 = new PhoneCall("555-555-5555", "666-666-6666", "6/9/2020", "12:00", "am", "6/7/2019", "12:30","am");
        // Add call to bill object
        bill.addPhoneCall(call3);
        // ----> Added second call to call object <----


        // ---> Second call <------
        // Create a new phone call object
        PhoneCall call2 = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "am", "6/7/2020", "12:30","am");
        // Add call to bill object
        bill.addPhoneCall(call2);
        // ----> Added second call to call object <----


        // Print to pretty file in sorted order by start time and include duration
        PrettyPrinter pp = new PrettyPrinter(prettyfilename);
        pp.dump(bill);


//        // Create a newline in output
//        for(int i=0; i<=3; ++i)
//        {
//          System.out.println();
//        }
//
//        System.out.println("Read from file");
//        System.out.println("_______________");
//        // READING FROM FILE
//        TextParser textparser = new TextParser(filename, bill, customername);
//        System.out.println(textparser.parse());

        System.exit(0);
      }

          System.exit(0);
        }
        else{
          //  System.out.println("Extra arguments");
          System.exit(1);
        }

      }

//      for (String arg : args) {
//      System.out.println(arg);
//    }
//    System.exit(1);

    }




