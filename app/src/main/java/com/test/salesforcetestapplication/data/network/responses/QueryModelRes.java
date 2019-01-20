package com.test.salesforcetestapplication.data.network.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QueryModelRes {

	@SerializedName("totalSize")
	@Expose
	private int totalSize;
	@SerializedName("done")
	@Expose
	private boolean done;
	@SerializedName("nextRecordsUrl")
	@Expose
	private String nextRecordsUrl;
	@SerializedName("records")
	@Expose
	private List<Record> records = null;

	public List<Record> getRecords() {
		return records;
	}


	public class Record {

		@SerializedName("attributes")
		@Expose
		private Attributes attributes;
		@SerializedName("AccountId__c")
		@Expose
		public String accountIdC;
		@SerializedName("ConnectionReceivedId")
		@Expose
		public String connectionReceivedId;
		@SerializedName("ConnectionSentId")
		@Expose
		public String connectionSentId;
		@SerializedName("CreatedById")
		@Expose
		public String createdById;
		@SerializedName("CreatedDate")
		@Expose
		public String createdDate;
		@SerializedName("CurrencyIsoCode")
		@Expose
		public String currencyIsoCode;
		@SerializedName("Description__c")
		@Expose
		public String descriptionC;
		@SerializedName("EndDate__c")
		@Expose
		public String endDateC;
		@SerializedName("Geolocation__c")
		@Expose
		public String geolocationC;
		@SerializedName("Id")
		@Expose
		public String id;
		@SerializedName("IsApproved__c")
		@Expose
		public boolean isApprovedC;
		@SerializedName("IsDeleted")
		@Expose
		public boolean isDeleted;
		@SerializedName("IsDone__c")
		@Expose
		public boolean isDoneC;
		@SerializedName("IsLocked__c")
		@Expose
		public boolean isLockedC;
		@SerializedName("LastActivityDate")
		@Expose
		public String lastActivityDate;
		@SerializedName("LastModifiedById")
		@Expose
		public String lastModifiedById;
		@SerializedName("LastModifiedDate")
		@Expose
		public String lastModifiedDate;
		@SerializedName("LastReferencedDate")
		@Expose
		public String lastReferencedDate;
		@SerializedName("LastViewedDate")
		@Expose
		public String lastViewedDate;
		@SerializedName("Name")
		@Expose
		public String name;
		@SerializedName("OwnerId")
		@Expose
		public String ownerId;
		@SerializedName("RecordTypeId")
		@Expose
		public String recordTypeId;
		@SerializedName("StartDate__c")
		@Expose
		public String startDateC;
		@SerializedName("Status__c")
		@Expose
		public String statusC;
		@SerializedName("Subject__c")
		@Expose
		public String subjectC;
		@SerializedName("SystemModstamp")
		@Expose
		public String systemModstamp;
	}

	public class Attributes {

		@SerializedName("type")
		@Expose
		private String type;
		@SerializedName("url")
		@Expose
		private String url;

	}

}
