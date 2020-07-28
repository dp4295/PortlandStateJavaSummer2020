package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneCall;

import javax.swing.*;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {

    private String callee;
    private String caller;
    private Date startdatatime;
    private Date enddatetime;

    @Override
    public String getCaller() {
        return null;
    }

    @Override
    public String getCallee() {
        return null;
    }

    @Override
    public String getStartTimeString() {
        return Utility.dateToString(startdatatime) ;
    }

    @Override
    public String getEndTimeString() {
        return Utility.dateToString(enddatetime);
    }

    @Override
    public int compareTo(PhoneCall o) {
        return 0;
    }
}
