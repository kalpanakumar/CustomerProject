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

public class CustomerTodo extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject JSON = null;
		String data = req.getParameter("data");
		
		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			JSON = new JSONObject(data);
			String Item = JSON.getString("TodoItem");
			HttpSession session = req.getSession();	
			String CustomerEmail = session.getAttribute("CustomerEmail").toString();
			String LogInEmail = session.getAttribute("email").toString();
			TodoList obj = new TodoList();
			Query query = new Query("Customer");
			PreparedQuery pq = datastore.prepare(query);				
			obj.setTodoList(Item);
			obj.setCustomerEmail(CustomerEmail);
			obj.setLoginEmail(LogInEmail);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
			 	pm.makePersistent(obj);			 	
			} finally {
				pm.close();
			}	
			resp.getWriter().write("True");
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
