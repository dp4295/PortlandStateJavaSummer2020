package edu.pdx.cs410J.deep;

import java.util.regex.Pattern;

/**
 * Check Date and Time are in correct format, exit the program if invalid date or time entered
 *
 * @author : Deep
 *
 */
public class CheckDateAndTimeValidity {


    public void validInvalidDateAndTime(String [] args) {

    String start = args[4];
    String start_time = args[5];
    String end = args[6];
    String end_time = args[7];

//        String start = "01/15/2020";
//        String start_time = "11:39";
//        String end = "02/20/2020";
//        String end_time = "11:39";

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

}
