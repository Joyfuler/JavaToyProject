package space.jdbc.member;

import java.sql.Date;
import java.sql.Timestamp;

public class Customer {
   private String id;
   private String password;
   private Date regDate; // 가입일
   private Timestamp updateDate; // 수정일시


   public Customer() {}

   public Customer(String id, String password) {
      this.id = id;
      this.password = password;
   }

   public Customer(String id, String password, Date regDate, Timestamp updateDate) {
      this.id = id;
      this.password = password;
      this.regDate = regDate;
      this.updateDate = updateDate;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Date getRegDate() {
      return regDate;
   }

   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }

   public Timestamp getUpdateDate() {
      return updateDate;
   }

   public void setUpdateDate(Timestamp updateDate) {
      this.updateDate = updateDate;
   }

   @Override
   public String toString() {
      return "Customer[" +
              "id='" + id + '\'' +
              ", password='" + password + '\'' +
              ", regDate=" + regDate +
              ", updateDate=" + updateDate +
              ']';
   }
}


