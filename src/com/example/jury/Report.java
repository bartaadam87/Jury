package com.example.jury;

public class Report {

	// private variables
	int _id;
	String _type;
	String _head;
	String _eyes;
	String _ears;
	String _coat;
	String _tail;
	String _condition;
	String _impress;
	String _comment;

	// Empty constructor
	public Report() {

	}

	// constructor
	public Report(int id, String type, String _head, String _eyes,
			String _ears, String _coat, String _tail, String _condition,
			String _impress, String _comment) {
		this._id = id;
		this._type = type;
		this._head = _head;
		this._eyes = _eyes;
		this._ears = _ears;
		this._coat = _coat;
		this._tail = _tail;
		this._condition = _condition;
		this._impress = _impress;
		this._comment = _comment;
	}
	
	// constructor
	public Report(String type, String _head, String _eyes,
			String _ears, String _coat, String _tail, String _condition,
			String _impress, String _comment) {
		this._type = type;
		this._head = _head;
		this._eyes = _eyes;
		this._ears = _ears;
		this._coat = _coat;
		this._tail = _tail;
		this._condition = _condition;
		this._impress = _impress;
		this._comment = _comment;
	}

	// get ID
	public int getID() {
		return this._id;
	}

	// set id
	public void setID(int id) {
		this._id = id;
	}

	// get type
	public String getType() {
		return this._type;
	}

	// set type
	public void setType(String type) {
		this._type = type;
	}
	
	// get head
	public String getHead() {
		return this._head;
	}

	// set head
	public void setHead(String head) {
		this._head = head;
	}
	
	// get eyes
	public String getEyes() {
		return this._eyes;
	}

	// set eyes
	public void setEyes(String eyes) {
		this._eyes = eyes;
	}
	
	// get ears
	public String getEars() {
		return this._ears;
	}

	// set ears
	public void setEars(String ears) {
		this._ears = ears;
	}
	
	// get coat
	public String getCoat() {
		return this._coat;
	}

	// set coat
	public void setCoat(String coat) {
		this._coat = coat;
	}
	
	// get tail
	public String getTail() {
		return this._tail;
	}

	// set tail
	public void setTail(String tail) {
		this._tail = tail;
	}
	
	// get condition
	public String getCondition() {
		return this._condition;
	}

	// set condition
	public void setCondition(String condition) {
		this._condition = condition;
	}
	
	// get impress
	public String getImpress() {
		return this._impress;
	}
	
	// set impress
	public void setImpress(String impress) {
		this._impress = impress;
	}
	
	// get comment
	public String getComment() {
		return this._comment;
	}
	
	// set comment
	public void setComment(String comment) {
		this._comment = comment;
	}
}