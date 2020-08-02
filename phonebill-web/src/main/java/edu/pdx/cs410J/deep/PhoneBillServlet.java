package edu.pdx.cs410J.deep;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

//import static edu.pdx.cs410J.deep.Project4.CUSTOMER_PARAMETER;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class PhoneBillServlet extends HttpServlet
{
    public static final String PASS = "pass";
    static final String CUSTOMER_PARAMETER = "customer";
    static final String CALLER_NUMBER_PARAMETER = "callernumber";
    static final String CALLEE_NUMBER_PARAMETER  = "calleenumber";
    static final String START_DATE_PARAMETER  = "startdate";
    static final String START_TIME_PARAMETER= "starttime";
    static final String START_AMPM_PARAMETER = "startampm";
    static final String END_DATE_PARAMETER = "enddate";
    static final String END_TIME_PARAMETER = "endtime";
    static final String END_AMPM_PARAMETER = "endampm";


    private final Map<String, PhoneBill> phonebillMap = new HashMap<String, PhoneBill>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        PrintWriter pw = response.getWriter();

        String customer = getParameter(CUSTOMER_PARAMETER, request );
        String startdate  = getParameter(START_DATE_PARAMETER, request);
        String enddate = getParameter(END_DATE_PARAMETER, request);
        String decision ;




        decision = Utility.checkCustomer(customer);
        if(decision != PASS){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, Messages.nullOrEmptyCustomerForPhoneBill());
        }

        if((startdate == null && enddate != null) || (startdate != null && enddate == null)){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, Messages.invalidDate());
           return;
        }


        if(startdate != null && enddate != null && !startdate.isEmpty() && !enddate.isEmpty())
        {

            // Check if start date is valid date
            decision = Utility.dateValid(startdate);
            if(decision != PASS){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, Messages.dateISnotValid());
            }

            // Check if end date is valid date
            decision = Utility.dateValid(enddate);
            if(decision != PASS){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, Messages.dateISnotValid());
            }


            // Check if the customer is in the list
            PhoneBill phoneBill = phonebillMap.get(customer);
            if(phoneBill == null){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, Messages.noCustomerFound());
                return;
            }


            List<PhoneCall> phoneCallList = phoneBill.getPhoneCalls();

            PhoneBill copy = new PhoneBill(phoneBill.getCustomer());
            List<PhoneCall> temp = new ArrayList<>();
            for(int i=0; i<phoneCallList.size(); ++i){

                // Later you we only copy PhoneCall if it fall between start and end date
              temp.add(phoneCallList.get(i));
            }

            if(temp.size() == 0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND, Messages.noPhoneCallFoundBetweenThisDate());
            }

            copy.setCallcollection(temp);

            TextDumper dumper = new TextDumper(pw);
            dumper.dump(copy);
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            getPhonebill(customer, response);
        }

//        if (customer == null) {
//            missingRequiredParameter(response, CUSTOMER_PARAMETER);
//        }else {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, Messages.noPhoneBillForCustomer(customer));
//        }


    }


//    /**
//     * Handles an HTTP POST request by storing the dictionary entry for the
//     * "word" and "definition" request parameters.  It writes the dictionary
//     * entry to the HTTP response.
//     */
//    @Override
//    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
//    {
//        response.setContentType( "text/plain" );
//
//        String customer = getParameter(CUSTOMER_PARAMETER, request );
//        String callernumber = getParameter(CALLER_NUMBER_PARAMETER, request);
//
//        if (customer == null) {
//            missingRequiredParameter(response, CUSTOMER_PARAMETER);
//            return;
//        }
//
//        String definition = getParameter(DEFINITION_PARAMETER, request );
//        if ( definition == null) {
//            missingRequiredParameter( response, DEFINITION_PARAMETER );
//            return;
//        }
//
//        this.dictionary.put(word, definition);
//
//        PrintWriter pw = response.getWriter();
//        pw.println(Messages.definedWordAs(word, definition));
//        pw.flush();
//
//        response.setStatus( HttpServletResponse.SC_OK);
//    }


    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String customer = getParameter(CUSTOMER_PARAMETER, request );
        String callernumber = getParameter(CALLER_NUMBER_PARAMETER, request);
        String calleenumber = getParameter(CALLEE_NUMBER_PARAMETER, request);
        String startdate = getParameter(START_DATE_PARAMETER, request);
        String starttime = getParameter(START_TIME_PARAMETER, request);
        String startampm = getParameter(START_AMPM_PARAMETER, request);
        String enddate = getParameter(END_DATE_PARAMETER, request);
        String endtime = getParameter(END_TIME_PARAMETER, request);
        String endampm = getParameter(END_AMPM_PARAMETER, request);



        // Validating input value
        String decision;

        decision = Utility.checkCustomer(customer);
        if(!(decision.equals(PASS)))
        {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.nullOrEmptyCustomerForPhoneBill());
        }

        decision = Utility.checkInvalidPhoneNumber(callernumber);
        if(!(decision.equals(PASS))) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.invalidNumber());
        }

        decision = Utility.checkInvalidPhoneNumber(calleenumber);
        if(!(decision.equals(PASS)))
        {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.invalidNumber());
        }

        decision = Utility.checkInvalidDateAndTimeFormat(startdate, starttime, startampm);
        if(!(decision.equals(PASS))){
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.invalidDate());
        }

        decision = Utility.checkInvalidDateAndTimeFormat(enddate, endtime, endampm);
        if(!(decision.equals(PASS))){
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.invalidDate());
        }
        // End of validation check

       List<PhoneCall> phoneCallsList;
        PhoneBill phoneBill;

        if(phonebillMap.containsKey(customer)){
            phoneBill = phonebillMap.get(customer);
            phoneCallsList = phoneBill.getPhoneCalls();
        }else {
            phoneBill = new PhoneBill(customer);
            phoneCallsList= new ArrayList<PhoneCall>();
        }


        try {
            String start= startdate+ " "+ starttime + " " + startampm  ;
            String end  = enddate + " " + endtime + " "+ endampm;
            PhoneCall phoneCall  = new PhoneCall(callernumber, calleenumber, start, end);
            phoneCallsList.add(phoneCall);
            phoneBill.setCallcollection(phoneCallsList);
            phonebillMap.put(customer, phoneBill);

            response.setStatus( HttpServletResponse.SC_OK);
            PrintWriter pw = response.getWriter();
            TextDumper dumper = new TextDumper(pw);
            dumper.dump(phoneBill);
            pw.close();

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }




//    /**
//     * Handles an HTTP DELETE request by removing all dictionary entries.  This
//     * behavior is exposed for testing purposes only.  It's probably not
//     * something that you'd want a real application to expose.
//     */
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/plain");
//
//        this.dictionary.clear();
//
//        PrintWriter pw = response.getWriter();
//        pw.println(Messages.allDictionaryEntriesDeleted());
//        pw.flush();
//
//        response.setStatus(HttpServletResponse.SC_OK);
//
//    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

//    /**
//     * Writes the definition of the given word to the HTTP response.
//     *
//     * The text of the message is formatted with
//     * {@link Messages#formatDictionaryEntry(String, String)}
//     */
//    private void writeDefinition(String word, HttpServletResponse response) throws IOException {
//        String definition = this.dictionary.get(word);
//
//        if (definition == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//
//        } else {
//            PrintWriter pw = response.getWriter();
//            pw.println(Messages.formatDictionaryEntry(word, definition));
//
//            pw.flush();
//
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//    }

//    /**
//     * Writes all of the dictionary entries to the HTTP response.
//     *
//     * The text of the message is formatted with
//     * {@link Messages#formatDictionaryEntry(String, String)}
//     */
//    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
//    {
//        PrintWriter pw = response.getWriter();
//        Messages.formatDictionaryEntries(pw, dictionary);
//
//        pw.flush();
//
//        response.setStatus( HttpServletResponse.SC_OK );
//    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private void getPhonebill(String name, HttpServletResponse response) throws IOException {

        PhoneBill phoneBill = phonebillMap.get(name);

        if(phoneBill == null){
            return;
        }
        PrintWriter pw = response.getWriter();
        TextDumper dumper = new TextDumper(pw);
        dumper.dump(phoneBill);
        response.setStatus(HttpServletResponse.SC_OK);
    }



    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is <code>null</code>
     *         or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value))
            return null;
        else
            return value;
    }


    @VisibleForTesting
    PhoneBill getDefinition(String phonebill) {
        return this.phonebillMap.get(phonebill);
    }

}
