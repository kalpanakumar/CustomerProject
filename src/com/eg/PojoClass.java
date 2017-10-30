package com.eg;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;



	@PersistenceCapable
	public class PojoClass {

	  //  @PrimaryKey
	  //  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	 //   private Key key;

	    @Persistent
	    private String password;

	    @Persistent
	    private String email;
	  //getter and setter methods
	    
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


	    
	    
	}

