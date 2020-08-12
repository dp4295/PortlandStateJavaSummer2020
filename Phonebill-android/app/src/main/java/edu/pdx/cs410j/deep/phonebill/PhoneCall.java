package edu.pdx.cs410j.deep.phonebill;

import java.text.ParseException;
import java.util.Date;

import edu.pdx.cs410J.AbstractPhoneCall;


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

//        if(this.startdatatime.compareTo(o.startdatatime) > 0) {
//            return this.startdatatime.compareTo(o.startdatatime);
//        }else {
//            return this.getEndTime().compareTo(o.getEndTime());
//        }
        return 0;
    }


    /**
     * @return String with Caller number, Callee number, Start time and end time
     */
    public String getPhoneCallToWrite() {
        return getCaller() + "," + getCallee() + "," + getStartTimeString() + "," + getEndTimeString();
    }

    public String printPhoneCall(){
        return "  caller("+getCaller() + ") dial to callee("+ getCallee() +") at " + getStartTimeString() + " and ended at " + getEndTimeString();
    }

    public String prettyPrintCaller(PhoneBill phoneBill){
        return phoneBill.getCustomer() + " called from number " + getCaller() +  " to number " + getCallee() + " at " + getStartTimeString() + "  and ended call at " + getEndTimeString();
    }

    public String prettyPrintCaller(){
        return " called from number " + getCaller() +  " to number " + getCallee() + " at " + getStartTimeString() + "  and ended call at " + getEndTimeString();
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

