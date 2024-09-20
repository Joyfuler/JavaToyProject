package 하성;

import DataSource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCChargeDao implements ChargeDao {

	@Override
	public boolean insert(Charge chargeList) {
		boolean result = false;
		
		try(Connection connection = DataSource.getDataSource();
			PreparedStatement pStatement
					= connection.prepareStatement("INSERT INTO CHARGE (CHARGE_NUM,PERIOD_DATE,PT_COUNT)"
							+ " VALUES (?, ?, ?)")) {
			
			pStatement.setInt(1, chargeList.getCharge_num());
			pStatement.setInt(2, chargeList.getPeriod_date());
			pStatement.setInt(3, chargeList.getPt_count());
			
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
	public List<Charge> findAll() {
		List<Charge> charge = new ArrayList<Charge>();
		
		try (Connection connection = DataSource.getDataSource();
		PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM CHARGE ORDER BY CHARGE_NUM DESC");
		ResultSet rs = pStatement.executeQuery()) {
		
		while(rs.next()) {
			Charge charge2 = new Charge(
					rs.getInt("charge_num"),
					rs.getInt("period_date"),
					rs.getInt("pt_count"),
					rs.getString("name")
			);
			
			charge.add(charge2);
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return charge;
	}

	@Override
	public Charge findByCharge_num(int num) {
		Charge charge = null;

		try(Connection connection = DataSource.getDataSource();
			PreparedStatement pStatement
					= connection.prepareStatement("SELECT * FROM CHARGE WHERE CHARGE_NUM = ?")){
			
			pStatement.setInt(1, num);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs.next()) {
				charge = new Charge();
					
				charge.setCharge_num(rs.getInt("charge_num"));
				charge.setPeriod_date(rs.getInt("period_date"));
				charge.setPt_count(rs.getInt("pt_count"));
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return charge;	
		}

	@Override
	public boolean update(Charge chargeList) {
		boolean result = false;
		
		try(Connection conn = DataSource.getDataSource();
			PreparedStatement pStatement
			= conn.prepareStatement("UPDATE CHARGE SET PERIOD_DATE = ? , PT_COUNT = ?, NAME = ? WHERE CHARGE_NUM = ?")){
		
			pStatement.setInt(1, chargeList.getPeriod_date());
			pStatement.setInt(2, chargeList.getPt_count());
			pStatement.setString(3, chargeList.getName());
			pStatement.setInt(4, chargeList.getCharge_num());
			
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
	public boolean deleteByCharge_Num(int num) {
		boolean result = false;

		try(Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement
					= connection.prepareStatement("DELETE FROM CHARGE WHERE CHARGE_NUM = ?")){
			
			pStatement.setInt(1, num);
			
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
