package com.avalon.client.entry;

import java.util.ArrayList;

public class StoneEntry {
	private int x;
	private int y;
	
	private String color_type;
	private int width;
	private int height;
	boolean bEnable;
	
	public StoneEntry(int x,int y,int width,int height,String color_type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.color_type = color_type; // Black White Gray
		bEnable = false;
	}

	public boolean isbEnable() {
		return bEnable;
	}

	public void setbEnable(boolean bEnable) {
		this.bEnable = bEnable;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor_type() {
		return color_type;
	}

	public void setColor_type(String color_type) {
		this.color_type = color_type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
