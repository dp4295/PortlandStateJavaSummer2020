package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import javax.management.MBeanAttributeInfo;
import java.io.*;


/**
 * This class is read from file and save all the data ino PhoneBill object <code>TextParser</code>
 * @return PhoneBill object return
 */
public class TextParser implements PhoneBillParser {


    String path;
    PhoneBill phonebill;
    String userinput;

    /**
     * @return PhoneBill object
     * @throws ParserException
     */
    @Override
    public AbstractPhoneBill<?> parse() throws ParserException {

        File file = new File(path);
        String result;
        BufferedReader bufferedreader;

        try {


            bufferedreader = new BufferedReader(new FileReader(file));

            while ((result = bufferedreader.readLine()) != null){

               // System.out.println(result);

                String breaks[] = result.split(",");

                if(breaks[0] == null || breaks[0].trim().equals("")) {
                    bufferedreader.close();
                    return phonebill;
                }

                if(breaks.length != 5)
                {
                 //   bufferedreader.close();
                    System.out.println("File is Malformaated");
                    System.exit(1);
                }



                String customername = breaks[0];

                System.out.println(userinput);
                System.out.println(customername);

                if(customername !=  userinput)
                {
                    bufferedreader.close();
                    System.out.println("Customer given in command line is different than the one found in the text file");
                    System.exit(1);
                }

                String callernumber = breaks[1];
                String calleenumber = breaks[2];

               String datesplit[] = breaks[3].split(" ",2);
               String startdate = datesplit[0];
               String starttime = datesplit[1];

               String enddatesplit[] = breaks[4].split(" ", 2 );
               String enddate = enddatesplit[0];
               String endtime = enddatesplit[1];

//               System.out.println(calleenumber);
//               System.out.println(callernumber);
//               System.out.println(startdate);
//               System.out.println(starttime);
//               System.out.println(enddate);
//               System.out.println(endtime);


               PhoneCall call = new PhoneCall(callernumber, calleenumber,startdate, starttime, enddate, endtime);

                phonebill.addPhoneCall(call);
                phonebill.DisplayFromFile();

            }

            bufferedreader.close();
            return phonebill;

        } catch (FileNotFoundException e) {
            e.printStackTrace();


        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }


    /**
     * Constructor
     * @param path  file path
     * @param phonebill  phonebill object
     */

    public TextParser(String path, PhoneBill phonebill, String userinput)
    {
        this.path = path;
        this.phonebill = phonebill;
        this.userinput = userinput;
    }
}