package com.lishyWist.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lishyWist.DataStore;
import com.lishyWist.FileSystemDataStore;
import com.lishyWist.model.Item;
import com.lishyWist.model.ObjectStore;
import com.lishyWist.model.ObjectStoreImpl;
import com.lishyWist.model.User;
import com.lishyWist.model.WishList;

@Configuration
public class AppConfig {

	@Bean
	Gson gson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	@Bean
	DataStore dataStore() {
		String rootDirectory = "/Users/chadheise/Documents/programming/lishyWist/db";
		return new FileSystemDataStore(rootDirectory);
	}

	@Bean
	ScopeMapping scopeMapping() {
		return new FileSystemScopeMapping();
	}

	@Bean
	ObjectStore objectProvider() {
		return new ObjectStoreImpl(dataStore(), scopeMapping(), gson());
	}

	// TODO: Remove this when done experimenting
	@PostConstruct
	private void setupDB() {
		System.out.println("Calling setup");
		ObjectStore objectProvider = objectProvider();

		Item itemA1 = new Item("item a1", "note a1", "url a1");
		itemA1.setId("a1");
		objectProvider.putItem(itemA1);

		Item itemA2 = new Item("item a2", "note a2", "url a2");
		itemA2.setId("a2");
		itemA2.setIsPurchased(true);
		objectProvider.putItem(itemA2);

		List<String> listA = new ArrayList<>();
		listA.add("a1");
		listA.add("a2");
		WishList wishList = new WishList();
		wishList.setId("a");
		wishList.setItems(listA);
		wishList.setName("Chad's wonderful wish list");
		Set<String> listAViewers = new HashSet<String>();
		listAViewers.add("john");
		// TODO: Add validation when setting a viewer that the viewer is a friend
		wishList.setViewers(listAViewers);

		User chad = new User();
		chad.setId("chad");
		chad.setName("Chad Heise");
		List<String> listIds = new ArrayList<>();
		// TODO: Add verification that a user is not adding a list already owned by another user
		listIds.add("a");
		chad.setLists(listIds);
		List<String> chadFriends = new ArrayList<>();
		chadFriends.add("john");
		chad.setFriends(chadFriends);

		wishList.setOwner(chad.getId());

		User john = new User();
		john.setId("john");
		john.setName("John Doe");
		List<String> johnFriends = new ArrayList<>();
		johnFriends.add("chad");
		john.setFriends(johnFriends);
		objectProvider.putUser(john);
		
		objectProvider.putWishList(wishList);
		objectProvider.putUser(chad);
	}

}
