package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDTO;


@Repository
public class BoardDAOImpl implements BoardDAO {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public void insertBoard(BoardDTO boardDTO) {
		sqlSession.insert("boardMapper.insertBoard", boardDTO);
		
	}
	
	@Override
	public List<BoardDTO> getBoardList(){
		List<BoardDTO> boardList = sqlSession.selectList("boardMapper.getBoardList");
		
		return boardList;
	}
	
	@Override
	public void updateReadCnt(long boardId) {
		sqlSession.update("boardMapper.updateReadCnt", boardId);
	}
	
	@Override
	public BoardDTO getBoardDetail(long boardId) {
		
		BoardDTO boardDTO = sqlSession.selectOne("boardMapper.boardDetail", boardId);
		return boardDTO;
		
		
	}
	@Override
	public String getPasswd(long boardId) {
		String passwd = sqlSession.selectOne("boardMapper.getBoardPassword",boardId);
		
		return passwd;
	}
	
	@Override
	public void updateBoard(BoardDTO boardDTO) {
		
		sqlSession.update("boardMapper.updateBoard", boardDTO);
		
	}
	
	@Override
	public void removeBoard(BoardDTO boardDTO) {
		
		sqlSession.delete("boardMapper.removeBoard", boardDTO);
	}

}
