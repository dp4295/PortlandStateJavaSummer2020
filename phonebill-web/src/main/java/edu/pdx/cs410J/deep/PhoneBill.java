package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @Override
    public Collection getPhoneCalls() {

        Collections.sort(callcollection);

        return callcollection;
    }



}
