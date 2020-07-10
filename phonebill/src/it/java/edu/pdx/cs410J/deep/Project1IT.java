package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project1.class, args );
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















//  @Test
//    public void testExtraCommandLineArguments()
//  {
//      MainMethodResult result = invokeMain(new String[] {"-print", "123-345-12324", "122-123-1234","7/7/2020 10:44", "7/8/2002 10:50", "extra"});
//          assertThat(result.getExitCode(), equalTo(1));
//          assertThat(result.getTextWrittenToStandardOut(), containsString("Too many arguments"));
//  }


}