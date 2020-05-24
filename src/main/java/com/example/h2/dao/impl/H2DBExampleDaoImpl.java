/**
 * 
 */
package com.example.h2.dao.impl;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import com.example.h2.dao.H2DBExampleDao;
import com.example.h2.util.InsertRecordIntoDB;

/**
 * @author H4R5H
 *
 */

@Repository
public class H2DBExampleDaoImpl implements H2DBExampleDao {

	private static final String CREATE_STUDENT_TABLE = "CREATE TABLE STUDENT(STUDID INT(10), STUD_NAME VARCHAR(1000))";
	private static final String INSERT_STUDENT = "INSERT INTO STUDENT VALUES(1,'hARSH')";
	private Environment env;
	private JdbcTemplate h2JdbcTemplate;
	private Logger log = Logger.getLogger(H2DBExampleDaoImpl.class);

	/**
	 * @param env
	 * @param h2JdbcTemplate
	 */
	@Autowired
	public H2DBExampleDaoImpl(Environment env, JdbcTemplate h2JdbcTemplate) {
		this.env = env;
		this.h2JdbcTemplate = h2JdbcTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.h2.dao.H2DBExampleDao#setInsertIntoH2DB()
	 */
	@Override
	public Boolean setInsertIntoH2DB() throws Exception {
		log.info("Entered into H2DbExample Dao Impl Class...");
		h2JdbcTemplate = getJdbcTemplate();
		h2JdbcTemplate.execute(CREATE_STUDENT_TABLE);
//		int j = h2JdbcTemplate.update(INSERT_STUDENT);
		CopyOnWriteArrayList<String> uniNameList = new CopyOnWriteArrayList<>();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<5;i++) {
			String uniqueName = UUID.randomUUID().toString().substring(0,8);
//			System.out.println("*****  "+uniqueName);
			uniNameList.add(uniqueName);
			Thread t = new Thread(new InsertRecordIntoDB(uniqueName,h2JdbcTemplate,sb));
			log.debug("Entered in Multi Threading loop...");
			t.start();
			t.join();
		}
		
//		System.out.println(sb);
		return true;
	}

	private JdbcTemplate getJdbcTemplate() throws SQLException {

		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(env.getProperty("h2.connection.datasource"));
		dmds.setUrl(env.getProperty("h2.connection.url") + UUID.randomUUID().toString().replaceAll("-", ""));
		dmds.setUsername(env.getProperty("h2.connection.username"));
		dmds.setPassword(env.getProperty("h2.connection.password"));
		JdbcTemplate h2HdbcTempate = new JdbcTemplate(dmds);
		return h2HdbcTempate;
	}

}
