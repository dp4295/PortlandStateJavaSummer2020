package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;
import org.w3c.dom.CDATASection;
import java.io.Reader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TextParser implements PhoneBillParser {

    private final String DIR = "deep";
    private final String  FILENAME = "deep.txt";
    private PhoneBill phoneBill;
    private String sd; // Start date
    private String ed; // End date
    public static final String PASS = "pass";
    private String match;
    private Reader reader;
    private String startsearch;
    private String endsearch;

    /**
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



                String start = br[3] + " " +  br[4];
                String end = br[5] + " " + br[6];



                PhoneCall phoneCall = new PhoneCall(callernumber, calleenumber, start, end);

                if(phoneBill.getCustomer().equals(br[0])) {
                    phoneBill.addPhoneCall(phoneCall);
                }
            }

               bufferedReader.close();

            return phoneBill;

        }catch (IOException | ParseException e) {
            e.printStackTrace();
            throw  new ParserException("While parsing", e);
        }
        // return null;
    }




    public AbstractPhoneBill parseForSearch() throws ParserException {

        File file1 = new File(DIR + "/" + FILENAME);

        String result;
        BufferedReader bufferedReader;

        try{

            bufferedReader = new BufferedReader(new FileReader(file1));

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


                String start = br[3] + " " +  br[4];
                String end = br[5] + " " + br[6];

                Date startdate = Utility.convertDateFromString(start);
                Date enddate = Utility.convertDateFromString(end);

                Date parsed_ss = Utility.covertDateFromStringMM_dd_yyyy(br[3] + " 00:00 am");
                Date parsed_es = Utility.covertDateFromStringMM_dd_yyyy(br[5] + " 00:00 am");

                Date ss = Utility.covertDateFromStringMM_dd_yyyy(startsearch + " 00:00 am");
                Date es = Utility.covertDateFromStringMM_dd_yyyy(endsearch+ " 00:00 am");


                if(ss.compareTo(parsed_ss) >= 0 && ss.compareTo(parsed_es) <= 0 || es.compareTo(parsed_ss) >= 0 && es.compareTo(parsed_es) <=0)
                {
                    PhoneCall phoneCall = new PhoneCall(callernumber, calleenumber, start, end);

                    if(phoneBill.getCustomer().equals(br[0])) {
                        phoneBill.addPhoneCall(phoneCall);
                    }

                }

            }

            bufferedReader.close();

            return phoneBill;

        }catch (IOException | ParseException e) {
            e.printStackTrace();
            throw  new ParserException("While parsing", e);
        }


    }




    /**
     * Constructor
     * @param phoneBill
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

    public TextParser(String match, String start, String end){
        this.match = match;
        this.startsearch = start;
        this.endsearch = end;
    }

    public TextParser(Reader reader){
        this.reader = reader;
    }

}
