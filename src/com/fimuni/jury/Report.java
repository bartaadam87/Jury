package com.fimuni.jury;

public class Report {
	int _id;
	String _no;
	String _breed;
	String _code;
	String _cclass;
	String _sex;
	String _born;
	String _type;
	String _head;
	String _eyes;
	String _ears;
	String _coat;
	String _tail;
	String _condition;
	String _impress;
	String _comment;
	String _mark;
	String _rank;
	String _biv;
	String _nomination;
	String _note;
	String _title;
	String _reason;

	// Empty constructor
	public Report() {

	}

	// constructor
	public Report(int id, String no, String _breed, String _code,
			String _cclass, String _sex, String _born, String _type,
			String _head, String _eyes, String _ears, String _coat,
			String _tail, String _condition, String _impress, String _comment,
			String _mark, String _rank, String _biv, String _nomination,
			String _note, String _title, String _reason) {
		this._id = id;
		this._no = no;
		this._breed = _breed;
		this._code = _code;
		this._cclass = _cclass;
		this._sex = _sex;
		this._born = _born;
		this._type = _type;
		this._head = _head;
		this._eyes = _eyes;
		this._ears = _ears;
		this._coat = _coat;
		this._tail = _tail;
		this._condition = _condition;
		this._impress = _impress;
		this._comment = _comment;
		this._mark = _mark;
		this._rank = _rank;
		this._biv = _biv;
		this._nomination = _nomination;
		this._note = _note;
		this._title = _title;
		this._reason = _reason;
	}

	// constructor
	public Report(String no, String _breed, String _code, String _cclass,
			String _sex, String _born, String _type, String _head,
			String _eyes, String _ears, String _coat, String _tail,
			String _condition, String _impress, String _comment, String _mark,
			String _rank, String _biv, String _nomination, String _note,
			String _title, String _reason) {
		this._no = no;
		this._breed = _breed;
		this._code = _code;
		this._cclass = _cclass;
		this._sex = _sex;
		this._born = _born;
		this._type = _type;
		this._head = _head;
		this._eyes = _eyes;
		this._ears = _ears;
		this._coat = _coat;
		this._tail = _tail;
		this._condition = _condition;
		this._impress = _impress;
		this._comment = _comment;
		this._mark = _mark;
		this._rank = _rank;
		this._biv = _biv;
		this._nomination = _nomination;
		this._note = _note;
		this._title = _title;
		this._reason = _reason;
	}

	// get ID
	public int getID() {
		return this._id;
	}

	// set id
	public void setID(int id) {
		this._id = id;
	}

	// get no
	public String getNo() {
		return this._no;
	}

	// set no
	public void setNo(String no) {
		this._no = no;
	}

	// get breed
	public String getBreed() {
		return this._breed;
	}

	// set breed
	public void setBreed(String breed) {
		this._breed = breed;
	}

	// get code
	public String getCode() {
		return this._code;
	}

	// set code
	public void setCode(String code) {
		this._code = code;
	}

	// get cclass
	public String getCclass() {
		return this._cclass;
	}

	// set cclass
	public void setCclass(String cclass) {
		this._cclass = cclass;
	}

	// get sex
	public String getSex() {
		return this._sex;
	}

	// set sex
	public void setSex(String sex) {
		this._sex = sex;
	}

	// get born
	public String getBorn() {
		return this._born;
	}

	// set born
	public void setBorn(String born) {
		this._born = born;
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

	// set coat
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

	// get mark
	public String getMark() {
		return this._mark;
	}

	// set mark
	public void setMark(String mark) {
		this._mark = mark;
	}
	
	// get rank
	public String getRank() {
		return this._rank;
	}

	// set rank
	public void setRank(String rank) {
		this._rank = rank;
	}
	
	// get BIV
	public String getBiv() {
		return this._biv;
	}

	// set BIV
	public void setBiv(String biv) {
		this._biv = biv;
	}
	
	// get nomination
	public String getNomination() {
		return this._nomination;
	}

	// set nomination
	public void setNomination(String nomination) {
		this._nomination = nomination;
	}
	
	// get note
	public String getNote() {
		return this._note;
	}

	// set note
	public void setNote(String note) {
		this._note = note;
	}
	
	// get title
	public String getTitle() {
		return this._title;
	}

	// set title
	public void setTitle(String title) {
		this._title = title;
	}
	
	// get reason
	public String getReason() {
		return this._reason;
	}

	// set reason
	public void setReason(String reason) {
		this._reason = reason;
	}
}