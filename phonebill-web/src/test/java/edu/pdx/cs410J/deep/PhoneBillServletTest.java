package edu.pdx.cs410J.deep;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static edu.pdx.cs410J.deep.Messages.missingRequiredParameter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class PhoneBillServletTest {

  @Ignore
  @Test
  public void requestWithNoCustomerReturnMissingParameter() throws ServletException, IOException {

    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doGet(request, response);

    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter("customer"));

  }

  @Ignore
  @Test
  public void requestCustomerWithNoPhoneBillReturnsNotFound() throws ServletException, IOException{

    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    String customername = "Deep";
    when(request.getParameter("customer")).thenReturn(customername);


    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doGet(request, response);

    verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, Messages.noPhoneBillForCustomer(customername));


  }



  @Test
  public void addPhoneCallToPhoneBill() throws ServletException, IOException, ParseException {

    String customer = "Customer";
    String  callerphonenumber  = "503-899-8889";
    String calleenumnber = "123-121-2222";
    String startdate = "02/7/2020";
    String starttime = "12:30";
    String startampm = "am";
    String enddate = "2/7/2020";
    String endtime = "12:35";
    String endampm = "am";

    PhoneBillServlet servlet = createPhoneBill(customer, callerphonenumber, calleenumnber, startdate, starttime, startampm, enddate, endtime, endampm);
    PhoneBill phoneBill = servlet.getDefinition(customer);
    assertThat(phoneBill, not(nullValue()));
    List<PhoneCall> phoneCallList = phoneBill.getPhoneCalls();
    for(PhoneCall phoneCall: phoneCallList){
      assertThat(phoneCall.getCaller(), equalTo(callerphonenumber));
    }

//    HttpServletRequest request = mock(HttpServletRequest.class);
//    when(request.getParameter("Customer")).thenReturn(customer);
//    when(request.getParameter("503-899-8889")).thenReturn(callerphonenumber);
//
//    HttpServletResponse response = mock(HttpServletResponse.class);
//
//    // Use a StringWriter to gather the text from multiple calls to println()
//    StringWriter stringWriter = new StringWriter();
//    PrintWriter pw = new PrintWriter(stringWriter, true);
//
//    when(response.getWriter()).thenReturn(pw);
//
//    servlet.doPost(request, response);
//
////    assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(word, definition)));
//
////    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
////    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
//    verify(response).setStatus(HttpServletResponse.SC_OK);
//
//   // assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
//
//    PhoneBill phoneBill = servlet.getPhoneBill(customer);
//    assertThat(servlet.getDefinition(customer), equalTo(callerphonenumber));
  }

  private PhoneBillServlet createPhoneBill(String customer, String caller, String  callee, String startdate, String startime, String startampm, String enddate, String endtime, String endampm) throws IOException, ServletException {
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callernumber")).thenReturn(caller);
    when(request.getParameter("calleenumber")).thenReturn(callee);
    when(request.getParameter("startdate")).thenReturn(startdate);
    when(request.getParameter("starttime")).thenReturn(startime);
    when(request.getParameter("startampm")).thenReturn(startampm);
    when(request.getParameter("enddate")).thenReturn(enddate);
    when(request.getParameter("endtime")).thenReturn(endtime);
    when(request.getParameter("endampm")).thenReturn(endampm);

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    servlet.doPost(request, response);
    return servlet;
  }

}
