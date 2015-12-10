package com.lishyWist.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class WishList {

	private String id = UUID.randomUUID().toString();
	private String owner;
	private String name;
	private List<String> items = new ArrayList<>(); // TODO: Should this be a set?
	private Set<String> viewers = new HashSet<>(); // User id's of viewers

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(final String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(final List<String> items) {
		this.items = items;
	}
	
	public Set<String> getViewers() {
		return viewers;
	}

	public void setViewers(final Set<String> viewers) {
		this.viewers = viewers;
	}

}
