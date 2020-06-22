$(function () {
    /* ------------------menu 缩小放大-------------------*/

    $(function () {
        $.sidebarMenu(".sidebar-menu");
        $(".menu").click(function () {
            if( $("body").is(".sidebar-mini")  ){
                $("body").removeClass("sidebar-mini")
            }else {
                $("body").addClass("sidebar-mini")
            }
        })
    });

});