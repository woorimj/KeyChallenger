package model;


public class Manager {
   private String id;
   private String pwd;
   private String nickName;
   private int birth;
   private int stampCount;
   

   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPwd() {
      return pwd;
   }
   public void setPwd(String pwd) {
      this.pwd = pwd;
   }
   public String getNickName() {
      return nickName;
   }
   public void setNickName(String nickName) {
      this.nickName = nickName;
   }
   public int getBirth() {
      return birth;
   }
   public void setBirth(int birth) {
      this.birth = birth;
   }
   public int getStampCount() {
      return stampCount;
   }
   public void setStampCount(int stampCount) {
      this.stampCount = stampCount;
   }
}
