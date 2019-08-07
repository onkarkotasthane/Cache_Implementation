package com.cache;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.dao.PersonDAO;
import com.entity.Person;
import com.utils.CacheInfo;

/**
 * @author onkar_kotasthane
 *
 */
@Component
public class CacheTest {

	@Autowired
	PersonDAO cachedPersonDAOImpl;

	public void test() {
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			// SAVEING TEST
			cachedPersonDAOImpl.savePerson(new Person("Person1"));
			cachedPersonDAOImpl.savePerson(new Person("Person2"));
			cachedPersonDAOImpl.savePerson(new Person("Person3"));
			cachedPersonDAOImpl.savePerson(new Person("Person4"));
			cachedPersonDAOImpl.savePerson(new Person("Person5"));
			cachedPersonDAOImpl.savePerson(new Person("Person6"));
			
			// UPDATION TEST
			Person personToUpdate = cachedPersonDAOImpl.getPerson(2);
			personToUpdate.setName(personToUpdate.getName() + "-Updated");
			cachedPersonDAOImpl.updatePerson(personToUpdate);
			
			// SAVEING TEST
			cachedPersonDAOImpl.savePerson(new Person("Person7"));
			cachedPersonDAOImpl.savePerson(new Person("Person8"));
			cachedPersonDAOImpl.savePerson(new Person("Person9"));
			cachedPersonDAOImpl.savePerson(new Person("Person10"));
			cachedPersonDAOImpl.savePerson(new Person("Person11"));
			cachedPersonDAOImpl.savePerson(new Person("Person12"));
			cachedPersonDAOImpl.savePerson(new Person("Person13"));
			cachedPersonDAOImpl.savePerson(new Person("Person12"));
			cachedPersonDAOImpl.savePerson(new Person("Person13"));
			stopWatch.stop();
			System.out.println("[" + stopWatch.getTotalTimeSeconds() + "]");
			
			
			// REQUEST FETCH TEST
			System.out.println("\n*REQUESTS-");
			int totalRequests = 1000;
			int[] randomRequestPersonIds = new int[totalRequests];
			Random random = new Random();
			for (int indx = 1; indx <= totalRequests; indx++) {
				int randomRequestPersonId = (Math.abs(random.nextInt()) % 13) + 1;
				randomRequestPersonIds[indx-1] = randomRequestPersonId;
			}
			
			stopWatch = new StopWatch();
			stopWatch.start();
			for (int requestNo = 1; requestNo <= randomRequestPersonIds.length; requestNo++) {
				// Request Calls
				System.out.println("Request " + (requestNo) + ": Record Id: " + randomRequestPersonIds[requestNo-1] + " " + cachedPersonDAOImpl.getPerson(randomRequestPersonIds[requestNo-1]).getName());
			}
			stopWatch.stop();
			System.out.println("[" + stopWatch.getTotalTimeSeconds() + "]");
			System.out.println("Total Resuests: " + randomRequestPersonIds.length);
			
			// CACHE HIT STAT
			CacheInfo.showStats(randomRequestPersonIds.length);
			
			System.out.println("End");
		} catch (Exception e) {
			System.out.println("EXCEPTION");
			e.printStackTrace();
		}
	}
}
