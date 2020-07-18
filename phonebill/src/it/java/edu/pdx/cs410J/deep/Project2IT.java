package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project2.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void NoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("No command line argument found"));
  }


    @Test
    public void oneComandlineArgument(){
        MainMethodResult result = invokeMain("-README");
        assertThat(result.getExitCode(),equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(),containsString("Missing customer name, caller number, callee number, start date, start time, end date and end time"));
    }

    @Test
    public void twoCommnadlineArgument(){
        MainMethodResult result = invokeMain("-README", "Deep");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(),containsString("Invalid command line arguments"));
    }


    @Test
    public void nineCommandlineArgument(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file.txt" , "Deep", "123-123-1234", "123-123-1111", "1/7/2020", "12:34", "1/7/2020", "12:45");
        //  assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Congradulation, You meet MAXIMUM require arguments."));
    }



    @Test
    public void tenCommnadlineArgument(){
        MainMethodResult result = invokeMain("-textFile","path", "Deep", "123-123-123", "123-123-1234","1/7/2020", "1:23", "1/7/2020", "1:30", "Extra");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Extra arguments"));
    }



    @Test
    public void threeCommandlineArgument(){
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid command line arguments"));
    }

    @Test
    public void fourCommandlineArgument(){
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234", "123-123-1111");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid command line arguments"));
    }

    @Test
    public void fiveCommandlineArgument(){
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234", "123-123-1111", "7/12/2020");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid command line arguments"));
    }

    @Test
    public void sixCommandlineArgument(){
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234", "123-123-1111", "7/12/2020", "12:00");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid command line arguments"));


    }


    @Test
    public void sevenCommandlineArgument(){
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234", "123-123-1111", "7/12/2020", "12:00", "7/12/2020");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid arguments.It should not have any -print, -README or -textFile"));
    }

    @Test
    public void eightCommandLineArgument()
    {
        MainMethodResult result = invokeMain("-printrr", "Deep", "123-123-1234", "123-123-1111", "7/12/2020", "12:00", "7/12/2020", "12:34");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Missing -print option"));
    }


    @Test
    public void invalidPathTest(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/cs410J/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Something is wrong, can not create file this time. Check the path"));
    }


    @Test
    public void fileNotCreated(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Something is wrong, can not create file this time. Check the path"));
    }

    @Test
    public void fileCreated(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file1.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("File created"));
    }


    @Test
    public void fileAlreadyexit(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file1.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("File already exist"));
    }

    @Test
    public void writingToNewfile(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Write out into file successfully"));
    }


    @Test
    public void writingToNewfileNotPossible(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file.trt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Something is wrong, can not create file this time. Check the path"));
    }










//  @Test
//    public void testExtraCommandLineArguments()
//  {
//      MainMethodResult result = invokeMain(new String[] {"-print", "123-345-12324", "122-123-1234","7/7/2020 10:44", "7/8/2002 10:50", "extra"});
//          assertThat(result.getExitCode(), equalTo(1));
//          assertThat(result.getTextWrittenToStandardOut(), containsString("Too many arguments"));
//  }


}