package com.signied.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.signied.dto.RoomVO;
import com.signied.util.DBManager;

public class SigniedSearchDAO {

	private SigniedSearchDAO() {
	}

	private static SigniedSearchDAO instance = new SigniedSearchDAO();

	public static SigniedSearchDAO getInstance() {
		return instance;
	}

	public List<RoomVO> searchRoom(String checkIn, String checkOut, int totalAmount) throws SQLException {

		List<RoomVO> list = new ArrayList<RoomVO>();
		String sql = "SELECT r.roomNum, r.roomName, r.roomType, r.viewType, r.roomCapacity, r.roomPrice, r.inventory, r.img\n"
				+ "FROM room r\n"
				+ "LEFT JOIN (\n"
				+ "    SELECT roomNum, COUNT(*) as daily_reserved_count\n"
				+ "    FROM reservation\n"
				+ "    WHERE (checkIn <= TO_DATE( ? , 'YYYY-MM-DD') AND checkOut > TO_DATE( ? , 'YYYY-MM-DD')) \n"
				+ "    GROUP BY roomNum\n"
				+ ") res ON r.roomNum = res.roomNum\n"
				+ "WHERE r.roomCapacity >= ? \n"
				+ "AND COALESCE(res.daily_reserved_count, 0) < r.inventory "
				+ "ORDER BY r.roomPrice ASC";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RoomVO vo = null;

		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, checkIn);
			ps.setString(2, checkOut);
			ps.setInt(3, totalAmount);

			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new RoomVO();
				vo.setRoomNum(rs.getInt("roomNum"));
				vo.setRoomName(rs.getString("roomname"));
				vo.setRoomType(rs.getString("ROOMTYPE"));
				vo.setViewType(rs.getString("viewType"));
				vo.setInventory(rs.getInt("inventory"));
				vo.setRoomCapacity(rs.getInt("roomCapacity"));
				vo.setRoomPrice(rs.getInt("roomPrice"));
				vo.setImg(rs.getString("img"));

				list.add(vo);
				// System.out.println("검색한 room list : " + vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, ps, rs);
		}
		return list;
	}

	public List<RoomVO> detailSearchRoom(String sort, List<String> viewTypes, List<String> roomTypes)
			throws SQLException {
		StringBuilder query = new StringBuilder("SELECT * FROM room WHERE 1=1");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RoomVO vo = null;
		List<RoomVO> roomList = new ArrayList<RoomVO>();

		List<String> conditions = new ArrayList<>();

		if (viewTypes != null) {
			StringJoiner viewCondition = new StringJoiner("', '", "viewtype IN ('", "')");
			for (String viewType : viewTypes) {
				// 뷰타입에 대한 입력 검증을 하십시오 (예: Enum을 사용하여 확인)
				viewCondition.add(viewType);
			}
			conditions.add(viewCondition.toString());
		}

		if (roomTypes != null) {
			StringJoiner bedCondition = new StringJoiner("', '", "roomtype IN ('", "')");
			for (String bedType : roomTypes) {
				// 침대타입에 대한 입력 검증을 하십시오 (예: Enum을 사용하여 확인)
				bedCondition.add(bedType);
			}
			conditions.add(bedCondition.toString());
		}

		// 정렬 조건
		String orderBy = "";
		if (sort.equals("asc")) {
			orderBy = "ORDER BY roomprice ASC";
		} else if (sort.equals("desc")) {
			orderBy = "ORDER BY roomprice DESC";
		}

		// SQL 쿼리 구성
		String sql = "SELECT * FROM room";
		if (!conditions.isEmpty()) {
			sql += " WHERE " + String.join(" AND ", conditions);
		}
		sql += " " + orderBy;

		// DB 연결 및 쿼리 실행
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 결과 처리
			while (rs.next()) {
				// 각 호텔 정보 처리 (예: 출력 또는 객체화)
				vo = new RoomVO();
				vo.setRoomNum(rs.getInt("roomNum"));
				vo.setRoomName(rs.getString("roomName"));
				vo.setRoomType(rs.getString("roomType"));
				vo.setViewType(rs.getString("viewType"));
				vo.setRoomCapacity(rs.getInt("roomCapacity"));
				vo.setRoomPrice(rs.getInt("roomPrice"));
				vo.setInventory(rs.getInt("inventory"));
				vo.setImg(rs.getString("img"));

				roomList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// 오류 처리
		} finally {
			DBManager.close(conn, ps, rs);
		}

		return roomList;

	}
}
