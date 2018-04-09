package com.cafe24.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	private static final int LIST_PAGE_SIZE = 10;
	
	@Autowired
	SqlSession sqlSession;

	public static int getListPageSize() {
		return LIST_PAGE_SIZE;
	}

	public boolean delete(long no) {
	    return (sqlSession.delete("board.delete", no) == 1);
	}

	public Long getMaxGroupNo() {
	    return sqlSession.selectOne("board.getMaxGroupNo");
	}
	
	public Long getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}

	public boolean insert(BoardVo vo) {
		return (sqlSession.insert("board.insert", vo)==1);
	}

	public boolean insertReply(BoardVo vo) {
		return (sqlSession.insert("insertReply", vo)==1);
	}

	public void updateForReply(BoardVo vo) {
		sqlSession.update("board.updateForReply",vo);
	}

	public boolean updateBoard(BoardVo vo) {
		return (sqlSession.update("board.updateBoard", vo)==1);
	}

	public List<Map<String, Object>> getList(Map<String, Object> parameter) {
		return sqlSession.selectList("board.getList", parameter);
	}
	
	public BoardVo getBoard(Long no) {
	    return sqlSession.selectOne("board.getBoardByNo", no);
	}
	
	public boolean updateViews(long no) {
		return (sqlSession.update("board.addViews", no)==1);
	}
}
