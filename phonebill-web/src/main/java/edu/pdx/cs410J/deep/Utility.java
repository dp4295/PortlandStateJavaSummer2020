package edu.pdx.cs410J.deep;

// import org.apache.tools.ant.taskdefs.Local;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * This class is created to provide validation and conversion funtionalities <code>Utility</code>
 *
 */
public class Utility {


    /**
     * @param date
     * @return It return string in SHORT format (03/26/2020)
     */
    // more info: https://docs.oracle.com/javase/tutorial/i18n/format/dateFormat.html
    public static String dateToString(Date date){
        DateFormat dateformat;

        // Format: 03/26/2020
        dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        return (dateformat.format(date));
    }

    /**
     * @param datestring
     * @return It return java date
     * @throws ParseException
     */
    public static Date convertDateFromString(String datestring) throws ParseException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");

        date = format.parse(datestring);

        return date;
    }

    /**
     * This method can be use to check if date enter by user as string is valid or not <code>checkInvalidDateAndTimeFormat</code>
     */
    public boolean checkInvalidDateAndTimeFormat(String date, String time, String ampm){

        // Valid date format MM/dd/yyyy am/pm or M/dd/yyyy or MM/d/yyyy or M/d/yyyy
        if((Pattern.matches("[0-90-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", date) ) || (Pattern.matches("[0-9]{1}/[0-90-9]{2}/[0-90-90-90-9]{4}", date) ) ||
                (Pattern.matches("[0-90-9]{2}/[0-9]{1}/[0-90-90-90-9]{4}", date) || (Pattern.matches("[0-9]{1}/[0-9]{1}/[0-90-90-90-9]{4}", date) ) )) {

            // Valid time is from range 0 to 12 hour in 12:00 format
            if(!(Pattern.matches("0[1-9]|1[012]", time))) {

                if((Pattern.matches("am", ampm)) || (Pattern.matches("pm", ampm))) {
                         // If all the case are valid then it exit with 0
                         System.exit(0);
                         return true;
                }
                else {
                    System.out.println("Invalid Date Format");
                    System.exit(1);
                    return false;
                }

            }
            else {
                System.out.println("Invalid Date Format");
                System.exit(1);
                return false;
            }

        }
        else {
            System.out.println("Invalid Date Format");
            System.exit(1);
            return false;
        }
    }


    /**
     * @param number
     * @return if number is correct format return true otherwise false
     */
    public boolean checkInvalidPhoneNumber(String number) {
        if ((Pattern.matches("[0-90-90-9]{3}-[0-90-90-9]{3}-[0-90-90-90-9]{4}", number))) {
            System.exit(0);
            return true;
        } else {
            System.out.println("Invalid number");
            System.exit(1);
            return false;
        }
    }




}
