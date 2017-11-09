package com.example;

import java.io.IOException;



import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.jdo.Query;

import com.example.jdo.CustomerJDO;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

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
			session.setAttribute("Id", CustomerId);
			String Address = JSON.getString("CustomerAddress");
			CustomerJDO c = new CustomerJDO();
			Query q = pm.newQuery(CustomerJDO.class,
					"email== '" + CustomerEmail + "' && LoginEmail== '" + LoginEmail + "'");
			List<CustomerJDO> ReqEmail = (List<CustomerJDO>) q.execute();
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
			PersistenceManager pm = PMF.get().getPersistenceManager();
			JSON = new JSONObject(data);
			String CustomerId = JSON.getString("Id");
			 System.out.println(CustomerId);

			 
			Query q = pm.newQuery(CustomerJDO.class, "Id== '" + CustomerId + "'");
			List<CustomerJDO> CustomerDetails = (List<CustomerJDO>) q.execute();
			if (!CustomerDetails.isEmpty()) {
				for (CustomerJDO obj : CustomerDetails) {
					String customerId =obj.getId();
					String Save_Customer_Name = obj.getName();
					String Save_Customer_Address = obj.getAddress();
					String Save_Customer_email = obj.getEmail();
					int Save_Customer_Number = obj.getMobileNumber();
					System.out.print(Save_Customer_Name + " " + Save_Customer_Address + " " + Save_Customer_email + " "
							+ Save_Customer_Number);	
					try {
					CustomerTodo TodoListObj = new CustomerTodo();	
					List<String> Todo = TodoListObj.getCustomerDetail(customerId);
					System.out.println(Todo);
					System.out.println("The control comes to the line after calling the customer's details");
					}catch( Exception e){
					e.toString();
					}
					JSONObject JSON_send_data = new JSONObject();
					JSON_send_data.put("Name", Save_Customer_Name);
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
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
		JSONObject JSON = null;
		String data = req.getParameter("data");
		System.out.println(data);
		
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			JSON = new JSONObject(data);
			String name = JSON.getString("Name");
			int mobileNumber = Integer.parseInt(JSON.getString("mobileNumber"));
			String Address = JSON.getString("Address");
			String Id = JSON.getString("Id");
			
			CustomerJDO c = pm.getObjectById(CustomerJDO.class, Id);
			c.setAddress(Address);
			c.setMobileNumber(mobileNumber);
			c.setName(name);
			
			try {
				pm.makePersistent(c);
			} finally {
				pm.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
