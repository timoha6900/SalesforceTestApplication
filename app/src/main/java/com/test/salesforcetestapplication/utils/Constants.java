package com.test.salesforcetestapplication.utils;

public interface Constants {

	String BASE_URL = "http://www.amock.io/api/timoha6900/";

	/**
	 * Default number of loading accounts
	 */
	int DEF_ITEM_LOADING_AMOUNT = 40;


	enum DataProcessingStatus {
		Waiting, Loading, Success, NetworkError, DbError
	}
}
