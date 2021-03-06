package com.eg;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
public class LogIn extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject JSON = null;
		String data = req.getParameter("data");
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			JSON = new JSONObject(data);
			String pass = JSON.getString("EnterLogInPassword");
			String email = JSON.getString("EnterLogInEmail").toLowerCase();
			Query q = pm.newQuery(PojoClass.class, "email== '" + email + "' && password== '" + pass + "'");
			List<PojoClass> ReqEmail = (List<PojoClass>) q.execute();
			if (!ReqEmail.isEmpty()) {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("True");
			} else {
				resp.getWriter().write("false");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
