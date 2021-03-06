/* 
 * Copyright 2012-2017 netsteadfast of copyright Chen Xin Nien
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * -----------------------------------------------------------------------
 * 
 * author: 	Chen Xin Nien
 * contact: chen.xin.nien@gmail.com
 * 
 */
package com.netsteadfast.base.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.netsteadfast.base.Constants;

public class ScriptTypeCode {
	public static final String BSH = "BSH"; // beanShell2
	public static final String GROOVY = "GROOVY"; // groovy
	
	public static boolean isTypeCode(String type) {
		if (BSH.equals(type) || GROOVY.equals(type)) {
			return true;
		}
		return false;
	}
	
	public static Map<String, String> getTypeMap(boolean pleaseSelect) {
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		if (pleaseSelect) {
			dataMap.put(Constants.HTML_SELECT_NO_SELECT_ID, Constants.HTML_SELECT_NO_SELECT_NAME);
		}
		dataMap.put(BSH, "java ( BeanShell2 )");
		dataMap.put(GROOVY, "groovy");
		return dataMap;
	}	
	
}
