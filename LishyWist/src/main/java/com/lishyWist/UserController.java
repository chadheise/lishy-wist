package com.lishyWist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lishyWist.model.Item;
import com.lishyWist.model.ObjectStore;
import com.lishyWist.model.User;
import com.lishyWist.model.WishList;

// TODO: Properly report exceptions (don't let exception details through to the end user)

@RestController
@RequestMapping("/v1/user/")
public class UserController {

	@Autowired
	Gson gson;

	@Autowired
	ObjectStore objectStore;

	/**
	 * Returns the ids for all of a user's lists.
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("")
	public String user(@RequestParam(value = "userId") String userId) {
		return gson.toJson(objectStore.getUser(userId));
	}

	/**
	 * Returns the ids for all of a user's lists.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/lists")
	public String lists(@RequestParam(value = "userId") String userId) {
		User user = objectStore.getUser(userId);
		return gson.toJson(user.getLists());
	}

	
	/**
	 * Returns all the items a user has in a list.
	 * 
	 * @param name
	 * @return
	 * 
	 */
	@RequestMapping("/items")
	public String items(@RequestParam(value = "userId") String userId) {
		// TODO: Implement - potentially reuse code from ListController /items
		return "not implemented";
	}
	/**
	 * Returns the ids for all of a user's friends.
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/friends")
	public String friends(@RequestParam(value = "userId") String userId) {
		User user = objectStore.getUser(userId);
		return gson.toJson(user.getFriends());
	}

	/**
	 * Returns a list of all items shared by a user with the current user
	 */
	@RequestMapping("friendList")
	public String friends(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "friendId") String friendId) {
		User user = objectStore.getUser(userId);
		User friend = objectStore.getUser(friendId);

		// Use map to dedupe items that may be in multiple lists
		Map<String, Item> items = new HashMap<>(); 

		// TODO: These nested for loops cause a huge branchout in the number of
		// data store calls

		// Get all of the friend's wish lists
		List<String> wishListIds = friend.getLists();
		for (String wishListId : wishListIds) {
			WishList wishList = objectStore.getWishList(wishListId);
			// If the current user is permitted to view the list
			if (wishList.getViewers().contains(user.getId())) {
				// Add all of the list's items on the list to the master list
				for (String itemId : wishList.getItems()) {
					items.put(itemId, objectStore.getItem(itemId));
				}
			}
		}

		return gson.toJson(items.values());
	}

}
