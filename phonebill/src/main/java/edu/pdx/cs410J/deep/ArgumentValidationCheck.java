package edu.pdx.cs410J.deep;

import java.util.regex.Pattern;


public class ArgumentValidationCheck {

    /**
     * This methods check if passed date and time matches or not
     */
    public void validInvalidDateAndTime(String start, String start_time, String end, String end_time) {


//        String start = "01/15/2020";
//        String start_time = "11:39";
//        String end = "02/20/2020";
//        String end_time = "11:39";


        // Check valid date mm/dd/yyyy or m/dd/yyyy or mm/d/yyyy or m/d/yyyy
        if((Pattern.matches("[0-90-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", start) ) || (Pattern.matches("[0-9]{1}/[0-90-9]{2}/[0-90-90-90-9]{4}", start) ) ||
                (Pattern.matches("[0-90-9]{2}/[0-9]{1}/[0-90-90-90-9]{4}", start) || (Pattern.matches("[0-9]{1}/[0-9]{1}/[0-90-90-90-9]{4}", start) ) ))
        {

            if(!(Pattern.matches("[0-90-9]{2}:[0-90-9]{2}", start_time)))
            {
                System.out.println("Valid start date but invalid start time E.g: 2/04/2020 12:34");
                System.exit(1);
            }
            else {
                //  System.out.println("Valid date and time");
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
            if(!(Pattern.matches("[0-90-9]{2}:[0-90-9]{2}", end_time)))
            {
                System.out.println("Valid end date but invalid end time E.g: 2/04/2020 12:34");
                System.exit(1);
            }
            else {
                //  System.out.println("Valid date and time");
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
    public void isPathCorrect(String path)
    {
        if(path.startsWith("src/main/resources/edu/pdx/cs410J/deep/")){
//            System.out.println("valid");
//            System.exit(0);
        }else{
            System.out.println("Invalid path location. Valid file name must follow by src/main/resources/edu/pdx/cs410J/deep/");
            System.exit(1);
        }
    }





}
