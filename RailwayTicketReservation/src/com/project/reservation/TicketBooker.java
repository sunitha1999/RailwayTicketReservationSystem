package com.project.reservation;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class TicketBooker{
	
	static int noOfAvailableLB=1;
	static int noOfavailableMB=1;
	static int noOfavailableUB=1;
	static int noOfavailableRAC=1;
	static int noOfavailableWL=1;
	
	//bookedRAC, bookedWAL -Queue, because FIFO should be followed for booking
	//contains passengerId of RAC, WL. <Integer> -passengerId
	static Queue<Integer> filledIdRacList = new LinkedList<>();
	static Queue<Integer> filledIdWaitingList = new LinkedList<>();
	//confirmed ticket list- contains passenger if confirmed tickets
	static List<Integer> confirmedIdList = new ArrayList<Integer>();
	
	//List containing available position(seat no) in each type
	static List<Integer> availableLBPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> availableMBPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> availableUBPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> availableRacPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> availableWLPosition = new ArrayList<>(Arrays.asList(1));
	
	//mapping id with passenger
	static Map<Integer, Passenger> passengerMap = new HashMap<>();
	
	//book ticket
	public void bookTicket(Passenger p, int seatNo, String berth)
	{
		p.seatNo=seatNo;
		p.allotted=berth;
		//map id to passenger
		passengerMap.put(p.passengerId, p);
		//add id to confirmedIdList
		confirmedIdList.add(p.passengerId);
		System.out.println("Ticket Booked Successfully!!");
	}
	
	public void addToRAC(Passenger p, int seatNo, String alloted)
	{
		p.seatNo=seatNo;
		p.allotted=alloted;
		passengerMap.put(p.passengerId, p);
		filledIdRacList.add(p.passengerId);
		System.out.println("Added to RAC");
	}
	
	public void addToWL(Passenger p, int seatNo, String alloted)
	{
		p.seatNo=seatNo;
		p.allotted=alloted;
		passengerMap.put(p.passengerId, p);
		filledIdWaitingList.add(p.passengerId);
		System.out.println("Added to Waiting List");
	}
	
	public void cancelTicket(int passengerId)
	{	//find passenger from map and store 
		Passenger p = passengerMap.get(passengerId);
		passengerMap.remove(Integer.valueOf(passengerId));//remove from map
		confirmedIdList.remove(Integer.valueOf(passengerId));//remove from confirmedList
		int seatNo = p.seatNo;//find and store the cancelled seat no to use it in future
		System.out.println("Ticket cancelled successfully");
		//find the berth of cancelled ticket, add it to availableList of corresp berth
		if(p.allotted.equals("L"))
		{
			noOfAvailableLB++;
			availableLBPosition.add(seatNo);
		}
		else if(p.allotted.equals("M"))
		{
			noOfavailableMB++;
			availableMBPosition.add(seatNo);
		}
		else if(p.allotted.equals("U"))
		{
			noOfavailableUB++;
			availableUBPosition.add(seatNo);
		}
		
		//check if any RAC is there
		if(filledIdRacList.size()>0)
		{
			Passenger pFromRAC = passengerMap.get(filledIdRacList.poll());//read and remove head of queue
			int seatOnRAC=pFromRAC.seatNo;	//store this seat for future use
			availableRacPosition.add(seatOnRAC);//add seat position to available RAC list
			filledIdRacList.remove(Integer.valueOf(pFromRAC.passengerId));//remove id from filled RAC list
			noOfavailableRAC++;//increment no of RAC seats
			
			//check if anyone is there in WL
			if(filledIdWaitingList.size()>0)
			{
				Passenger pFromWL = passengerMap.get(filledIdWaitingList.poll());
				int seatOnWL = pFromWL.seatNo;
				availableWLPosition.add(seatOnWL);//add seat position to available WL 
				filledIdWaitingList.remove(Integer.valueOf(pFromWL.passengerId));//remove id from filled WL
			
				pFromWL.seatNo = availableRacPosition.get(0);//1st available RAC seat given to
			//1st passenger in WL(i.e to passenger removed from WL)
				pFromWL.allotted="RAC";
				availableRacPosition.remove(0);//one blank pos filled in RAC, since 
				//one confirmed ticket canceled
				//since placing p from WL to RAC, empty RAC getting filled. So remove this.
				filledIdRacList.add(pFromWL.passengerId);
			
				noOfavailableWL++;
				noOfavailableRAC--;
			
			}
		Main.bookTicket(pFromRAC);
		}
	}
	
	public void printAvailable()
	{
		System.out.println("Available Lower Berth: "+noOfAvailableLB);
		System.out.println("Available Middle Berth: "+noOfavailableMB);
		System.out.println("Available Upper Berth: "+noOfavailableUB);
		System.out.println("Available RAC: "+noOfavailableRAC);
		System.out.println("Available Waiting List: "+noOfavailableWL);
		System.out.println("*******************************************");
	}
	
	public void printPassenger()
	{
		if(passengerMap.size()==0)
		{
			System.out.println("No details available");
			return;
		}
		for(Passenger p: passengerMap.values())
		{
			System.out.println("Passenger ID: "+p.passengerId);
			System.out.println("Passenger Name: "+p.passengerName);
			System.out.println("Passenger age: "+p.age);
			System.out.println("Status: "+p.seatNo+p.allotted);
			System.out.println("*********************************************");
		}
		
	}
	
	
	
}