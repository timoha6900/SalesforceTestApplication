package com.test.salesforcetestapplication.utils;

import java.util.Set;

public class ConverterUtil {

	public static String[] setToArray(Set<String> set) {
		String[] array = new String[set.size()];
		int i = 0;
		for(String key : set){
			array[i++] = key;
		}
		return array;
	}

}
