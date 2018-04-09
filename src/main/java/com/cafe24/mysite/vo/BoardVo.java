package com.cafe24.mysite.vo;

public class BoardVo {
    private Long no;
    private String title;
    private String content;
    private Long groupNo;
    private Long orderNo;
    private Long depth;
    private String regDate;
    private Long views;
    private Long userNo;
    
	public BoardVo() {
	}
	
	public BoardVo(Long no, String title, String content, Long groupNo, Long orderNo, Long depth, String regDate,
	        Long views, Long userNo) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.regDate = regDate;
		this.views = views;
		this.userNo = userNo;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}

    @Override
    public String toString() {
        return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", groupNo=" + groupNo + ", orderNo="
                + orderNo + ", depth=" + depth + ", regDate=" + regDate + ", views=" + views + ", userNo=" + userNo
                + "]";
    }
}
