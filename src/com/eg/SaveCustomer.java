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
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	System.out.println("Control comes to the saveCustomer servlet");
	resp.setContentType("text/html;charset=UTF-8");
	int flag=0;
	JSONObject JSON = null;
	String data = req.getParameter("data");
	try {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		JSON = new JSONObject(data);
		String name = JSON.getString("CustomerName");
	
		//int number = parseInt(JSON.getString("CustomerNumber"));
		HttpSession session = req.getSession();
		
		String LoginEmail = session.getAttribute("email").toString();
		
		
		String CustomerEmail = JSON.getString("CustomerEmail");
		
		session.setAttribute("CustomerEmail", CustomerEmail);
		String Address = JSON.getString("CustomerAddress");
		
		Customer c = new Customer();
		
		
		Query query = new Query("Customer");
		PreparedQuery pq = datastore.prepare(query);
		for (Entity signIn: pq.asIterable()){
			String ReqEmail = (String) signIn.getProperty("email");

			if(CustomerEmail.equals(ReqEmail)){
				//resp.sendRedirect("index.jsp");
               System.out.println("email exist");
               flag =1;
            System.out.print("This email already exist ");  
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("false");
			} 
		}
		if(flag == 0){
			c.setName(name);
			c.setEmail(CustomerEmail);
			c.setAddress(Address);
			c.setLoginEmail(LoginEmail);
			
			
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
	
	


	//
	
	

	
}
}
