package com.ssafy.sample.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.sample.model.dao.ICoffeeDAO;
import com.ssafy.sample.model.dao.CoffeeDAO;
import com.ssafy.sample.dto.CoffeeDTO;
import com.ssafy.sample.util.DBUtil;

public class CoffeeService implements ICoffeeService {
	private ICoffeeDAO coffeeDAO = new CoffeeDAO();
	
	@Override
	public List<CoffeeDTO> selectAll() throws SQLException {
		Connection conn = DBUtil.getConnection();
		try {
			return coffeeDAO.getAllCoffes(conn);
		} finally {
			DBUtil.close(conn);
		}
	} // 1

	@Override
	public CoffeeDTO selectByCode(String code) throws SQLException {
		Connection conn = DBUtil.getConnection();
		try {
			return coffeeDAO.getAllCoffees(conn, code);
		} finally {
			DBUtil.close(conn);
		}
	} // 2

	@Override
	public int insert(CoffeeDTO coffee) throws SQLException {
		return 0;
	} // 3

	@Override
	public int deleteByCode(String code) throws SQLException {
		Connection conn = DBUtil.getConnection();
		try {
			coffeeDAO.deleteCoffee(conn, code);
		} finally {
			DBUtil.close(conn);
		}
	} // 4

	@Override
	public int update(CoffeeDTO coffee) throws SQLException {
		Connection conn = DBUtil.getConnection();
		try {
			coffeeDAO.updateCoffee(conn, coffee);
		} finally {
			DBUtil.close(conn);
		}
	} // 5
}
