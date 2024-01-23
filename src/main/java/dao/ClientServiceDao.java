package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientService;

public class ClientServiceDao
{

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("deepa");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public ClientService saveClientService(ClientService clientService)
	{
		et.begin();
		em.persist(clientService);
		et.commit();
		return clientService;	
	}
	public ClientService findClientService(int id)
	{
		ClientService clientService=em.find(ClientService.class, id);
		if(clientService != null)
		{
			return clientService;
		}
		return null;
		
	}
	public ClientService deleteClientService(int id)
	{
		ClientService clientService=em.find(ClientService.class, id);
		if(clientService != null) 
		{
			et.begin();
			em.remove(clientService);
			et.commit();
			return clientService;
		}
		return null;
	}
	public ClientService updatClientService(int id, ClientService clientService)
	{
		ClientService clientService1 =em.find(ClientService.class, id);

		if(clientService1!=null)
		{
			clientService1.setClientServiceId(id);
			et.begin();
			em.merge(clientService);
			et.commit();
			return clientService;
		}
		return null;
	}
}
