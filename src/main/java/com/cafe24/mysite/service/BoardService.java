package com.cafe24.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.CommentVo;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	CommentDao commentDao;
	
	public long getEndPage(String keyword) {
		long totCount = boardDao.getTotalCount(keyword);
		long endPage = (totCount%BoardDao.getListPageSize()==0)?
				  (totCount/BoardDao.getListPageSize()):
				  (totCount/BoardDao.getListPageSize()+1);
		endPage = (endPage==0)?1:endPage;
		return endPage;
	}
	
	public long getTotCount(String keyword) {
		return boardDao.getTotalCount(keyword);
	}
	
	public List<Map<String, Object>> getList(Map<String, Object> parameter) {
		return boardDao.getList(parameter);
	}
	
	public void write(BoardVo vo) {
	    vo.setGroupNo(boardDao.getMaxGroupNo());
        boardDao.insert(vo);
	}
	
	public BoardVo getBoard(long no) {
		return boardDao.getBoard(no);
	}
	
	public void modify(BoardVo vo) {
		boardDao.updateBoard(vo);
	}
	
	public void addViews(long no) {
		boardDao.updateViews(no);
	}
	
	public void reply(BoardVo vo) {
		BoardVo reply = boardDao.getBoard(vo.getNo());
		reply.setTitle(vo.getTitle());
		reply.setContent(vo.getContent());
		boardDao.updateForReply(reply);
		boardDao.insertReply(reply);
	}

	public void delete(int no) {
	    boardDao.delete((long)no);
	}
	
	public void comment(CommentVo vo) {
	    commentDao.insert(vo);
	}
	
	public List<Map<String, Object>> getCmtList(int no) {
	    return commentDao.getList((long)no);
	}
	
	public void commentDelete(int no) {
	    commentDao.delete((long)no);
	}
	
}
