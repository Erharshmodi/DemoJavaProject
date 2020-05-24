/**
 * 
 */
package com.example.h2.service;

import java.lang.reflect.InvocationTargetException;

/**
 * @author H4R5H
 *
 */

// @Service
public interface H2DBExampleService {

	public Boolean getInsertDatIntoH2() throws Exception;

	public String getGraphData(String pkgName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException;
}
