package com.utils;

import java.util.Iterator;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import com.dao.PersonDAOImpl;

/**
 * @author onkar_kotasthane
 *
 */
public class CacheInfo {

	/**
	 * @param totalRequests
	 * 
	 * SHOW CACHE TEST STATISTICS
	 */
	public static void showStats(int totalRequests) {
		System.out.println("\n*Cache Operation Stats-");
		System.out.println("-----------------------------------------");
		System.out.println("Total Request(s): " + totalRequests);
		System.out.println("Cache: " + (totalRequests - PersonDAOImpl.totalDBFetchCount));
		System.out.println("DB: " + PersonDAOImpl.totalDBFetchCount);
		System.out.println(String.format("*Cache HIT Rate: %.2f", (((float) (totalRequests - PersonDAOImpl.totalDBFetchCount) / totalRequests) * 100.00)) + "%");
		System.out.println(String.format("Cache MISS Rate: %.2f", (((float) (PersonDAOImpl.totalDBFetchCount) / totalRequests) * 100.00)) + "%");
	}

	/**
	 * DETECTING AVAILBE CACHE PROVIDERS
	 */
	public static void showAvailableCacheProviders() {
		System.out.println("List of available cache providers:\n");
		Iterator<CachingProvider> ite = Caching.getCachingProviders().iterator();
		int cacheCounter = 0;
		while (ite.hasNext()) {
			System.out.println((cacheCounter += 1) + ". " + ite.next().getClass());
		}
		System.out.println("Total cache providers: " + cacheCounter);
	}
}
