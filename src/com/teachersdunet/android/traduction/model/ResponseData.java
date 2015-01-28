package com.teachersdunet.android.traduction.model;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
	
	@SerializedName("translatedText")
	private String translatedText;
	
	public String getTranslatedText(){
		return this.translatedText;
	}
}
