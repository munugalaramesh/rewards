package com.company.rewards.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class CollectionUtils extends org.springframework.util.CollectionUtils{
	
	
	  public static <T> Stream<T> asStream(final Collection<T> collection) {
		    return Optional.ofNullable(collection).map(Collection::stream).orElseGet(Stream::empty);
		}
	  
	  
	  

}
