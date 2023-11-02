function slideSetInterval(){
    $('.slider').stop().animate({marginLeft:-2000},1500, function(){
        $(".slider img:first").appendTo('.slider');
        $('.slider').css({marginLeft:0});
    });
}

setInterval(slideSetInterval, 7000);