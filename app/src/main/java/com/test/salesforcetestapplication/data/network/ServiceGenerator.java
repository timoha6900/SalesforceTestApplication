package com.test.salesforcetestapplication.data.network;

import com.test.salesforcetestapplication.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private static Retrofit.Builder retroBuilder =
			new Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create());

	public static <S> S createService(Class<S> serviceClass) {

//		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//		httpClient.addInterceptor(logging);

		Retrofit retrofit = retroBuilder.client(httpClient.build()).build();

		return retrofit.create(serviceClass);
	}

}
