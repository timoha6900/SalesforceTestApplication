package com.test.salesforcetestapplication.utils;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.test.salesforcetestapplication.data.managers.DataManager;
import com.test.salesforcetestapplication.data.network.responses.DescribeModelRes;

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
 * Loads data from the server and creates from them the Accounts table in the database.
 */
class CreatingAccountsLiveData extends LiveData<Constants.DataProcessingStatus> {
	private DataManager mDataManager;

	CreatingAccountsLiveData(Context context) {
		setValue(Constants.DataProcessingStatus.Waiting);
		mDataManager = DataManager.getInstance(context);
	}


	private void startProcessing() {
		setValue(Constants.DataProcessingStatus.Loading);

		Call<DescribeModelRes> call = mDataManager.getDescribeModel();
		call.enqueue(new Callback<DescribeModelRes>() {
			@Override
			public void onResponse(@NonNull Call<DescribeModelRes> call, @NonNull Response<DescribeModelRes> response) {
				if(response.code() == 200 && response.body() != null){
					createTable(response.body().getFields())
							.subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribe(observer);
				} else {
					setValue(Constants.DataProcessingStatus.NetworkError);
				}
			}

			@Override
			public void onFailure(@NonNull Call<DescribeModelRes> call, @NonNull Throwable t) {
				setValue(Constants.DataProcessingStatus.NetworkError);
			}
		});
	}

	private Observable<Constants.DataProcessingStatus> createTable(final List<DescribeModelRes.Field> fields) {
		return Observable.create(emitter -> {
			if(mDataManager.createAccountTable(fields)){
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
