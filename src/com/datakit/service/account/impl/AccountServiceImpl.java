package com.datakit.service.account.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datakit.dao.BaseHibernateDAO;
import com.datakit.entity.Account;
import com.datakit.service.account.AccountService;

@Service("userService")
public class AccountServiceImpl implements AccountService {
	
	@Resource
	private BaseHibernateDAO<Account> baseDAO;

	@Override
	public void saveAccount(Account user) {
		baseDAO.save(user);
	}

	@Override
	public void updateUser(Account user) {
		baseDAO.update(user);
	}

	@Override
	public Account findAccountById(int id) {
		return baseDAO.get(Account.class, id);
	}

	@Override
	public void deleteUser(Account user) {
		baseDAO.delete(user);
	}

	@Override
	public List<Account> findAllList() {
		return baseDAO.find(" from Account u order by u.createTime");
	}

	@Override
	public Account findAccountByNameAndPassword(String username, String password) {
		return baseDAO.get(" from Account u where u.userName = ? and u.password = ? ", new Object[] { username, password });
	}

}
