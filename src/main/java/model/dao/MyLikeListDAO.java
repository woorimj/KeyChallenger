package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List
import model.MyLikeList;
import model.Post;

//내가 좋아요 누른 게시글 리스트를 관리하는 DAO
public class MyLikeListDAO{
    
    private JDBCUtil jdbcUtil = null; //JDBCUtil 객체 참조 변수 선언
    
    public MyLikeListDAO() {        //생성자
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성 및 이용
    }
    
    // 1. MyLikeList에서 id, postId를 받아오고, postId를 이용하여 Post에서 likeId를 받아옴
    public String getLikeIdByMyLikeListId(char id, char postId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String likeId = null;

        try {
            conn = jdbcUtil.getConnection();
            String sql = "SELECT likeId FROM Post WHERE postId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(postId));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                likeId = rs.getString("likeId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, pstmt, conn);
        }

        return likeId;
    }

    // 2. 내가 좋아요를 누른 게시글들을 모아놓은 리스트 객체 생성
    public List<MyLikeList> getMyLikeList(char id) {
        List<MyLikeList> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = jdbcUtil.getConnection();
            String sql = "SELECT * FROM MyLikeList WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(id));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                char postId = rs.getString("postId").charAt(0);
                MyLikeList myLikeList = new MyLikeList(id, postId);
                list.add(myLikeList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs, pstmt, conn); // ResultSet, PreparedStatement, Connection 반환
        }

        return list;
    }

    // 3. likeId가 True인지 False인지 확인하여 반환
    public boolean checkLike(String likeId) {
        return likeId != null && likeId.equals("True");
    }

    // 4. 만약 checkLike의 결과가 False이면, likePost()라는 함수 안에서 likeId를 True로 바꾸고 postId의 게시글을 list에 add
    public void likePost(char id, char postId) {
        String likeId = getLikeIdByMyLikeListId(id, postId);
        if (!checkLike(likeId)) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = jdbcUtil.getConnection();
                String updateSql = "UPDATE Post SET likeId = 'True' WHERE postId = ?";
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setString(1, String.valueOf(postId));
                pstmt.executeUpdate();

                // list에 추가
                List<MyLikeList> myLikeList = getMyLikeList(id);
                myLikeList.add(new MyLikeList(id, postId));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                jdbcUtil.close(pstmt, conn);
            }
        }
    }

    // 5. 만약 checkLike의 결과가 True이면, unlikePost()라는 함수 안에서 likeId를 False로 바꾸고 postId의 게시글을 list에서 delete한다.
    public void unlikePost(char id, char postId) {
        String likeId = getLikeIdByMyLikeListId(id, postId);
        if (checkLike(likeId)) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = jdbcUtil.getConnection();
                String updateSql = "UPDATE Post SET likeId = 'False' WHERE postId = ?";
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setString(1, String.valueOf(postId));
                pstmt.executeUpdate();

                //list에서 삭제
                List<MyLikeList> myLikeList = getMyLikeList(id);
                MyLikeList itemToRemove = null;
                for (MyLikeList myLike : myLikeList) {
                    if (myLike.getPostId() == postId) {
                        itemToRemove = myLike;
                        break;
                    }
                }
                if (itemToRemove != null) {
                    myLikeList.remove(itemToRemove);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                jdbcUtil.close(pstmt, conn);
            }
        }
    }
}
