package edu.pdx.cs410J.deep;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.pdx.cs410J.InvokeMainTestCase;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can handle the calls
 * to {@link System#exit(int)} and the like.
 */
public class Project1Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return invokeMain(Project1.class, args );
    }


  @Test
  public void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project1.class.getResourceAsStream("README.txt");
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }


 @Test
  public void nocommandlineArgument(){
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
     public void nineCommandlineArgument(){
        MainMethodResult result = invokeMain("-print", "Deep", "123-123-1234", "123-123-1111", "1/7/2020", "12:34", "1/7/2020", "12:45", "Extra");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Extra argument"));
    }




}
