package 하성;

public class Admin {
	private int manager_num;
	private String position;
	
	Admin(){}
	
	public Admin(int manager_num,String position) {
		this.manager_num = manager_num;
		this.position = position;
	}

	public int getManager_num() {
		return manager_num;
	}

	public void setManager_num(int manager_num) {
		this.manager_num = manager_num;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Admin [manager_num=" + manager_num + ", position=" + position + "]";
	}


	
	
}
