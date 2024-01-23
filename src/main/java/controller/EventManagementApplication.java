package controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.Admin;
import dto.Service;
import dto.*;
public class EventManagementApplication 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
//		System.out.println(LocalDate.now());
		
		while(true)
		{
			System.out.println("Press -1- for Admin Registration");
			System.out.println("Press -2- for Admin Login");
			System.out.println("Press -3- for EventBooking for Client");
			int value = sc.nextInt();
			if(value == 1)
			{
				System.out.println("\t Admin Registration Page");
				Admin a1 = new Admin();
				System.out.print("Enter Name : "); String adminName = sc.next();
				System.out.print("Enter ContactNo : "); long adminContact = sc.nextLong();
				System.out.print("Enter Email : "); String adminEmail = sc.next();
				System.out.print("Enter Password : "); String adminPassword = sc.next();
				System.out.print("Enter Adding Event Counts : "); int eventCounts = sc.nextInt();
				List<Service> services = new ArrayList<Service>();
				for(int i=0;i<eventCounts;i++)
				{
					System.out.println("Press -1- for Food");
					System.out.println("Press -2- for photoshoot");
					int svalue = sc.nextInt();
					if(svalue == 1)
					{
						Service s1 = new Service();
						System.out.println("Press -1- for veg");
						System.out.println("Press -2- for Nonveg");
						int foodtype = sc.nextInt();
						if(foodtype == 1)
						{
							
						}
					}
				}
			}
		}

	}

}
