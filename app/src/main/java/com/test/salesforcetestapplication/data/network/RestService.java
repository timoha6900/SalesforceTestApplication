package com.test.salesforcetestapplication.data.network;

import com.test.salesforcetestapplication.data.network.responses.DescribeModelRes;
import com.test.salesforcetestapplication.data.network.responses.QueryModelRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {

	@GET("describe")
	Call<DescribeModelRes> getDescribeModel();

	@GET("query")
	Call<QueryModelRes> getQueryModel();

}
