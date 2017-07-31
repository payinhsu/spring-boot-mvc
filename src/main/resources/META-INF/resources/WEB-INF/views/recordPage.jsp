<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>award records</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script type="text/javascript" src="js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
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
        <c:if test="${not empty errorCode and not empty errorDesc and errorCode ne '99SYS0000'}">
            <div id="errorDialog" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">发生错误</h4>
                        </div>
                        <div class="modal-body">
                            <p>错误训席:${errorDesc}</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="errorCode" name="errorCode" value="${errorCode}"/>
        </c:if>
    </div>
</body>
<script>
    window.onload = function(){
        console.log('errorCode='+$("#errorCode").val());
        if($("#errorCode").val() !== '')
            $('#errorDialog').modal('show');
    }
</script>
</html>