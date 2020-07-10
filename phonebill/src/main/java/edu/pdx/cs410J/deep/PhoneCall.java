package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;



public class PhoneCall extends AbstractPhoneCall {


  private String callee;
  private String caller;
  private String start;
  private String end;

  /**
   * PhoneCall Constructor
   * Create a new <code>PhoneCall</code>
   * @param callee
   *        Phone number of caller
   * @param caller
   *        Phone number of person who was called
   * @param start
 *        Date and time call began
   * @param end
   */
  public PhoneCall(String callee, String caller, String start, String end) {

//    if(callee.equals("")){
//      throw new IllegalArgumentException("callee cannot be empty");
//    }

//    if(caller.equals("")){
//      throw new IllegalArgumentException("Caller cannot be empty");
//    }


    this.callee = callee;
    this.caller = caller;
    this.start = start;
    this.end = end;
  }

  public void add(PhoneCall phonecall) {

    this.callee = phonecall.callee;
    this.caller = phonecall.caller;
    this.start = phonecall.start;
    this.end = phonecall.end;
  }


  @Override
  public String getCaller() {
   return this.caller;
  }

  @Override
  public String getCallee() {
    return this.callee;
  }

  @Override
  public String getStartTimeString() {
    return this.start;
  }

  @Override
  public String getEndTimeString() {
   return this.end;
  }




}

