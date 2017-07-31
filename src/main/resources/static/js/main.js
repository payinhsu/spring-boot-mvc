$(document).ready(function () {
    // Opera 8.0+
    var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
    // Firefox 1.0+
    var isFirefox = typeof InstallTrigger !== 'undefined';
    // Safari 3.0+ "[object HTMLElementConstructor]"
    var isSafari = /constructor/i.test(window.HTMLElement) || (function (p) {
            return p.toString() === "[object SafariRemoteNotification]";
    })(!window['safari'] || safari.pushNotification);
    // Internet Explorer 6-11
    var isIE = /*@cc_on!@*/false || !!document.documentMode;
    // Edge 20+
    var isEdge = !isIE && !!window.StyleMedia;
    // Chrome 1+
    var isChrome = !!window.chrome && !!window.chrome.webstore;
    // Blink engine detection
    var isBlink = (isChrome || isOpera) && !!window.CSS;

    var cumulativeOffset = function(element) {
        var top = 0, left = 0;
        do {
            top += element.offsetTop  || 0;
            left += element.offsetLeft || 0;
            element = element.offsetParent;
        } while(element);

        return {
            top: top,
            left: left
        };
    };

    $("#box1").click(function () {
        openBox(this.id);
    });
    $("#box2").click(function () {
        openBox(this.id);
    });
    $("#box3").click(function () {
        openBox(this.id);
    });
    $("#box4").click(function () {
        openBox(this.id);
    });

    var smallerBox = function (id) {
        $("#" + id).attr('class', 'smallerOpenedAwardBox');
        //remove click event
        $("#" + id).prop('onclick', null).off('click');
        $("#" + id).nextAll().each(function (index, element) {
            $(element).prop('onclick', null).off('click');
        });
        $("#" + id).prevAll().each(function (index, element) {
            $(element).prop('onclick', null).off('click');
        });

        $("div").remove("#box5");

        $('#awardTitle').css('display', 'none');
        $("#" + id).nextAll().each(function (index, element) {
            $(element).addClass("closedAwardBox");
        });
        $("#" + id).prevAll().each(function (index, element) {
            $(element).addClass("closedAwardBox");
        });
    }

    var openBox = function (id) {
        var beforeTop = cumulativeOffset($("#" + id).get(0)).top;
        var beforeLeft = cumulativeOffset($("#" + id).get(0)).left;
        $('#awardTitle').css('display', 'inline-block');

        $("#" + id).attr('class', 'activeClosedAwardBox');
        //remove click event
        $("#" + id).prop('onclick', null).off('click');
        $("#" + id).nextAll().each(function (index, element) {
            $(element).prop('onclick', null).off('click');
        });
        $("#" + id).prevAll().each(function (index, element) {
            $(element).prop('onclick', null).off('click');
        });
        if (isFirefox) {
            // console.log('top:'+beforeTop);
            // console.log('left:'+beforeLeft);
            if (id === "box1") {
                var des = $("#showAwardsection").position();
                $("#" + id).attr('class', 'closedAwardBoxAnimation1');
                // console.log('des.top:'+des.top);
                // console.log('des.left:'+des.left);
                $("#" + id).animate({
                    top: -$(document).height()/2,
                    left: '450px'
                }, 2500, function () {
                    // $("#" + id).attr('class', 'openedAwardBox1');
                });
                // $("#" + id).animate({
                //     marginTop: '-=' + $(document).height() / 4,
                //     marginLeft: '+=' + $(document).width() / 2
                // }, 4000, function() {
                // });


            } else if (id === "box2") {
                $("#" + id).attr('class', 'closedAwardBoxAnimation2');
                // var des = $("#showAwardsection").position();
                // console.log(des);
                // var el = $("#" + id);
                // el.css("position", "absolute");
                // el.animate({ top: (des.top/5)+"px" ,left :($(document).width() / 2)+"px"}, 2000, undefined, function () {
                //     // el.append("<div>hello world</div>")
                //     setTimeout(function () {
                //         el.css("position", "relative");
                //         el.css("display", "inline-block");
                //         el.attr('class','smallerOpenedAwardBox');
                //         // el.prev().each(function (index, element) {
                //         //     console.log(element);
                //         //
                //         //     if(element){
                //         //         console.log(el.attr('id'));
                //         //         // el.parent().append("<div id='"+el.attr('id')+"' class='smallerOpenedAwardBox'></div>")
                //         //     }
                //         // });
                //         if(el.prev()){
                //             console.log(el.prev());
                //             el.prev().append("<div id='"+el.attr('id')+"' class='smallerOpenedAwardBox'></div>")
                //             // el.insertAfter(el.prev());
                //         }else if(el.after()){
                //             console.log(el.after());
                //             el.insertBefore(el.after());
                //         }
                //     }, 50);
                //     setTimeout(function () {
                //         el.remove();
                //     }, 100);
                // });
                var des = $("#showAwardsection").position();
                var el = $("#" + id);
                el.css("position", "absolute");
                // console.log('des.top:'+des.top);
                // console.log('des.left:'+des.left);
                el.animate({ top: -des.top+"px" ,left :des.left+"px"}, 2000, undefined, function () {
                    if(el.prev() && el.prev().hasClass('closedAwardBox')){
                        // console.log(el.prev());
                        el.prev().append("<div id='"+el.attr('id')+"' class='smallerOpenedAwardBox'></div>");
                        // el.insertAfter(el.prev());
                    }else if(el.next() && el.next().hasClass('closedAwardBox')){
                        // console.log(el.next());
                        $("<div id='"+el.attr('id')+"' class='smallerOpenedAwardBox'></div>").insertBefore(el.next());
                    }
                    setTimeout(function () {
                        el.remove();
                    }, 50);
                    // .appendTo("#showAwardsection").css("position", "relative").css("display", "inline-block");
                });
            } else if (id === "box3") {
                $("#" + id).attr('class', 'closedAwardBoxAnimation3');
            } else if (id === "box4") {
                $("#" + id).attr('class', 'closedAwardBoxAnimation4');
            }
        } else {
            $("#" + id).attr('class', 'closedAwardBoxAnimation');
        }

        $("#awardTitle").addClass('showAwardTitle').outerWidth(); // Reflow

        $("#awardTitle").addClass('hideAwardTitle').one('transitionend', function () {
            setTimeout(function () {
                $("#awardName").text('');
                $("#awardTitle").removeClass('awardTitle');
            }, 1000);
        });
        setTimeout(function () {
            $("#fromPage").val('2');
            $("#updateSigninInfo").submit();
        }, 5000);
        // $(".boxes").append('<div id="box5" class="closedAwardBox"></div>');

        // $("#"+id).nextAll().each(function(index,element){
        //     $(element).removeClass(element.className);
        // });
        // $("#"+id).prevAll().each(function(index,element){
        //     $(element).removeClass(element.className);
        // });
        // setTimeout(function() { smallerBox(id); }, 2000);

    };
});


