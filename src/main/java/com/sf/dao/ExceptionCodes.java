package com.sf.dao;

public enum ExceptionCodes {
	  SQL_INTEGRITY_VOILATION(0, "SQL Integrity voilated."),
	  INVALID_SQL_SYNTAX(1, "SQL syntax seems to be incorrect"),
	  MISSING_PARAMETERS(2, "Some parameters are missing"),
	  EXCESS_VALUES(3,"too many values in the statement");

	  private final int id;
	  private final String msg;

	  ExceptionCodes(int id, String msg) {
	    this.id = id;
	    this.msg = msg;
	  }

	  public int getId() {
	    return this.id;
	  }

	  public String getMsg() {
	    return this.msg;
	  }
}
