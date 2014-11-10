package com.avalon.client.social;

import java.util.ArrayList;

import com.avalon.client.entry.UserEntry;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FBManager {
	// 페이스북 연동에 필요한 토큰 및 객체
	private static String ACCESS_TOKEN ;
	static FacebookClient facebookClient;

	static FacebookClient publicOnlyFacebookClient;
	
	public FBManager(String ACCESS_TOKEN){
		this.ACCESS_TOKEN = ACCESS_TOKEN;
		
		facebookClient = new DefaultFacebookClient(ACCESS_TOKEN);
		publicOnlyFacebookClient = new DefaultFacebookClient();
		
		
	}
	
	public void postWall(String post) {
		FacebookType publishMessageResponse = null;
		
		try {
			publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, 
					Parameter.with("message", post));
		} catch(FacebookOAuthException e) {
			//error occur!!
		}
	}
	
	public UserEntry matchUser() {
		UserEntry user = new UserEntry();
		User fbuser = facebookClient.fetchObject("me", User.class);
		
		user.setACCESS_KEY(this.ACCESS_TOKEN);
		user.setName(fbuser.getName());
		user.setId(fbuser.getId());
		
		user.fbuser = fbuser;
		
		//user.setFbLink(fbuser.)
		return user;
	}
	
	public String getJSON(User user) {
		JsonObject result = facebookClient.fetchObject("me", JsonObject.class);
		return result.toString();
	}
	
	public User getFbUser(String json) {
		JsonMapper jsonMapper = new DefaultJsonMapper();
		User user = jsonMapper.toJavaObject(json, User.class);
		
		return user;
	}
	
	
}
