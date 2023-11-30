package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Member;

public class MemberDAO {
    private JDBCUtil jdbcUtil = null;

    public MemberDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    //회원 생성
    public int create(Member member) throws SQLException {
        String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[] { member.getId(), member.getPwd(), member.getNickname(),
                member.getBirth(), member.getCharacter(), member.getStampCount() };
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 insert문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // insert 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commitAndClose(); // 트랜잭션 커밋 및 resource 반환
        }
        return 0;
    }

    //회원 수정
    public int update(Member member) throws SQLException {
        String sql = "UPDATE MEMBER " + "SET pwd=?, nickname=?, birth=?, character=?, stampCount=? "
                + "WHERE id=?";
        Object[] param = new Object[] { member.getPwd(), member.getNickname(), member.getBirth(),
                member.getCharacter(), member.getStampCount(), member.getId() };
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commitAndClose(); // 트랜잭션 커밋 및 resource 반환
        }
        return 0;
    }

    //회원 삭제
    public int remove(String memberId) throws SQLException {
        String sql = "DELETE FROM MEMBER WHERE id=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId }); // JDBCUtil에 delete문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // delete 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commitAndClose(); // 트랜잭션 커밋 및 resource 반환
        }
        return 0;
    }

    //회원 정보 찾기 
    public Member findMember(String memberId) throws SQLException {
        String sql = "SELECT pwd, nickname, birth, character, stampCount "
                + "FROM MEMBER WHERE id=? ";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId }); // JDBCUtil에 query문과 매개 변수 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
            if (rs.next()) { // 회원 정보 발견
                Member member = new Member( // Member 객체를 생성하여 회원 정보를 저장
                        memberId,
                        rs.getString("pwd"),
                        rs.getString("nickname"),
                        rs.getString("birth"),
                        rs.getString("character"),
                        rs.getString("stampCount"));
                return member;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return null;
    }

    //회원 정보 검색하고 List에 저장 및 반환
    public List<Member> findMemberList() throws SQLException {
        String sql = "SELECT id, pwd, nickname, birth, character, stampCount "
                + "FROM MEMBER ORDER BY id";
        jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
            List<Member> memberList = new ArrayList<>(); // Member들의 리스트 생성
            while (rs.next()) {
                Member member = new Member( // Member 객체를 생성하여 현재 행의 정보를 저장
                        rs.getString("id"),
                        rs.getString("pwd"),
                        rs.getString("nickname"),
                        rs.getString("birth"),ㄴ
                        rs.getString("character"),
                        rs.getString("stampCount"));
                memberList.add(member); // List에 Member 객체 저장
            }
            return memberList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return null;
    }
    
    public List<Member> findMemberList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT id, pwd, nickname, birth, character, stampCount "
                + "FROM MEMBER ORDER BY id";
        jdbcUtil.setSqlAndParameters(sql, null, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            int start = ((currentPage - 1) * countPerPage) + 1;
            if ((start >= 0) && rs.absolute(start)) {
                List<Member> memberList = new ArrayList<>();
                do {
                    Member member = new Member(
                            rs.getString("id"),
                            rs.getString("pwd"),
                            rs.getString("nickname"),
                            rs.getString("birth"),
                            rs.getString("character"),
                            rs.getString("stampCount"));
                    memberList.add(member);
                } while ((rs.next()) && (--countPerPage > 0));
                return memberList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}
