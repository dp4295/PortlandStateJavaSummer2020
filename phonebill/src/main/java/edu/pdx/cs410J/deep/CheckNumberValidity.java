package edu.pdx.cs410J.deep;

import java.util.regex.Pattern;

public class CheckNumberValidity {

    public void validInvalidPhoneNumber(String[] args){


        String callernumner = args[2];
        String calleenumber = args[3];

        if(!(Pattern.matches("[0-90-90-9]{3}-[0-90-90-9]{3}-[0-90-90-90-9]{4}", callernumner)))
        {
            System.err.println("Invalid number format e.g 123-333-1234");
            System.exit(1);
        }

        if(!(Pattern.matches("[0-90-90-9]{3}-[0-90-90-9]{3}-[0-90-90-90-9]{4}", calleenumber)))
        {
            System.err.println("Invalid number format e.g 123-222-1234");
            System.exit(1);
        }

    }



}
