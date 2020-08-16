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


  @Test
  public void addPhoneCallToBillNullCustomer() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String customer = null;
    String callerPhonenumber = "123-123-1234";
    String calleePhonenumber = "534-111-1234";
    String start = "01/8/2020 1:12 am";
    String end = "01/014/2020 1:12 pm";


    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
    when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
    when(request.getParameter("start")).thenReturn(start);
    when(request.getParameter("end")).thenReturn(end);


    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"customer\" is missing");
  }


  @Test
  public void addPhoneCallToBillNullcallerPhonenumber() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String customer = "Sam";
    String callerPhonenumber = null;
    String calleePhonenumber = "111-111-1111";
    String start = "01/01/2020 1:12 am";
    String end = "01/02/2020 1:12 pm";


    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
    when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
    when(request.getParameter("start")).thenReturn(start);
    when(request.getParameter("end")).thenReturn(end);


    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    //verify(pw,times(0).println(any(String.class));
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"callerNumber\" is missing");
  }

  @Test
  public void addPhoneCallToBillNullcalleePhonenumber() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String customer = "customer";
    String callerPhonenumber = "503-123-2222";
    String calleePhonenumber = null;
    String start = "01/01/2020 1:12 am";
    String end = "01/02/2020 1:12 pm";


    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
    when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
    when(request.getParameter("start")).thenReturn(start);
    when(request.getParameter("end")).thenReturn(end);


    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"calleeNumber\" is missing");
  }

  @Test
  public void addPhoneCallToBillNullStart() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String customer = "customer";
    String callerPhonenumber = "503-123-2222";
    String calleePhonenumber = "534-111-1111";
    String start = null;
    String end = "01/02/2020 1:12 pm";


    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
    when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
    when(request.getParameter("start")).thenReturn(start);
    when(request.getParameter("end")).thenReturn(end);


    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"start\" is missing");
  }

  @Test
  public void addPhoneCallToBillNullEnd() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String customer = "customer";
    String callerPhonenumber = "503-123-2222";
    String calleePhonenumber = "534-111-1111";
    String start = "01/01/2020 1:12 am";
    String end = null;


    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn(customer);
    when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
    when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
    when(request.getParameter("start")).thenReturn(start);
    when(request.getParameter("end")).thenReturn(end);


    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    //verify(pw,times(0).println(any(String.class));
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"end\" is missing");
  }


  @Test
  public void doPostCustomer()  throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("DeepTest");
    when(request.getParameter("callerNumber")).thenReturn("112-111-1111");
    when(request.getParameter("calleeNumber")).thenReturn("123-122-4423");
    when(request.getParameter("start")).thenReturn("1/1/2020 1:12 pm");
    when(request.getParameter("end")).thenReturn("1/8/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  public void doPostCustomerFailed()  throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("DeepTest");
    when(request.getParameter("callerNumber")).thenReturn("541-111-111");
    when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
    when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
    when(request.getParameter("end")).thenReturn("01/02/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid number");
  }


  @Test
  public void doPostCustomerFailedDate()  throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("DeepTest");
    when(request.getParameter("callerNumber")).thenReturn("541-111-1111");
    when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
    when(request.getParameter("start")).thenReturn("01/01/220 1:12 am");
    when(request.getParameter("end")).thenReturn("01/03/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid date format");
  }

  @Test
  public void doPostCustomerFailedDate2()  throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("DeepTest");
    when(request.getParameter("callerNumber")).thenReturn("541-111-1111");
    when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
    when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
    when(request.getParameter("end")).thenReturn("01/02/220 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid date format");
  }

  @Test
  public void doGetCustomer() throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("DeepTest");
    when(request.getParameter("callerNumber")).thenReturn("111-111-1111");
    when(request.getParameter("calleeNumber")).thenReturn("222-222-2222");
    when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
    when(request.getParameter("end")).thenReturn("01/02/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    // -------------------- doGet ---------------------------
    HttpServletRequest Arequest = mock(HttpServletRequest.class);
    when(Arequest.getParameter("customer")).thenReturn("DeepTest");
    HttpServletResponse Aresponse = mock(HttpServletResponse.class);
    try {
      StringWriter stringWriter = new StringWriter();
      PrintWriter pw = new PrintWriter(stringWriter, true);
      when(Aresponse.getWriter()).thenReturn(pw);

      servlet.doGet(Arequest, Aresponse);
    } catch (IOException err) {
      System.err.println(err.getMessage());
    }
    verify(Aresponse).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  public void requestCustomerWithNoPhoneBillReturnsNotFound() throws IOException {

    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("notfound");
    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doGet(request, response);
    int status = response.getStatus();
    assertThat(status, equalTo(0));
//    verify(response).;
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
