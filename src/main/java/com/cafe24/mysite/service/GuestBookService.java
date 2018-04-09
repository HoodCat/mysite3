package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestBookDao;

	public List<GuestBookVo> getList() {
		return guestBookDao.getList();
	}
	
	public List<GuestBookVo> getMessageList(Long no) {
	    return guestBookDao.getList(no);
	}
	
	public void insert(GuestBookVo vo) {
		guestBookDao.insert(vo);
	}
	
	public GuestBookVo insertMessage(GuestBookVo vo) {
	    GuestBookVo result = null;
	    int count = guestBookDao.insert(vo);
	    if(count == 1) {
	        result = guestBookDao.get(vo.getNo());
	    }
	    return result;
	}
	
	public boolean delete(GuestBookVo vo) {
		return guestBookDao.delete(vo);
	}
}
