package com.avalon.client.entry;

import java.io.IOException;
import java.util.HashMap;

import com.avalon.client.social.FBManager;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RoomEntry {
	private int num; //  방번호
	private String title;  //  방제목
	
	private int time; // 제한시간 분


	private boolean state; //게임 시작,시작 x 기본은 시작을 안했으므로 false
	private int wStone; // 백색돌 갯수
	private int bStone; // 흑색돌 갯수
	private int turn; 
	
	ObjectMapper om = new ObjectMapper();
	/*
	 턴의 상태입니다.
	 만약 게임이 시작안했으면 즉 state가 false라면 turn =0이고 백색돌이
	 먼저 시작하니까 백색의 turn 일때 turn =1 흑색의 turn 일때 turn =2가 되게합니다.
	 */
	public UserEntry me;
	public UserEntry enemy;
	
	private String owner; // 방장 ID
	
	private int numOfUser;
	public RoomEntry(){
		state=false;
		wStone =14;
		bStone =14;
		turn = 0;
		numOfUser=1;
		time = 5;
		owner = "";
	}
	
	public RoomEntry(UserEntry me){
		state=false;
		wStone =14;
		bStone =14;
		turn = 0;
		numOfUser=1;
		time = 5;
		num = -1;
		this.me = me;
		this.enemy = new UserEntry();
		owner = me.getId();
	}
	
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public UserEntry getMe() {
		return me;
	}

	public UserEntry getEnemy() {
		return enemy;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public int getNumOfUser() {
		return numOfUser;
	}

	public void setNumOfUser(int numOfUser) {
		this.numOfUser = numOfUser;
	}
	
	public void delUsers(){ //누군가 나갔을 시에 그 사람의 정보를 제거합니다.
		numOfUser=1;
	}

	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getwStone() {
		return wStone;
	}
	public void setwStone(int wStone) {
		this.wStone = wStone;
	}
	public int getbStone() {
		return bStone;
	}
	public void setbStone(int bStone) {
		this.bStone = bStone;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public void setMe(UserEntry me) {
		this.me = me;
	}
	
	public void setEnemy(UserEntry enemy) {
		this.enemy = enemy;
	}
	
	public String getJSON() {
		
		
		
		try {
			HashMap<String,String> msg = new HashMap<String,String>();
			msg.put("OWNER", owner);
			msg.put("NUM", ""+num);
			msg.put("TITLE", title);
			msg.put("TIME", ""+time);
			msg.put("ME", me.getJSON2());
			msg.put("ENEMY", enemy.getJSON2());
			
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
	
	public void setFromJSON(String jsonStr) {
		HashMap<String, String> m;
		try {
			m = om.readValue(jsonStr, new TypeReference<HashMap<String, String>>(){});
			this.owner = m.get("OWNER");
			this.num = Integer.parseInt(m.get("NUM"));
			this.title = m.get("TITLE");
			this.time = Integer.parseInt(m.get("TIME"));
			this.me = new UserEntry();
			this.enemy = new UserEntry();
			UserEntry temp = new UserEntry();
			
			temp.setFromJSON2(m.get("ME"));
			enemy.setFromJSON2(m.get("ENEMY"));
			
			if(enemy.getId().equals("")){
				me = temp;
			}else if(me.getId().equals(temp.getId())){
				me = temp;
				this.owner = me.getId();
			}else{
				me = enemy;
				enemy = temp;
				this.owner = temp.getId();
			}
			
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

