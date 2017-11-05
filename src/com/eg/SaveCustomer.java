package com.eg;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.jdo.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class SaveCustomer extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		JSONObject JSON = null;
		String data = req.getParameter("data");
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
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
			Query q = pm.newQuery(Customer.class,
					"email== '" + CustomerEmail + "' && LoginEmail== '" + LoginEmail + "'");
			List<Customer> ReqEmail = (List<Customer>) q.execute();
			if (!ReqEmail.isEmpty()) {
				resp.getWriter().write("false");
			} else {
				c.setName(name);
				c.setEmail(CustomerEmail);
				c.setAddress(Address);
				c.setLoginEmail(LoginEmail);
				c.setId(CustomerId);
				c.setMobileNumber(mobileNumber);
				try {
					pm.makePersistent(c);
				} finally {
					pm.close();
				}
				resp.getWriter().write("True");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req1, HttpServletResponse resp1) throws IOException, ServletException {
		// System.out.println("control comes to fetch servlet");

		JSONObject JSON = null;
		String data = req1.getParameter("data");
		try {
			// DatastoreService datastore =
			// DatastoreServiceFactory.getDatastoreService();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			JSON = new JSONObject(data);
			String CustomerId = JSON.getString("Id");
			 System.out.println(CustomerId);
			// Query query = new Query("Customer");
			// System.out.println("the query is " + query);
			// PreparedQuery pq =datastore.prepare(query);
			// System.out.println("the prepared query is " + pq.asIterable());
			// for (Entity CustomerData : pq.asIterable()) {

			// System.out.print("Comes inside the for loop");
			// String Save_Customer_Id = (String)
			// CustomerData.getProperty("Id");
			// System.out.println(CustomerId);
			// if (CustomerId.equals(Save_Customer_Id))
			// {
			Query q = pm.newQuery(Customer.class, "Id== '" + CustomerId + "'");
			List<Customer> CustomerDetails = (List<Customer>) q.execute();
			System.out.println("customerDeatials " + CustomerDetails);
			if (!CustomerDetails.isEmpty()) {
				for (Customer obj : CustomerDetails) {
					String customerId =obj.getId();
					System.out.println(customerId);
					String Save_Customer_Name = obj.getName();
					String Save_Customer_Address = obj.getAddress();
					String Save_Customer_email = obj.getEmail();
					int Save_Customer_Number = obj.getMobileNumber();
					System.out.print(Save_Customer_Name + " " + Save_Customer_Address + " " + Save_Customer_email + " "
							+ Save_Customer_Number);	
					  JSONObject JSON_send_data = new JSONObject(data);
					  JSON_send_data.put("Name", Save_Customer_Name);
					  JSON_send_data.put("Number", Save_Customer_Number);
					  JSON_send_data.put("Email", Save_Customer_email);
					  JSON_send_data.put("Address", Save_Customer_Address);
					  String jsonText = JSON_send_data.toString();
					  resp1.getWriter().write(jsonText);
					   
				}
			}else{
				System.out.println("It is empty");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
