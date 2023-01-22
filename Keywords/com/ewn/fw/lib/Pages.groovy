package com.ewn.fw.lib

public class Pages {
	private static final Pages instance = new Pages();

	private Map<Class,Object> map = new HashMap<Class,Object>();

	public static <T> T As(Class<T> classOf) throws InstantiationException, IllegalAccessException {

		synchronized(instance){
			if(!instance.map.containsKey(classOf)){

				T obj = classOf.newInstance();

				instance.map.put(classOf, obj);
			}
			return (T)instance.map.get(classOf)
		}
	}
}
