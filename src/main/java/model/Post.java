package model;

import java.util.Date;

public class Post {
    private String postId;
    private String title;
    private String activity;
    private String photo;
    private String review;
    private String keyword; // db에 이미 저장되어있음 =
    private Date postDate; 
    private int likeCount;
    private String likeId;
    private String id;
    
    public Post(String postId, String title, String activity, String photo, String review, String keyword, Date postDate, int likeCount, String likeId, String userId) {
        this.postId = postId;
        this.title = title;
        this.activity = activity;
        this.photo = photo;
        this.review = review;
        this.keyword = keyword;
        this.postDate = postDate;
        this.likeCount = likeCount;
        this.likeId = likeId;
        this.id = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    // 업데이트 시간 제한
    public boolean canUpdatePost() {
        // 현재 시간과 POSTDATE를 비교하여 수정 가능 여부를 판단하는 로직
        long now = System.currentTimeMillis();
        long postTime = postDate.getTime();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // 하루를 밀리초로 계산
        return (now - postTime) <= oneDayInMillis;
    }
}

