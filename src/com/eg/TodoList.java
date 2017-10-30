package com.eg;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class TodoList {
	
	 @Persistent
	    private String CustomerEmail;
	 @Persistent
	    private String LoginEmail;
	 @Persistent
	    private String TodoList;
	
	public String getCustomerEmail() {
		return CustomerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}
	public String getLoginEmail() {
		return LoginEmail;
	}
	public void setLoginEmail(String loginEmail) {
		LoginEmail = loginEmail;
	}
	public String getTodoList() {
		return TodoList;
	}
	public void setTodoList(String todoList) {
		TodoList = todoList;
	}
	 

}
