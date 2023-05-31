package com.spring.board.dao;

import java.util.List;

import com.spring.board.dto.BoardDTO;


public interface BoardDAO {
	
	public void insertBoard(BoardDTO boardDTO);
	
	public List<BoardDTO> getBoardList();
	
	public void updateReadCnt(long boardId);
	public BoardDTO getBoardDetail(long boardId);
	
	public String getPasswd(long boardId);
	public void updateBoard(BoardDTO boardDTO);
	
	public void removeBoard(BoardDTO boardDTO);
}
