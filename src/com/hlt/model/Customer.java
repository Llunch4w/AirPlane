package com.hlt.model;

public class Customer{
     String name;
     String idCard;
     String phoneNumber;
     String flightNum;
     String order[];
     int OrderNum;
     String type;
     public Customer( String name,String idCard,String phoneNumber){
    	 this.name=name;
    	 this.idCard=idCard;
    	 this.phoneNumber=phoneNumber;
     }
     public void setName(String Name) {
    	 name = Name;
     } 
     public void setIDCard(String IDCard) {
    	 idCard = IDCard;
     }
     public void setPhoneNumber(String PHNUM) {
    	 phoneNumber = PHNUM;
     }
  //   public void setPhone
     public String getName() {
    	 return name;
     }
    
     public String getIDCard() {
    	 return idCard;
     }
     public String getPhoneNumber() {
    	 return phoneNumber;
     }
     public String getFlightNumr() {
    	 return flightNum;
     }
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

     
}