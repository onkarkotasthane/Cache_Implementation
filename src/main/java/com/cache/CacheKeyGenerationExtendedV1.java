package com.cache;

import java.lang.annotation.Annotation;
import javax.cache.annotation.CacheInvocationParameter;
import javax.cache.annotation.CacheKeyGenerator;
import javax.cache.annotation.CacheKeyInvocationContext;
import javax.cache.annotation.GeneratedCacheKey;
import org.jsr107.ri.annotations.DefaultGeneratedCacheKey;

import com.entity.Person;

/**
 * @author onkar_kotasthane
 *
 */
public class CacheKeyGenerationExtendedV1 implements CacheKeyGenerator {

	@Override
	public GeneratedCacheKey generateCacheKey(CacheKeyInvocationContext<? extends Annotation> cacheKeyInvocationContext) {
		final CacheInvocationParameter[] allParameters = cacheKeyInvocationContext.getAllParameters();
		for (final CacheInvocationParameter parameter : allParameters) {
			if (Person.class.equals(parameter.getRawType())) {
				final Person person = Person.class.cast(parameter.getValue());
				return new DefaultGeneratedCacheKey(new Object[] { person.getId() });
			}
		}
		throw new IllegalArgumentException("No argument found in method signature");
	}
}
