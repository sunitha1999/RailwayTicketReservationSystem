package com.project.reservation;
import java.util.Scanner;

public class Main {
	
	public static void bookTicket(Passenger p)
	{
		TicketBooker booker = new TicketBooker();
		if(booker.noOfavailableWL==0)
		{
			System.out.println("No tickets available");
			return;
		}
		if(p.preferredBerth.equals("L") && (booker.noOfAvailableLB>0)||
				p.preferredBerth.equals("M") && (booker.noOfavailableMB>0)||
				p.preferredBerth.equals("U") && (booker.noOfavailableUB>0)
		 )
		{
			System.out.println("Preferred berth available");
			if(p.preferredBerth.equals("L"))
			{
				System.out.println("Lower Berth given");
				booker.bookTicket(p,booker.availableLBPosition.get(0),"L");//.get(0)-first from the availablePosLB list is retrieved
				booker.availableLBPosition.remove(0);
				booker.noOfAvailableLB--;
				
			}
			
			else if(p.preferredBerth.equals("M"))
			{
				System.out.println("Middle Berth given");
				booker.bookTicket(p, booker.availableMBPosition.get(0), "M");
				booker.availableMBPosition.remove(0);
				booker.noOfavailableMB--;
				
			}
			
			else if(p.preferredBerth.equals("U"))
			{
				System.out.println("Upper Berth given");
				booker.bookTicket(p, booker.availableUBPosition.get(0), "U");
				booker.availableUBPosition.remove(0);
				booker.noOfavailableUB--;
				
			}
		}
		//preferred Berth NOT available- book available berth
		else if(booker.noOfAvailableLB>0)
		{
			System.out.println("Preferred Berth Not available-Lower Berth Given");
			booker.bookTicket(p, booker.availableLBPosition.get(0), "L");
			booker.availableLBPosition.remove(0);
			booker.noOfAvailableLB--;
			
		}
		else if(booker.noOfavailableMB>0)
		{
			System.out.println("Preferred Berth Not available-Middle Berth Given");
			booker.bookTicket(p, booker.availableMBPosition.get(0), "M");
			booker.availableMBPosition.remove(0);
			booker.noOfavailableMB--;
			
		}
		else if(booker.noOfavailableUB>0)
		{
			System.out.println("Preferred Berth Not available-Upper Berth Given");
			booker.bookTicket(p, booker.availableUBPosition.get(0), "U");
			booker.availableUBPosition.remove(0);
			booker.noOfavailableUB--;
			
		}
		
		else if(booker.noOfavailableRAC>0)
		{
			System.out.println("Berth not available - RAC available");
			booker.addToRAC(p, booker.availableRacPosition.get(0), "RAC");
			booker.availableRacPosition.remove(0);
			booker.noOfavailableRAC--;
			
		}
		else if(booker.noOfavailableWL>0)
		{
			System.out.println("Berth not available - Waiting List available");
			booker.addToWL(p, booker.availableWLPosition.get(0), "WL");
			booker.availableWLPosition.remove(0);
			booker.noOfavailableWL--;
			
		}
	}
	
	//cancel Ticket
	public static void cancelTicket(int id)
	{
		TicketBooker booker = new TicketBooker();
		//check if passenger is valid by checking if id is present as key in map 
		if(booker.passengerMap.containsKey(id))
			booker.cancelTicket(id);
		else
			System.out.println("Passenger details unknown");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		boolean condition=true;
		while(condition)
		{
			System.out.println("1.Book ticket\n2.Cancel ticket\n"
					+ "3.Show Available tickets\n4.Show booked ticket\n5.Exit");
			int choice=sc.nextInt();
			
			switch(choice)
			{
				case 1://book ticket
				{	
					System.out.println("Please enter your name, age and preferred berth(L,M,U)");
					String name=sc.next();
					int age=sc.nextInt();
					String preferredBerth=sc.next();
				//creating obj of passenger, initialize using constructor
					Passenger p = new Passenger(name, age, preferredBerth);
					bookTicket(p);
				}
					break;
				case 2://cancel ticket
				{
					System.out.println("Please enter the passenger id to Cancel: ");
					int id=sc.nextInt();
					cancelTicket(id);
				}
					break;
				case 3://print available tickets
				{
					TicketBooker booker =  new TicketBooker();
					booker.printAvailable();
				}
					break;
				case 4://print Passengers
				{
					TicketBooker booker =  new TicketBooker();
					booker.printPassenger();
				}
					break;
				case 5:
				{
					condition=false;
				}
			}
			
		}

	}

}
