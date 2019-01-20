package com.test.salesforcetestapplication.data.storage;

import com.test.salesforcetestapplication.data.network.responses.DescribeModelRes;
import com.test.salesforcetestapplication.data.network.responses.QueryModelRes;

import java.util.List;

public interface DatabaseHandler {
	boolean createAccountTable(List<DescribeModelRes.Field> fields);
	boolean insertDataInAccountTable(List<QueryModelRes.Record> records);
	boolean isAccountTableExists();
	boolean isAccountTableEmpty();
	List<AccountRecordModel> getAccounts(int lastId, int offset);
}
