package com.test.salesforcetestapplication.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DescribeModelRes {

	@SerializedName("actionOverrides")
	@Expose
	private List<ActionOverride> actionOverrides = null;
	@SerializedName("activateable")
	@Expose
	private boolean activateable;
	@SerializedName("childRelationships")
	@Expose
	private List<ChildRelationship> childRelationships = null;
	@SerializedName("compactLayoutable")
	@Expose
	private boolean compactLayoutable;
	@SerializedName("createable")
	@Expose
	private boolean createable;
	@SerializedName("custom")
	@Expose
	private boolean custom;
	@SerializedName("customSetting")
	@Expose
	private boolean customSetting;
	@SerializedName("deletable")
	@Expose
	private boolean deletable;
	@SerializedName("deprecatedAndHidden")
	@Expose
	private boolean deprecatedAndHidden;
	@SerializedName("feedEnabled")
	@Expose
	private boolean feedEnabled;
	@SerializedName("fields")
	@Expose
	private List<Field> fields = null;
	@SerializedName("hasSubtypes")
	@Expose
	private boolean hasSubtypes;
	@SerializedName("isSubtype")
	@Expose
	private boolean isSubtype;
	@SerializedName("keyPrefix")
	@Expose
	private String keyPrefix;
	@SerializedName("label")
	@Expose
	private String label;
	@SerializedName("labelPlural")
	@Expose
	private String labelPlural;
	@SerializedName("layoutable")
	@Expose
	private boolean layoutable;
	@SerializedName("listviewable")
	@Expose
	private Object listviewable;
	@SerializedName("lookupLayoutable")
	@Expose
	private Object lookupLayoutable;
	@SerializedName("mergeable")
	@Expose
	private boolean mergeable;
	@SerializedName("mruEnabled")
	@Expose
	private boolean mruEnabled;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("namedLayoutInfos")
	@Expose
	private List<Object> namedLayoutInfos = null;
	@SerializedName("networkScopeFieldName")
	@Expose
	private Object networkScopeFieldName;
	@SerializedName("queryable")
	@Expose
	private boolean queryable;
	@SerializedName("recordTypeInfos")
	@Expose
	private List<RecordTypeInfo> recordTypeInfos = null;
	@SerializedName("replicateable")
	@Expose
	private boolean replicateable;
	@SerializedName("retrieveable")
	@Expose
	private boolean retrieveable;
	@SerializedName("searchLayoutable")
	@Expose
	private boolean searchLayoutable;
	@SerializedName("searchable")
	@Expose
	private boolean searchable;
	@SerializedName("supportedScopes")
	@Expose
	private List<SupportedScope> supportedScopes = null;
	@SerializedName("triggerable")
	@Expose
	private boolean triggerable;
	@SerializedName("undeletable")
	@Expose
	private boolean undeletable;
	@SerializedName("updateable")
	@Expose
	private boolean updateable;
	@SerializedName("urls")
	@Expose
	private Urls_ urls;

	public List<Field> getFields() {
		return fields;
	}


	public class Field {

		@SerializedName("aggregatable")
		@Expose
		private boolean aggregatable;
		@SerializedName("aiPredictionField")
		@Expose
		private boolean aiPredictionField;
		@SerializedName("autoNumber")
		@Expose
		private boolean autoNumber;
		@SerializedName("byteLength")
		@Expose
		private int byteLength;
		@SerializedName("calculated")
		@Expose
		private boolean calculated;
		@SerializedName("calculatedFormula")
		@Expose
		private Object calculatedFormula;
		@SerializedName("cascadeDelete")
		@Expose
		private boolean cascadeDelete;
		@SerializedName("caseSensitive")
		@Expose
		private boolean caseSensitive;
		@SerializedName("compoundFieldName")
		@Expose
		private Object compoundFieldName;
		@SerializedName("controllerName")
		@Expose
		private Object controllerName;
		@SerializedName("createable")
		@Expose
		private boolean createable;
		@SerializedName("custom")
		@Expose
		private boolean custom;
		@SerializedName("defaultValue")
		@Expose
		private Object defaultValue;
		@SerializedName("defaultValueFormula")
		@Expose
		private Object defaultValueFormula;
		@SerializedName("defaultedOnCreate")
		@Expose
		private boolean defaultedOnCreate;
		@SerializedName("dependentPicklist")
		@Expose
		private boolean dependentPicklist;
		@SerializedName("deprecatedAndHidden")
		@Expose
		private boolean deprecatedAndHidden;
		@SerializedName("digits")
		@Expose
		private int digits;
		@SerializedName("displayLocationInDecimal")
		@Expose
		private boolean displayLocationInDecimal;
		@SerializedName("encrypted")
		@Expose
		private boolean encrypted;
		@SerializedName("externalId")
		@Expose
		private boolean externalId;
		@SerializedName("extraTypeInfo")
		@Expose
		private Object extraTypeInfo;
		@SerializedName("filterable")
		@Expose
		private boolean filterable;
		@SerializedName("filteredLookupInfo")
		@Expose
		private Object filteredLookupInfo;
		@SerializedName("formulaTreatNullNumberAsZero")
		@Expose
		private boolean formulaTreatNullNumberAsZero;
		@SerializedName("groupable")
		@Expose
		private boolean groupable;
		@SerializedName("highScaleNumber")
		@Expose
		private boolean highScaleNumber;
		@SerializedName("htmlFormatted")
		@Expose
		private boolean htmlFormatted;
		@SerializedName("idLookup")
		@Expose
		private boolean idLookup;
		@SerializedName("inlineHelpText")
		@Expose
		private Object inlineHelpText;
		@SerializedName("label")
		@Expose
		private String label;
		@SerializedName("length")
		@Expose
		private int length;
		@SerializedName("mask")
		@Expose
		private Object mask;
		@SerializedName("maskType")
		@Expose
		private Object maskType;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("nameField")
		@Expose
		private boolean nameField;
		@SerializedName("namePointing")
		@Expose
		private boolean namePointing;
		@SerializedName("nillable")
		@Expose
		private boolean nillable;
		@SerializedName("permissionable")
		@Expose
		private boolean permissionable;
		@SerializedName("picklistValues")
		@Expose
		private List<PicklistValue> picklistValues = null;
		@SerializedName("polymorphicForeignKey")
		@Expose
		private boolean polymorphicForeignKey;
		@SerializedName("precision")
		@Expose
		private int precision;
		@SerializedName("queryByDistance")
		@Expose
		private boolean queryByDistance;
		@SerializedName("referenceTargetField")
		@Expose
		private Object referenceTargetField;
		@SerializedName("referenceTo")
		@Expose
		private List<String> referenceTo = null;
		@SerializedName("relationshipName")
		@Expose
		private String relationshipName;
		@SerializedName("relationshipOrder")
		@Expose
		private Object relationshipOrder;
		@SerializedName("restrictedDelete")
		@Expose
		private boolean restrictedDelete;
		@SerializedName("restrictedPicklist")
		@Expose
		private boolean restrictedPicklist;
		@SerializedName("scale")
		@Expose
		private int scale;
		@SerializedName("searchPrefilterable")
		@Expose
		private boolean searchPrefilterable;
		@SerializedName("soapType")
		@Expose
		private String soapType;
		@SerializedName("sortable")
		@Expose
		private boolean sortable;
		@SerializedName("type")
		@Expose
		private String type;
		@SerializedName("unique")
		@Expose
		private boolean unique;
		@SerializedName("updateable")
		@Expose
		private boolean updateable;
		@SerializedName("writeRequiresMasterRead")
		@Expose
		private boolean writeRequiresMasterRead;

		public String getName() {
			return name;
		}
	}

	public class ActionOverride {

		@SerializedName("formFactor")
		@Expose
		private String formFactor;
		@SerializedName("isAvailableInTouch")
		@Expose
		private boolean isAvailableInTouch;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("pageId")
		@Expose
		private String pageId;
		@SerializedName("url")
		@Expose
		private Object url;

	}

	public class ChildRelationship {

		@SerializedName("cascadeDelete")
		@Expose
		private boolean cascadeDelete;
		@SerializedName("childSObject")
		@Expose
		private String childSObject;
		@SerializedName("deprecatedAndHidden")
		@Expose
		private boolean deprecatedAndHidden;
		@SerializedName("field")
		@Expose
		private String field;
		@SerializedName("junctionIdListNames")
		@Expose
		private List<Object> junctionIdListNames = null;
		@SerializedName("junctionReferenceTo")
		@Expose
		private List<Object> junctionReferenceTo = null;
		@SerializedName("relationshipName")
		@Expose
		private String relationshipName;
		@SerializedName("restrictedDelete")
		@Expose
		private boolean restrictedDelete;

	}

	public class PicklistValue {

		@SerializedName("active")
		@Expose
		private boolean active;
		@SerializedName("defaultValue")
		@Expose
		private boolean defaultValue;
		@SerializedName("label")
		@Expose
		private String label;
		@SerializedName("validFor")
		@Expose
		private Object validFor;
		@SerializedName("value")
		@Expose
		private String value;

	}

	public class RecordTypeInfo {

		@SerializedName("active")
		@Expose
		private boolean active;
		@SerializedName("available")
		@Expose
		private boolean available;
		@SerializedName("defaultRecordTypeMapping")
		@Expose
		private boolean defaultRecordTypeMapping;
		@SerializedName("developerName")
		@Expose
		private String developerName;
		@SerializedName("master")
		@Expose
		private boolean master;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("recordTypeId")
		@Expose
		private String recordTypeId;
		@SerializedName("urls")
		@Expose
		private Urls urls;

	}

	public class SupportedScope {

		@SerializedName("label")
		@Expose
		private String label;
		@SerializedName("name")
		@Expose
		private String name;

	}

	public class Urls {

		@SerializedName("layout")
		@Expose
		private String layout;

	}

	public class Urls_ {

		@SerializedName("compactLayouts")
		@Expose
		private String compactLayouts;
		@SerializedName("rowTemplate")
		@Expose
		private String rowTemplate;
		@SerializedName("approvalLayouts")
		@Expose
		private String approvalLayouts;
		@SerializedName("uiDetailTemplate")
		@Expose
		private String uiDetailTemplate;
		@SerializedName("uiEditTemplate")
		@Expose
		private String uiEditTemplate;
		@SerializedName("defaultValues")
		@Expose
		private String defaultValues;
		@SerializedName("listviews")
		@Expose
		private String listviews;
		@SerializedName("describe")
		@Expose
		private String describe;
		@SerializedName("uiNewRecord")
		@Expose
		private String uiNewRecord;
		@SerializedName("quickActions")
		@Expose
		private String quickActions;
		@SerializedName("layouts")
		@Expose
		private String layouts;
		@SerializedName("sobject")
		@Expose
		private String sobject;

	}

}
