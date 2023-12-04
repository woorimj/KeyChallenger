package model;

public class MyLikeList {
    private char id;
    private char postId;
    
    public MyLikeList() {} //기본 생성자
    
    public MyLikeList (char id, char postId) {
        this.id = id;
        this.postId = postId;
    }
    
    public char getId() {
        return id;
    }
    public void setId(char id) {
        this.id = id;
    }
    public char getPostId() {
        return postId;
    }
    public void setPostID(char postId) {
        this.postId = postId;
    }
}
