package com.datakit.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;












import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.datakit.dao.IAccountDao;
import com.datakit.dao.IUserDao;
import com.datakit.entity.Account;
import com.datakit.entity.User;

import junit.framework.Assert;  

/** 
 * 测试
 * @version <b>1.0</b> 
 */  
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IAccountDao accountDao;
	@Test
	public void testAddUser() {
		User user = new User();
		user.setId("user1");
		user.setName("java2000_wl");
		boolean result = userDao.add(user);
		Assert.assertTrue(result);
	}
	@Test
    public void testPublishMessage() throws Exception {
        Account bean = new Account( );
        bean.setUserName("Pengdi");
        bean.setCreateTime(new Date());
        bean.setPassword("wilian");
        bean.setPhoneNumber("15914130049");
        accountDao.add(bean); //发布一个普通的javabean消息
    }
	@Test
    public void testReceiveMessage() throws Exception {
		while (true) { //这里是一个死循环,目的就是让进程不退出,用于接收发布的消息
            try {
                System.out.println("current time: " + new Date());
 
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	/**
	 * 批量新增 普通方式
	 * <br>------------------------------<br>
	 */
	@Test
	public void testAddUsers1() {
		List<User> list = new ArrayList<User>();
		for (int i = 10; i < 50000; i++) {
			User user = new User();
			user.setId("user" + i);
			user.setName("java2000_wl" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		for (User user : list) {
			userDao.add(user);
		}
		System.out.println(System.currentTimeMillis() -  begin);
	}
	
	/**
	 * 批量新增 pipeline方式
	 * <br>------------------------------<br>
	 */
	@Test
	public void testAddUsers2() {
		List<User> list = new ArrayList<User>();
		for (int i = 10; i < 1500000; i++) {
			User user = new User();
			user.setId("user" + i);
			user.setName("java2000_wl" + i);
			list.add(user);
		}
		long begin = System.currentTimeMillis();
		boolean result = userDao.add(list);
		System.out.println(System.currentTimeMillis() - begin);
		Assert.assertTrue(result);
	}
	
	/**
	 * 修改
	 * <br>------------------------------<br>
	 */
	@Test
	public void testUpdate() {
		User user = new User();
		user.setId("user1");
		user.setName("new_password");
		boolean result = userDao.update(user);
		Assert.assertTrue(result);
	}
	
	/**
	 * 通过key删除单个
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDelete() {
		String key = "user1";
		userDao.delete(key);
	}
	
	/**
	 * 批量删除
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDeletes() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("user" + i);
		}
		userDao.delete(list);
	}
	
	/**
	 * 获取
	 * <br>------------------------------<br>
	 */
	@Test
	public void testGetUser() {
		String id = "user1";
		User user = userDao.get(id);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getName(), "java2000_wl");
	}

	/**
	 * 设置userDao
	 * @param userDao the userDao to set
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
}