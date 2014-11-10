//지은이 서왕덕
package com.avalon.client.entry;

import java.io.IOException;
import java.util.HashMap;

import com.avalon.client.social.FBManager;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.Facebook;
import com.restfb.types.User;

public class UserEntry {
	private String email;
	private String ACCESS_KEY;
	private int win;
	private int lose;
	private String score;//승/패 표시 example : 9/4
	private String state; // 0 :  대기중  1 :  게임중
	private String id;
	private String name;
	
	ObjectMapper om = new ObjectMapper();
	
	@Facebook
	public User fbuser;
	
	public String getACCESS_KEY() {
		return ACCESS_KEY;
	}

	public void setACCESS_KEY(String aCCESS_KEY) {
		ACCESS_KEY = aCCESS_KEY;
	}


	public void setScore(String score) {
		this.score = score;
	}

	public UserEntry(){
		ACCESS_KEY = "";
		email = "";
		win = 0;
		lose = 0;
		state = "오프라인";
		id = "";
		name="";
		updateScore();
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public UserEntry(String email, int win, int lose) {
		ACCESS_KEY = "";
		this.email=email;
		this.win=win;
		this.lose=lose;
		id = "";
		name="";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getFbuser() {
		return fbuser;
	}

	public void setFbuser(User fbuser) {
		this.fbuser = fbuser;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public String getScore() {
		return score;
	}

	public void updateScore() {
		this.score = win+"/"+lose;
	}
	
	public String getJSON(FBManager fbManager) {
		
		
		
		try {
			HashMap<String,String> msg = new HashMap<String,String>();
			
			msg.put("NAME", name);
			msg.put("ID", id);
			msg.put("EMAIL", email);
			msg.put("ACCESS_KEY", ACCESS_KEY);
			msg.put("WIN", ""+win);
			msg.put("LOSE", ""+lose);
			msg.put("STATE", state);
			msg.put("FBUSER", fbManager.getJSON(fbuser));
			return om.writeValueAsString(msg);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String getJSON2() {
		
		
		
		try {
			HashMap<String,String> msg = new HashMap<String,String>();
			
			msg.put("NAME", name);
			msg.put("ID", id);
			msg.put("EMAIL", email);
			msg.put("ACCESS_KEY", ACCESS_KEY);
			msg.put("WIN", ""+win);
			msg.put("LOSE", ""+lose);
			msg.put("STATE", state);
			//msg.put("FBUSER", fbManager.getJSON(fbuser));
			return om.writeValueAsString(msg);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void setFromJSON(FBManager fbManager, String jsonStr) {
		HashMap<String, String> m;
		try {
			m = om.readValue(jsonStr, new TypeReference<HashMap<String, String>>(){});
			
			this.id = m.get("ID");
			this.name = m.get("NAME");
			this.email = m.get("EMAIL");
			this.ACCESS_KEY = m.get("ACCESS_KEY");
			this.win = Integer.parseInt(m.get("WIN"));
			this.lose = Integer.parseInt(m.get("LOSE"));
			this.state = m.get("STATE");
			this.fbuser = fbManager.getFbUser(m.get("FBUSER"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void setFromJSON2(String jsonStr) {
		HashMap<String, String> m;
		try {
			m = om.readValue(jsonStr, new TypeReference<HashMap<String, String>>(){});
			
			this.id = m.get("ID");
			this.name = m.get("NAME");
			this.email = m.get("EMAIL");
			this.ACCESS_KEY = m.get("ACCESS_KEY");
			this.win = Integer.parseInt(m.get("WIN"));
			this.lose = Integer.parseInt(m.get("LOSE"));
			this.state = m.get("STATE");
			//this.fbuser = fbManager.getFbUser(m.get("FBUSER"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
