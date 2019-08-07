package com.listener;

import com.entity.Person;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryEvictedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.hazelcast.map.listener.MapClearedListener;
import com.hazelcast.map.listener.MapEvictedListener;

/**
 * @author onkar_kotasthane
 *
 */
public class HazelcastEventListener implements EntryAddedListener<Integer, Person>,
		EntryRemovedListener<Integer, Person>, EntryUpdatedListener<Integer, Person>,
		EntryEvictedListener<Integer, Person>, MapEvictedListener, MapClearedListener {

	@Override
	public void entryAdded(EntryEvent<Integer, Person> event) {
		System.out.println("LISTENER: Entry added in Hazelcast Cache named: " + event.getName());
	}

	@Override
	public void mapCleared(MapEvent event) {
	}

	@Override
	public void mapEvicted(MapEvent event) {
	}

	@Override
	public void entryEvicted(EntryEvent<Integer, Person> event) {
	}

	@Override
	public void entryUpdated(EntryEvent<Integer, Person> event) {
		System.out.println("LISTENER: Entry updated in Hazelcast Cache named: " + event.getName());
	}

	@Override
	public void entryRemoved(EntryEvent<Integer, Person> event) {
	}
}
