package com.ssafy.sample.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.sample.dto.CoffeeDTO;



public interface ICoffeeService {
	/**
	 * 커피 전체 목록 조회
	 */
	List<CoffeeDTO> selectAll() throws SQLException;
	
	/**
	 * 커피 정보 상세 조회
	 */
	CoffeeDTO selectByCode(String code) throws SQLException;
	
	/**
	 * 커피 정보 추가
	 */
	int insert(CoffeeDTO coffe) throws SQLException;
	
	/**
	 * 커피 정보 삭제
	 */
	int deleteByCode(String code) throws SQLException;

	/**
	 * 커피 정보 수정
	 */
	int update(CoffeeDTO coffe) throws SQLException;
}
