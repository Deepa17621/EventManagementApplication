package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.AdminDao;
import dao.ClientDao;
import dao.ClientEventDao;
import dao.ClientServiceDao;
import dao.ServiceDao;
import dto.Admin;
import dto.Client;
import dto.ClientEvent;
import dto.ClientService;
import dto.EventType;
import dto.Service;

public class EventManagementController
{
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("deepa");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	Scanner sc=new Scanner(System.in);
	
	
	
	AdminDao adminDao=new AdminDao();
	ServiceDao serviceDao=new ServiceDao();
	ClientDao clientDao=new ClientDao();
	ClientServiceDao csDao=new ClientServiceDao();
	ClientEventDao ceDao=new ClientEventDao();
	
	public static void main(String[] args)
	{
		EventManagementController em=new EventManagementController();
//		main method is used to check whether method is working or not .
//		1..Save Admin data 
//		System.out.println(em.saveAdmin());
		
//		2.admin login	
//		System.out.println(em.adminLogin());
		
//		3.Create Services		
//		System.out.println(em.createService());
		
//		4. get All the services 
//		System.out.println(em.getAllServices());
		
//		5. Delete Service
//		System.out.println(em.deleteService());
		
//		6.Update Service		
//		System.out.println(em.updateService());
		
//		7.Client SignUp
//		System.out.println(em.clientSignup());
		
//		8.client Login
//		if(em.clientLogin()!=null)
//		{
//			System.out.println("Successfully Logged in");
//		}
//		else System.out.println("Invalid email or password");
		
//		 9. Create Client Event
//		System.out.println(em.createClientEvent());
		
//		10. Get ClientEvent 
//		System.out.println(em.getClientEvent());

//		11.Display Client Event Details
//		ClientEvent cEvent=new ClientEvent();
//		em.displayClientEventDetails(cEvent);
		
//		12.Add Client Service
//		System.out.println(em. addClientService());
		
	}
	public Admin saveAdmin() 
	{
		Admin a=new Admin();
		
		System.out.println("Enter admin name: ");
		a.setAdminName(sc.next());
		System.out.println("Enter admin mail: ");
		a.setAdminMail(sc.next());
		System.out.println("Enter admin password: ");
		a.setAdminPassword(sc.next());
		System.out.println("Enter admin contact number: ");
		a.setAdminContact(sc.nextLong());
		
		return adminDao.saveAdmin(a);
	}
	public Admin adminLogin() 
	{
		System.out.println("================Admin Login Page=================");
		System.out.println("enter email: ");
		String email=sc.next();
		System.out.println("enter valid password: ");
		String password=sc.next();
		String q="select a from Admin a where a.adminMail=?1 and a.adminPassword=?2";
		Query query=em.createQuery(q);
		query.setParameter(1, email);
		query.setParameter(2, password);
		Admin admin=(Admin) query.getSingleResult();
		if(admin.getAdminMail().equals(email) && admin.getAdminPassword().equals(password))
		{
			System.out.println("**********Login success**************");
			return admin;
		}
		else {
			System.out.println("Invalid Admin Creditials.");
		
		return null;
		}
	}
	public Service createService()
	{
		System.out.println("============Create New Services==========");
		
		Admin exAdmin=adminLogin();
		if(exAdmin != null)
		{
			    Service s=new Service();
				System.out.println("Enter Service name: ");
				s.setServiceName(sc.next());
				sc.nextLine();
				System.out.println("Enter service cost per person: ");
				s.setServiceCostPerPerson(sc.nextDouble());
				System.out.println("Enter cost per day: ");
				s.setCostPerDay(sc.nextDouble());
				
				Service service= serviceDao.saveService(s);
				exAdmin.getServices().add(service);
				adminDao.updateAdmin(exAdmin.getAdminId() , exAdmin );
				
				return service;	
		}
		return null;
	}
	public List<Service> getAllServices()
	{
		System.out.println("============GET ALL SERVICES============");
		System.out.println("enter admin credentials to proceed");
		Admin adminLogin=adminLogin();
		if(adminLogin != null)
		{
			
			String q="select s from Service s";
			Query query=em.createQuery(q);
			List<Service> service=(List<Service>)query.getResultList();
			System.out.println("serviceId     "+ "serviceName     "+"cost_per_person     "+ "cost_per_Day");
			for(Service s: service)
			{

				System.out.println("     "+s.getServiceId()+"          "+s.getServiceName()+"                   "+ s.getServiceCostPerPerson()+"              "+ s.getCostPerDay());
			}
			return service;
			
		}
		else return null;
		
	}
	public String updateService()
	{
		System.out.println("************Update Exisiting Service******************");
		
		Admin admin=adminLogin();
		List<Service> services=getAllServices();
//		System.out.println("serviceId     "+ "serviceName     "+"cost_per_person     "+ "cost_per_Day");
//		for(Service s: services)
//		{
//
//			System.out.println("     "+s.getServiceId()+"          "+s.getServiceName()+"                   "+ s.getServiceCostPerPerson()+"              "+ s.getCostPerDay());
//		}
		System.out.println("Choose service id from above to update: ");
		int serviceId=sc.nextInt();
		Service tobeUpdated=serviceDao.findService(serviceId);
		System.out.println("Enter new cost per person to update:: ");
		tobeUpdated.setServiceCostPerPerson(sc.nextDouble());
		System.out.println("Enter new cost per day to update:: ");
		tobeUpdated.setCostPerDay(sc.nextDouble());
		
		Service updatedService=serviceDao.updatService(serviceId, tobeUpdated);
		if(updatedService != null)
		{
			return "Service update success";
		}
		else return "Insert service first before going to update";
			
		
	}
	public Service deleteService() 
	{
		System.out.println("*******Delete Service*************");
		
		Admin exAdmin=adminLogin();
		List<Service> services=exAdmin.getServices();
		if(exAdmin != null)
		{
			System.out.println("serviceId     "+ "serviceName     "+"cost_per_person     "+ "cost_per_Day");
			for(Service s: services)
			{

				System.out.println("     "+s.getServiceId()+"          "+s.getServiceName()+"                   "+ s.getServiceCostPerPerson()+"              "+ s.getCostPerDay());
			}
			System.out.println("Choose service id from above to delete: ");
			int id=sc.nextInt();
			Admin ad=adminDao.findAdmin(exAdmin.getAdminId());
			Service toBeDeleted=serviceDao.findService(id);
			List<Service> newList=new ArrayList<Service>();
			for (Service service : services)
			{
				if(!service.getServiceName().equals(toBeDeleted.getServiceName()))
				{
					newList.add(service);
				}
				
			}
//			services.remove(toBeDeleted);
			exAdmin.setServices(services);
			ad.setServices(newList);
			adminDao.updateAdmin(exAdmin.getAdminId(), ad);
			System.out.println("Deleted Service Details: ");
			return toBeDeleted;
		}
		else
		{
		System.out.println("Service Not Yet Deleted");
		return null;
		}
	}
	public Client clientSignup()
	{
		System.out.println("--------------Client Sign Up Page---------");
		Client client=new Client();
		System.out.println("Enter Client Name:  ");
		client.setClientName(sc.next());
		sc.nextLine();
		System.out.println("Enter client Email:  ");
		client.setEmail(sc.next());;
		System.out.println("Enter password:  ");
		client.setPassword(sc.next());
		sc.nextLine();
		System.out.println("Enter client contact: ");
		client.setClientContact(sc.nextLong());
		sc.nextLine();
		System.out.println("Enter client Address");
		client.setClientAddress(sc.next());
		
		return clientDao.saveClient(client);
	}
	public Client clientLogin() 
	{
		System.out.println("-------------Client Login Page-------------");
		System.out.println("enter email: ");
		String email=sc.next();
		System.out.println("enter valid password: ");
		String password=sc.next();
		String q="select c from Client c where c.email=?1 and c.password=?2";
		Query query=em.createQuery(q);
		query.setParameter(1, email);
		query.setParameter(2, password);
		Client client=(Client) query.getSingleResult();
		if(client.getEmail().equals(email) && client.getPassword().equals(password))
		{
			return client;
		}
		return null;
		
	}
	public ClientEvent createClientEvent() 
	{
		Client client=clientLogin();
		ClientEvent cEvent=new ClientEvent();
		List<ClientEvent> clientEvents = new ArrayList<ClientEvent>();
		EventType[] eventType=EventType.values();
		int num=1;
		for (EventType e : eventType)
		{
			System.out.println(num++ +". "+ e);
		}
//		//choose event type.
		System.out.println("Choose the eventType listed above: ");
		switch (sc.nextInt())
		{
			case 1: cEvent.setEventType(EventType.Marriage); break;
			case 2: cEvent.setEventType(EventType.Engagement); break;
			case 3: cEvent.setEventType(EventType.Birthday); break;
			case 4: cEvent.setEventType(EventType.Anniversary); break;
			case 5: cEvent.setEventType(EventType.Babyshower); break;
			case 6: cEvent.setEventType(EventType.Reunion); break;
			case 7: cEvent.setEventType(EventType.NamingCeremony); break;
			case 8: cEvent.setEventType(EventType.BachelorParty); break;
		}
		System.out.println("Enter No of People will be present: ");
		cEvent.setNoOfPeople(sc.nextInt());
		System.out.println("Enter start date of Event: ");
		cEvent.setStartDate(sc.next());
		System.out.println("Enter no of Days: ");
		cEvent.setNoOfDays(sc.nextInt());
		System.out.println("Enter Cost of your Budget: ");
		cEvent.setTotalCost(sc.nextDouble());	
		sc.nextLine();
		System.out.println("Enter location");
		cEvent.setLocation(sc.next());
		
		cEvent.setClient(client);
		
//	    ClientEvent saveClientEvent=ceDao.saveClientEvent(cEvent);
		
		List<Service> services=getAllServices();
		
		System.out.println("serviceId     "+ "serviceName     "+"cost_per_person     "+ "cost_per_Day");
		for(Service s: services)
		{

			System.out.println("     "+s.getServiceId()+"          "+s.getServiceName()+"                   "+ s.getServiceCostPerPerson()+"              "+ s.getCostPerDay());
		}
		System.out.println("Enter no of services: ");
		int count=sc.nextInt();
		double eventCost=0;
		ClientService cServiceO=new ClientService();
		List<ClientService> clientServices=new ArrayList<ClientService>();
		for (int i = 1; i <=count; i++)
		{
			System.out.println("Enter"+ i +" service id you want to add: ");
			int id=sc.nextInt();
			Service service=serviceDao.findService(id);
			cServiceO.setClientServiceName(service.getServiceName());
			cServiceO.setClientServiceNoOfDays(cEvent.getNoOfDays());
			cServiceO.setClientServiceCostPerPerson(service.getServiceCostPerPerson());
			cServiceO.setClientServiceCost(cEvent.getNoOfPeople() * cServiceO.getClientServiceCostPerPerson());
			cServiceO.setClientServiceCost(cEvent.getNoOfPeople() * cServiceO.getClientServiceCostPerPerson()* cServiceO.getClientServiceNoOfDays());
			eventCost+=cServiceO.getClientServiceCost();
			clientServices.add(cServiceO);
			ClientService cs1=csDao.saveClientService(cServiceO);
		}
		cEvent.setTotalCost(eventCost);
		cEvent.setClientService(clientServices);
		clientEvents.add(cEvent);
		client.setClientEvent(clientEvents);
		Client updatedClient=clientDao.updateClient(client.getClientId(), client);
		if(updatedClient!=null)
		{
			System.out.println("Client Event Created");
			return cEvent;
		}	
		else
		{
			System.out.println("Problem in Client Event creation ");
			return null;
		}
		
	}
	
	public ClientEvent getClientEvent()
	{
		Client client=clientLogin();
		if(client != null)
		{
			System.out.println("Enter ClientEventId: ");
			int cEId=sc.nextInt();
			Query query=em.createQuery("select ce from ClientEvent ce where clientEventId=?1");
			query.setParameter(1, cEId);
			ClientEvent ce=(ClientEvent) query.getSingleResult();
			return ce;
		}
		return null;
	}
	public void displayClientEventDetails(ClientEvent cEvent)
	{
		System.out.println("Event ID : "+cEvent.getClientEventId());
		System.out.println("Event Type : "+cEvent.getEventType());
		System.out.println("Event Client Name : "+cEvent.getClient().getClientName());
		System.out.println("Event Client Email : "+cEvent.getClient().getEmail());
		System.out.println("Event Location : "+cEvent.getLocation());
		System.out.println("Total People Count : "+cEvent.getNoOfPeople());
		System.out.println("Total Cost : "+cEvent.getTotalCost());
		
		List<ClientService> clientServicesList = cEvent.getClientService();
		for (ClientService clientService : clientServicesList) 
		{
			System.out.println(clientService);
		}
	}
	public String addClientService()
	{
		System.out.println("------Add Client Service--------");
		Client exClient = clientLogin();
		if(exClient != null)
		{
			List<ClientEvent> exClientEvents = exClient.getClientEvent();
			System.out.println("Enter Client Event Id : "); int exClientEventId = sc.nextInt();
			int count = 0;
			for(ClientEvent events : exClientEvents)
			{
				if(events.getClientEventId() == exClientEventId)
				{
					count ++;
					double eventCost = events.getTotalCost();
					List<ClientService> exClientServices = events.getClientService();
					System.out.println("Enter Service Adding Count : "); int serviceCount = sc.nextInt();
					for(int i=1;i<=serviceCount;i++)
					{
						ClientService cs = new ClientService();
						List<Service> listOfServices = getAllServices();
						System.out.println("\t ----- Service Lists -----");
						for (Service service : listOfServices) 
						{
							System.out.println(service);
						}
						System.out.print("Enter Service Id :");
						int svalue = sc.nextInt();
						Service s1 = serviceDao.findService(svalue);
						cs.setClientServiceName(s1.getServiceName());
						cs.setClientServiceNoOfDays(events.getNoOfDays());
						cs.setClientServiceCostPerPerson(s1.getServiceCostPerPerson());
						cs.setClientServiceCost(events.getNoOfPeople() * cs.getClientServiceCostPerPerson() * cs.getClientServiceNoOfDays());
						eventCost = eventCost + cs.getClientServiceCost();
						exClientServices.add(cs);
						ClientService cs1= csDao.saveClientService(cs);
					}
					
					events.setTotalCost(count);
					events.setClientService(exClientServices);
					ClientEvent ce1 = ceDao.updateClientEvent(events.getClientEventId(),events);
					if(ce1 != null)
					{
						return "Client Service Added";
					}
				}
			}
			if(count == 0)
				return "Invalid Id Event Not Found";
		}
		return "Client Service Not Added";
	}

}
