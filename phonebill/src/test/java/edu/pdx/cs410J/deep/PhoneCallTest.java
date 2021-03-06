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
    PhoneCall call = new PhoneCall("123-123-1234" ,"123-222-1234", "7/12/2020","12:00", "am", "7/12/2020", "12:00", "am");
    assertThat(call.getStartTimeString(), not(nullValue()));
  }

  @Test
  public void initiallyAllPhoneCallsHaveTheSameCallee() {
    String callee = "123-333-1234";
    PhoneCall call = new PhoneCall("123-123-1234", callee, "7/12/2020","11:00", "am", "7/12/2020", "12:00", "pm");
    assertThat(call.getCallee(), equalTo(callee));
  }

  @Test
  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {

    PhoneCall call = new PhoneCall("123-123-1234" ,"123-222-1234", "7/12/2020",null, "am", "7/12/2020", "13:00", "am");
    assertThat(call.getStartTime(), is(nullValue()));
  }

  @Test
  public void calleeIsNotNumber() {
    String callee = "123-45-678a";
    PhoneCall call = new PhoneCall("123-123-1234" ,"123-222-123a", "7/12/2020","12:00", "am", "7/12/2020", "12:00", "pm");
    for (String str : call.getCallee().split("-")[1].split("-")) {
      Integer.parseInt(str);
    }
  }


  @Test
  public void forProject1ItIsOkayIfGetEndTimeReturnsNull() {
    PhoneCall call = new PhoneCall("123-123-1234" ,"123-222-1234", "7/12/2020","11:00","am", null, null, null );
    assertThat(call.getEndTimeString(), is(nullValue()));

  }

  @Test
  public void StartTimeEndTimeIsNotSame() {
    PhoneCall call = new PhoneCall("123-123-1234" ,"123-222-1234", "7/12/2020","11:00", "am", "7/12/2020", "12:00", "pm");
    assertThat(call.getEndTimeString(), not(call.getStartTimeString()));
  }

  @Test
  public void validInvalidDateAndTime() {

    String start = "01/15/2020";
    String start_time = "11:39";
    String end = "02/20/2020";
    String end_time = "11:39";

    if(Pattern.matches("[0-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", start))
    {
      if(!(Pattern.matches("[0-90-9]{2}:[0-90-9]{2}", start_time)))
      {
        System.out.println("Valid start date but invalid start time E.g: 2/04/2020 12:34 am/pm");
      }
      else {
        //  System.out.println("Valid date and time");
      }
    }
    else {
      System.out.println("Invalid start date E.g 2/04/2020");
    }


    if(Pattern.matches("[0-9]{2}/[0-90-9]{2}/[0-90-90-90-9]{4}", end))
    {
      if(!(Pattern.matches("[0-90-9]{2}:[0-90-9]{2}", end_time)))
      {
        System.out.println("Valid end date but invalid end time E.g: 2/04/2020 12:34");
      }
      else {
        //  System.out.println("Valid date and time");
      }
    }
    else {
      System.out.println("Invalid end date E.g 2/04/2020");
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
