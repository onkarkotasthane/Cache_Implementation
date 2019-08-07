package com.dao;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

import org.hibernate.Session;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import com.entity.Person;
import com.session.SessionFactoryManager;

/**
 * @author onkar_kotasthane
 * 
 * DEFINING DEFULT CACHE NAME TO BE USED BY JSR-107 ANNOTATION
 */
@Component
@CacheDefaults(cacheName = "OkayCache")
public class PersonDAOImpl implements PersonDAO {

	static public int totalDBFetchCount;

	/**
	 * SAVE ENTITY 
	 * ANNOTATION WITH SpEL (SPRING EXPRESSION LANGUAGE)
	 */
	@Override
	@CachePut(cacheNames = "OkayCache", key = "#person.id")
	public Person savePerson(@CacheKey @CacheValue final Person person) {
		Session session = SessionFactoryManager.getActiveSessionFactory().openSession();
		session.beginTransaction();
		session.save(person);
		System.out.println("Inserting: Auto-Generated Id " + person.getId() + " Value: " + person.getName());
		session.getTransaction().commit();
		session.close();
		return person;
	}

	/**
	 * UPDATE ENTITY
	 * ANNOTATION WITH SpEL (SPRING EXPRESSION LANGUAGE)
	 * */
	@Override
	@CachePut(cacheNames = "OkayCache", key = "#person.id")
	public Person updatePerson(@CacheValue Person person) {

		// Updating Person object in DB
		Session session = SessionFactoryManager.getActiveSessionFactory().openSession();
		session.beginTransaction();
		session.update(person);
		System.out.println("[*Updating Record Id: " + person.getId() + "]");
		session.getTransaction().commit();
		session.close();
		return person;
	}

	/**
	 * JCache(JSR-107) ANNOTATION WILL RETURN REQUESTED VALUE IF KEY IS PRESENT IN CACHE WITHOUT 
	 * EXECUTING METHOD ELSE WILL EXECUTE METHOD & STORE NEW KEY-VALUE IN CACHE
	 */
	@Override
	@CacheResult
	public Person getPerson(@CacheKey int personId) {
		// CONTROL WILL ENTER IN THIS METHOD ONLY IF REQUESTED ID IS NOT PRESSENT IN THE CACHE
		PersonDAOImpl.totalDBFetchCount += 1;
		System.out.println("[*Fetching from DB Record Id: " + personId + "]");
		Session session = SessionFactoryManager.getActiveSessionFactory().openSession();
		session.beginTransaction();
		Person person = session.get(Person.class, personId);
		session.getTransaction().commit();
		session.close();
		return person;
	}

	@Override
	@CacheRemove
	public void deletePerson(@CacheKey int personId) {
		Session session = SessionFactoryManager.getActiveSessionFactory().openSession();
		session.beginTransaction();
		session.delete(session.get(Person.class, personId));
		session.getTransaction().commit();
		session.close();
	}
}
