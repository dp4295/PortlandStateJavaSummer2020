package edu.pdx.cs410J.deep;

import org.junit.Test;
import org.junit.validator.ValidateWith;

import javax.swing.*;
import java.util.Date;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneCallTest {

  @Test
  public void getStartTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall("deep", "unknown", "6/7/2020 10:21", "6/7/2020 10:30");
    assertThat(call.getStartTimeString(), not(nullValue()));
  }

  @Test
  public void initiallyAllPhoneCallsHaveTheSameCallee() {
    String callee = "Callee";
    PhoneCall call = new PhoneCall( callee, "deep", "6/7/2020 10:21", "6/7/2020 10:30");
    assertThat(call.getCallee(), equalTo(callee));
  }

  @Test
  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {

    PhoneCall call = new PhoneCall( "deep", "unknown", null, "6/7/2020 10:30");
    assertThat(call.getStartTime(), is(nullValue()));
  }

  @Test
  public void calleeIsNotNumber(){
    String callee = "123-45-678a";
    PhoneCall call = new PhoneCall( callee, "deep", "6/7/2020 10:21", "6/7/2020 10:30");
    for(String str: call.getCallee().split("-")[1].split("-"))
    {
      Integer.parseInt(str);
    }
  }


  @Test
  public void forProject1ItIsOkayIfGetEndTimeReturnsNull(){
    PhoneCall call = new PhoneCall( "deep", "unknown", "6/7/2020 10:30", null);
    assertThat(call.getEndTimeString(), is(nullValue()));

  }

  @Test
  public void StartTimeEndTimeIsNotSame()
  {
    PhoneCall call = new PhoneCall( null, null, "6/7/2020 10:21", "6/7/2020 10:21");
    assertThat(call.getEndTimeString(), equalTo(call.getStartTimeString()));
  }

  @Test
  public void validInvalidDateAndTime(String[] args){

//    String date = "01/15/2020";
//    String  time= "11:39";



    String start = args[4];
    String time = args[]

    if(Pattern.matches("[0-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", date))
    {
      if(!(Pattern.matches("[0-90-9]{2}:[0-90-9]{2}", time)))
      {
        System.out.println("Valid date but invalid time");
      }
      else {
      //  System.out.println("Valid date and time");
      }
    }else{
      System.out.println("Invalid date");
    }
  }


  @Test
  public void validInvalidPhoneNumber(){
    String number = "123-122-1234" ;

    if(!(Pattern.matches("[0-90-90-9]{3}-[0-90-90-9]{3}-[0-90-90-90-9]{4}", number)))
    {
      System.err.println("Invalid number format e.g 123-123-1234");

    }
    else
    {
      // System.err.print("Passed valid number test");
    }
  }


}
