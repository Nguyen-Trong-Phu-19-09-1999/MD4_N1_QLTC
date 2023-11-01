$(document).on('click', '#siderbar li', function (){
    $(this).addClass('active').siblings().removeClass('active')
});
$(document).ready(function() {
    $('#toggleSidebar').click(function(event) {
        event.preventDefault();
        $('.left-menu').toggleClass('hide');
        $('.content-wrapper').toggleClass('hide');
    });

    $('.sub-menu ul').hide();
    $('.sub-menu a').click(function(event) {
        event.preventDefault();
        $(this).parent('.sub-menu').children('ul').slideToggle('100');
        $(this).find('.right').toggleClass('fa-caret-up fa-caret-down');
    });
});
