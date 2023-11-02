package com.signied.controller.action;

public class ActionFactory {
	private ActionFactory() {
	}

	public static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		System.out.println(command);

		if (command.equals("hotelQnA")) {
			action = new HotelQnAAction();
		} else if (command.equals("QnAwrite")) {
			action = new HotelQnAWriteAction();
		} else if (command.equals("QnAList")) {
			action = new HotelQnAListAction();
		} else if (command.equals("QnA_check")) {
			action = new HotelQnACheckFormAction();
		} else if (command.equals("QnA_check_pass")) {
			action = new HotelQnACheckAction();
		} else if (command.equals("QnA_view")) {
			action = new HotelQnAViewAction();
		} else if (command.equals("QnA_delete")) {
			action = new HotelQnADeleteAction();
		} else if (command.equals("QnA_admin_check_form")) {
			action = new HotelQnAadminCheckForm();
		} else if (command.equals("QnA_admin_check_pass")) {
			action = new HotelQnAadminCheckAction();
		} else if (command.equals("QnA_reply_form_admin")) {
			action = new HotelQnAReplyFormAction();
		} else if (command.equals("people_num"))
			action = new PeopleNumAction();
		else if (command.equals("date_input"))
			action = new DateInputAction();
		else if (command.equals("search_room"))
			action = new SearchRoomList();
		else if (command.equals("get_date"))
			action = new GetSystemDateAction();
		else if (command.equals("QnA_Reply"))
			action = new HotelQnAReplyAction();
		else if (command.equals("QnA_reply_check_form"))
			action = new HotelQnAReplyCheckForm();
		else if (command.equals("QnA_reply_check_pass"))
			action = new HotelQnAReplyCheckPass();
		else if (command.equals("QnA_reply_view"))
			action = new HotelQnAReplyViewAction();
		else if (command.equals("Reservation_save"))
			action = new ReservationSaveAction();
		else if (command.equals("Reservation_num"))
			action = new ReservationNumAction();
		else if (command.equals("detail_search"))
			action = new DetailSearchRoom();
		else if (command.equals("Reservation_complete"))
			action = new ReservationComplete();
		else if (command.equals("Reservation_pass_check_form"))
			action = new ReservationPassCheckForm();
		else if (command.equals("Reservation_check_pass"))
			action = new ReservationCheckPass();
		else if (command.equals("Reservation_delete"))
			action = new ReservationDelete();

		return action;
	}
}
