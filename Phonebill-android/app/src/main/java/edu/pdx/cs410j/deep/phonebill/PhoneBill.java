package edu.pdx.cs410j.deep.phonebill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneBill extends AbstractPhoneBill {

    private String customer;
    List<PhoneCall> callcollection;

    /**
     * Constructor
     * Create a new <code>PhoneBill</code>
     * @param customer customer name
     */
    public PhoneBill(String customer)
    {
        this.customer = customer;
        callcollection = new ArrayList<PhoneCall>();
    }


    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        callcollection.add((PhoneCall) call);
    }

    public void setPhoneCall(List<PhoneCall> phoneCall) { this.callcollection =phoneCall;}


    @Override
    public List<PhoneCall> getPhoneCalls() {

        Collections.sort(callcollection);

        return callcollection;
    }

    public void setCallcollection(List<PhoneCall> phoneCalls){
        this.callcollection = phoneCalls;
    }




}
