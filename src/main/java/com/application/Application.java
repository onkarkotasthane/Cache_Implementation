package com.application;

import javax.annotation.PostConstruct;
import javax.cache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cache.CacheTest;
import com.utils.CacheInfo;

/**
 * @author onkar_kotasthane
 *
 */
@ComponentScan(basePackages = {"com"})
@SpringBootApplication
@EnableCaching
@Configuration
public class Application {
	
	@Autowired
	private CacheTest cacheTest;
	
	@Autowired
    private CacheManager cacheManager;
	
	@PostConstruct
	private void begin() {
		// SHOW AVAILABLE CACHE PROVIDERS
		CacheInfo.showAvailableCacheProviders();		
		System.out.println("Using cache manager: " + cacheManager.getClass().getName());

		// START TEST
		cacheTest.test();
	}

	public static void main(String[] args) {
	
		/*
		 * INITIALIZING SPRING BOOT APPLICATION WITH AUTO SPRING CONFIGURATIONS
		 * INJECTING TE SPRING BOOT APPLICATION CONTEXT
		 */
		SpringApplication.run(Application.class, args);
	}	
}