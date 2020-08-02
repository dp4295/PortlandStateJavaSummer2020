package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;
import org.w3c.dom.CDATASection;

import java.io.*;
import java.text.ParseException;
import java.util.Date;

public class TextParser implements PhoneBillParser {

    private final String DIR = "deep";
    private final String  FILENAME = "deep.txt";
    private PhoneBill phoneBill;
    private String sd; // Start date
    private String ed; // End date
    public static final String PASS = "pass";
    private String match;


    /**
     *
     * @return PhoneBill object
     * @throws ParserException
     */
    @Override
    public AbstractPhoneBill parse() throws ParserException {

        File file = new File(DIR + "/" + FILENAME);
        String result;
        BufferedReader bufferedReader;

        try{

            bufferedReader = new BufferedReader(new FileReader(file));

            while((result = bufferedReader.readLine()) != null){
                String br[] = result.split(",");

                if(br[0] == null || br[0].trim().equals("")){
                    bufferedReader.close();
                    return phoneBill;
                }

                if(phoneBill == null){

                        phoneBill = new PhoneBill(match);

                }

                String callernumber = br[1];
                String calleenumber = br[2];

//                String datesplit[] = br[3].split(" ", 2);
//                String startdate =  datesplit[0];
//                String starttime = datesplit[1];


//                String datesplit[] = br[3].split(",", 2);
////                String startdate =  datesplit[0];
////                String starttime = datesplit[1];

//                String enddatesplit[] = br[4].split(",", 2);
//                String endtdate = enddatesplit[0];
//                String endtime = enddatesplit[1];


                String start = br[3] + " " +  br[4];
                String end = br[5] + " " + br[6];

                Date startdate = Utility.convertDateFromString(start);
                Date enddate = Utility.convertDateFromString(end);

                PhoneCall phoneCall = new PhoneCall(callernumber, calleenumber, start, end);

                if(phoneBill.getCustomer().equals(br[0])) {
                    phoneBill.addPhoneCall(phoneCall);
                }
            }

               bufferedReader.close();

            return phoneBill;

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
         return null;
    }

    /**
     * Constructor
     * @param phoneBill
     * @param sd
     * @param ed
     */
    public TextParser(PhoneBill phoneBill, String startdate, String enddate)
    {
      this.phoneBill = phoneBill;
      this.sd = startdate;
      this.ed = enddate;
    }

    public TextParser(String match){
        this.match = match;
    }

}
