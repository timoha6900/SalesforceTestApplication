package com.test.salesforcetestapplication.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.test.salesforcetestapplication.data.network.responses.DescribeModelRes;
import com.test.salesforcetestapplication.data.network.responses.QueryModelRes;
import com.test.salesforcetestapplication.utils.ConverterUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class DataBaseHelper extends SQLiteOpenHelper implements DatabaseHandler {
	public static final String ACCOUNT_ID = "_id";

	private static final String TAG = "DB_Log";
	private static final String DATABASE_NAME = "TestAppDB";
	private static final String ACCOUNT_TABLE_NAME = "Account";
	private static final int DB_VERSION = 1;

	private static Set<String> ACCOUNT_TABLE_FIELDS_NAMES;


	public DataBaseHelper(Context context){
		super (context, DATABASE_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public boolean createAccountTable(List<DescribeModelRes.Field> fields) {
		boolean isSuccess = true;
		ACCOUNT_TABLE_FIELDS_NAMES = new HashSet<>();
		ACCOUNT_TABLE_FIELDS_NAMES.add(ACCOUNT_ID);
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("CREATE TABLE ");
		sbQuery.append(ACCOUNT_TABLE_NAME);
		sbQuery.append("(");
		sbQuery.append(ACCOUNT_ID);
		sbQuery.append(" INTEGER PRIMARY KEY AUTOINCREMENT");

		for(int i = 0; i < fields.size(); i++){
			sbQuery.append(", ");
			sbQuery.append(fields.get(i).getName());
			sbQuery.append(" text");
			ACCOUNT_TABLE_FIELDS_NAMES.add(fields.get(i).getName());
		}

		sbQuery.append(");");

		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try {
			db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);

			db.execSQL(sbQuery.toString());
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			isSuccess = false;
			Log.w(TAG, e);
		} finally {
			db.endTransaction();
			db.close();
		}
		return isSuccess;
	}

	@Override
	public boolean insertDataInAccountTable(List<QueryModelRes.Record> models) {
		boolean isSuccess = true;
		List<AccountRecordModel> records = new ArrayList<>();
		for(QueryModelRes.Record model : models){
			records.add(new AccountRecordModel(model));
		}

		long id = 0;
		int i = 0;
		ContentValues cv = new ContentValues();
		Set<String> keySet = records.get(0).getRecord().keySet();
		keySet.remove(ACCOUNT_ID);

		filterFieldsNamesSet(keySet);

		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();

		for(AccountRecordModel record : records){
			cv.clear();
			for(String key : keySet){
				cv.put(key, record.getRecord().get(key));
			}
			try {
				id = db.insertOrThrow(ACCOUNT_TABLE_NAME, "null", cv);
			} catch (SQLException e) {
				isSuccess = false;
				Log.w(TAG, "Exception when inserting data in Account table in record " + i, e);
			}
			i++;
		}

		if(id != -1){
			db.setTransactionSuccessful();
		}
		db.endTransaction();
		cv.clear();
		db.close();

		return isSuccess;
	}

	@Override
	public boolean isAccountTableExists() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = '"
				+ ACCOUNT_TABLE_NAME + "'", null);
		if(!cursor.moveToFirst()) {
			cursor.close();
			db.close();
			return false;
		}
		int count = cursor.getInt(0);
		cursor.close();
		db.close();
		return count > 0;
	}

	@Override
	public boolean isAccountTableEmpty() {
		SQLiteDatabase db = getReadableDatabase();
		boolean result = DatabaseUtils.queryNumEntries(db, ACCOUNT_TABLE_NAME) == 0;
		db.close();
		return result;
	}

	@Override
	public List<AccountRecordModel> getAccounts(int lastId, int offset) {
		ArrayList<AccountRecordModel> result = new ArrayList<>();
		Set<String> keySet = new AccountRecordModel(null).getRecord().keySet();
		filterFieldsNamesSet(keySet);
		String [] columns = ConverterUtil.setToArray(keySet);

		SQLiteDatabase db = getReadableDatabase();
		String selection = "_id > ? LIMIT " + offset;
		String [] selectionsArgs = {String.valueOf(lastId)};
		Cursor c = db.query(ACCOUNT_TABLE_NAME, columns, selection, selectionsArgs, null, null, null);
		if(c.moveToFirst()) {
			do {
				int i = 0;
				AccountRecordModel model = new AccountRecordModel(null);
				TreeMap<String, String> modelMap = model.getRecord();
				for(String key : keySet){
					modelMap.put(key, c.getString(i++));
				}
				result.add(model);
			} while (c.moveToNext());
		}

		c.close();
		db.close();
		return result;
	}

	/**
	 * Removes from a keySet keys that do not correspond to the column names in the Accounts table.
	 * @param keySet table field names
	 */
	private void filterFieldsNamesSet(Set<String> keySet){
		if(ACCOUNT_TABLE_FIELDS_NAMES == null){
			initAccountTableFieldsNameSet();
		}

		String[] keys = ConverterUtil.setToArray(keySet);
		for (String key : keys) {
			if (!ACCOUNT_TABLE_FIELDS_NAMES.contains(key)) {
				keySet.remove(key);
			}
		}
	}

	/**
	 * Don't use when Database is open
	 */
	private void initAccountTableFieldsNameSet() {
		ACCOUNT_TABLE_FIELDS_NAMES = new HashSet<>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("PRAGMA table_info(" + ACCOUNT_TABLE_NAME + ")", null);
		if(c.moveToFirst()){
			do{
				ACCOUNT_TABLE_FIELDS_NAMES.add(c.getString(1));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
	}

}
