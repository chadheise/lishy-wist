package com.lishyWist.model;

public interface ObjectStore {

	User getUser(String userId);

	void putUser(User user);

	Item getItem(String itemId);

	void putItem(Item item);

	WishList getWishList(String wishListId);

	void putWishList(WishList wishList);

}
