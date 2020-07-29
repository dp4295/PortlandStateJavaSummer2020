package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project3IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project3.class, args );
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
        assertThat(result.getTextWrittenToStandardOut(),containsString("Valid options are only -README, - print , -textFile"));
    }

    @Test
    public void twoCommnadlineArgument(){
        MainMethodResult result = invokeMain("-README", "Deep");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(),containsString("Invalid command line arguments"));
    }


    @Test
    public void nineCommandlineArgument(){
        MainMethodResult result = invokeMain("-textFile", "deep/file.txt" , "Deep", "123-123-1234", "123-123-1111", "1/7/2020", "12:34", "am", "1/7/2020", "12:45", "am");
        //  assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Passed eleven arguments"));
    }



//    @Test
//    public void tenCommnadlineArgument(){
//        MainMethodResult result = invokeMain("-textFile","path", "Deep", "123-123-123", "123-123-1234","1/7/2020", "1:23", "1/7/2020", "1:30", "Extra");
//        assertThat(result.getExitCode(), equalTo(1));
//        assertThat(result.getTextWrittenToStandardOut(), containsString("Extra arguments"));
//    }



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
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234", "123-123-1111", "7/12/2020", "12:00", "am", "7/12/2020");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid command line arguments"));
    }

    @Test
    public void eightCommandLineArgument()
    {
        MainMethodResult result = invokeMain("-printrr", "Deep", "123-123-1234", "123-123-1111", "7/12/2020", "12:00", "7/12/2020", "12:34");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid command line arguments"));
    }


//    @Test
//    public void invalidPathTest(){
//        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/cs410J/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
//        assertThat(result.getTextWrittenToStandardOut(), containsString("Something is wrong, can not create file this time. Check the path"));
//    }


//    @Test
//    public void fileNotCreated(){
//        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
//        assertThat(result.getTextWrittenToStandardOut(), containsString("Something is wrong, can not create file this time. Check the path"));
//    }

//    @Test
//    public void fileCreated(){
//        MainMethodResult result = invokeMain
//        ("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file1.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
//        assertThat(result.getTextWrittenToStandardOut(), containsString("File created"));
//    }


    @Test
    public void fileAlreadyexit(){
        MainMethodResult result = invokeMain("-textFile", "deep/file1.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "am" , "1/7/2020", "10:41", "pm");
       // assertThat(result.getTextWrittenToStandardOut(), containsString("File already exist"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Passed eleven arguments"));

    }

    @Test
    public void writingToNewfile(){
        MainMethodResult result = invokeMain("-textFile", "deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "am" ,"1/7/2020", "10:41","am");
        // assertThat(result.getTextWrittenToStandardOut(), containsString("Write out into file successfully"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Passed eleven arguments"));
    }


//    @Test
//    public void writingToNewfileNotPossible(){
//        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file.trt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
//        assertThat(result.getTextWrittenToStandardOut(), containsString("Something is wrong, can not create file this time. Check the path"));
//    }




  @Test
    public void testExtraCommandLineArguments()
  {
      MainMethodResult result = invokeMain("textFile", "deep/deep.txt", "-pretty", "-", "Project3", "123-456-7890",  "452-234-2125",  "01/03/2020",  "11:00",  "am",  "01/03/2020",  "2:00",  "pm", "extra");
          assertThat(result.getExitCode(), equalTo(1));
          assertThat(result.getTextWrittenToStandardOut(), containsString("Extra arguments"));
  }


}