package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}
	
	// 클래스, 메소드에 걸 수도 있다.
	@Transactional
	public void modify(UserVo vo) {
		userDao.update(vo);
	}
}
