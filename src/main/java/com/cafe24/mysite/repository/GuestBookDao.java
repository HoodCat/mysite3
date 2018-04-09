package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(GuestBookVo vo) {
	    System.out.println(vo);
	    int count = sqlSession.insert("guestbook.insert",vo);
	    System.out.println(vo);
		return count;
	}
	
	public GuestBookVo get(Long no) {
	    return sqlSession.selectOne("guestbook.getByNo", no);
	}
	
	public boolean delete(GuestBookVo vo) {
	    Map<String, Object> map = new HashMap<>();
	    
	    map.put("no", vo.getNo());
	    map.put("pwd", vo.getPassword());
	    
		return sqlSession.delete("guestbook.delete", map)==1;
	}
	
	public List<GuestBookVo> getList() {
		return sqlSession.selectList("guestbook.getList");
	}
	
	public List<GuestBookVo> getList(Long no) {
	    return sqlSession.selectList("guestbook.getListByNo", no);
	}
}
