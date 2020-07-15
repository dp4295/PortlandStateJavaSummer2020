package edu.pdx.cs410J.deep;

import java.util.regex.Pattern;

public class CheckNumberValidity {


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



}
