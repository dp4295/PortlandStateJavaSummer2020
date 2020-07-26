package edu.pdx.cs410J.deep;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;


public class ArgumentValidationCheck {



    /**
     * This methods check phone call’s end time is before its starts time
     * @Return true false
     */
     public Boolean checkForEndTimeBeForStartTime(String starttime, String stime, String startampm, String endtime, String etime, String endampm) throws ParseException {


          String std = starttime + " " + stime + " " + startampm;
          String etd = endtime + " " + etime + " " + endampm;

          SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
          SimpleDateFormat et = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
          Date start = st.parse(std);
          Date end = et.parse(etd);

//          System.out.print(start);
//          System.out.print(end);
//          System.out.print(start.compareTo(end));
          if (start.compareTo(end) > 0) {
              return false;
          } else if (start.compareTo(end) < 0) {
              return true;
          }

          return false;
      }





    /**
     * This methods check if passed date match with format or not
     * Valid format
     * 07/23/2020 12:00 PM
     * 07/23/2020 12:23 PM
     */
    public void validInvalidDateAndTime(String start, String start_time, String startampm,  String end, String end_time, String endampm) {


        // Check valid date mm/dd/yyyy am/pm or m/dd/yyyy or mm/d/yyyy or m/d/yyyy
        if((Pattern.matches("[0-90-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", start) ) || (Pattern.matches("[0-9]{1}/[0-90-9]{2}/[0-90-90-90-9]{4}", start) ) ||
                (Pattern.matches("[0-90-9]{2}/[0-9]{1}/[0-90-90-90-9]{4}", start) || (Pattern.matches("[0-9]{1}/[0-9]{1}/[0-90-90-90-9]{4}", start) ) ))
        {

           if(!(Pattern.matches("0[1-9]|1[012]", start_time)))
            {

                if((Pattern.matches("am", startampm)) || (Pattern.matches("pm", startampm)))
                {

                }
                else {
//                    System.out.println("Valid start date but invalid start time E.g: 2/04/2020 12:34 am/pm");
//                    System.exit(1);
                    System.out.println("am/pm required");
                    System.exit(1);
                }
            }
            else {

               System.out.println("Valid start date but invalid start time E.g: 2/04/2020 12:34 am/pm");
               System.exit(1);
            }
        }
        else {
            System.out.println("Invalid start date E.g 2/04/2020");
            System.exit(1);
        }

        // Check valid date mm/dd/yyyy or m/dd/yyyy or mm/d/yyyy or m/d/yyyy
        if((Pattern.matches("[0-90-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", end) ) || (Pattern.matches("[0-9]{1}/[0-90-9]{2}/[0-90-90-90-9]{4}", end) ) ||
                (Pattern.matches("[0-90-9]{2}/[0-9]{1}/[0-90-90-90-9]{4}", end) || (Pattern.matches("[0-9]{1}/[0-9]{1}/[0-90-90-90-9]{4}", end))))
        {

            if(!(Pattern.matches("0[1-9]|1[012]", end_time)))
            {
                if((Pattern.matches("am", endampm)) || (Pattern.matches("pm", endampm)))
                {

                }else {
                    System.out.println("am/pm required");
                    System.exit(1);
                }

            }
            else {
                //  System.out.println("Valid date and time");
                System.out.println("Valid end date but invalid end time E.g: 2/04/2020 12:34 am/pm");
                System.exit(1);
            }
        }
        else {
            System.out.println("Invalid end date E.g 2/04/2020");
            System.exit(1);
        }
    }


    /**
     * This methods check for valid phone number
     * @return Invalid message and exit 1
     */
    public void validInvalidPhoneNumber(String callernumner, String calleenumber){


        if(!(Pattern.matches("[0-90-90-9]{3}-[0-90-90-9]{3}-[0-90-90-90-9]{4}", callernumner)))
        {
            System.err.println("Invalid number format " + callernumner + " valid  format is 123-333-1234");
            System.exit(1);
        }

        if(!(Pattern.matches("[0-90-90-9]{3}-[0-90-90-9]{3}-[0-90-90-90-9]{4}", calleenumber)))
        {
            System.err.println("Invalid number format " + calleenumber + " valid  format is 123-333-1234");
            System.exit(1);
        }

    }

    /**
     * This method check for invalid option
     * @return true or false
    */
    public boolean validInvalidOption(String[] args) {
        if (args[0] == "-README") {
            return true;
        } else if (args[0] == "-print") {
            return true;
        } else if (args[0] == "-textFile") {
            return true;
        }
        return false;
    }

    /**
     *  This method check for valid path
     * @return valid or invalid message
     */
    public boolean isvalidfilename(String path) {
        if (path.endsWith(".txt")) {
            // System.out.println("File can be created");
            return true;
        } else {
            System.out.println("Invalid file name. Can't create file");
            return false;
        }
    }

        /** This method check if file is exist or not <code>isFileExist</code>
         * @return valid or invlid message
         * @return Boolean
         */
        public boolean isFileExist(String filename){

            File file = new File(filename);

            if(file.exists())
            {
                System.out.println("File is exist");
                return true;
            }
            else
            {
                System.out.println("File doesn't exist");
                return false;
            }

        }

}
