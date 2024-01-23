package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientEvent;

public class ClientEventDao 
{

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("deepa");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public ClientEvent saveClientEvent(ClientEvent clientEvent)
	{
		et.begin();
		em.persist(clientEvent);
		et.commit();
		return clientEvent;	
	}
	public ClientEvent findClientEvent(int id)
	{
		ClientEvent clientEvent=em.find(ClientEvent.class, id);
		if(clientEvent != null)
		{
			return clientEvent;
		}
		return null;
		
	}
	public ClientEvent deleteclientEvent(int id)
	{
		ClientEvent clientEvent=em.find(ClientEvent.class, id);
		if(clientEvent != null) 
		{
			et.begin();
			em.remove(clientEvent);
			et.commit();
			return clientEvent;
		}
		return null;
	}
	public ClientEvent updateClientEvent(int id, ClientEvent clientEvent)
	{
		ClientEvent clientEvent1 =em.find(ClientEvent.class, id);

		if(clientEvent1!=null)
		{
			clientEvent1.setClientEventId(id);
			et.begin();
			em.merge(clientEvent);
			et.commit();
			return clientEvent;
		}
		return null;
	}
}
