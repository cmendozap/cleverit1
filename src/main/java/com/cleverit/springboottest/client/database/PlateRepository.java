package com.cleverit.springboottest.client.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cleverit.springboottest.client.model.plate.Plate;


@Component
@Repository
public class PlateRepository implements PlateDAO {

    private SessionFactory sessionFactory;
    
    @Autowired 
    public PlateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public String storeAll(List<Plate> plates) {

		if (!this.sessionFactory.getCurrentSession().isOpen()) {
			this.sessionFactory.openSession();
		}
		
        Session session = this.sessionFactory.getCurrentSession();
        
        session.beginTransaction();
        
        String stringQuery = "DELETE FROM Plate";
        @SuppressWarnings("rawtypes")
		Query query = session.createQuery(stringQuery);
        query.executeUpdate();
        session.getTransaction().commit();
        
        session.beginTransaction();
        for (Plate plate: plates) {
        	if (plate.getIdUser()!=null & plate.getLicensePlate()!=null & plate.getAlias()!=null) {
        		session.save(plate);
        	}
        	
        }
        
        session.getTransaction().commit();
        
        session.close();
        
        return "done";
	}


	
	

}
