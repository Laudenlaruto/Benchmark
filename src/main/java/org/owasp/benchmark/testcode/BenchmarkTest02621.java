/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Nick Sanidas <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest02621")
public class BenchmarkTest02621 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String queryString = request.getQueryString();
		String paramval = "vector"+"=";
		int paramLoc = queryString.indexOf(paramval);
		if (paramLoc == -1) {
			response.getWriter().println("getQueryString() couldn't find expected parameter '" + "vector" + "' in query string.");
			return;
		}
		String param = queryString.substring(paramLoc + paramval.length()); // 1st assume "vector" param is last parameter in query string.
		int ampersandLoc = queryString.indexOf("&", paramLoc);
		if (ampersandLoc != -1) {
			param = queryString.substring(paramLoc + paramval.length(), ampersandLoc);
		}
		param = java.net.URLDecoder.decode(param, "UTF-8");

		String bar = doSomething(param);
		
		String fileName = null;
		java.io.FileOutputStream fos = null;

		try {
			fileName = org.owasp.benchmark.helpers.Utils.testfileDir + bar;
	
			fos = new java.io.FileOutputStream(fileName);
	        response.getWriter().write("Now ready to write to file: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(fileName));
   		} catch (Exception e) {
			System.out.println("Couldn't open FileOutputStream on file: '" + fileName + "'");
//			System.out.println("File exception caught and swallowed: " + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();
                    fos = null;
				} catch (Exception e) {
					// we tried...
				}
			}
		}
	}  // end doPost
	
	private static String doSomething(String param) throws ServletException, IOException {

		String bar = "safe!";
		java.util.HashMap<String,Object> map86911 = new java.util.HashMap<String,Object>();
		map86911.put("keyA-86911", "a_Value"); // put some stuff in the collection
		map86911.put("keyB-86911", param); // put it in a collection
		map86911.put("keyC", "another_Value"); // put some stuff in the collection
		bar = (String)map86911.get("keyB-86911"); // get it back out
		bar = (String)map86911.get("keyA-86911"); // get safe value back out
	
		return bar;	
	}
}