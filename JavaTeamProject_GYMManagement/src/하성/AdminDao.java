package 하성;

import java.util.List;

public interface AdminDao {
	
	boolean insert(Admin admin);
	
	List<Admin> findAll();
	Admin findByManager_num(int manager_num);
	
	boolean update(Admin admin);
	
	boolean deleteByManager_num(int manager_num);
}
