package com.eg;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;



	@PersistenceCapable
	public class Customer {

	  //  @PrimaryKey
	  //  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	 //   private Key key;

	    @Persistent
	    private String Name;
	   // @Persistent
	  //  private int mobileNumber;
	    @Persistent
	    private String email;
	    @Persistent
	    private String Address;
	    @Persistent
	    private String LoginEmail;
	  //getter and setter methods
	    
		public String getLoginEmail() {
			return LoginEmail;
		}

		public void setLoginEmail(String loginEmail) {
			LoginEmail = loginEmail;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			this.Name = name;
		}

	/*	public int getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(int mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		*/
		public String getAddress() {
			return Address;
		}

		public void setAddress(String address) {
			Address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


	    
	    
	}


