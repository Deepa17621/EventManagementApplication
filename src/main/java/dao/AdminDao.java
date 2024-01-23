package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Admin;

public class AdminDao 
{
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("deepa");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public Admin saveAdmin(Admin admin)
	{
		et.begin();
		em.persist(admin);
		et.commit();
		return admin;	
	}
	public Admin findAdmin(int id)
	{
		Admin admin=em.find(Admin.class, id);
		if(admin != null)
		{
			return admin;
		}
		return null;
		
	}
	public Admin deleteAdmin(int id)
	{
		Admin admin=em.find(Admin.class, id);
		if(admin != null) 
		{
			et.begin();
			em.remove(admin);
			et.commit();
			return admin;
		}
		return null;
	}
	public Admin updateAdmin(int id, Admin admin)
	{
		Admin admin1 =em.find(Admin.class, id);

		if(admin1!=null)
		{
			admin1.setAdminId(id);
			et.begin();
			em.merge(admin);
			et.commit();
			return admin;
		}
		return null;
	}
	
	

}
