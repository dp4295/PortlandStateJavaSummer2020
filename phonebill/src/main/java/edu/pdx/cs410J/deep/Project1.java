package edu.pdx.cs410J.deep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) throws IOException {
  //  PhoneCall call = new PhoneCall(null, null,);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
//    PhoneCall call = new PhoneCall("deep", "Unknown", "6/7/2020 10:21", "6/7/2020 10:30");
//    PhoneBill bill = new PhoneBill("Deep", (Collection<PhoneCall>) call);

   //x System.out.println("Length: " + args.length);


    if(args.length == 0)
    {
         System.out.println("No command line argument found");
         System.exit(1);

    }
    if (args.length == 1) {
      System.out.println("Missing customer name, caller number, callee number, start date, start time, end date and end time");

      if(args[0].equals("-README"))
      {
        InputStream readme = Project1.class.getResourceAsStream("README.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
        String line = reader.readLine();
        System.out.print(line);
      }else if(args[0].equals("-print"))
      {
        
      }

      System.exit(1);
    }

    if(args.length == 2)
    {
      System.out.println("Missing caller number, callee number, start date, start time, end date and end time");
      System.exit(1);
    }

    if(args.length == 3)
    {
      System.out.println("Missing callee number, start date, start time, end date and end time");
      System.exit(1);

    }

    if(args.length ==4)
    {
      System.out.println("Missing start date, start time, end date and end time");
      System.exit(1);

    }

    if(args.length == 5)
    {
      System.out.println("Missing start time, end date and end time");
      System.out.print(1);
    }

    if (args.length == 6)
    {
      System.out.println("Missing end date and end time");
      System.out.print(1);
    }

    if(args.length == 7)
    {
      System.out.println("Missing end time");
      System.exit(1);
    }

    if(args.length == 8)
    {
      System.out.println("Argument matched");
      System.exit(1);
    }

    if(args.length >= 9)
    {
      System.out.println("Extra argument");
      System.exit(1);
    }

    for (String arg : args) {
      System.out.println(arg);
    }
    System.exit(1);
  }

//  private static void printErrorMessageAndExit(String message, String[] args) throws IOException {
//    System.out.println(message);
//
//    if(args.length == 1)
//    {
//         if(args[0].equals("-README"))
//         {
//           InputStream readme = Project1.class.getResourceAsStream("README.txt");
//           BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
//           String line = reader.readLine();
//           System.out.print(line);
//         }
//
//          if(args[0].equals("-print"))
//         {
//
//         }
//    }


   // System.exit(1);


}