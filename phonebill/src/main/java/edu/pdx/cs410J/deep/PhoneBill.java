package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Collection;
import java.util.List;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    private final String customer;
    private Collection<PhoneCall> callCollection;

    /**
     * Creates a new <code>PhoneBill</code>
     * @param customer
     * @param callCollection
     *
     */
    public PhoneBill(String customer, Collection<PhoneCall> callCollection) {
        this.customer = customer;
        this.callCollection = callCollection;

    }

    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void addPhoneCall(PhoneCall phonecall) {


    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return callCollection;
    }



}
