<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>Award Boxes</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="css/main.css">
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script type="text/javascript" src="js/lib/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
    <div class="boxes">
        <div id="middle">
            <div id="awardSection">
                <div class="blockS">
                    <div class="title">中奖名单</div>
                    <div id="awardList">
                        <c:if test="${fn:length(recordList) > 0}">
                            <c:forEach items="${recordList}" var="record" varStatus="loop">
                                <p>恭喜${record.userName} 获得 ${record.itemName}</p>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
                <div class="blockS">
                    <div class="title">会员账号</div>

                    <div id="memberList">
                        <p>${userAccount}</p>
                        <c:choose>
                            <c:when test="${todayHasOpen eq true}">
                                <p>今日已开启</p>
                            </c:when>
                            <c:otherwise>
                                <p>今日未开启</p>
                            </c:otherwise>
                        </c:choose>
                        <p>开启宝箱次数显示</p>
                        <p>${countOpenBox}次</p>
                    </div>
                </div>

            </div>
            <div id="showAwardsection">
            </div>
            <div id="boxes">
                <div id="box1" class="closedAwardBox"></div>
                <div id="box2" class="closedAwardBox"></div>
                <div id="box3" class="closedAwardBox"></div>
                <div id="box4" class="closedAwardBox"></div>
            </div>
        </div>
    </div>
    <div class="gotAwardTitle">
        <div id="awardTitle" class="awardTitle"><p id="awardName">${sessionScope.todayItem.name}</p></div>
    </div>
    <form id="updateSigninInfo" method="post" action="/fafaSignin/updateSigninInfo">
        <input type="hidden" id="itemId" name="itemId" value="${sessionScope.todayItem.itemId}">
        <input type="hidden" id="userId" name="userId" value="${userId}">
        <input type="hidden" id="userAccount" name="userAccount" value="${userAccount}">
        <input type="hidden" id="fromPage" name="fromPage" value="${fromPage}">
    </form>
</body>
<script>
    window.onload = function(){
        var fromPage = $('#fromPage').val();
        $("#awardSection").css('marginTop',"100px");
        $("#boxes").css('marginTop',"-300px");
        if(fromPage !== '1'){
            $("#boxes").css('display',"none");
            alert('请勿使用非正常操作行为');
        }
    }

</script>
</html>