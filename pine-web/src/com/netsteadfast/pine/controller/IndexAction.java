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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.netsteadfast.base.Constants;
import com.netsteadfast.pine.util.ExampleSubscribe;

@Controller
public class IndexAction {
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
	@RequestMapping(value = "/exampleDashboard.do", method = RequestMethod.GET)
	public ModelAndView exampleDashboard() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(Constants.PAGE_MESSAGE, ExampleSubscribe.getErr());
		mv.addObject("power", ExampleSubscribe.getPower());
		mv.addObject("temp", ExampleSubscribe.getTemp());
		mv.setViewName( "dashboard/exampleDashboard" );
		return mv;
	}
	
}
