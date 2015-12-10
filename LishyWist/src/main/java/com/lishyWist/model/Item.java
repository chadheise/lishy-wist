package com.lishyWist.model;

import java.util.UUID;

public class Item {

	private String id = UUID.randomUUID().toString();
	private String name;
	private String note;
	private String url;
	private Boolean isPurchased = false;

	public Item() {
		this(null, null, null);
	}

	public Item(final String name, final String note, final String url) {
		setName(name);
		setNote(note);
		setUrl(url);
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getNote() {
		return note;
	}

	public String getUrl() {
		return url;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setNote(final String note) {
		this.note = note;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Boolean getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(final Boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

}
