package model.dao;

import java.sql.SQLException;
import model.Post;

import model.Post;

public class PostDAO {
    private JDBCUtil jdbcUtil = null;
    
    public PostDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    // 게시글 생성
    public void createPost(Post post) {
        String query = "INSERT INTO POST (POST_ID, TITLE, ACTIVITY, PHOTO, REVIEW, KEYWORD, POSTDATE, LIKECOUNT, LIKEID, ID) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        jdbcUtil.setSqlAndParameters(query, new Object[]{
            post.getPostId(),
            post.getTitle(),
            post.getActivity(),
            post.getPhoto(),
            post.getReview(),
            post.getKeyword(),
            new java.sql.Date(post.getPostDate().getTime()),
            post.getLikeCount(),
            post.getLikeId(),
            post.getId()
        });
        
        try {
            int result = jdbcUtil.executeUpdate();
            if (result > 0) {
                System.out.println("게시글 작성 완료");
            } else {
                System.out.println("게시글 작성 실패");
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }
    
    // 게시글 업데이트
    public void updatePost(Post post) {
        if (post.canUpdatePost()) {
            String query = "UPDATE POST " +
                    "SET TITLE = ?, ACTIVITY = ?, PHOTO = ?, REVIEW = ?, LIKECOUNT = ?, LIKEID = ?, ID = ? " +
                    "WHERE POST_ID = ?";

            jdbcUtil.setSqlAndParameters(query, new Object[]{
                    post.getTitle(),
                    post.getActivity(),
                    post.getPhoto(),
                    post.getReview(),
                    post.getLikeCount(),
                    post.getLikeId(),
                    post.getId(),
                    post.getPostId()
            });

            try {
                int result = jdbcUtil.executeUpdate();
                if (result > 0) {
                    System.out.println("게시글 업데이트 완료.");
                } else {
                    System.out.println("게시글 업데이트 실패.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jdbcUtil.close();
            }
        } else {
            System.out.println("수정할 수 없는 게시물입니다.");
        }
    }

    
    // 게시글 삭제
    public void deletePost(String postId) {
        String query = "DELETE FROM POST WHERE POST_ID = ?";
        
        jdbcUtil.setSqlAndParameters(query, new Object[]{postId});

        try {
            int result = jdbcUtil.executeUpdate();
            if (result > 0) {
                System.out.println("게시글 삭제 완료.");
            } else {
                System.out.println("게시글 삭제 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }
    
//    // 사용자의 인증 및 권한 검증이 필요할것 같은데 -> 컨트롤러에서
//    // 게시글 좋아요
//    public void likePost(String postId, String userId) {
//        String query = "UPDATE POST SET LIKECOUNT = LIKECOUNT + 1, LIKEID = CONCAT(LIKEID, ?) WHERE POST_ID = ?";
//        
//        // 사용자 ID를 좋아요한 사람 목록에 추가하기 위해 ','로 구분하여 연결합니다.
//        String likeIdToAdd = userId + ",";
//        
//        jdbcUtil.setSqlAndParameters(query, new Object[]{likeIdToAdd, postId});
//
//        try {
//            int result = jdbcUtil.executeUpdate();
//            if (result > 0) {
//                System.out.println("게시글 좋아요");
//            } else {
//                System.out.println("좋아요 실패");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            jdbcUtil.close();
//        }
//    }
//    
//    // 게시글 좋아요 삭제
//    public void unlikePost(String postId, String userId) {
//        String query = "UPDATE POST SET LIKECOUNT = LIKECOUNT - 1, LIKEID = REPLACE(LIKEID, ?, '') WHERE POST_ID = ?";
//        
//        // 사용자 ID를 좋아요한 사람 목록에서 제거합니다.
//        String likeIdToRemove = userId + ",";
//        
//        jdbcUtil.setSqlAndParameters(query, new Object[]{likeIdToRemove, postId});
//
//        try {
//            int result = jdbcUtil.executeUpdate();
//            if (result > 0) {
//                System.out.println("좋아요 취소.");
//            } else {
//                System.out.println("좋아요 취소 실패.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            jdbcUtil.close();
//        }
//    }
//}
//
