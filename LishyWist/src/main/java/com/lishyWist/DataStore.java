package com.lishyWist;

public interface DataStore {

	String getData(String scope, String key);

	void putData(String scope, String key, String data);

}
