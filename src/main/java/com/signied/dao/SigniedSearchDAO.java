package com.signied.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
		// 주어진 체크인/체크아웃 날짜에 사용 가능하며 주어진 인원 수를 수용할 수 있는 방들의 정보를 가격 오름차순으로 가져옴.
		String sql = "SELECT r.roomNum, r.roomName, r.roomType, r.viewType, r.roomCapacity, r.roomPrice, r.inventory, r.img\n"
				+ "FROM room r\n"

				// reservation 테이블에서 주어진 체크인과 체크아웃 날짜 조건을 만족하는 데이터를 그룹화하여 해당 기간에 예약된 방의
				// 개수(daily_reserved_count)를 계산
				// 계산된 정보를 room 테이블과 결합(LEFT JOIN)
				// 만약 room 테이블의 특정 방에 해당하는 예약 정보가 reservation 테이블에 없으면 daily_reserved_count는
				// NULL 값으로 처리
				+ "LEFT JOIN (\n"
				+ "    SELECT roomNum, COUNT(*) as daily_reserved_count\n"
				+ "    FROM reservation\n"
				+ "    WHERE (checkIn <= TO_DATE( ? , 'YYYY-MM-DD') AND checkOut > TO_DATE( ? , 'YYYY-MM-DD')) \n"
				+ "    GROUP BY roomNum\n"
				+ ") res ON r.roomNum = res.roomNum\n"

				// 방의 수용 인원(roomCapacity)이 주어진 인원 수(totalAmount)보다 크거나 같아야함
				// COALESCE 함수는 첫 번째 인자의 값이 NULL인 경우 두 번째 인자의 값을 반환하고
				// 해당 방에 대한 예약이 없으면 daily_reserved_count 값이 NULL이므로, 이 경우 0을 반환한다.
				// 따라서 해당 방의 예약된 개수가 해당 방의 재고(inventory)보다 작아야 한다는 조건을 나타냄
				+ "WHERE r.roomCapacity >= ? \n"
				+ "AND COALESCE(res.daily_reserved_count, 0) < r.inventory "

				// 결과를 방의 가격(roomPrice)에 따라 오름차순으로 정렬
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

	public List<RoomVO> detailSearchRoom(String sort, List<String> viewTypes, List<String> roomTypes,
			List<RoomVO> roomLists)
			throws SQLException {
		List<RoomVO> roomList = new ArrayList<RoomVO>();

		// roomNum 리스트 가져오기
		List<Integer> roomNums = roomLists.stream().map(RoomVO::getRoomNum).collect(Collectors.toList());

		List<String> conditions = new ArrayList<>();

		// roomNums를 조건에 추가
		StringJoiner roomNumCondition = new StringJoiner(", ", "roomNum IN (", ")");
		for (Integer roomNum : roomNums) {
			roomNumCondition.add(roomNum.toString());
		}
		conditions.add(roomNumCondition.toString());

		if (viewTypes != null && !viewTypes.isEmpty()) {
			StringJoiner viewCondition = new StringJoiner("', '", "viewType IN ('", "')");
			for (String viewType : viewTypes) {
				viewCondition.add(viewType);
			}
			conditions.add(viewCondition.toString());
		}

		if (roomTypes != null && !roomTypes.isEmpty()) {
			StringJoiner roomTypeCondition = new StringJoiner("', '", "roomType IN ('", "')");
			for (String roomType : roomTypes) {
				roomTypeCondition.add(roomType);
			}
			conditions.add(roomTypeCondition.toString());
		}

		String orderBy;
		if ("asc".equalsIgnoreCase(sort)) {
			orderBy = "ORDER BY roomPrice ASC";
		} else if ("desc".equalsIgnoreCase(sort)) {
			orderBy = "ORDER BY roomPrice DESC";
		} else {
			throw new IllegalArgumentException("Invalid sort type: " + sort);
		}

		String sql = "SELECT * FROM room";
		if (!conditions.isEmpty()) {
			sql += " WHERE " + String.join(" AND ", conditions);
		}
		sql += " " + orderBy;

		try (Connection conn = DBManager.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				RoomVO vo = new RoomVO();
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
		}
		return roomList;
	}
}
