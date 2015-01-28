package com.teachersdunet.android.traduction.model;

import com.google.gson.annotations.SerializedName;

public class Result {
	@SerializedName("responseData")
	ResponseData responseData;
	
	public ResponseData getResponseData(){
		return responseData;
	}
	
}
