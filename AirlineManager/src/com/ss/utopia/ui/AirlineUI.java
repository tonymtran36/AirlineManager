package com.ss.utopia.ui;

import java.util.Scanner;

import com.ss.utopia.service.Admin;
import com.ss.utopia.service.Agent;
import com.ss.utopia.service.Traveler;

public class AirlineUI {

	public void employee(Scanner scan) {
		Agent agent = new Agent();
		agent.enterFlights(scan);
		//agent method

	}
	
	public void admin(Scanner scan) {
		Admin admin = new Admin();
		admin.enterOptions(scan);
		//admin method

	}
	
	public void traveler(Scanner scan) {
		Traveler user = new Traveler(); //create traveler
		user.validateTicket(scan); //Traveler case - enter membership number
	}
	

	
	public static void main(String[] args) {
		AirlineUI airlineUI = new AirlineUI();
		Scanner scan = new Scanner(System.in);
		int user = 0;
		while(true) {
			System.out.println("Please enter the number for the category of user are you.\n1. Employee\n2. Admin\n3. Traveler");
			if (scan.hasNextInt()) {
				user = scan.nextInt();
			}
			switch (user) {
				case (1):
					//Employee
					airlineUI.employee(scan);
					break;
				case (2):
					//Admin
					airlineUI.admin(scan);
					break;

				case (3):
					//Traveler
					airlineUI.traveler(scan);
					break;

				default:
					System.out.println("not a number or wrong number");
			}
			//catch if its not a number or wrong number
		}
		//scan.close();
	}
}
