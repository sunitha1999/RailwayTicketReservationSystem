package com.project.reservation;

public class Passenger {

	static int id=1;//static var to give id for every passenger
	String passengerName;
	int age;
	String preferredBerth;//(L,U,M)
	int passengerId;//id of passenger automatically created
	String allotted;//(L,U,M ,RAC, WL)
	int seatNo;//2L-2
	
	public Passenger(String passengerName, int age, String preferredBerth) {
		
		this.passengerName = passengerName;
		this.age = age;
		this.preferredBerth = preferredBerth;
		this.passengerId=id++;
		this.allotted="";
		this.seatNo=-1;
	}
	
}
