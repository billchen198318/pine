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
package com.netsteadfast.pine.service;

import java.util.List;

import com.netsteadfast.base.exception.ServiceException;
import com.netsteadfast.base.model.DefaultResult;
import com.netsteadfast.base.service.IBaseService;
import com.netsteadfast.vo.PublishVO;

public interface IPublishService<T extends java.io.Serializable, E extends java.io.Serializable, PK extends java.io.Serializable> extends IBaseService<T, E, PK> {
	
	public static String MAPPER_ID_PO2VO="publish.po2vo";
	public static String MAPPER_ID_VO2PO="publish.vo2po";
	
	public DefaultResult<List<PublishVO>> findSimpleResult() throws ServiceException, Exception;
	
}
