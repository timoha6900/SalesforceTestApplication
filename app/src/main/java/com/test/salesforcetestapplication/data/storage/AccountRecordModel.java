package com.test.salesforcetestapplication.data.storage;

import com.google.gson.annotations.SerializedName;
import com.test.salesforcetestapplication.data.network.responses.QueryModelRes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class AccountRecordModel {
	private TreeMap<String, String> record;
	private static TreeMap<String, Field> fieldMap = null;

	AccountRecordModel(QueryModelRes.Record recordRes){

		record = new TreeMap<>();
		record.put(DataBaseHelper.ACCOUNT_ID, "null");

		if(recordRes == null) {
			recordRes = new QueryModelRes().new Record();

			if(fieldMap == null) {
				initFieldMap(recordRes);
			}
			for(String key : fieldMap.keySet()){
				record.put(key, "null");
			}

		} else {
			if(fieldMap == null) {
				initFieldMap(recordRes);
			}
			for(String key : fieldMap.keySet()){
				try {
					record.put(key, convertAttrToString(fieldMap.get(key).get(recordRes)));
				} catch (IllegalAccessException e) { e.printStackTrace(); }
			}
		}
	}

	private void initFieldMap(QueryModelRes.Record recordRes) {
		fieldMap = new TreeMap<>();
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(recordRes.getClass().getFields()));
		for (Field field : fields) {
			try {
				fieldMap.put(field.getAnnotation(SerializedName.class).value(), field);
			} catch (NullPointerException e) { e.printStackTrace(); }
		}
	}

	private String convertAttrToString (Object attr) {
		if(attr == null) {
			return "null";
		} else {
			return attr.toString();
		}
	}

	public TreeMap<String, String> getRecord(){
		return record;
	}

}
