package com.fimuni.jury;

public class Jury {
	int _id;
	String _name;
	String _ip;
	
	public Jury() {

	}

	public Jury(int id, String name, String ip){
		this._id = id;
		this._name = name;
		this._ip = ip;
	}

	public Jury(String name, String ip){
		this._name = name;
		this._ip = ip;
	}
	
	// get ID
	public int getID() {
		return this._id;
	}

	// set id
	public void setID(int id) {
		this._id = id;
	}

	// get name
	public String getName() {
		return this._name;
	}

	// set name
	public void setName(String name) {
		this._name = name;
	}
	
	// get ip
	public String getIp() {
		return this._ip;
	}

	// set ip
	public void setIp(String ip) {
		this._ip = ip;
	}
}