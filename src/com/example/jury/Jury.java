package com.example.jury;

public class Jury {
	int _id;
	String _name;
	
	public Jury() {

	}
	
	public Jury(int id, String name){
		this._id = id;
		this._name = name;
	}

	public Jury(String name){
		this._name = name;
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
}