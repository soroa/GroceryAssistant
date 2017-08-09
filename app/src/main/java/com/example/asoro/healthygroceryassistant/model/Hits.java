package com.example.asoro.healthygroceryassistant.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Hits {


	@SerializedName("hits")
	List<Hit> mHits;
	@SerializedName("q")
	String mQuery;
	@SerializedName("more")
	boolean mMore;

	public boolean isMore() {
		return mMore;
	}

	public List<Hit> getHits() {
		return mHits;
	}


	public String getQuery() {
		return mQuery;
	}

}
