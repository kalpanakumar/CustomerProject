package com.eg;

import java.io.IOException;


import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class SignUp extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		int flag=0;
		JSONObject JSON = null;
		String data = req.getParameter("data");
		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			JSON = new JSONObject(data);
			String pass = JSON.getString("EnterPassword");
			String email = JSON.getString("EnterEmail").toLowerCase();
			HttpSession session = req.getSession();
			session.setAttribute("email", email);
			PojoClass c = new PojoClass();
			Query query = new Query("PojoClass");
			PreparedQuery pq = datastore.prepare(query);
			for (Entity signIn: pq.asIterable()){
				String ReqEmail = (String) signIn.getProperty("email");

				if(email.equals(ReqEmail)){
					//resp.sendRedirect("index.jsp");
	               System.out.println("email exist");
	               flag =1;
	            System.out.print("This email already exist ");  
	            resp.setContentType("text/html;charset=UTF-8");
	            resp.getWriter().write("false");
				} 
			}
			if(flag == 0){
				c.setPassword(pass);
				c.setEmail(email);
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
