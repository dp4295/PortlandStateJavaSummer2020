package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneCall;


public class PhoneCall extends AbstractPhoneCall{


  private String callee;
  private String caller;
  private String start;
  private String starttime;
  private String end;
  private String endtime;

  /**
   * PhoneCall Constructor
   * Create a new <code>PhoneCall</code>
   * @param callee
   *        Phone number of caller
   * @param caller
   *        Phone number of person who was called
   * @param start
   *        Date call began (e.g: 7/23/2020)
   * @param starttime
   *        Start time (eg: 24:00)
   * @param end
   *        Date call end (e.g: 7/23/2020)
   * @param  endtime
   *        end time (e.g: 24:00)
   */
  public PhoneCall(String caller, String callee, String start, String starttime, String end, String endtime) {

    this.caller = caller;
    this.callee = callee;
    this.start = start;
    this.starttime = starttime;
    this.end = end;
    this.endtime = endtime;
  }

  public void add(PhoneCall phonecall) {
    this.caller = phonecall.caller;
    this.callee = phonecall.callee;
    this.start = phonecall.start;
    this.starttime = phonecall.starttime;
    this.end = phonecall.end;
    this.endtime = phonecall.endtime;
  }


  @Override
  public String getCaller() {
   return this.caller;
  }

  @Override
  public String getCallee() {
    return this.callee;
  }

//  @Override
//  public String getStartTimeString() {
//    return this.start;
//  }

//  @Override
//  public String getEndTimeString() {
//   return this.end;
//  }

  @Override
  public String getStartTimeString() {

    if(start == null && starttime == null)
    {
      return null;
    }

    String start_date_time = start + " " + starttime;
    return start_date_time;
  }

  @Override
  public String getEndTimeString() {

    if(end == null && endtime == null)
    {
      return null;
    }

    String end_date_time = end + " " + endtime;
    return end_date_time;
  }

  /**
   *
   * @return String with Caller number, Callee number, Start time and end time
   */
  public String getPhoneCallToWrite() {

    return getCaller() + "," + getCallee() + "," + getStartTimeString() + "," + getEndTimeString();
  }


}

