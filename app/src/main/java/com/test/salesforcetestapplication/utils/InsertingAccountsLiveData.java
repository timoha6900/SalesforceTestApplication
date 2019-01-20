package com.test.salesforcetestapplication.utils;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.test.salesforcetestapplication.data.managers.DataManager;
import com.test.salesforcetestapplication.data.network.responses.QueryModelRes;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Loads data from the server and inserts it into the Accounts table in the database.
 */
class InsertingAccountsLiveData extends LiveData<Constants.DataProcessingStatus> {
	private DataManager mDataManager;

	InsertingAccountsLiveData(Context context){
		mDataManager = DataManager.getInstance(context);
		setValue(Constants.DataProcessingStatus.Waiting);
	}


	private void startProcessing(){
		setValue(Constants.DataProcessingStatus.Loading);

		Call<QueryModelRes> call = mDataManager.getQueryModel();
		call.enqueue(new Callback<QueryModelRes>() {
			@Override
			public void onResponse(@NonNull Call<QueryModelRes> call, @NonNull Response<QueryModelRes> response) {
				if(response.code() == 200 && response.body() != null){
					insertData(response.body().getRecords())
							.subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribe(observer);
				} else {
					setValue(Constants.DataProcessingStatus.NetworkError);
				}
			}

			@Override
			public void onFailure(@NonNull Call<QueryModelRes> call, @NonNull Throwable t) {
				setValue(Constants.DataProcessingStatus.NetworkError);
			}
		});
	}

	private Observable<Constants.DataProcessingStatus> insertData(final List<QueryModelRes.Record> records) {
		return Observable.create(emitter -> {
			if(mDataManager.insertDataInAccountTable(records)){
				emitter.onNext(Constants.DataProcessingStatus.Success);
			}else{
				emitter.onNext(Constants.DataProcessingStatus.DbError);
			}
		});
	}

	private Observer<Constants.DataProcessingStatus> observer = new Observer<Constants.DataProcessingStatus>() {
		@Override
		public void onSubscribe(Disposable d) {}

		@Override
		public void onNext(Constants.DataProcessingStatus status) {
			setValue(status);
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
		}

		@Override
		public void onComplete() {}
	};


	@Override
	protected void onActive() {
		super.onActive();
		startProcessing();
	}

	@Override
	protected void onInactive() {
		super.onInactive();
	}

}
