package com.lishyWist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lishyWist.model.Item;
import com.lishyWist.model.ObjectStore;
import com.lishyWist.model.WishList;

@RestController
@RequestMapping("/v1/list/")
public class ListController {

	@Autowired
	Gson gson;

	@Autowired
	ObjectStore objectStore;

	/**
	 * Returns data for a list.
	 * 
	 * @param listId
	 * @return
	 */
	@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public String list(@RequestParam(value = "listId") String listId) {
		// TODO: Add authentication by user
		WishList wishList = objectStore.getWishList(listId);
		return gson.toJson(wishList);
	}

	/**
	 * Returns the items in a list.
	 * 
	 * @param listId
	 * @return
	 */
	@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
	public String user(@RequestParam(value = "listId") String listId) {
		// TODO: Add authentication by user
		WishList wishList = objectStore.getWishList(listId);

		// Convert item IDs to their data
		// TODO: Consider changing model to make this more efficient
		List<Item> items = new ArrayList<>();
		for (String itemId : wishList.getItems()) {
			Item item = objectStore.getItem(itemId);
			// Hide purchased state from the item's owner
			item.setIsPurchased(null);
			items.add(item);
		}

		return gson.toJson(items);
	}

	/**
	 * Returns the user id of a list's owner.
	 * 
	 * @param listId
	 * @return
	 */
	@RequestMapping(value = "/owner", produces = MediaType.APPLICATION_JSON_VALUE)
	public String owner(@RequestParam(value = "listId") String listId) {
		// TODO: Add authentication by user
		WishList wishList = objectStore.getWishList(listId);

		return gson.toJson(wishList.getOwner());
	}

	// TODO: Add POST to setViewers
	
}
