package com.ssafy.sample.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.sample.dto.CoffeeDTO;

public interface ICoffeeDAO {

	/**
	 * 커피 전체 목록 조회
	 */
	List<CoffeeDTO> selectAll(Connection conn) throws SQLException;
	
	/**
	 * 커피 정보 상세 조회
	 */
	CoffeeDTO selectByCode(Connection conn, String code) throws SQLException;
	
	/**
	 * 커피 정보 추가
	 */
	void insert(Connection conn, CoffeeDTO coffe) throws SQLException;
	
	/**
	 * 커피 정보 삭제
	 */
	void deleteByCode(Connection conn, String code) throws SQLException;

	/**
	 * 커피 정보 수정
	 */
	void update(Connection conn, CoffeeDTO coffe) throws SQLException;
}
