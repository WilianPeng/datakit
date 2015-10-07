package com.datakit.dao.impl;

import org.springframework.stereotype.Repository;

import com.datakit.dao.BaseRedisDao;
import com.datakit.dao.IAccountDao;
import com.datakit.entity.Account;

@Repository("accountDao")
@SuppressWarnings("all")
public class AccountDao extends BaseRedisDao<String, Account> implements IAccountDao{

	@Override
	public boolean add(Account account) {
		sendMessage("account", account);
		return true;
	}

}
