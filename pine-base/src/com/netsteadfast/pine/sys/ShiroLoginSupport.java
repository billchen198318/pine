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
package com.netsteadfast.pine.sys;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.netsteadfast.base.AppContext;
import com.netsteadfast.base.model.DefaultResult;
import com.netsteadfast.base.model.YesNo;
import com.netsteadfast.pine.service.IAccountService;
import com.netsteadfast.pine.vo.AccountVO;
import com.netsteadfast.po.TbAccount;

public class ShiroLoginSupport {
	protected Logger logger = Logger.getLogger(ShiroLoginSupport.class);
	private IAccountService<AccountVO, TbAccount, String> accountService;
	
	public ShiroLoginSupport() {
		try {
			this.initServiceBeans();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initServiceBeans() throws Exception {
		accountService = (IAccountService<AccountVO, TbAccount, String>)AppContext.getBean("pine.service.AccountService");
	}
	
	public IAccountService<AccountVO, TbAccount, String> getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService<AccountVO, TbAccount, String> accountService) {
		this.accountService = accountService;
	}

	protected Subject getSubject(ServletRequest request, ServletResponse response) {
		return SecurityUtils.getSubject();
	}
	
	public Subject forceCreateLoginSubject(HttpServletRequest request, HttpServletResponse response, String accountId, String captchaStr) throws Exception {
		AccountVO account = this.queryUser( accountId );
		if ( account == null ) {
			logger.warn( "no accountId: " + accountId );
			throw new Exception( "no accountId: " + accountId );
		}
		request.getSession().setAttribute(GreenStepBaseFormAuthenticationFilter.DEFAULT_CAPTCHA_PARAM, captchaStr);
		GreenStepBaseUsernamePasswordToken token = new GreenStepBaseUsernamePasswordToken();
		token.setCaptcha( captchaStr );
		token.setUsername( account.getAccount() );
		token.setPassword( account.getPassword().toCharArray() );
		Subject subject = this.getSubject(request, response);
		subject.login(token);
		return subject;
	}
	
	public Subject forceCreateLoginSubject(String accountId) throws Exception {
		AccountVO account = this.queryUser( accountId );
		if ( account == null ) {
			logger.warn( "no accountId: " + accountId );
			throw new Exception( "no accountId: " + accountId );
		}
		Subject subject = SecurityUtils.getSubject();
		GreenStepBaseUsernamePasswordToken token = new GreenStepBaseUsernamePasswordToken();
		token.setCaptcha( "0123" );
		token.setUsername( account.getAccount() );
		token.setPassword( account.getPassword().toCharArray() );
		subject.login(token);
		return subject;
	}
	
	public AccountVO queryUserValidate(String account) throws Exception {
		AccountVO accountObj = this.queryUser(account);
		this.userValidate(accountObj);
		return accountObj;
	}
	
	public AccountVO queryUser(String account) throws Exception {
		if (StringUtils.isBlank(account)) {
			return null;
		}
		if (accountService == null) {
			logger.error( "no service bean: core.service.AccountService" );
			throw new Exception( "no service bean: core.service.AccountService" );
		}
		AccountVO accountObj = new AccountVO();
		accountObj.setAccount(account);		
		DefaultResult<AccountVO> result = accountService.findByUK(accountObj);
		if (result.getValue()==null) {
			return null;
		}
		accountObj = result.getValue();
		return accountObj;
	}
	
	private void userValidate(AccountVO account) throws Exception {
		if(account == null) {
			return;
		}
		if (!YesNo.YES.equals(account.getOnJob())) {
			throw new InvalidAccountException("Invalid account!");
		}
	}	
	
}
