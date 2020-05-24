/**
 * 
 */
package com.example.h2.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.h2.dao.H2DBExampleDao;
import com.example.h2.service.H2DBExampleService;

/**
 * @author H4R5H
 *
 */
@Service
public class H2DBExampleServiceImpl implements H2DBExampleService {

	private H2DBExampleDao h2DbExampleDao;

	/**
	 * @param h2DbExampleDao
	 */
	@Autowired
	public H2DBExampleServiceImpl(H2DBExampleDao h2DbExampleDao) {
		this.h2DbExampleDao = h2DbExampleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.h2.service.H2DBExampleService#getInsertDatIntoH2()
	 */
	@Override
	public Boolean getInsertDatIntoH2() throws Exception {
		Boolean flag = h2DbExampleDao.setInsertIntoH2DB();
		return flag;
	}

	@Override
	public String getGraphData(String pkgName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		Class<?> cls = Class.forName(pkgName);
		Method method = cls.getDeclaredMethod("getGraphData");
		Object obj = method.invoke(cls.newInstance(), null);
		return obj.toString();
//		return null;
	}

}
