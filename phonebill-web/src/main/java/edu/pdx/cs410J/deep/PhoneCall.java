package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneCall;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {

    private String callee;
    private String caller;
    private Date startdatatime;
    private Date enddatetime;


    /**
     * Constructor <code>PhoneCall</code>
     * @return
     */
    PhoneCall(String caller, String callee, String startdatatime, String enddatetime ) throws ParseException {
        this.caller = caller;
        this.callee = callee;
        this.startdatatime = Utility.convertDateFromString(startdatatime);
        this.enddatetime = Utility.convertDateFromString(enddatetime);
    }

    @Override
    public String getCaller() {
        return caller;
    }

    @Override
    public String getCallee() {
        return callee;
    }

    @Override
    public String getStartTimeString() {
        return Utility.dateToString(startdatatime);
    }

    @Override
    public String getEndTimeString() {
        return Utility.dateToString(enddatetime);
    }



    @Override
    public int compareTo(PhoneCall o) {
        return 0;
    }




    /**
     * @return String with Caller number, Callee number, Start time and end time
     */
    public String getPhoneCallToWrite() {
        return getCaller() + "," + getCallee() + "," + getStartTimeString() + "," + getEndTimeString();
    }

    public String printPhoneCall(){
        return "caller("+getCaller() + ") dial to callee("+ getCallee() +") at " + getStartTimeString() + " and ended at " + getEndTimeString();
    }

//    /**
//     * This method wtite destination file <code>getPhonecallForFile</code>
//     */
//    public String getPhonecallForFile()
//    {
//         Date starttime = this.startdatatime;
//         Date endtime = this.enddatetime;
//
//        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
//        String[] starttimeString = format.
//    }


}
