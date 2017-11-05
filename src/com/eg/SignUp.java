package com.eg;

import java.io.IOException;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;
import javax.jdo.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class SignUp extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		JSONObject JSON = null;
		String data = req.getParameter("data");
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			JSON = new JSONObject(data);
			String pass = JSON.getString("EnterPassword");
			String email = JSON.getString("EnterEmail").toLowerCase();
			HttpSession session = req.getSession();
			session.setAttribute("email", email);
			PojoClass c = new PojoClass();
			Query q = pm.newQuery(PojoClass.class, "email== '" + email + "'");
			List<PojoClass> ReqEmail = (List<PojoClass>) q.execute();
			if (!ReqEmail.isEmpty()) {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("false");
			} else {
				c.setPassword(pass);
				c.setEmail(email);
				resp.setContentType("text/html;charset=UTF-8");
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
}
