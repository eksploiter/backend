package com.ssafy.sample.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.sample.dto.CoffeeDTO;

public class CoffeeDAO implements ICoffeeDAO {

	@Override
	public List<CoffeeDTO> selectAll(Connection conn) throws SQLException {
		String sql = "SELECT * FROM coffees";
		List<CoffeeDTO> coffeeList = new ArrayList<>();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CoffeeDTO coffee = new CoffeeDTO();
				coffee.setCode(rs.getString("code"));
				coffee.setName(rs.getString("name"));
				coffee.setPrice(rs.getInt("price"));
				coffee.setBeans(rs.getString("beans"));
				coffee.setManager(rs.getString("manager"));
				coffeeList.add(coffee);
			}
		}
		return coffeeList;
	} // 1

	@Override
	public CoffeeDTO selectByCode(Connection conn, String code) throws SQLException {
		String sql = "SELECT * FROM coffees WHERE code = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				CoffeeDTO coffee = new CoffeeDTO(rs.getString("code"), rs.getString("name"), rs.getInt("price"), rs.getString("beans"), rs.getString("manager"));
				return coffee;
			}
		}
		return null;
	} // 2

	@Override
	public void insert(Connection conn, CoffeeDTO coffee) throws SQLException {
		String sql = "INSERT INTO products(name, price, beans, manager) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, coffee.getName());
			stmt.setInt(2, coffee.getPrice());
			stmt.setString(3, coffee.getBeans());
			stmt.setString(4, coffee.getManager());
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				coffee.setCode(generatedKeys.getString(1));
			}
		}
	} // 3

	@Override
	public void deleteByCode(Connection conn, String code) throws SQLException {
		String sql = "DELETE FROM products WHERE code = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, code);
			stmt.executeUpdate();
		}
	} // 4

	@Override
	public void update(Connection conn, CoffeeDTO coffee) throws SQLException {
		String sql = "UPDATE coffees SET name=?, price=?, beans=?, manager=?";
		
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, coffee.getName());
            stmt.setInt(2, coffee.getPrice());
            stmt.setString(3, coffee.getBeans());
            stmt.setString(4, coffee.getManager());
            stmt.executeUpdate();
        }
	} // 5
}
