package com.eg;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class TodoList {
	
	 @Persistent
	    private String Id;
	 @Persistent
	    private String LoginEmail;
	 @Persistent
	    private String TodoList;
	
	
	public String getLoginEmail() {
		return LoginEmail;
	}
	public void setLoginEmail(String loginEmail) {
		LoginEmail = loginEmail;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTodoList() {
		return TodoList;
	}
	public void setTodoList(String todoList) {
		TodoList = todoList;
	}
	 

}
