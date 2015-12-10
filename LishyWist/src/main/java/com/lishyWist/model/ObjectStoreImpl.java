package com.lishyWist.model;

import com.google.gson.Gson;
import com.lishyWist.DataStore;
import com.lishyWist.config.ScopeMapping;

public class ObjectStoreImpl implements ObjectStore {

	private final DataStore dataStore;
	private final ScopeMapping scopeMapping;
	private final Gson gson;

	public ObjectStoreImpl(final DataStore dataStore, final ScopeMapping scopeMapping, final Gson gson) {
		this.dataStore = dataStore;
		this.scopeMapping = scopeMapping;
		this.gson = gson;
	}

	@Override
	public User getUser(final String userId) {
		String userJson = dataStore.getData(scopeMapping.getUsersScope(), userId);
		return gson.fromJson(userJson, User.class);
	}

	@Override
	public void putUser(final User user) {
		dataStore.putData(scopeMapping.getUsersScope(), user.getId(), gson.toJson(user));
	}

	@Override
	public Item getItem(final String itemId) {
		String itemJson = dataStore.getData(scopeMapping.getItemsScope(), itemId);
		return gson.fromJson(itemJson, Item.class);
	}

	@Override
	public void putItem(final Item item) {
		dataStore.putData(scopeMapping.getItemsScope(), item.getId(), gson.toJson(item));
	}

	@Override
	public WishList getWishList(final String wishListId) {
		String wishListJson = dataStore.getData(scopeMapping.getListsScope(), wishListId);
		return gson.fromJson(wishListJson, WishList.class);
	}

	@Override
	public void putWishList(final WishList wishList) {
		dataStore.putData(scopeMapping.getListsScope(), wishList.getId(), gson.toJson(wishList));
	}

}
