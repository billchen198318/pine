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
package com.netsteadfast.pine.base;

import java.io.IOException;
import java.util.Properties;

import com.netsteadfast.base.model.YesNo;

public class PineConfig {
	
	private static Properties properties = new Properties();
	
	static {
		try {
			properties.load( PineConfig.class.getClassLoader().getResourceAsStream("pine.properties") );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getScriptBaseDir() {
		return properties.getProperty("pine.scriptBaseDir");
	}
	
	public static String getServerConfigDir() {
		return properties.getProperty("pine.serverConfigDir");
	}
	
	public static boolean getEnableLog() {
		return YesNo.YES.equals(properties.getProperty("pine.enableLog"));
	}	
	
}
