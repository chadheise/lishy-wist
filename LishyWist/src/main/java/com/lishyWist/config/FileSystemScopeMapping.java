package com.lishyWist.config;

public class FileSystemScopeMapping implements ScopeMapping {

	@Override
	public String getUsersScope() {
		return "users";
	}

	@Override
	public String getListsScope() {
		return "lists";
	}

	@Override
	public String getItemsScope() {
		return "items";
	}

}
