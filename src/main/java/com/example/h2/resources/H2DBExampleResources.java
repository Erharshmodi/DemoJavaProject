/**
 * 
 */
package com.example.h2.resources;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.h2.service.H2DBExampleService;

/**
 * @author H4R5H
 *
 */
@RestController
@RequestMapping("api/")
public class H2DBExampleResources {

	private Logger log = LogManager.getLogger(H2DBExampleResources.class);
	private H2DBExampleService h2DbService;

	public H2DBExampleResources() {
		super();
	}

	@Autowired
	public H2DBExampleResources(H2DBExampleService h2DbService) {
		this.h2DbService = h2DbService;
	}

	@GetMapping("/default-action")
	public String defaultAction() {
		return "default-Action";
	}

	@GetMapping("/h2/insertData")
	public String insertDataIntoH2DB() throws Exception {
		log.info("Entered into H2DB Example Resource...");
		Boolean flag = h2DbService.getInsertDatIntoH2();
		if (flag)
			return "success";
		else
			return "fail";
	}
	
	@PostMapping("/reflection/gsec10year")
	public String getRefelctionCall(String pkgName) {
		String msg="";
		try {
			try {
				msg = h2DbService.getGraphData(pkgName);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return msg;
	}
}
