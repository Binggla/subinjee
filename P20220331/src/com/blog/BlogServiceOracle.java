package com.blog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogServiceOracle extends BlogDAO implements BlogService {

	// 로그인
	@Override
	public boolean login(User user) {
		conn = getConnect();
		String sql = "SELECT * FROM user_info " //
				+ "WHERE user_id = ? " //
				+ "AND user_pw = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserPw());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// 회원 가입
	@Override
	public boolean insertUser(User user) {
		conn = getConnect();
		String sql = "INSERT INTO user_info (user_id, user_pw) " //
				+ "VALUES (?, ?)";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserPw());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// 전체 리스트 조회
	@Override
	public List<Writing> getList() {

		conn = getConnect();
		List<Writing> writings = new ArrayList<>();
		String sql = "SELECT * FROM writing_list " //
				+ "ORDER BY writing_date";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Writing wri = new Writing();
				wri.setBoardName(rs.getString("board_name"));
				wri.setWritingNo(rs.getInt("writing_no"));
				wri.setWritingDate(rs.getString("writing_date"));
				wri.setUserId(rs.getString("user_id"));
				wri.setWritingSub(rs.getString("writing_sub"));
				wri.setWriting(rs.getString("writing"));

				writings.add(wri);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return writings;
	}

	@Override
	public Writing getWriting(int writingNo) {
		conn = getConnect();
		String sql = "SELECT * FROM writing_list " //
				+ "WHERE writing_no = ?";

		Writing wri = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, writingNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				wri = new Writing();
				wri.setBoardName(rs.getString("board_name"));
				wri.setWritingNo(rs.getInt("writing_no"));
				wri.setWritingDate(rs.getString("writing_date"));
				wri.setUserId(rs.getString("user_id"));
				wri.setWritingSub(rs.getString("writing_sub"));
				wri.setWriting(rs.getString("writing"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return wri;
	}

	// 게시판 이름으로 조회
	@Override
	public List<Writing> selectListName(String boardName) {
		conn = getConnect();
		List<Writing> writings = new ArrayList<>();
		String sql = "SELECT * FROM writing_list " //
				+ "WHERE board_name = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Writing wri = new Writing();
				wri.setBoardName(rs.getString("board_name"));
				wri.setWritingNo(rs.getInt("writing_no"));
				wri.setWritingDate(rs.getString("writing_date"));
				wri.setUserId(rs.getString("user_id"));
				wri.setWritingSub(rs.getString("writing_sub"));
				wri.setWriting(rs.getString("writing"));

				writings.add(wri);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return writings;
	}

	// 게시판 제목으로 조회
	@Override
	public List<Writing> selectListUser(String userId) {

		conn = getConnect();
		List<Writing> writings = new ArrayList<>();
		String sql = "SELECT * FROM writing_list " //
				+ "where user_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Writing wri = new Writing();
				wri.setBoardName(rs.getString("board_name"));
				wri.setWritingNo(rs.getInt("writing_no"));
				wri.setWritingDate(rs.getString("writing_date"));
				wri.setUserId(rs.getString("user_id"));
				wri.setWritingSub(rs.getString("writing_sub"));
				wri.setWriting(rs.getString("writing"));

				writings.add(wri);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return writings;

	}

	// 게시판 날짜로 조회
	@Override
	public List<Writing> selectListDate(String writingDate1, String writingDate2) {
		conn = getConnect();
		List<Writing> writings = new ArrayList<>();
		String sql = "SELECT * FROM writing_list " //
				+ "WHERE writing_date BETWEEN ? AND ?" + "ORDER BY writing_date";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, writingDate1);
			psmt.setString(2, writingDate2);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Writing wri = new Writing();
				wri.setBoardName(rs.getString("board_name"));
				wri.setWritingNo(rs.getInt("writing_no"));
				wri.setWritingDate(rs.getString("writing_date"));
				wri.setUserId(rs.getString("user_id"));
				wri.setWritingSub(rs.getString("writing_sub"));
				wri.setWriting(rs.getString("writing"));

				writings.add(wri);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return writings;
	}

	// 게시글 등록
	@Override
	public boolean insertPost(Writing writing) {

		conn = getConnect();
		String sql = "insert into writing_list (board_name, writing_no, writing_date, user_id, writing_sub, writing)\r\n"
				+ "values (?, tmp_seq.NEXTVAL, sysdate, ?, ?, ?)";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, writing.getBoardName());
			psmt.setString(2, writing.getUserId());
			psmt.setString(3, writing.getWritingSub());
			psmt.setString(4, writing.getWriting());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;

	}

	// 게시글 수정
	@Override
	public boolean updatePost(Writing writing) {
		conn = getConnect();
		String sql = "UPDATE writing_list " //
				+ "SET writing_sub = ?, " + "    writing = ? " + "WHERE writing_no = ?";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, writing.getWritingSub());
			psmt.setString(2, writing.getWriting());
			psmt.setInt(3, writing.getWritingNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// 게시글 삭제
	@Override
	public boolean deletePost(int writingNo) {
		conn = getConnect();
		String sql = "DELETE writing_list " //
				+ "WHERE writing_no = ?";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, writingNo);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// 댓글 보기
	@Override
	public List<ReComment> getComment(int writingNo) {

//		List<Comment> comments = new ArrayList<>();
//		
//		conn = getConnect();
//		String sql = "select *\r\n"
//				   + "from comment_list\r\n"
//				   + "where writing_no = ?";
//		
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, writingNo);
//			rs = psmt.executeQuery();
//
//			while (rs.next()) {
//				Comment com = new Comment();
//				com.setWritingNo(writingNo);
//				com.setCommentNo(rs.getInt("comment_no"));
//				com.setCommentDate(rs.getString("comment_date"));
//				com.setUserId(rs.getString("user_id"));
//				com.setUserComment(rs.getString("user_comment"));
//				
//				comments.add(com);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//
//		return comments;

		List<ReComment> comments = new ArrayList<>();

		conn = getConnect();
		String sql = "select *\r\n"
				+ "from comment_list LEFT OUTER JOIN recomment_list ON comment_list.comment_no = recomment_list.comment_no\r\n"
				+ "where writing_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, writingNo);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ReComment reCom = new ReComment();
				reCom.setWritingNo(writingNo);
				reCom.setUserId(rs.getString("user_id"));
				reCom.setCommentNo(rs.getInt("comment_no"));
				reCom.setCommentDate(rs.getString("comment_date"));
				reCom.setUserComment(rs.getString("user_comment"));
				reCom.setReUserId(rs.getString("re_user_id"));
				reCom.setReCommentNo(rs.getInt("recomment_no"));
				reCom.setReCommentDate(rs.getString("recomment_date"));
				reCom.setUserReComment(rs.getString("user_recomment"));

				comments.add(reCom);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return comments;
	}
	
	

	// 새 댓글 달기
	@Override
	public boolean commenting(Comment comment) {

		conn = getConnect();
		String sql = "INSERT INTO comment_list (writing_no, comment_no, comment_date, user_id, user_comment)\r\n"
				+ "values (?, tmp_seq2.NEXTVAL, sysdate, ?, ?)";
		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, comment.getWritingNo());
			psmt.setString(2, comment.getUserId());
			psmt.setString(3, comment.getUserComment());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	// 대댓글 달기
	@Override
	public boolean reCommenting(ReComment reCom) {

		conn = getConnect();
		String sql = "insert into recomment_list (comment_no, recomment_no, recomment_date, re_user_id, user_recomment)\r\n"
				+ "values (?, ?||tmp_seq3.NEXTVAL, sysdate, ?, ?)";
		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, reCom.getCommentNo());
			psmt.setInt(2, reCom.getCommentNo());
			psmt.setString(3, reCom.getUserId());
			psmt.setString(4, reCom.getUserReComment());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	// 댓글 수정
	@Override
	public boolean updateComment(Comment comment) {
		conn = getConnect();
		String sql = "UPDATE comment_list\r\n" + "SET user_comment = ?" + "WHERE comment_no = ?";
		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, comment.getUserComment());
			psmt.setInt(2, comment.getCommentNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	// 댓글 삭제
	@Override
	public boolean deleteComment(int commentNo) {

		conn = getConnect();
		String sql = "DELETE comment_list\r\n" + "WHERE comment_no = ?";
		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, commentNo);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	// 댓글 번호 동일 체크
	public boolean searchComment(int writingNo, int commentNo) {

		conn = getConnect();

		String sql = "SELECT * FROM comment_list\r\n" //
				+ "WHERE writing_no = ? AND comment_no = ?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, writingNo);
			psmt.setInt(2, commentNo);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 유저 체크(회원가입)
	@Override
	public boolean checkUser(String userId) {
		conn = getConnect();
		String sql = "SELECT * FROM user_info\r\n" //
				+ "WHERE user_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 유저 체크 (글)
	@Override
	public boolean checkUser(User user, int writingNo) {

		conn = getConnect();
		String sql = "SELECT * FROM writing_list\r\n" //
				+ "WHERE user_id = ? AND writing_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setInt(2, writingNo);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// 유저 체크 (댓글)
	@Override
	public boolean checkUserComment(User user, int commentNo) {

		conn = getConnect();
		String sql = "SELECT * FROM comment_list\r\n" //
				+ "WHERE user_id = ? AND comment_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setInt(2, commentNo);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
