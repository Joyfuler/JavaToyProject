package space.jdbc.member;

import java.util.List;

public interface CustomerDao {

    public int insert(Customer customer);
    public List<Customer> findAll();
    public Customer findById(String id);
    public int update(Customer customer);
    public int deleteById(String id);

}
