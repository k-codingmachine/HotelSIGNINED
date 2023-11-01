package com.signied.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.signied.dto.ReservationVO;
import com.signied.util.DBManager;

public class ReservationDAO {
	private static ReservationDAO instance = new ReservationDAO();
	
	private ReservationDAO() {}
	
	public static ReservationDAO getInstance() {
		return instance;
	}
// 예약조회	
	public ReservationVO selectOneByNum(int num) {
		ReservationVO vo = null;
		String sql = "select * from reservation where RESERVENUM =?";
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new ReservationVO();
				vo.setReserveNum(rs.getInt(1));
				vo.setReserveEmail(rs.getString(2));
				vo.setReservePwd(rs.getString(3));
				vo.setReserveName(rs.getString(4));
				vo.setReservePhone(rs.getString(5));
				vo.setCheckIn(rs.getString(6));
				vo.setCheckOut(rs.getString(7));
				vo.setGuestNum(rs.getInt(8));
				vo.setBreakfast(rs.getInt(9));
				vo.setRoomNum(rs.getInt(10));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

// 예약등록
	public int insertReservation(ReservationVO vo1) {
		int result = -1;
		String sql = "insert into RESERVATION values(RESERVATION_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo1.getReserveEmail());
			pstmt.setString(2, vo1.getReservePwd());
			pstmt.setString(3, vo1.getReserveName());
			pstmt.setString(4, vo1.getReservePhone());
			pstmt.setString(5, vo1.getCheckIn());
			pstmt.setString(6, vo1.getCheckOut());
			pstmt.setInt(7, vo1.getGuestNum());			
			pstmt.setInt(8, vo1.getBreakfast());
			pstmt.setInt(9, vo1.getRoomNum());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
// 예약 삭제
	public int deleteReservation(int num) {
		int result = -1;
		String sql = "delete from reservation where reservenum=?";
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}


		
}