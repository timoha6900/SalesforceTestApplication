package com.test.salesforcetestapplication.data.managers;

import android.content.Context;

import com.test.salesforcetestapplication.data.network.RestService;
import com.test.salesforcetestapplication.data.network.ServiceGenerator;
import com.test.salesforcetestapplication.data.network.responses.DescribeModelRes;
import com.test.salesforcetestapplication.data.network.responses.QueryModelRes;
import com.test.salesforcetestapplication.data.storage.AccountRecordModel;
import com.test.salesforcetestapplication.data.storage.DataBaseHelper;
import com.test.salesforcetestapplication.data.storage.DatabaseHandler;

import java.util.List;

import retrofit2.Call;

public class DataManager {

	private static DataManager INSTANCE = null;

	private RestService mRestService;
	private DatabaseHandler mDatabaseHandler;


	private DataManager(Context context) {
		this.mRestService = ServiceGenerator.createService(RestService.class);
		this.mDatabaseHandler = new DataBaseHelper(context);
	}


	/**
	 * @param context ApplicationContext
	 * @return DataManager instance
	 */
	public static DataManager getInstance(Context context) {
		if(INSTANCE == null){
			INSTANCE = new DataManager(context);
		}
		return INSTANCE;
	}

	// region ============ Network =============

	public Call<DescribeModelRes> getDescribeModel() {
		return mRestService.getDescribeModel();
	}

	public Call<QueryModelRes> getQueryModel() {
		return mRestService.getQueryModel();
	}

	// endregion


	// region ============ Database =============

	public boolean createAccountTable(List<DescribeModelRes.Field> fields) {
		return mDatabaseHandler.createAccountTable(fields);
	}

	public boolean insertDataInAccountTable(List<QueryModelRes.Record> records) {
		return mDatabaseHandler.insertDataInAccountTable(records);
	}

	public boolean isAccountTableEmpty() {
		return  mDatabaseHandler.isAccountTableEmpty();
	}

	public boolean isAccountTableExists() {
		return mDatabaseHandler.isAccountTableExists();
	}

	public List<AccountRecordModel> getAccounts(int lastId, int offset) {
		return mDatabaseHandler.getAccounts(lastId, offset);
	}

	// endregion

}
