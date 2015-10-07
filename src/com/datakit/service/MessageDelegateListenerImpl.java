package com.datakit.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.datakit.dao.BaseHibernateDAO;
import com.datakit.entity.Account;
import com.datakit.service.account.AccountService;

public class MessageDelegateListenerImpl implements MessageDelegateListener {
	@Resource
	private AccountService userService;
    @Override
    public void handleMessage(Serializable message) {
        //什么都不做,只输出
        if(message == null){
            System.out.println("null");
        } else if(message.getClass().isArray()){
            System.out.println(Arrays.toString((Object[])message));
        } else if(message instanceof List<?>) {
            System.out.println(message);
        } else if(message instanceof Map<? , ?>) {
            System.out.println(message);
        } else {
        	if(message instanceof Account)
        	{
        		userService.saveAccount((Account)message);
        	}
            //System.out.println(ToStringBuilder.reflectionToString(message));
        }
    }
}