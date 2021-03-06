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
package com.netsteadfast.pine.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netsteadfast.base.dao.IBaseDAO;
import com.netsteadfast.base.service.BaseService;
import com.netsteadfast.pine.dao.ISysCodeDAO;
import com.netsteadfast.pine.service.ISysCodeService;
import com.netsteadfast.po.TbSysCode;
import com.netsteadfast.vo.SysCodeVO;

@Service("pine.service.SysCodeService")
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class SysCodeServiceImpl extends BaseService<SysCodeVO, TbSysCode, String> implements ISysCodeService<SysCodeVO, TbSysCode, String> {
	protected Logger logger=Logger.getLogger(SysCodeServiceImpl.class);
	private ISysCodeDAO<TbSysCode, String> sysCodeDAO;
	
	public SysCodeServiceImpl() {
		super();
	}

	public ISysCodeDAO<TbSysCode, String> getSysCodeDAO() {
		return sysCodeDAO;
	}

	@Autowired
	@Resource(name="pine.dao.SysCodeDAO")
	@Required		
	public void setSysCodeDAO(ISysCodeDAO<TbSysCode, String> sysCodeDAO) {
		this.sysCodeDAO = sysCodeDAO;
	}

	@Override
	protected IBaseDAO<TbSysCode, String> getBaseDataAccessObject() {
		return sysCodeDAO;
	}

	@Override
	public String getMapperIdPo2Vo() {		
		return MAPPER_ID_PO2VO;
	}

	@Override
	public String getMapperIdVo2Po() {
		return MAPPER_ID_VO2PO;
	}

}
