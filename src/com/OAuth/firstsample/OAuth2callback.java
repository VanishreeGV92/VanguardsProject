package com.OAuth.firstsample;

import com.google.gson.Gson;
//import com.OAuth.firstsample.OAuthPojo;
import com.OAuth.firstsample.GsonUtility;
import com.OAuth.firstsample.Setup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/oauth2callback")
public class OAuth2callback   extends HttpServlet
{
	  private static final long serialVersionUID = 1L;
	  static String className = "com.OAuth.firstsample.Oauth2callback";
	  
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	  {
	      System.out.println ("code");
	      PrintWriter p = response.getWriter();

		
	    try
	    {
	      String code = request.getParameter("code");
	      System.out.println (code);
	      
	      String urlParameters = "code=" + 
	        code + 
	        "&client_id=" + Setup.CLIENT_ID +
	        "&client_secret=" + Setup.CLIENT_SECRET + 
	        "&redirect_uri=" + Setup.REDIRECT_URL +
	        "&grant_type=authorization_code";
	      
	      
	      URL url = new URL("https://accounts.google.com/o/oauth2/token");
	      URLConnection conn = url.openConnection();
	      conn.setDoOutput(true);
	      OutputStreamWriter writer = new OutputStreamWriter(
	        conn.getOutputStream());
	      writer.write(urlParameters);
	      writer.flush();
	      String line1 = "";
	      BufferedReader reader = new BufferedReader(new InputStreamReader(
	        conn.getInputStream()));
	      String line;
	      while ((line = reader.readLine()) != null)
	      {
	        line1 = line1 + line;
	      }
	      
	     System.out.println(line1);
	     HashMap<String,Object> map = new HashMap<String,Object>();
	      
	    //  a.
	      String s = GsonUtility.getJsonElementString("access_token", line1);
	      System.out.println("access token");
	      System.out.println(s);
	      
	      url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+ s);
	      URLConnection urlConn = url.openConnection();
	     String outputString = "";
	    // String line;
	      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	      while ((line = reader.readLine()) != null) {
	              outputString += line;
	      }
	      System.out.println("i am here");
	      
	      System.out.println(outputString);
	      
	      Gson gson = new Gson();
	      map = gson.fromJson(outputString , HashMap.class);
	     // String name = map.get("email");
	      System.out.println(map.get("id"));
	      System.out.println(map.get("email"));
	      System.out.println(map.get("name"));
	        HttpSession session=request.getSession();  
	        session.setAttribute("name",map.get("id"));
	        session.setAttribute("email",map.get("email"));  
	        session.setAttribute("name",map.get("name"));  
	         

	      //OAuthPojo data = (OAuthPojo)new Gson().fromJson(line1, OAuthPojo.class);
	      writer.close();
	      reader.close();
	      //OAuthPojo pojo = new OAuthPojo();
	   //   pojo.setEmail(email);
	      //pojo.setName(name);
	     // request.setAttribute("auth", data);
	     // request.getRequestDispatcher("/google.jsp").forward(request, response);
	      System.out.println("bf Send redirect");
	      
	      response.sendRedirect("/google.jsp");
	      System.out.println("af Send redirect");

	     // p.print("<a href='newFile.jsp'>visit</a>");  
	      //RequestDispatcher requestDispatcher = request
                 // .getRequestDispatcher("/newFile.jsp");
         // requestDispatcher.forward(request, response);

	    }
	    catch (MalformedURLException e)
	    {
	      e.printStackTrace();
	    }
	    catch (ProtocolException e)
	    {
	      e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	  {}
	}

