package com.datakit.service.account;

import java.util.List;

import com.datakit.entity.Account;

public interface AccountService {

	public void saveAccount(Account account);
	
	public void updateUser(Account account);
	
	public Account findAccountById(int id);
	
	public void deleteUser(Account account);
	
	public List<Account> findAllList();
	
	public Account findAccountByNameAndPassword(String username, String password);
}
