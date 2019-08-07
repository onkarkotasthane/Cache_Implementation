package com.dao;

import org.springframework.stereotype.Component;

import com.entity.Person;

/**
 * @author onkar_kotasthane
 *
 */
@Component
public interface PersonDAO {
	Person savePerson(Person person);
	Person updatePerson(Person person);
	Person getPerson(int personId);
	void deletePerson(int personId);
}
