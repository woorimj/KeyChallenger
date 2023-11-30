package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.CalendarStamp;

public class StampDAO {
    private JDBCUtil jdbcUtil = null;
    
    public StampDAO() {          
        jdbcUtil = new JDBCUtil();
    }
    
    // 스탬프 등록
    public void createStamp(CalendarStamp stamp) {
        String query = "INSERT INTO CalendarStamp (REPLY_ID, POST_ID, CONTENT) " +
                       "VALUES (?, ?, ?)";
        
        jdbcUtil.setSqlAndParameters(query, new Object[]{
                stamp.getReplyID(),
                stamp.getPostID(),
                stamp.getContent()
        });
        
        try {
            int result = jdbcUtil.executeUpdate();
            if (result > 0) {
                System.out.println("스탬프 등록 완료");
            } else {
                System.out.println("스탬프 등록 실패");
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }
}
