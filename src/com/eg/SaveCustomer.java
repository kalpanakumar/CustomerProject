package com.eg;

import java.io.IOException;



import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class SaveCustomer extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
	resp.setContentType("text/html;charset=UTF-8");
	int flag=0;
	JSONObject JSON = null;
	String data = req.getParameter("data");
	try {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		JSON = new JSONObject(data);
		String name = JSON.getString("CustomerName");
		int mobileNumber = Integer.parseInt(JSON.getString("CustomerNumber"));
		
		HttpSession session = req.getSession();
		
		String LoginEmail = String.valueOf(session.getAttribute("email"));
		
		
		String CustomerEmail = JSON.getString("CustomerEmail");
		String CustomerId = JSON.getString("Id");
		
		session.setAttribute("CustomerEmail", CustomerEmail);
		String Address = JSON.getString("CustomerAddress");	
		Customer c = new Customer();	
		Query query = new Query("Customer");
		PreparedQuery pq = datastore.prepare(query);
		for (Entity signIn: pq.asIterable()){
			String ReqEmail = (String) signIn.getProperty("email");

			if(CustomerEmail.equals(ReqEmail)){
				//resp.sendRedirect("index.jsp");
               
               flag =1;
            
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("false");
			} 
		}
		if(flag == 0){
			c.setName(name);
			c.setEmail(CustomerEmail);
			c.setAddress(Address);
			c.setLoginEmail(LoginEmail);
			c.setId(CustomerId);
			c.setMobileNumber(mobileNumber);
			resp.setContentType("text/html;charset=UTF-8");
           
            flag=0;
            PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
			 	pm.makePersistent(c);
			 	
			} finally {
				pm.close();
			}
			 resp.getWriter().write("True");
		}
		
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void doGet(HttpServletRequest req1, HttpServletResponse resp1) throws IOException {
		//System.out.println("control comes to fetch servlet");
		

		JSONObject JSON = null;
		String data = req1.getParameter("data");
		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			JSON = new JSONObject(data);
			String CustomerId = JSON.getString("Id");
			System.out.println(CustomerId);
			Query query = new Query("Customer");
			System.out.println("the query is "+query);
			PreparedQuery pq = datastore.prepare(query);
			System.out.println("the prepared query is "+pq.asIterable());
			for (Entity CustomerData: pq.asIterable()){
				
				//System.out.print("Comes inside the for loop");
				String Save_Customer_Id = (String)CustomerData.getProperty("Id");
				System.out.println(CustomerId);
				if(CustomerId.equals(Save_Customer_Id)){
					String Save_Customer_Name = (String) CustomerData.getProperty("Name");
					String Save_Customer_Address = (String) CustomerData.getProperty("Address");
					String Save_Customer_email = (String) CustomerData.getProperty("email");
					int Save_Customer_Number = Integer.parseInt(CustomerData.getProperty("mobileNumber").toString());
System.out.print(Save_Customer_Name+" "+ Save_Customer_Address+ " "+  Save_Customer_email + " "+ Save_Customer_Number);
JSONObject JSON_send_data = new JSONObject(data);
JSON_send_data.put("Name",Save_Customer_Name);
JSON_send_data.put("Number",Save_Customer_Number);
JSON_send_data.put("Email",Save_Customer_email);
JSON_send_data.put("Address",Save_Customer_Address);
String jsonText = JSON_send_data.toString();
resp1.getWriter().write(jsonText);

				}
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
}

