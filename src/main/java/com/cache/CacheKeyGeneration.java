package com.cache;

import java.util.Arrays;

import javax.cache.annotation.GeneratedCacheKey;

/**
 * @author onkar_kotasthane
 *
 */
public class CacheKeyGeneration implements GeneratedCacheKey {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final Object[] params;
	private final int hash;

	public CacheKeyGeneration(final Object[] parameters) {
		this.params = parameters;
		this.hash = Arrays.deepHashCode(parameters);
	}

	@Override
	public boolean equals(final Object o) {
		o.equals(new Object());
		return true;
	}

	@Override
	public int hashCode() {
		return hash;
	}
}
