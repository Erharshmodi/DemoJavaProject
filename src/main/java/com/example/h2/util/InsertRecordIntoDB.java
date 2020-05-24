/**
 * 
 */
package com.example.h2.util;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author H4R5H
 *
 */
public class InsertRecordIntoDB implements Runnable {

	String uniqueTableName;
	JdbcTemplate h2JdbcTemplate;
	StringBuffer sb;

	public InsertRecordIntoDB(String uniqueTableName, JdbcTemplate h2JdbcTemplate, StringBuffer sb) {
		this.uniqueTableName = uniqueTableName;
		this.h2JdbcTemplate = h2JdbcTemplate;
		this.sb =sb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
//		sb.append(" select * from STUDENT"+uniqueTableName+" unionAll");
//		h2JdbcTemplate.execute("CREATE TABLE STUDENT" + uniqueTableName + "(STUDID INT(10), STUD_NAME VARCHAR(1000))");

		int j = h2JdbcTemplate.update("INSERT INTO STUDENT VALUES(1,'hARSH')");
		System.out.println("*******     "+j);
	}

}
