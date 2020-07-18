package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project2 {

  public static void main(String[] args) throws IOException, ParserException {

    // Do you want to read from file
    Scanner in = new Scanner(System.in);



    if (args.length == 0) {
      System.out.println("No command line argument found");
      System.exit(1);
    }else if(args.length == 1) {

      System.out.println("Missing customer name, caller number, callee number, start date, start time, end date and end time");

      // valid options are -README , -print , -textFile only

      ArgumentValidationCheck validationCheck = new ArgumentValidationCheck();
      if((validationCheck.validInvalidOption(args))==true){
        System.out.println("Valid options are only -README , - print , -textFile");
        System.exit(1);
      }

      if (args[0].equals("-README")) {
        InputStream readme = Project2.class.getResourceAsStream("README.txt");
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
      ArgumentValidationCheck validationCheck = new ArgumentValidationCheck();
      if ((validationCheck.validInvalidOption(args)) == false) {

        // this is valid case. option is missing but PhoneCall can be added to PhoneBill


        // Validate Date and Time
        ArgumentValidationCheck validationcheck = new ArgumentValidationCheck();
        validationcheck.validInvalidDateAndTime(args[3], args[4], args[5], args[6]);

        // validate phone number
        validationcheck.validInvalidPhoneNumber(args[1],args[2]);

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


          ArgumentValidationCheck validationcheck  = new ArgumentValidationCheck();
          validationcheck.validInvalidDateAndTime(startdate, starttime, enddate, endtime);
          validationcheck.validInvalidPhoneNumber(callernumber, calleenumber);

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



      } else if (args.length == 9) {
      System.out.println("Congradulation, You meet MAXIMUM require arguments.");


      // Check if path first argument is -textFile
      if (args[0].equals("-textFile")) {

        // ArgumentValidationCheck class contain methods to argument validation
        ArgumentValidationCheck validationobj = new ArgumentValidationCheck();
        if (validationobj.isPathCorrect(args[1])) {
          //parse the file name from path
          String path = args[1];

          // Split the entire string with "/" token
          String breaks[] = path.split("/");
          String filename = breaks[7];

          // Create a new phone call record
          PhoneCall call = new PhoneCall(args[3], args[4], args[5], args[6], args[7], args[8]);

          // Create a bill object

          PhoneBill mybill = new PhoneBill(args[2]);
          mybill.addPhoneCall(call);


          // ---> Second call <------
          // Create a new phone call object
          PhoneCall call2 = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "6/7/2020", "12:30");
          // Add call to bill object
          mybill.addPhoneCall(call2);
          // ----> Added second call to call object <----


          mybill.display();

          // before creating a file make sure to valid isPathCorrect and parse file name
          mybill.CreateFile(filename);

          mybill.WriteToFile(args[1], mybill);

        }
      }


          // --> Reading from file <----
          // Do you want to read from file
    System.out.println();
    System.out.println("Do you want read from file. Press 1 for yes or 2 for No");
    String option;
    do{
      System.out.print("Input 1 or 2: ");
      option = in.nextLine();


      if(option.equals("1")) {

//         String File;
//         String path = "src/main/resources/edu/pdx/cs410J/deep/file.txt";
        String customername;
        System.out.println("Full customer name: e.g valid name: Deep Patel");
        customername = in.nextLine();

        PhoneBill bill = new PhoneBill(null);
        TextParser textparser = new TextParser("src/main/resources/edu/pdx/cs410J/deep/file.txt", bill,customername);
        System.out.println(textparser.parse());



      }else if(option.equals("2")){
        System.out.println("Exit the program");
        System.exit(0);
      }

    } while(!(Pattern.matches("[1-2]", option)));
    // ----> End of reading from file <---

    }

       else if (args.length >= 10) {
        System.out.println("Extra arguments");
        System.exit(1);
      }


//    for (String arg : args) {
//      System.out.println(arg);
//    }
//    System.exit(1);

  }
}
