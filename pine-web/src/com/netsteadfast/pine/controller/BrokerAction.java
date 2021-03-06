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
package com.netsteadfast.pine.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netsteadfast.base.Constants;
import com.netsteadfast.base.model.DefaultResult;
import com.netsteadfast.pine.service.IBrokerService;
import com.netsteadfast.pine.util.BrokerUtils;
import com.netsteadfast.po.PiBroker;
import com.netsteadfast.vo.BrokerVO;

@Controller
public class BrokerAction {
	
	private IBrokerService<BrokerVO, PiBroker, String> brokerService;
	
	public IBrokerService<BrokerVO, PiBroker, String> getBrokerService() {
		return brokerService;
	}

	@Autowired
	@Resource(name="pine.service.BrokerService")
	@Required		
	public void setBrokerService(IBrokerService<BrokerVO, PiBroker, String> brokerService) {
		this.brokerService = brokerService;
	}
	
	@RequestMapping(value = "historyStatistics.do")
	public ModelAndView historyStatistics() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(Constants.PAGE_MESSAGE, "");
		List<BrokerVO> brokers = null;
		try {
			DefaultResult<List<BrokerVO>> bResult = this.brokerService.findSimpleResult();
			if (bResult.getValue() != null) {
				brokers = bResult.getValue();
				BrokerUtils.checkStatus(brokers);
				BrokerUtils.checkHistoryStatistics(brokers);
			} else {
				mv.addObject(Constants.PAGE_MESSAGE, bResult.getSystemMessage().getValue());
			}			
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject(Constants.PAGE_MESSAGE, e.getMessage().toString());
		}
		mv.setViewName("broker/historyStatistics");
		mv.addObject("brokers", brokers);
		return mv;		
	}

	@RequestMapping(value = "brokerList.do")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(Constants.PAGE_MESSAGE, "");
		List<BrokerVO> brokers = null;
		try {
			DefaultResult<List<BrokerVO>> bResult = this.brokerService.findSimpleResult();
			if (bResult.getValue() != null) {
				brokers = bResult.getValue();
				BrokerUtils.checkStatus(brokers);
			} else {
				mv.addObject(Constants.PAGE_MESSAGE, bResult.getSystemMessage().getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject(Constants.PAGE_MESSAGE, e.getMessage().toString());
		}
		mv.setViewName("broker/brokerList");
		mv.addObject("brokers", brokers);
		return mv;
	}
	
	@RequestMapping(value = "brokerCreate.do")
	public String create() {
		return "broker/brokerCreate";
	}
	
	@RequestMapping(value = "brokerEdit.do")
	public ModelAndView edit(@RequestParam(name="oid") String oid) {
		String viewPage = "system/searchNoData";
		ModelAndView mv = new ModelAndView();
		mv.addObject(Constants.PAGE_MESSAGE, "");		
		BrokerVO broker = null;
		try {
			broker = new BrokerVO();
			broker.setOid(oid);
			DefaultResult<BrokerVO> result = this.brokerService.findObjectByOid(broker);
			if (result.getValue() != null) {
				broker = result.getValue();
				viewPage = "broker/brokerEdit";
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject(Constants.PAGE_MESSAGE, e.getMessage().toString());
		}
		mv.setViewName( viewPage );
		mv.addObject("broker", broker);
		return mv;		
	}
	
}
