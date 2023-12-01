package model;


public class Member {
   private String id;
   private String pwd;
   private String nickName;
   private int birth;
   private int stampCount;
   private String character;
   
   
   public Member(String id, String pwd, String nickName, int birth, int stampCount, String character) {
	super();
	this.id = id;
	this.pwd = pwd;
	this.nickName = nickName;
	this.birth = birth;
	this.stampCount = stampCount;
	this.character = character;
}
   
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
   public String getCharacter() {
	   return character;
   }
   public void setCharacter(String character) {
   }   

   public int getStampCount() {
      return stampCount;
   }
   public void setStampCount(int stampCount) {
      this.stampCount = stampCount;
   }
}
