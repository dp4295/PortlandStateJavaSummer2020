package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    private String customer;
    List<PhoneCall> callcollection;

    /**
     * Creates a new <code>PhoneBill</code>
     * @param customer    customer name
     */
    public PhoneBill(String customer) {
        this.customer = customer;
        callcollection = new ArrayList<PhoneCall>();
    }

    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void addPhoneCall(PhoneCall call) {
        callcollection.add(call);
    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return null;
    }


//    public void addCall(AbstractPhoneCall callcollection) {
//        callcollection.add((PhoneCall) callcollection);
//      }

      public void setCustomer(String customer)
      {
          this.customer = customer;
      }


//    @Override
//    public List<PhoneCall> getPhoneCalls() {
//        return callcollection;
//    }

}
