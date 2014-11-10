//지은이 서왕덕
package com.avalon.client.entry;

public class ChannelEntry {
	private int roomNumber;//방번호
	private RoomEntry[] room; //방 엔트리 
	private boolean roomState; //대기중,플레이중 상태 표시 대기중 = false,플레이중 =true
	private int nowRoomNumber;//현재 채널에 있는 방갯수 최대 4
	public ChannelEntry(){
		roomNumber=0;
		room=new RoomEntry[5];
		roomState = false;
		nowRoomNumber=0;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomEntry[] getRoom() {
		return room;
	}

	public void setRoom(RoomEntry[] room) {
		this.room = room;
	}
	public void addRoom(RoomEntry room){   //외부로 부터 room을 받아 채널안에 있는 room 배열에 저장
		if(nowRoomNumber>4) return;
		else this.room[nowRoomNumber++]=room;
	}
	public void delRoom(){ //게임이 끝나고 방이 없어졌을시 room 제거
		
	}

	public boolean isRoomState() {
		return roomState;
	}

	public void setRoomState(boolean roomState) {
		this.roomState = roomState;
	}

	public int getNowRoomNumber() {
		return nowRoomNumber;
	}

	public void setNowRoomNumber(int nowRoomNumber) {
		this.nowRoomNumber = nowRoomNumber;
	}
	
	
}
