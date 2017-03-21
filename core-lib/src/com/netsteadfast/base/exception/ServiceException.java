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
package com.netsteadfast.base.exception;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ServiceException extends BaseSysException {
	private static final long serialVersionUID = -4978934722020704383L;
	protected Logger logger=Logger.getLogger(ServiceException.class);
	
	public ServiceException(String message) {
		super(message);		
	}
	
	public void log() {
		try {
			logger.error(ServiceException.class.getName() + " : " + super.getMessage());			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
