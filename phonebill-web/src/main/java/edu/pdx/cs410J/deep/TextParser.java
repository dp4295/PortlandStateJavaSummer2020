package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;
import org.w3c.dom.CDATASection;
import java.io.Reader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TextParser implements PhoneBillParser<AbstractPhoneBill> {


    private String  filename;
    private PhoneBill phoneBill;
    private String sd; // Start date
    private String ed; // End date
    public static final String PASS = "pass";
    private String match;
    private Reader reader;
    private String startsearch;
    private String endsearch;



    TextParser(String filename){
        this.filename = filename;
    }


    /**
     * @return PhoneBill object
     * @throws ParserException
     */
    @Override
    public AbstractPhoneBill parse() throws ParserException {

        InputStream read_from_file;
        BufferedReader bufferedReader;


        String result;


            try {
                read_from_file = new FileInputStream(this.filename);
                bufferedReader = new BufferedReader(new InputStreamReader(read_from_file));

                String readline = bufferedReader.readLine();
                if (readline == null) {
                    throw new ParserException("No data");
                }
                String customer = readline;
                //creating phoneBill object
                phoneBill = new PhoneBill(customer, new ArrayList<>());

                readline = bufferedReader.readLine();
                while (readline != null) {
                    PhoneCall phoneCall;

                    String callernumber = readline;

                    readline = bufferedReader.readLine();
                    if (readline == null || readline.equals("")) {
                        throw new ParserException("File Malformed");
                    }
                    String calleenumber = readline;


                    readline = bufferedReader.readLine();
                    if (readline == null || readline.equals("")) {
                        throw new ParserException("File Malformed");
                    }
                    String br[] = readline.split(",");
                    String start = br[0] + " " + br[1];




                    readline = bufferedReader.readLine();
                    if (readline == null || readline.equals("")) {
                        throw new ParserException("File Malformed");
                    }
                    String ebr[] = readline.split(",");
                    String end = ebr[0] + " " + ebr[1];

                    phoneCall = new PhoneCall(callernumber, calleenumber, start, end);
                    this.phoneBill.addPhoneCall(phoneCall);

                    readline = bufferedReader.readLine();
                }
                return this.phoneBill;

            } catch (Exception e) {
                File file = new File(this.filename);
                if (!file.exists()) {
                    try {
                        String br[] = this.filename.split("/");
                        int len = br.length;
                        if (len == 2) {
                            File file1 = new File(br[0]);
                            if (!file1.exists()) {
                                if (file1.mkdir()) {
                                    System.out.println((br[0] + " File is created"));
                                } else {
                                    System.out.println(br[0] + " File can not be created");
                                }
                            }
                        }
                        file.createNewFile();
                    } catch (IOException err) {
                        throw new ParserException(err.getMessage());
                    }
                    throw new ParserException("File is created");


                } else {
                    throw new ParserException(e.getMessage());
                }
            }

        }


//            while((result = bufferedReader.readLine()) != null){
//                String br[] = result.split(",");
//
//                if(br[0] == null || br[0].trim().equals("")){
//                    bufferedReader.close();
//                    return phoneBill;
//                }
//
//                if(phoneBill == null){
//
//                        phoneBill = new PhoneBill(match);
//
//                }
//
//                String callernumber = br[1];
//                String calleenumber = br[2];
//
//
//
//                String start = br[3] + " " +  br[4];
//                String end = br[5] + " " + br[6];
//
//
//
//                PhoneCall phoneCall = new PhoneCall(callernumber, calleenumber, start, end);
//
//                if(phoneBill.getCustomer().equals(br[0])) {
//                    phoneBill.addPhoneCall(phoneCall);
//                }
//            }
//
//               bufferedReader.close();
//
//            return phoneBill;
//
//        }catch (IOException | ParseException e) {
//            e.printStackTrace();
//            throw  new ParserException("While parsing", e);
//        }
//        // return null;
//    }




//    public AbstractPhoneBill parseForSearch() throws ParserException {
//
//        File file1 = new File(DIR + "/" + FILENAME);
//
//        String result;
//        BufferedReader bufferedReader;
//
//        try{
//
//            bufferedReader = new BufferedReader(new FileReader(file1));
//
//            while((result = bufferedReader.readLine()) != null){
//                String br[] = result.split(",");
//
//                if(br[0] == null || br[0].trim().equals("")){
//                    bufferedReader.close();
//                    return phoneBill;
//                }
//
//                if(phoneBill == null){
//
//                    phoneBill = new PhoneBill(match);
//
//                }
//
//                String callernumber = br[1];
//                String calleenumber = br[2];
//
//
//                String start = br[3] + " " +  br[4];
//                String end = br[5] + " " + br[6];
//
//                Date startdate = Utility.convertDateFromString(start);
//                Date enddate = Utility.convertDateFromString(end);
//
//                Date parsed_ss = Utility.covertDateFromStringMM_dd_yyyy(br[3] + " 00:00 am");
//                Date parsed_es = Utility.covertDateFromStringMM_dd_yyyy(br[5] + " 00:00 am");
//
//
//                // User input
//                Date ss = Utility.covertDateFromStringMM_dd_yyyy(startsearch + " 00:00 am");
//                Date es = Utility.covertDateFromStringMM_dd_yyyy(endsearch+ " 00:00 am");
//
//
//                if(ss.compareTo(parsed_ss) >= 0 || ss.compareTo(parsed_es) <= 0 || es.compareTo(parsed_ss) >= 0 || es.compareTo(parsed_es) <=0)
//                {
//                    PhoneCall phoneCall = new PhoneCall(callernumber, calleenumber, start, end);
//
//                    if(phoneBill.getCustomer().equals(br[0])) {
//                        phoneBill.addPhoneCall(phoneCall);
//                    }
//
//                }
//
//            }
//
//            bufferedReader.close();
//
//            return phoneBill;
//
//        }catch (IOException | ParseException e) {
//            e.printStackTrace();
//            throw  new ParserException("While parsing", e);
//        }
//
//
//    }




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

//    public TextParser(String match){
//        this.match = match;
//    }

    public TextParser(String match, String start, String end){
        this.match = match;
        this.startsearch = start;
        this.endsearch = end;
    }

    public TextParser(Reader reader){
        this.reader = reader;
    }

}
