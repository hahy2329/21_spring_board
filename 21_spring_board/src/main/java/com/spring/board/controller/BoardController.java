package com.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.dto.BoardDTO;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	

	@GetMapping("/addBoard")
	public ModelAndView addBoard() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/addBoard");
		
		return mv;
		
	}
	
	@PostMapping("/addBoard")
	@ResponseBody
	public String addBoard(@ModelAttribute BoardDTO boardDTO) {
		
		boardService.insertBoard(boardDTO);
		
		String jsScript = "<script>";
		jsScript +="alert('Post added');";
		jsScript +="location.href='boardList';";
		jsScript +="</script>";
		
		return jsScript;
	}
	
	@GetMapping("/boardList")
	public ModelAndView boardList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardList");
		
		List<BoardDTO> boardList = boardService.getBoardList();
		mv.addObject("boardList", boardList);
		
		return mv;
	}
	
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("boardId") long boardId) {
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardDetail");
				
		BoardDTO boardDTO = boardService.getBoardDetail(boardId, true);
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
		
	}
	
	
	@GetMapping("/modifyBoard")
	public ModelAndView modifyBoard(@RequestParam("boardId") long boardId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/modifyBoard");
		
		BoardDTO boardDTO = boardService.getBoardDetail(boardId, false);
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
		
		
		
	}
	
	@PostMapping("/modifyBoard")
	@ResponseBody
	public String modifyBoard(@ModelAttribute BoardDTO boardDTO) {
		
		
		String jsScript = "";
		if(boardService.updateBoard(boardDTO)) {
			jsScript = "<script>";
			jsScript +="alert('Successful Update!');";
			jsScript +="location.href='boardList';";
			jsScript +="</script>";
			
		}
		else {
			
			jsScript ="<script>";
			jsScript +="alert('Check Your Password!');";
			jsScript +="history.go(-1)";
			jsScript +="</script>";
			
		}
		return jsScript;
	}
	
	@GetMapping("/removeBoard")
	public ModelAndView removeBoard(@RequestParam("boardId") long boardId) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/removeBoard");
		
		BoardDTO boardDTO = boardService.getBoardDetail(boardId, false);
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
		
	}
	
	@PostMapping("/removeBoard")
	@ResponseBody
	public String removeBoard(@ModelAttribute BoardDTO boardDTO) {
		
		
		String jsScript = "";
		if(boardService.removeBoard(boardDTO)) {
			jsScript = "<script>";
			jsScript +="alert('Successful Remove!');";
			jsScript +="location.href='boardList';";
			jsScript +="</script>";
		}
		else {
			
			jsScript ="<script>";
			jsScript +="alert('Check Your Password!');";
			jsScript +="history.go(-1)";
			jsScript +="</script>";
		}
		
		return jsScript;
	}
	
	
	
	
	
}
