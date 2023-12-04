package model.dao;

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
    
    
    //MyLikeList 생성
    public void create(MyLikeList myLikeList) throws SQLException {
        String query = "INSERT INTO MyLikeList VALUES(ID, POSTID)"+
                    "VALUES(?, ?)";
        
        jdbcUtil.setSqlAndParameters(query, new Object[] {
                myLikeList.getId(),
                myLikeList.getPostId()
        });
        
        try {
            int result = jdbcUtil.executeUpdate();
            if (result > 0) {
                System.out.println("MyLikeList 생성 완료");
            } else {
                System.out.println("MyLikeList 생성 실패");
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }}
    }
    
    //사용자의 인증 및 권한 검증이 필요할것 같은데 -> 컨트롤러에서
    // 게시글 좋아요
    public void likePost(String postId, String userId) {
        String query = "UPDATE POST SET LIKECOUNT = LIKECOUNT + 1, LIKEID = CONCAT(LIKEID, ?) WHERE POST_ID = ?";
        
        // 사용자 ID를 좋아요한 사람 목록에 추가하기 위해 ','로 구분하여 연결합니다.
        String likeIdToAdd = userId + ",";
        
        jdbcUtil.setSqlAndParameters(query, new Object[]{likeIdToAdd, postId});
    
        try {
            int result = jdbcUtil.executeUpdate();
            if (result > 0) {
                System.out.println("게시글 좋아요");
            } else {
                System.out.println("좋아요 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }
    
    // 게시글 좋아요 삭제
    public void unlikePost(String postId, String userId) {
        String query = "UPDATE POST SET LIKECOUNT = LIKECOUNT - 1, LIKEID = REPLACE(LIKEID, ?, '') WHERE POST_ID = ?";
        
        // 사용자 ID를 좋아요한 사람 목록에서 제거합니다.
        String likeIdToRemove = userId + ",";
        
        jdbcUtil.setSqlAndParameters(query, new Object[]{likeIdToRemove, postId});
    
        try {
            int result = jdbcUtil.executeUpdate();
            if (result > 0) {
                System.out.println("좋아요 취소.");
            } else {
                System.out.println("좋아요 취소 실패.");
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
