package space.jdbc.member;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MainTest {
    public static void main(String[] args) {
        CustomerDao customerDao = new JDBCCustomerDao();
        Customer insertCustomer = new Customer("bbb", "3456");
        System.out.println(customerDao.insert(insertCustomer) +"행이 삽입되었습니다.");

        for (Customer customer : customerDao.findAll()){
            System.out.println(customer);
        }

        Customer customer3 = customerDao.findById("bbb");
        System.out.println(customer3);

        Customer customer = new Customer();
        customer.setId("aaa");
        customer.setPassword("1111");
        customer.setRegDate(Date.valueOf("2024-01-01"));
        customer.setUpdateDate(Timestamp.valueOf("2024-01-02 20:15:30.078987"));
        customerDao.update(customer);
        for (Customer customer2 : customerDao.findAll()){
            System.out.println(customer2);
        }

        int result = customerDao.deleteById("bbb");
        System.out.println(result + "행이 삭제되었습니다.");
    }
}
