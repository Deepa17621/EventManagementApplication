package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Client;

public class ClientDao
{
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("deepa");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public Client saveClient(Client client)
	{
		et.begin();
		em.persist(client);
		et.commit();
		return client;	
	}
	public Client findClient(int id)
	{
		Client client=em.find(Client.class, id);
		if(client != null)
		{
			return client;
		}
		return null;
		
	}
	public Client deleteClient(int id)
	{
		Client client=em.find(Client.class, id);
		if(client != null) 
		{
			et.begin();
			em.remove(client);
			et.commit();
			return client;
		}
		return null;
	}
	public Client updateClient(int id, Client client)
	{
		Client client1 =em.find(Client.class, id);

		if(client1!=null)
		{
			client1.setClientId(id);
			et.begin();
			
			em.merge(client);
			et.commit();
			return client;
		}
		return null;
	}
}
