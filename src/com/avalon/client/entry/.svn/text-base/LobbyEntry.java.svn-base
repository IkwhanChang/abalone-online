package com.avalon.client.entry;

import java.util.ArrayList;

//  로비 객체
public class LobbyEntry {
	 private ArrayList rooms =  new ArrayList(); //  대기/플레이 중인 방들
	 private ArrayList users = new ArrayList(); //  대기/플레이 중인 유저들
	 
	 public LobbyEntry(){
		 
	 }

	public ArrayList getRooms() {
		return rooms;
	}
	
	public void addRoom(RoomEntry room){
		room.setNum(rooms.size()+1);
		this.rooms.add(room);
	}
	
	public void delRoom(int num) {
		this.rooms.remove(num);
	}

	public void setRooms(ArrayList rooms) {
		this.rooms = rooms;
	}
	
	 public void addUser(UserEntry user) {
		 this.users.add(user);
	 }

	public ArrayList getUsers() {
		return users;
	}

	public void setUsers(ArrayList users) {
		this.users = users;
	}
	 
	 
	
}
