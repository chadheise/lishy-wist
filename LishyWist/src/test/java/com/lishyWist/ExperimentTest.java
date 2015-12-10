package com.lishyWist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lishyWist.config.FileSystemScopeMapping;
import com.lishyWist.config.ScopeMapping;
import com.lishyWist.model.Item;
import com.lishyWist.model.User;

import scala.annotation.meta.setter;

public class ExperimentTest {

	@Test
	public void testItemSerialization() {
		Gson gson = new Gson();
		Item item = new Item();
		item.setName("red ball");
		item.setNote("A giant red ball, 3 feet in diameter");
		item.setUrl("www.gaintredball.com");
		System.out.println(gson.toJson(item));

	}

	@Test
	public void FileSystemDataStoreTest() {
		String rootDirectory = "/Users/chadheise/Documents/programming/lishyWist/db";

		DataStore store = new FileSystemDataStore(rootDirectory);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Item item = new Item();
		item.setName("red ball");
		item.setNote("A giant red ball, 3 feet in diameter");
		item.setUrl("www.gaintredball.com");

		store.putData("myScope", "myKey", gson.toJson(item));
	}

	@Test
	public void FileSystemDataStoreTest2() {
		String rootDirectory = "/Users/chadheise/Documents/programming/lishyWist/db";
		DataStore store = new FileSystemDataStore(rootDirectory);

		ScopeMapping scopeMapping = new FileSystemScopeMapping();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		List<String> listIds = new ArrayList<>();
		listIds.add(UUID.randomUUID().toString());
		listIds.add(UUID.randomUUID().toString());

		User user = new User();
		user.setLists(listIds);

		store.putData(scopeMapping.getUsersScope(), user.getId(), gson.toJson(user));
	}

}
