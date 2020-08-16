package edu.pdx.cs410J.deep;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static edu.pdx.cs410J.deep.PhoneBillURLParameters.CALLER_NUMBER_PARAMETER;
import static java.net.HttpURLConnection.HTTP_OK;
import static edu.pdx.cs410J.deep.PhoneBillURLParameters.CUSTOMER_PARAMETER;
import static edu.pdx.cs410J.deep.PhoneBillURLParameters.CALLEE_NUMBER_PARAMETER;
import static edu.pdx.cs410J.deep.PhoneBillURLParameters.START_TIME_PARAMETER;
import static edu.pdx.cs410J.deep.PhoneBillURLParameters.END_TIME_PARAMETER;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient extends HttpRequestHelper {
    private static final String WEB_APP = "phonebill";
    private static final String SERVLET = "calls";

    private final String url;


    /**
     * Creates a client to the Phone Bil REST service running on the given host and port
     *
     * @param hostName The name of the host
     * @param port     The port
     */
    public PhoneBillRestClient(String hostName, int port) {
        this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
    }


    /**
     //     * Returns all dictionary entries from the server
     //     */
    public Map<String, String> getAllDictionaryEntries() throws IOException {
        Response response = get(this.url, Map.of());
        return Messages.parseDictionary(response.getContent());
    }


    /**
     * Return all Phonebill entries from the server
     */
    public Map<String, String> getAllPhoneBillEntries() throws IOException {
        Map<String, String> map = new HashMap<>();
        return map;
    }


    /**
     * Returns the definition for the given word
     */
    public String getDefinition(String word) throws IOException {
        Response response = get(this.url, Map.of("word", word));
        throwExceptionIfNotOkayHttpStatus(response);
        String content = response.getContent();
        return Messages.parseDictionaryEntry(content).getValue();
    }

    /**
     * Get All the PhoneBill entries for the given customer
     */
    public String getPhoneBills(String customer) throws IOException, ParserException {
        // Response response = get(this.url + "?customer=" + customer, Map.of());
        Response response = get(this.url + "?customer=" + customer, Map.of(CUSTOMER_PARAMETER, customer));
      //  Response response = get(this.url , Map.of(CUSTOMER_PARAMETER, customer));
        throwExceptionIfNotOkayHttpStatus(response);

        String content = response.getContent();


       TextParser parser = new TextParser(customer);
        PhoneBill phoneBill = (PhoneBill) parser.parse();

        if(phoneBill == null){
            Messages.noCustomerFound();
            System.exit(0);
        }

       // System.out.println("Searching call detail for " + customer + " where date between " + args[6] + " to " + args[7] + ".......");
        for(PhoneCall phonecall : ((PhoneBill)phoneBill).getPhoneCalls()){

            if(phoneBill.getCustomer().equals(customer)){
               content =  phonecall.printPhoneCall();

            }
        }

        return content;
    }


    /**
     *
     * @param customer
     * @return
     * @throws IOException
     */
    public String getPhoneCalls(String customer) throws IOException{
        Response response = get(this.url, Map.of("customer", customer));
        throwExceptionIfNotOkayHttpStatus(response);
        return response.getContent();
    }

    /**
     *
     * @param customer
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public String searcPhoneCalls(String customer, String start, String end) throws IOException{
        Response response = get(this.url, Map.of("customer", customer, "start",start , "end", end ));
        throwExceptionIfNotOkayHttpStatus(response);
        return response.getContent();
    }

    /**
     *
     * @param customer
     * @param callernumber
     * @param calleenumber
     * @param start
     * @param end
     * @throws IOException
     */
    public void postPhoneCall(String customer, String callernumber, String calleenumber, String start, String end ) throws IOException{
        Response response = postToMyURL(Map.of("customer", customer, "callerNumber", callernumber, "calleeNumber", calleenumber, "start", start, "end", end ));
        throwExceptionIfNotOkayHttpStatus(response);
    }

    @VisibleForTesting
    Response postToMyURL(Map<String, String> dictionaryEntries) throws IOException {
        return post(this.url, dictionaryEntries);
    }

    public void removeAllDictionaryEntries() throws IOException {
        Response response = delete(this.url, Map.of());
        throwExceptionIfNotOkayHttpStatus(response);
    }

    private Response throwExceptionIfNotOkayHttpStatus(Response response) {
        int code = response.getCode();
        if (code != HTTP_OK) {
            //   throw new PhoneBillRestException(code);
        }
        return response;
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }


    @VisibleForTesting
//    class PhoneBillRestException extends RuntimeException {
//      PhoneBillRestException(int httpStatusCode) {
//        super("Got an HTTP Status Code of " + httpStatusCode);
//      }
//    }

    class PhoneBillRestException extends RuntimeException {
        private final int httpStatusCode;

        PhoneBillRestException(int httpStatusCode) {
            super("Got an HTTP Status Code of " + httpStatusCode);
            this.httpStatusCode = httpStatusCode;
        }

        public int getHttpStatusCode() {
            return this.httpStatusCode;
        }

    }

}
