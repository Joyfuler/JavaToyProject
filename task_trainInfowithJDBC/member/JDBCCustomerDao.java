package space.jdbc.member;

import space.jdbc.member.DataSource;
import space.jdbc.memo.Memo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JDBCCustomerDao implements CustomerDao{

    @Override
    public int insert(Customer customer) {
        int result = 0;
        String sql = "insert into customer \n" +
                "values (?, ?, sysdate(), sysdate());";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getPassword());
            result = pstmt.executeUpdate(); // insert된 줄 수. 정상적으로 입력시 1줄

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new LinkedList<>();
        String sql = "SELECT * FROM CUSTOMER ORDER BY REGDATE DESC";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()){
                customerList.add(new Customer(rs.getString("id"), rs.getString("password"),
                        rs.getDate("regdate"), rs.getTimestamp("updatedate")));
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer findById(String id) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM CUSTOMER WHERE ID = '" + id + "';";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

             ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                String inputId = rs.getString("id");
                String password = rs.getString("password");
                Date registerDate = rs.getDate("regdate");
                Timestamp updateDate = rs.getTimestamp("updatedate");

                customer.setId(inputId);
                customer.setPassword(password);
                customer.setRegDate(registerDate);
                customer.setUpdateDate(updateDate);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return customer;
    }

    @Override
    public int update(Customer customer) {
        int result = 0;
        String sql = "update customer set password = '"+customer.getPassword()+"', regdate = ?, updatedate = ? where id ='" +customer.getId() +"'";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDate(1, customer.getRegDate());
            pstmt.setTimestamp(2, customer.getUpdateDate());

            result = pstmt.executeUpdate();
            System.out.println(result + "행이 변경되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteById(String id) {
        int result = 0;
        String sql = "delete from customer where id = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
