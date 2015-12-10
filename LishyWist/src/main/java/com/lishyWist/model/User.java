package com.lishyWist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

	private String id = UUID.randomUUID().toString();
	private String name;
	private List<String> lists = new ArrayList<>();
	private List<String> friends = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

}
