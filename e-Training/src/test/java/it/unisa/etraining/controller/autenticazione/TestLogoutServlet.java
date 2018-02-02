package it.unisa.etraining.controller.autenticazione;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLogoutServlet {
  
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession session;
  @Mock
  RequestDispatcher dispatcher;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test01_SenzaSessione() throws Exception {
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);    
    try {
      new LogoutServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test02_ConSessione() throws Exception {
    when(request.getSession(false)).thenReturn(session);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);    
    try {
      new LogoutServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }

}
