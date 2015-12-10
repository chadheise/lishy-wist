package com.lishyWist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lishyWist.config.ScopeMapping;
import com.lishyWist.model.User;

@RestController
@RequestMapping("/v1/")
public class LishyWistController {

	@Autowired
	Gson gson;

	@Autowired
	DataStore dataStore;

	@Autowired
	ScopeMapping scopeMapping;

	/**
	 * Returns the ids for all of a user's lists.
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/lists")
	public String greeting(@RequestParam(value = "userId") String userId) {
		String userJson = dataStore.getData(scopeMapping.getUsersScope(), userId);
		User user = gson.fromJson(userJson, User.class);

		return gson.toJson(user.getLists());
	}

}