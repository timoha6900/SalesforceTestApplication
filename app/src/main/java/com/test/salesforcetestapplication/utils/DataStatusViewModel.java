package com.test.salesforcetestapplication.utils;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.test.salesforcetestapplication.data.managers.DataManager;

/**
 * Manages the process of creating and filling the Accounts table and downloading the necessary
 * data from the server. Notifies subscribers of process status changes.
 */
public class DataStatusViewModel extends AndroidViewModel {

	private CreatingAccountsLiveData creatingLiveData;
	private InsertingAccountsLiveData insertingLiveData;
	private MediatorLiveData<Constants.DataProcessingStatus> statusMediatorLiveData;
	private MutableLiveData<Constants.DataProcessingStatus> statusLiveData;


	public DataStatusViewModel(@NonNull Application application) {
		super(application);
		statusMediatorLiveData = new MediatorLiveData<>();
		statusLiveData = new MutableLiveData<>();
		statusMediatorLiveData.observeForever(mediatorStatusObserver);
		init(application);
	}

	private void init(Application application){
		statusMediatorLiveData.setValue(Constants.DataProcessingStatus.Waiting);

		DataManager dataManager = DataManager.getInstance(application);
		if(dataManager.isAccountTableExists()) {
			if(!dataManager.isAccountTableEmpty()) {
				statusMediatorLiveData.setValue(Constants.DataProcessingStatus.Success);
			} else {
				insertingLiveData = new InsertingAccountsLiveData(application);
				statusMediatorLiveData.addSource(insertingLiveData, insertingStatusObserver);
			}
		} else {
			creatingLiveData = new CreatingAccountsLiveData(application);
			insertingLiveData = new InsertingAccountsLiveData(application);

			statusMediatorLiveData.addSource(creatingLiveData, creatingStatusObserver);
		}
	}

	public void restart(Application application){
		init(application);
	}

	public MutableLiveData<Constants.DataProcessingStatus> getStatus() {
		return statusLiveData;
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		if(creatingLiveData != null) {
			statusMediatorLiveData.removeSource(creatingLiveData);
		}
		if(insertingLiveData != null) {
			statusMediatorLiveData.removeSource(insertingLiveData);
		}
		statusMediatorLiveData.removeObserver(mediatorStatusObserver);
	}

	private Observer<Constants.DataProcessingStatus> creatingStatusObserver = new Observer<Constants.DataProcessingStatus>() {
		@Override
		public void onChanged(@Nullable Constants.DataProcessingStatus status) {
			if(status != null && status != Constants.DataProcessingStatus.Waiting){
				if(status == Constants.DataProcessingStatus.Success){
					statusMediatorLiveData.addSource(insertingLiveData, insertingStatusObserver);
				}else{
					statusMediatorLiveData.setValue(status);
				}
			}
		}
	};

	private Observer<Constants.DataProcessingStatus> insertingStatusObserver = new Observer<Constants.DataProcessingStatus>() {
		@Override
		public void onChanged(@Nullable Constants.DataProcessingStatus status) {
			if(status != null && status != Constants.DataProcessingStatus.Waiting){
				statusMediatorLiveData.setValue(status);
			}
		}
	};

	private Observer<Constants.DataProcessingStatus> mediatorStatusObserver = new Observer<Constants.DataProcessingStatus>() {
		@Override
		public void onChanged(@Nullable Constants.DataProcessingStatus status) {
			statusLiveData.setValue(status);
		}
	};

}
