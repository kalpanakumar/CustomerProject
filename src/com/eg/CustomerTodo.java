package com.eg;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.jdo.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CustomerTodo extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject JSON = null;
		String data = req.getParameter("data");
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			JSON = new JSONObject(data);
			String Item = JSON.getString("TodoItem");
			HttpSession session = req.getSession();
			String CustomerEmail = session.getAttribute("CustomerEmail").toString();
			String LogInEmail = session.getAttribute("email").toString();
			TodoList obj = new TodoList();
			Query q = pm.newQuery(TodoList.class);
			obj.setTodoList(Item);
			obj.setCustomerEmail(CustomerEmail);
			obj.setLoginEmail(LogInEmail);
			try {
				pm.makePersistent(obj);
			} finally {
				pm.close();
			}
			resp.getWriter().write("True");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
}
