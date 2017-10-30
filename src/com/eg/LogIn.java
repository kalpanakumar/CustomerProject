package com.eg;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class LogIn extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject JSON = null;
		int flag=0;
		String data = req.getParameter("data");//will return value  
		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			JSON = new JSONObject(data);
			String pass = JSON.getString("EnterLogInPassword");
			String email = JSON.getString("EnterLogInEmail").toLowerCase();
			Query query = new Query("PojoClass");
			PreparedQuery pq = datastore.prepare(query);
			for (Entity signIn: pq.asIterable()){
				String ReqEmail = (String) signIn.getProperty("email");

				if(email.equals(ReqEmail)){
					
                   System.out.println("email exist");
                   flag =1;
   				String ReqPass = (String) signIn.getProperty("password");
                if (pass.equals(ReqPass)){
                	System.out.println("password matched");
                	resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write("True");
                	
                }
				} 
			}
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
}
