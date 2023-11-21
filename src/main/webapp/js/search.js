var dateIn = "";
var dateIn2 = "";

$(function () {
   $('.sertchClick > a').click(function (e) {
      e.preventDefault();
      $('.sertchWrap').stop().slideToggle();
   });


   $('.date_anchor').click(function (e) {
      e.preventDefault();
      var dateInput = $('.dateInput');
      if (dateInput.css('top') === '80px' && dateInput.css('opacity') === '1') {
         // 현재 상태가 top: 80px이고 opacity: 1인 경우
         dateInput.animate({ top: 40, opacity: 0 }, 600);
      } else {
         // 현재 상태가 top: 50px이고 opacity: 0인 경우
         dateInput.animate({ top: 80, opacity: 1 }, 600);
      };
   });

   //예약 가능 날짜 제한(현재 날짜 ~ 두달 후 까지)
   var now = new Date();
   var oneMonthLater = new Date(now.setMonth(now.getMonth() + 2));   // 두달 후
   const year = oneMonthLater.getFullYear();
   const month = ('0' + (oneMonthLater.getMonth() + 1)).slice(-2);
   const day = ('0' + oneMonthLater.getDate()).slice(-2);
   const dateStr = `${year}-${month}-${day}`;
   console.log(dateStr);


   var input = document.getElementById('input-id');
   var datepicker = new HotelDatepicker(input, {
      endDate: dateStr,
      inline: true,
      showTopbar: false,
      moveBothMonths: true,
      minNights: 1,
      maxNights: 30,
      startOfWeek: 'sunday',
      selectForward: true,
      format: 'YYYY-MM-DD',
      i18n: {
         selected: '선택한 기간:',
         night: '박',
         nights: '박',
         button: '닫기',
         clearButton: '취소',
         submitButton: '확인',
         'checkin-disabled': '체크인 비활성화',
         'checkout-disabled': '체크아웃 비활성화',
         'day-names-short': ['일', '월', '화', '수', '목', '금', '토'],
         'day-names': ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
         'month-names-short': ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
         'month-names': ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
         'error-more': '1박 이상으로 설정해주세요',
         'error-more-plural': '%d박 이상으로 설정해주세요',
         'error-less': '1박 이상으로 설정해주세요',
         'error-less-plural': '%d박 이상으로 설정해주세요',
         'info-more': '1박 이상으로 설정해주세요',
         'info-more-plural': '%d박 이상으로 설정해주세요',
         'info-range': '%d부터 %d박까지의 기간을 선택하세요',
         'info-range-equal': '%d박을 선택하세요',
         'info-default': '날짜 범위를 선택하세요',
         'aria-application': '달력',
         'aria-selected-checkin': '체크인일로 선택된 날짜, %s',
         'aria-selected-checkout': '체크아웃일로 선택된 날짜, %s',
         'aria-selected': '선택된 날짜, %s',
         'aria-disabled': '사용 불가능한 날짜, %s',
         'aria-choose-checkin': '%s를 체크인 날짜로 선택하세요',
         'aria-choose-checkout': '%s를 체크아웃 날짜로 선택하세요',
         'aria-prev-month': '이전 달을 보려면 이전으로 이동하세요',
         'aria-next-month': '다음 달을 보려면 다음으로 이동하세요',
         'aria-close-button': '달력 닫기',
         'aria-clear-button': '선택된 날짜 지우기',
         'aria-submit-button': '확인 버튼'
      },
      onSelectRange: function () {
         console.log('날짜 범위가 선택되었습니다!');
         var inputData = $('#input-id').val(); // input-id의 값을 가져옵니다.
         var bak = $('#tooltip-input-id').text();
         $.ajax({
            url: 'HotelServlet?command=date_input', // 서버 엔드포인트 URL을 입력합니다.
            type: 'get',
            data: { data: inputData, bak: bak },
            success: function (response) {
               var bak = response.bak; // + 추가 bak을 받아와서 출력함
               $('.night').text(bak);
               var date1 = response.dateView;
               var date2 = response.dateView2;
               console.log(date1);
               console.log(date2);
               dateIn = response.dateIn;
               dateIn2 = response.dateIn2;
               // 서버에서 반환된 응답을 hotelCheckinAndCheckout div에 표시합니다.
               $('.date_day').eq(0).text(date1);
               $('.date_day').eq(1).text(date2);
            },
            error: function () {
               console.log("에러 발생");

            }
         });

      },

      /*onBasicRange: function(){
        console.log('기본 날짜 범위를 출력합니다!');
      }*/

   });
});

//index 페이지의 어른, 어린이 인원수 +- 버튼 작동
function add(targetId) {
   let adultAmount = document.getElementById('adultAmount').value;
   let childAmount = document.getElementById('childAmount').value;
   let totalAmount = parseInt(adultAmount) + parseInt(childAmount);
   //어른 + 어린이 수 최대 4명까지 가능하도록 함
   if (totalAmount < 4) {
      let hm = document.getElementById(targetId);
      hm.value++;
   }
}

function del(targetId) {
   let hm = document.getElementById(targetId);
   if (targetId === 'adultAmount' && hm.value <= 1) {
      // 어른 수량이 1 이하일 때는 값을 감소시키지 않음
      return;
   }

   if (hm.value > 0) {
      hm.value--;
   }
}

$(function () {
   $('.amounts').click(function () {

      $.ajax({
         url: 'HotelServlet?command=people_num', // 서버 엔드포인트 URL을 입력
         type: 'get',
         data: {
            adultAm: $('#adultAmount').val(),
            childAm: $('#childAmount').val()
         },
         success: function (response) {
            var amountView = response.amountView;
            var amountView2 = response.amountView2;
            console.log(amountView);
            console.log(amountView2);
            // 서버에서 반환된 응답을 hotelCheckinAndCheckout div에 표시
            $('.person_num').eq(1).text(amountView);
            $('.person_num').eq(2).text(amountView2);
         },
         error: function () {
            console.log("에러 발생", textStatus, errorThrown);

         }
      })
   });
});



//default date 입력
$(function () {
   // 서버에서 현재 날짜를 가져오는 요청
   $.ajax({
      url: 'HotelServlet?command=get_date',
      type: 'get',
      success: function (response) {
         var dateView = response.dateView;
         var dateView2 = response.dateView2;
         dateIn = response.dateIn;
         dateIn2 = response.dateIn2;
         // 가져온 현재 날짜를 이미 존재하는 dateView 엘리먼트에 넣어줌
         $('.date_day').eq(0).text(dateView);
         $('.date_day').eq(1).text(dateView2);
      },
      error: function () {
         console.log("에러 발생");
      }
   });
});

function amountCount() {
   var amountView = $('.person_num').eq(1).text();
   var amountView2 = $('.person_num').eq(2).text();
   var date1 = $('.date_day').eq(0).text();
   var date2 = $('.date_day').eq(1).text();
   var bak = $('.night').text();
   console.log(date1);
   console.log(date2);
   console.log(bak);
   $('.command').append("<input type='hidden' value='" + amountView + "' name='adultCount' />");
   $('.command').append("<input type='hidden' value='" + amountView2 + "' name='childCount' />");
   $('.command').append("<input type='hidden' value='" + date1 + "' name='checkIn' />");
   $('.command').append("<input type='hidden' value='" + date2 + "' name='checkOut' />");
   $('.command').append("<input type='hidden' value='" + bak + "' name='bak' />");
   $('.command').append("<input type='hidden' value='" + dateIn + "' name='originCheckIn' />");
   $('.command').append("<input type='hidden' value='" + dateIn2 + "' name='originCheckOut' />");
}
