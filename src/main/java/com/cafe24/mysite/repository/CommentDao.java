package com.cafe24.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.CommentVo;

@Repository
public class CommentDao {
    
    @Autowired
    SqlSession sqlSession;

	public boolean insert(CommentVo vo) {
		return (sqlSession.insert("comment.insert", vo)==1);
	}

	public List<Map<String, Object>> getList(long boardNo) {
		return sqlSession.selectList("comment.getCommentListByBoardNo", boardNo);
	}
	
	public boolean delete(long no) {
		return (sqlSession.delete("comment.delete", no)==1);
	}
}
