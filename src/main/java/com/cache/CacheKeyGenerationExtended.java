package com.cache;

import java.lang.annotation.Annotation;

import javax.cache.annotation.CacheKeyGenerator;
import javax.cache.annotation.CacheKeyInvocationContext;
import javax.cache.annotation.GeneratedCacheKey;

import org.springframework.context.annotation.Configuration;

import com.entity.Person;

/**
 * @author onkar_kotasthane
 *
 */
@Configuration
public class CacheKeyGenerationExtended implements CacheKeyGenerator {

	@Override
	public GeneratedCacheKey generateCacheKey(CacheKeyInvocationContext<? extends Annotation> cacheKeyInvocationContext) {
		Person person = (Person) cacheKeyInvocationContext.getKeyParameters()[0].getValue();
		int personId = person.getId();
		CacheKeyGeneration generatedCacheKeyImpl = new CacheKeyGeneration(new Object[] { personId });
		return generatedCacheKeyImpl;
	}
}
