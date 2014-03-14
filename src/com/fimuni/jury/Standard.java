package com.fimuni.jury;

public class Standard {

	int _id;
	String _breed;
	String _head;
	String _body;
	String _eyes;
	String _ears;
	String _coat;
	String _tail;
	String _condition;

	// Empty constructor
	public Standard() {

	}

	// constructor
	public Standard(String _breed, String _head, String _body, String _eyes, String _ears, String _coat,
			String _tail, String _condition) {
		this._breed = _breed;
		this._head = _head;
		this._eyes = _eyes;
		this._ears = _ears;
		this._body = _body;
		this._coat = _coat;
		this._tail = _tail;
		this._condition = _condition;
	}

	// constructor
	public Standard(int _id, String _breed, String _head, String _eyes, String _ears, String _body, String _coat,
			String _tail, String _condition) {
		this._id = _id;
		this._breed = _breed;
		this._head = _head;
		this._eyes = _eyes;
		this._ears = _ears;
		this._body = _body;
		this._coat = _coat;
		this._tail = _tail;
		this._condition = _condition;
	}
	
	// get ID
	public int getId() {
		return this._id;
	}

	public String getBreed() {
		return this._breed;
	}

	// get head
	public String getHead() {
		return this._head;
	}
	
	// get eyes
	public String getEyes() {
		return this._eyes;
	}

	// get ears
	public String getEars() {
		return this._ears;
	}

	// get body
	public String getBody() {
		return this._body;
	}
	
	// get coat
	public String getCoat() {
		return this._coat;
	}

	// get tail
	public String getTail() {
		return this._tail;
	}

	// get condition
	public String getCondition() {
		return this._condition;
	}
}