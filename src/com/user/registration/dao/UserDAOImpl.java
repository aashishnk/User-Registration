package com.user.registration.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.user.registration.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveNewUser(User theUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();//creating a session object

		currentSession.save(theUser);

	}

	@Override
	public List<User> getUserData(String mailId) {	//method to get user details by using User Mail Id

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = currentSession.createSQLQuery("select * from user_data u where u.email_id=:mailId")
				.addEntity(User.class)
				.setParameter("mailId", mailId);
		
		List<User> userData =  query.getResultList();
		
		//long s = ((long) currentSession.createQuery("select count(*) from User where emailId=:mailId").setParameter("mailId", mailId).iterate().next() );
		
		
		return userData;
	}

	@Override
	public void updateUserStatus(String mailId, int activeFlag, int status) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createNativeQuery("update user_data set active_flag = :activeFlag "+
		", status = :status, updated_date = sysdate "+
		"where email_id = :mailId", User.class)
				.setParameter("activeFlag", activeFlag)
				.setParameter("status",status)
				.setParameter("mailId",mailId);
		
		query.executeUpdate();
		
	}

}
