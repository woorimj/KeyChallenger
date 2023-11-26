package model;

public class CalendarStamp {
    private String reply_id;
    private String post_id;
    private String content;
    
    public CalendarStamp(String reply_id, String post_id, String content) {
        super();
        this.reply_id = reply_id;
        this.post_id = post_id;
        this.content = content;
    }
    
    public String getReplyID() {
        return reply_id;
    }
    
    public void setReplyID(String reply_id) {
        this.reply_id = reply_id;
    }
    
    public String getPostID() {
        return post_id;
    }
    
    public void setPostID(String post_id) {
        this.post_id = post_id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}