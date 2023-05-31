package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDAO;
import com.spring.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@Override
	public void insertBoard(BoardDTO boardDTO) {
		
		boardDTO.setPasswd(bCryptPasswordEncoder.encode(boardDTO.getPasswd()));
		
		boardDAO.insertBoard(boardDTO);
		
	}



	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = boardDAO.getBoardList();
		
		return boardList;
	}



	@Override
	public BoardDTO getBoardDetail(long boardId, boolean Increase) {
		
		if(Increase) {
			boardDAO.updateReadCnt(boardId);
		}
		
		BoardDTO boardDTO = boardDAO.getBoardDetail(boardId);
		
		return boardDTO;
		
	}



	@Override
	public boolean updateBoard(BoardDTO boardDTO) {
		
		boolean isUpdate = false;
		boolean valid = bCryptPasswordEncoder.matches(boardDTO.getPasswd(), boardDAO.getPasswd(boardDTO.getBoardId()));
		
		if(valid) {
			boardDAO.updateBoard(boardDTO);
			isUpdate = true;
		}
		
		return isUpdate;
	}



	@Override
	public boolean removeBoard(BoardDTO boardDTO) {
		boolean isRemove = false;
		
		boolean valid = bCryptPasswordEncoder.matches(boardDTO.getPasswd(), boardDAO.getPasswd(boardDTO.getBoardId()));
		
		if(valid) {
			boardDAO.removeBoard(boardDTO);
			isRemove = true;
		}
		
		return isRemove;
		
	}
	
	

}
