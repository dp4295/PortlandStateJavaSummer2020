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


//            result = bufferedreader.readLine();
//            if(result != null)
//            {
//                String breaks[] = result.split(",");
//                if(breaks[0].equals(userinput))
//                {
//                    System.out.println("Customer given in command line is different than the one found in the text file");
//
//                    System.exit(1);
//                }
//            }

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

//                System.out.println(customername);
//                System.out.println(userinput);
//
                if(phonebill != null && !phonebill.getCustomer().equalsIgnoreCase(breaks[0]))
                {
                    bufferedreader.close();
                    System.out.println("Customer given in command line is different than the one found in the text file");
                   // throw new ParserException("");
                    System.exit(1);
                }

                if(phonebill == null){
                    phonebill = new PhoneBill(breaks[0]);
                }
                String callernumber = breaks[1];
                String calleenumber = breaks[2];

               String datesplit[] = breaks[3].split(" ",2);
               String startdate = datesplit[0];
               String starttime = datesplit[1];

               String enddatesplit[] = breaks[4].split(" ", 2 );
               String enddate = enddatesplit[0];
               String endtime = enddatesplit[1];

            }

            bufferedreader.close();
            phonebill.DisplayFromFile();

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

    public TextParser(String filename, String userinput)
    {
        this.path = filename;
        this.userinput = userinput;
    }

    public TextParser(String filename, PhoneBill phonebill)
    {
        this.path = filename;
        this.phonebill = phonebill;
    }
}