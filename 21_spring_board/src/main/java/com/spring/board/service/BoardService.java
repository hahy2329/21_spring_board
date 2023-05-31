package com.spring.board.service;

import java.util.List;

import com.spring.board.dto.BoardDTO;

public interface BoardService {
	
	public void insertBoard(BoardDTO boardDTO);
	
	public List<BoardDTO> getBoardList();
	
	public BoardDTO getBoardDetail(long boardId, boolean Increase);
	
	public boolean updateBoard(BoardDTO boardDTO);
	
	public boolean removeBoard(BoardDTO boardDTO);
}
