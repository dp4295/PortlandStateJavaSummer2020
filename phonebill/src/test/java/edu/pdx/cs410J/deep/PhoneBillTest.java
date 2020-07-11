package edu.pdx.cs410J.deep;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class PhoneBillTest {


    @Test
    public void customerNameShouldNotEmpty(){
        PhoneBill phonebill = new PhoneBill("Deep");
        assertThat(phonebill.getCustomer(), is(not("")));
    }

    @Test
    public void phonebillCanAddPhonecall(){
        PhoneBill phonebill = new PhoneBill("Deep Pate");
        PhoneCall phonecall = new PhoneCall("555-555-5555", "666-666-6666", "6/7/2020", "12:00", "6/7/2020", "12:30");
        phonebill.addPhoneCall(phonecall);
        assertThat(phonebill.getPhoneCalls().size(), equalTo(1));
    }

    @Test
    public void customerNameCanincludeNumber() {
       PhoneBill phonebill = new PhoneBill("Deep Pa2r3");
        Pattern p = Pattern.compile("([A-Z].*[a-z].*[0-9])");
        Matcher m = p.matcher(phonebill.getCustomer());
        Boolean result = m.find();
        assertThat(phonebill.getCustomer(), result);


    }

}
