$(function(){
    $(".menu-items li").mouseenter(function(){
        $(".menu-items li.hover").removeClass("hover");
        $(this).addClass("hover");
        $(".menu-tabs").show();
        $(".menu-tabs .mtab").hide();
        $(".menu-tabs .mtab").eq($(this).index()).show();
    });
    $(".menu-items").parent().mouseleave(function(){
        $(".menu-items li.hover").removeClass("hover");
        $(".menu-tabs").hide();
    });
    $(".menu-tabs").mouseleave(function(){
        $(".menu-items li.hover").removeClass("hover");
        $(".menu-tabs").hide();
    });

/*    $(".tabtop li").click(function(){
        $(".tabtop li.active").removeClass("active");
        $(this).addClass("active");
        $(".tabcontent .tabs.active").removeClass("active");
        $(".tabcontent .tabs").eq($(this).index()).addClass("active");
    });*/

    $('.flexslider').flexslider({
        animation: "slide",
        directionNav:false,
        slideshow: true, 
        slideshowSpeed: 20000 
    });
});