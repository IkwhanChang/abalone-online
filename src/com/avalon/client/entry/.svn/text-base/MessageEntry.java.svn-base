package com.avalon.client.entry;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//  채팅 메시지의  Entry
public class MessageEntry {

	private String from_id;
	private String to_id;
	private String message;
	
	
	ObjectMapper om = new ObjectMapper();
	
	public MessageEntry() {
		this.from_id = "";
		this.to_id = "";
		this.message = "";
	}
	
	public MessageEntry(String from, String to, String msg){
		this.from_id = from;
		this.to_id = to;
		this.message = msg;
	}

	
	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJSON() throws JsonGenerationException, JsonMappingException, IOException {
		
		HashMap<String,String> msg = new HashMap<String,String>();
		
		msg.put("FROM_ID", from_id);
		msg.put("TO_ID", to_id);
		msg.put("MESSAGE", message);
		
		return om.writeValueAsString(msg);
	}
	
	public void setFromJSON(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, String> m = om.readValue(jsonStr, new TypeReference<HashMap<String, String>>(){});
		this.from_id = m.get("FROM_ID");
		this.to_id = m.get("TO_ID");
		this.message = m.get("MESSAGE");
	}
	
}
