package 하성;

import DataSource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdminDao implements AdminDao {

	@Override
	public boolean insert(Admin admin) {
		boolean result = false;
		
		try(Connection connection = DataSource.getDataSource();
			PreparedStatement pStatement
					= connection.prepareStatement("INSERT INTO ADMIN (MANAGER_NUM,POSITION)"
							+ " VALUES (?, ?)")) {
			
			pStatement.setInt(1, admin.getManager_num());
			pStatement.setString(2, admin.getPosition());
			
			int rows = pStatement.executeUpdate();
			
			if(rows > 0) {
				result = true;
			}
			
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Admin> findAll() {
		List<Admin> admin = new ArrayList<Admin>();
		
		try (Connection connection = DataSource.getDataSource();
		PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ADMIN ORDER BY MANAGER_NUM DESC FETCH FIRST 2 ROWS ONLY");
		ResultSet rs = pStatement.executeQuery()) {
		
		while(rs.next()) {
			Admin admin2 = new Admin(
					rs.getInt("manager_num"),
					rs.getString("position"));
			
			admin.add(admin2);
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public Admin findByManager_num(int num) {
		Admin admin  = null;
		
		try (Connection connection = DataSource.getDataSource();
		PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE MANAGER_NUM = ?")
		) {
		
		pStatement.setInt(1, num);	
		ResultSet rs = pStatement.executeQuery();
		
		if(rs.next()) {
			admin = new Admin();
				
			admin.setManager_num(rs.getInt("manager_num"));
			admin.setPosition(rs.getString("position"));
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}


	@Override
	public boolean update(Admin admin) {
		boolean result = false;
		
		try(Connection conn = DataSource.getDataSource();
			PreparedStatement pStatement
			= conn.prepareStatement("UPDATE ADMIN SET MANAGER_NUM = ? , POSITION = ? WHERE  = ?")){
		
			pStatement.setInt(1, admin.getManager_num());
			pStatement.setString(2, admin.getPosition());
			
			int affectedRows = pStatement.executeUpdate();
				
			if(affectedRows > 0) {
				result = true;
			}
			
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return result;
	}

	@Override
	public boolean deleteByManager_num(int manager_num) {
		boolean result = false;

		try(Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement
					= connection.prepareStatement("DELETE FROM ADMIN WHERE MANAGER_NUM = ?")){
			
			pStatement.setInt(1, manager_num);
			
			int rows = pStatement.executeUpdate();
			
			if(rows>0) {
				result = true;
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return result;
	}
	
}
