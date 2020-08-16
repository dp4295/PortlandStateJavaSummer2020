package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
//import picocli.CommandLine;

import java.io.*;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;


/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";
    public static final String PASS = "pass";
    public static final String GETPHONECALL = "GETPHONECALL";
    public static final String GETPHONECALLCRITERIA = "GETPHONECALLCRITERIA";
    public static final String POSTPHONECALL = "POSTPHONECALL";
    public static final String POSTANDPRINTPHONECALL = "POSTANDPRINTPHONECALL";
    public static final String SEARCHANDPRINTPHONECALL = "SEARCHANDPRINTPHONECALL";
    public static final String SEARCH = "SEARCH";
    public static final String SEARCHBYCUSTOMERNAME= "SEARCHBYCUSTOMERNAME";

    public static final String CUSTOMER_PARAMETER = "customer";


    public static void main(String... args) throws IOException, ParseException {
        String hostName = null;
        String portString = null;
        String word = null;
        String definition = null;

        String result;
        String action = null;

        boolean printme = false;
        boolean searchme = false;
        boolean launch = false;

        Set<String> options = new HashSet<>();
        // Available option
        options.add("-README");
        options.add("-search");
        options.add("-print");
        options.add("-port");
        options.add("-host");


        if (args.length == 0) {
            error(Messages.NoArgument());
        }

        if (args.length > 14) {
            error(Messages.ExtraArgument());
        }


        int index = 0;
        for (int i = 0; i < args.length; i++) {
            String str = args[i];

            if (str.equals("-README")) {

                InputStream readme = Project4.class.getResourceAsStream("README.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
                // BufferedReader reader = new BufferedReader(new FileReader("deep/README.txt"));
                String line = reader.readLine();

                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }

                System.exit(1);
            } else if (str.equals("-print")) {


                printme = true;
                launch = true;

            } else if (str.equals("-search")) {
                launch = true;
                searchme = true;
            } else if (str.equals("-port")) {
                if (i + 1 >= args.length) {
                    error("No port number");
                }

                portString = args[i + 1];

                result = Utility.portValid(portString);
                if (!(result.equals(PASS))) {
                    error(result);
                }
                ++i;
                launch = true;
            } else if (str.equals("-host")) {
                if (i + 1 >= args.length) {
                    error("No host name");
                }
                hostName = args[i + 1];
                i++;
                launch = true;
            } else {
                if (index > 14) {
                    error(Messages.ExtraArgument());
                    args[index] = str;
                    index++;
                }
            }
        }


//        if(searchme && index !=8){
//            error("-search require customer name start date and end date");
//        }

        if (launch && (portString == null) || hostName == null) {
            error("Host name and Port require");
        }


        /** Start data validation **/
        String message = null;
        if (args.length == 13 && launch) {

            message = Utility.checkInvalidPhoneNumber(args[5]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidNumber());
            }
            message = Utility.checkInvalidPhoneNumber(args[6]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidNumber());
            }

            message = Utility.checkInvalidDateAndTimeFormat(args[7], args[8], args[9]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidDate());
            }
            message = Utility.checkInvalidDateAndTimeFormat(args[10], args[11], args[12]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidDate());
            }

            if (launch) {
                action = POSTPHONECALL;
            }

        } else if (args.length == 5 && launch) {
            action = GETPHONECALL;
        } else if (args.length == 14 && printme && launch) {
            action = POSTANDPRINTPHONECALL;
        } else if (args.length == 8 && launch) {

            message = Utility.checkInvalidDate(args[6]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidDate());
            }
            message = Utility.checkInvalidDate(args[7]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidDate());
            }

            message = Utility.checkForEndDateBeforeStartdate(args[6], args[7]);
            if (!(message.equals(PASS))) {
                error(Messages.DateEndBeforeStart());
            }


            action = SEARCHANDPRINTPHONECALL;
        } else if (launch && searchme && args.length == 12) {

            message = Utility.checkInvalidDate(args[6]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidDate());
            }
            message = Utility.checkInvalidDate(args[9]);
            if (!(message.equals(PASS))) {
                error(Messages.invalidDate());
            }

            message = Utility.checkForEndDateBeforeStartdate(args[6], args[9]);
            if (!(message.equals(PASS))) {
                error(Messages.DateEndBeforeStart());
            }
            action = SEARCH;
        } else if (args.length > 14) {
            error(Messages.ExtraArgument());
        }
        /** End of data validation **/


        if (launch) {

            PhoneBillRestClient RestClient = new PhoneBillRestClient(hostName, Integer.parseInt(portString));
            String PhoneBillDumpString;
            PhoneBill phoneBill;
            TextDumper dumper;
            TextParser parser;
            String parserString;


            try {

                switch (action) {
                    case POSTPHONECALL:
                        RestClient.postPhoneCall(args[4], args[5], args[6], args[7] + " " + args[8] + " " + args[9], args[10] + " " + args[11] + " " + args[12]);
                        //RestClient.addPhoneCall(args);

                        PhoneBill phonebill = new PhoneBill(args[4]);
                        PhoneCall phoneCall = new PhoneCall(args[5], args[6], args[7] + " " + args[8] + " " + args[9], args[10] + " " + args[11] + " " + args[12]);

                        phonebill.addPhoneCall(phoneCall);

//                        StringWriter stringWriter = new StringWriter();
//                        PrintWriter pw = new PrintWriter(stringWriter);
                        TextDumper textDumper = new TextDumper(args[4]);
                        textDumper.dump(phonebill);

                        System.out.println("Phone call is added");
                        System.exit(0);

                    case GETPHONECALL:

                        try {
                            parserString = RestClient.getPhoneCalls(args[4]);
                            System.out.println(parserString);
                            System.exit(0);
                        } catch (IOException | PhoneBillRestClient.PhoneBillRestException err) {
                            System.err.println(err.getMessage());
                            System.exit(1);
                        }


                        System.exit(0);

                    case POSTANDPRINTPHONECALL:

                        phoneBill = new PhoneBill(args[5]);
                        phoneCall = new PhoneCall(args[6], args[7], args[8] + " " + args[9] + " " + args[10], args[11] + " " + args[12] + " " + args[13]);

                        phoneBill.addPhoneCall(phoneCall);

                        textDumper = new TextDumper(args[5]);
                        textDumper.dump(phoneBill);

                        System.out.println("Phone call is added");
                        System.out.println(phoneCall.prettyPrintCaller(phoneBill));


                        System.exit(0);

                    case SEARCHANDPRINTPHONECALL:

                        try {
                            String res = RestClient.searcPhoneCalls(args[5], args[6], args[7]);
                            System.out.println(res);
                            System.exit(0);
                        } catch (IOException | PhoneBillRestClient.PhoneBillRestException err) {
                            System.err.println(err.getMessage());
                            System.exit(1);
                        }

//                        parser = new TextParser(args[5], args[6], args[7]);
//                        phoneBill = (PhoneBill) parser.parse();
//
//                        if (phoneBill == null) {
//                            Messages.noCustomerFound();
//                            System.exit(0);
//                        }
//
//                        System.out.println("Searching call detail for " + args[5] + " where date between " + args[6] + " to " + args[7] + ".......");
//                        for (PhoneCall phonecall : ((PhoneBill) phoneBill).getPhoneCalls()) {
//
//                            //    if(phoneBill.getCustomer().equals(args[5]))
//                            if ((phoneBill.getCustomer().equals(args[5])) && (phonecall.getStartTimeString().compareTo(phonecall.getEndTimeString()) >= 0) && (phonecall.getEndTimeString().compareTo(phonecall.getStartTimeString()) >= 0)) {
//                                System.out.println(phonecall.printPhoneCall());
//
//                            }
//                        }

                        System.exit(0);

                    case SEARCH:

                     
                        try {
                            String res = RestClient.searcPhoneCalls(args[5], args[6], args[7]);
                            System.out.println(res);
                            System.exit(0);
                        } catch (IOException | PhoneBillRestClient.PhoneBillRestException err) {
                            System.err.println(err.getMessage());
                            System.exit(1);
                        }

                        System.exit(0);


//                        parserString = RestClient.getPhoneBills(args[5]);
//                        if(parserString == null || parserString.isEmpty()){
//                            Messages.noCustomerFound();
//                            System.exit(0);
//                        }

//                        parser = new TextParser(args[5], args[6], args[9]);
//                        phoneBill = (PhoneBill) parser.parse();
//
//                        if (phoneBill == null) {
//                            Messages.noCustomerFound();
//                            System.exit(0);
//                        }
//
//                        System.out.println("Searching call detail for " + args[5] + " where date between " + args[6] + " to " + args[9] + ".......");
//                        for (PhoneCall phonecall : ((PhoneBill) phoneBill).getPhoneCalls()) {
//
//                            if ((phoneBill.getCustomer().equals(args[5])) && (phonecall.getStartTimeString().compareTo(phonecall.getEndTimeString()) >= 0) && (phonecall.getEndTimeString().compareTo(phonecall.getStartTimeString()) >= 0)) {
//                                System.out.println(phonecall.printPhoneCall());
//                            }
//
//                            System.exit(0);
//
//                        }


//            catch (IOException | ParserException | ParseException e){
//              error("Error while connecting server");
//            }


                }
            }
            catch (IOException | ParseException e){
              error("Error while connecting server");
            }

                System.exit(0);
            }


//            catch(ParserException e){
//            e.printStackTrace();
//        }
        }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getCode(), response.getContent()));
        }
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [word] [definition]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  word         Word in dictionary");
        err.println("  definition   Definition of word");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no definition is specified, then the word's definition");
        err.println("is printed.");
        err.println("If no word is specified, all dictionary entries are printed");
        err.println();

        System.exit(1);
    }



}