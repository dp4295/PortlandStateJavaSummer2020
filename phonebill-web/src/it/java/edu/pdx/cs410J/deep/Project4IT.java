package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.deep.PhoneBillRestClient.PhoneBillRestException;
import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    public void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }


    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }

    @Test
    public void checkInvalidNumberofArg(){
        MainMethodResult result = invokeMain( Project4.class,"rrr", "bbbb", "aaa", "dddd", "wwww" , "qqq" , "rrrr", "yyyy", "ddddd", "uuuuu", "uuuuu", "1-12-2020", "1:30", "am", "1-12-2020", "1:50");
        assertThat(result.getTextWrittenToStandardError(), containsString("** Extra argument found"));

    }

    @Test
    public void checkCallerPhoneNumberTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-xxx-3333", "503-333-2222", "1/12/2020", "1:30", "am", "1/12/2020", "1:50", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidNumber()));
    }

    @Test
    public void checkCalleePhoneNumberTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "SSS-333-2222", "1/12/2020", "1:30", "am", "1/12/2020", "1:50", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidNumber()));
    }

    @Test
    public void checkStartDateMM_dd_yyyyTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "999-333-2222", "1/ZZ/2020", "1:30", "am", "1/12/2020", "1:50", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidDate()));
    }

    @Test
    public void checkendDateMM_dd_yyyyTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "999-333-2222", "1/12/2020", "1:30", "am", "X/12/2020", "1:50", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidDate()));
    }

    @Test
    public void checkstartTimeHHmmTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "999-333-2222", "1/12/2020", "zz:00", "am", "1/12/2020", "1:00", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidDate()));
    }

    @Test
    public void checkendTimeHHmmTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "999-333-2222", "1/12/2020", "12:00", "am", "1/12/2020", "xx:00", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidDate()));
    }

    @Test
    public void checkstartTimeampmTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "999-333-2222", "1/12/2020", "12:00", "xx", "1/12/2020", "xx:00", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidDate()));
    }

    @Test
    public void checkendTimeampmTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" , "Deep", "503-555-3333", "999-333-2222", "1/12/2020", "12:00", "am", "1/12/2020", "xx:00", "xx");
        assertThat(result.getTextWrittenToStandardError(), containsString(Messages.invalidDate()));
    }


    @Test
    public void checkPort(){
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "sss" , "Deep", "503-555-3333", "999-333-2222", "1/12/2020", "12:00", "am", "1/12/2020", "12:30", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString("** not valid port"));
    }





    @Ignore
    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatWordCount(0)));
    }


    @Ignore
    @Test(expected = PhoneBillRestException.class)
    public void test3NoDefinitionsThrowsAppointmentBookRestException() throws Throwable {
        String word = "WORD";
        try {
            invokeMain(Project4.class, HOSTNAME, PORT, word);

        } catch (UncaughtExceptionInMain ex) {
            throw ex.getCause();
        }
    }


    @Ignore
    @Test
    public void test4AddDefinition() {
        String word = "WORD";
        String definition = "DEFINITION";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, word, definition );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT, word );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));
    }






}