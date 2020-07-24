package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneCall;

import javax.swing.*;


public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall>{


  private String callee;
  private String caller;
  private String start;
  private String starttime;
  private String startampm;
  private String end;
  private String endtime;
  private String endampm;

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
   *        Start time (eg: 12:00)
   * @param end
   *        Date call end (e.g: 7/23/2020)
   * @param  endtime
   *        end time (e.g: 12:00)
   */
  public PhoneCall(String caller, String callee, String start, String starttime, String startampm, String end, String endtime,String endampm) {

    this.caller = caller;
    this.callee = callee;
    this.start = start;
    this.starttime = starttime;
    this.startampm = startampm;
    this.end = end;
    this.endtime = endtime;
    this.endampm = endampm;
  }

  public void add(PhoneCall phonecall) {
    this.caller = phonecall.caller;
    this.callee = phonecall.callee;
    this.start = phonecall.start;
    this.starttime = phonecall.starttime;
    this.startampm = phonecall.startampm;
    this.end = phonecall.end;
    this.endtime = phonecall.endtime;
    this.endampm = phonecall.endampm;
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

    if(start == null && starttime == null & startampm ==null)
    {
      return null;
    }

    String start_date_time = start + " " + starttime + " " + startampm;
    return start_date_time;
  }

  @Override
  public String getEndTimeString() {

    if(end == null && endtime == null && endampm == null)
    {
      return null;
    }

    String end_date_time = end + " " + endtime + " " + endampm;
    return end_date_time;
  }

  /**
   *
   * @return String with Caller number, Callee number, Start time and end time
   */
  public String getPhoneCallToWrite() {

    return getCaller() + "," + getCallee() + "," + getStartTimeString() + "," + getEndTimeString();
  }


  @Override
  public int compareTo(PhoneCall o){

    if(this.start.compareTo(o.start) != 0)
    {
      return this.start.compareTo((o.start));
    }
    else
    {
      return this.end.compareTo((o.end));
    }
  }
}

