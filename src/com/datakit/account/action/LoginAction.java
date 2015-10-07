package com.datakit.account.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.datakit.entity.Account;
import com.datakit.service.account.AccountService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author wilian
 * 账户域的Action
 */
@Controller
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private AccountService userService;
	
	private String username;
	private String password;
	
	/**
	 * 登录操作
	 * @return
	 */
	public String login(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Account user = userService.findAccountByNameAndPassword(username, password);
		if (user != null) {
			request.setAttribute("username", username);
			return SUCCESS;
		} else {
			return ERROR;
		}
			
	}
	/**
	 * 注册操作
	 * @return
	 */
	public String register()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Account account = new Account();
		account.setUserName(username);
		account.setPassword(password);
		userService.saveAccount(account);
		Account user = userService.findAccountByNameAndPassword(username, password);
		if (user != null) {
			request.setAttribute("username", username);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
