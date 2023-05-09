package com.liolik.test.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liolik.test.mvc.domain.Board;
import com.liolik.test.mvc.repository.BoardRepository;

/**
 * 
 * @author liolik
 *
 */
@Service
public class BoardService {

	@Autowired
	private BoardRepository repositoty;
	
	/**
	 * 목록 리턴
	 * @return
	 */
	public List<Board> getList(){
		return repositoty.getList();
	}
	
	/**
	 * 상세정보 리턴
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repositoty.get(boardSeq);
	}
	
	/**
	 * 등록 처리
	 * @param parameter
	 */
	public int save(Board parameter) {
		Board board = repositoty.get(parameter.getBoardSeq());
		if (board == null) {
			repositoty.save(parameter);
		}
		else {
			repositoty.update(parameter);
		}
		// useGeneratedKeys="true" keyProperty="boardSeq" 을 쿼리에 추가해주면
		// 새로 생성된 데이터의 값 받아와서 리턴할 수 있음
		return parameter.getBoardSeq();
	}

	/**
	 * 업데이트 처리
	 * @param board
	 */
	public void update(Board board) {
		repositoty.update(board);
	}
	
	/**
	 * 삭제처리
	 * @param boardSeq
	 */
	public boolean delete(int boardSeq) {
		Board board = repositoty.get(boardSeq);
		// boardSeq 값에 해당하는 데이터가 없는 경우
		if (board == null) { 
			return false; 
			} 
		
		// 해당 데이터 있는 경우
		repositoty.delete(boardSeq);
		return true;
	}
}
