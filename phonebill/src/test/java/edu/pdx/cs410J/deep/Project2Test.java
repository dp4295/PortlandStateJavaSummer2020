package edu.pdx.cs410J.deep;

import com.sun.tools.javac.Main;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.pdx.cs410J.InvokeMainTestCase;

/**
 * A unit test for code in the <code>Project2</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can handle the calls
 * to {@link System#exit(int)} and the like.
 */
public class Project2Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return invokeMain(Project2.class, args );
    }


  @Test
  public void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project2.class.getResourceAsStream("README.txt");
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
        MainMethodResult result = invokeMain("-textFile", "path" , "Deep", "123-123-1234", "123-123-1111", "1/7/2020", "12:34", "1/7/2020", "12:45");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("You meet MAXIMUM argument require"));
    }


    @Test
    public void tenCommnadlineArgument(){
        MainMethodResult result = invokeMain("-textFile","path", "Deep", "123-123-123", "123-123-1234","1/7/2020", "1:23", "1/7/2020", "1:30", "Extra");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Extra arguments"));
    }

    @Test
    public void writingToNewfile(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/pdx/cs410J/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Write out into file successfully"));
    }

    @Test
    public void invalidPathTest(){
        MainMethodResult result = invokeMain("-textFile", "src/main/resources/edu/cs410J/deep/file.txt", "Deep Patel", "123-123-1234", "123-123-1234", "1/7/2020", "10:39", "1/7/2020", "10:41");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Invalid path location. Valid file name must follow by src/main/resources/edu/pdx/cs410J/deep/"));
    }




}
