package com.test.salesforcetestapplication.utils;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.test.salesforcetestapplication.data.managers.DataManager;
import com.test.salesforcetestapplication.data.storage.AccountRecordModel;
import com.test.salesforcetestapplication.data.storage.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * On demand, downloads accounts data from the database. Stores previously loaded data.
 */
public class AccountsDynamicLoaderViewModel extends AndroidViewModel{
	private MutableLiveData<List<AccountRecordModel>> accountsData;
	private DataManager mDataManager;

	private boolean loading = false;


	public AccountsDynamicLoaderViewModel(@NonNull Application application) {
		super(application);
		accountsData = new MutableLiveData<>();
		accountsData.setValue(new ArrayList<>());
		mDataManager = DataManager.getInstance(application);
	}

	public boolean isLoading(){
		return loading;
	}

	public MutableLiveData<List<AccountRecordModel>> getAccountsData() {
		return accountsData;
	}

	public void loadAccounts(int offset) {
		loading = true;
		getAccounts(offset)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<List<AccountRecordModel>>() {
					@Override
					public void onSubscribe(Disposable d) {}

					@Override
					public void onNext(List<AccountRecordModel> accountRecordModels) {
						accountsData.setValue(accountRecordModels);
						loading = false;
					}

					@Override
					public void onError(Throwable e) {}

					@Override
					public void onComplete() {}
				});
	}

	private Observable<List<AccountRecordModel>> getAccounts(int offset) {
		return Observable.create(emitter -> {
			int lastId;
			List<AccountRecordModel> data = accountsData.getValue();
			if(data.size() > 0){
				lastId = Integer.parseInt(data.get(data.size()-1).getRecord().get(DataBaseHelper.ACCOUNT_ID));
			}else{
				lastId = 0;
			}
			data.addAll(mDataManager.getAccounts(lastId, offset));
			emitter.onNext(data);
		});
	}

}
