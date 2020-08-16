package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }

  @Test
  public void test0RemoveAllDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
   // client.removeAllDictionaryEntries();
  }

//  @Test
//  public void test1EmptyServerContainsNoDictionaryEntries() throws IOException {
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    Map<String, String> dictionary = client.getAllDictionaryEntries();
//    assertThat(dictionary.size(), equalTo(0));
//  }

  @Test
  public void TestAddPhoneCall() throws IOException{
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.postPhoneCall("test", "123-122-1234", "122-111-1222", "8/8/2020 12:00 am", "8/8/2020 12:30 pam");
  }

//  @Test(expected=PhoneBillRestClient.PhoneBillRestException.class)
//  public void TestAddNewPhoneCallFailed() throws IOException {
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    client.postPhoneCall("clientTest", "541-11-1111", "541-454-4111", "2/20/2020 1:00 pm", "03/21/2020 1:10 am");
//  }
//
//  @Test(expected=PhoneBillRestClient.PhoneBillRestException.class)
//  public void TestAddNewPhoneCallFailed2() throws  IOException{
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    client.postPhoneCall("clientTest", "541-111-1111", "541-454-4111", "2/20/020 1:00 pm", "03/21/2020 1:10 am");
//  }

  @Test
  public void TestGetPhoneCall () throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.getPhoneCalls("clientTest");
  }

  @Test
  public void TestSearch() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.searcPhoneCalls("clientTest", "1/1/2020 1:00 pm", "2/1/2020 2:00 pm");

  }




//  @Test
//  public void test1NonexistentPhoneBillThrowException() throws IOException{
//    PhoneBillRestClient client = newPhoneBillRestClient();
//
//            try{
//              try {
//                client.getPhoneBills("Notexistcustomer");
//              } catch (ParserException e) {
//                e.printStackTrace();
//              }
//              fail("Should have thrown a PhoneBillRestExceptiom");
//
//            }catch (PhoneBillRestClient.PhoneBillRestException ex){
//              assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
//            }
//  }

//  @Ignore
//  @Test
//  public void test2DefineOneWord() throws IOException {
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    String testWord = "TEST WORD";
//    String testDefinition = "TEST DEFINITION";
//    client.addDictionaryEntry(testWord, testDefinition);
//
//    String definition = client.getDefinition(testWord);
//    assertThat(definition, equalTo(testDefinition));
//  }
//
//  @Ignore
//  @Test
//  public void test4MissingRequiredParameterReturnsPreconditionFailed() throws IOException {
//    PhoneBillRestClient client = newPhoneBillRestClient();
//    HttpRequestHelper.Response response = client.postToMyURL(Map.of());
//    assertThat(response.getContent(), containsString(Messages.missingRequiredParameter("word")));
//    assertThat(response.getCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
//  }

}
