<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Static Content DEMO Page</title>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.2.0/css/bootstrap.min.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<table class="table">
		<h2>輸入共100％的各項機率</h2>
		<tr>
			<th>獎項一</th>
			<td>
				<input type="text" id="box1" />%
			</td>
		</tr>
		<tr>
			<th>獎項二</th>
			<td>
				<input type="text" id="box2" />%
			</td>
		</tr>
		<tr>
			<th>獎項三</th>
			<td>
				<input type="text" id="box3" />%
			</td>
		</tr>
		<tr>	
			<th>獎項四</th>
			<td>
				<input type="text" id="box4" />%
			</td>
		</tr>
		<tr>
			<th>獎項五</th>
			<td>
				<input type="text" id="box5" />%
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-info" value="單次抽獎" onclick="takeRandom()" />
			</td>
		</tr>
	</table>
	
	
		<br />
		<br />
		<h2>測試機率</h2>
		連續<input type="text" id="loopSize" size="5"  />次
		<div>
			<input type="button" class="btn btn-info" value="查看連續機率" onclick="testForLoops()" />
			<div id="loopsResultDiv"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			
		});
		
		function testForLoops(){
			var check = checkPercent();
			if(check == false){
				alert("請正確輸入機率，須加總共100％");
				return ;
			}
			var loopSize = $('#loopSize').val();
			if(loopSize == ''){
				alert("請輸入連續次數！！");
				return;
			}
			var loopSizeInt = parseInt($.trim(loopSize));
			var countBox1 = 0;
			var countBox2 = 0;
			var countBox3 = 0;
			var countBox4 = 0;
			var countBox5 = 0;
			for(var i=0 ; i<loopSizeInt; i++ ){
				var item = getRandom();
				switch(item){
					case 'box1':
						countBox1++;
						break;
					case 'box2':
						countBox2++;
						break;
					case 'box3':
						countBox3++;
						break;
					case 'box4':
						countBox4++;
						break;
					case 'box5':
						countBox5++;
						break;
					default:
						itemStr = "";
						break;
				}
			}
			
			var myHTML = "";
			myHTML = "<table>"
			+ "<tr>"
			+ "<td>"
			+ "獎項一 : 機率為：" + $('#box1').val() + "% ，共中次數： " + countBox1
			+ "</td>"
			+ "</tr>"

			+ "<tr>"
			+ "<td>"
			+ "獎項二 : 機率為：" + $('#box2').val() + "% ，共中次數： " + countBox2
			+ "</td>"
			+ "</tr>"

			+ "<tr>"
			+ "<td>"
			+ "獎項三 : 機率為：" + $('#box3').val() + "% ，共中次數： " + countBox3
			+ "</td>"
			+ "</tr>"

			+ "<tr>"
			+ "<td>"
			+ "獎項四 : 機率為：" + $('#box4').val() + "% ，共中次數： " + countBox4
			+ "</td>"
			+ "</tr>"

			+ "<tr>"
			+ "<td>"
			+ "獎項五 : 機率為：" + $('#box5').val() + "% ，共中次數： " + countBox5
			+ "</td>"
			+ "</tr>"
			+ "</TABLE>"
			$('#loopsResultDiv').empty().append(myHTML);
		}
		
		function takeRandom(){
			var check = checkPercent();
			if(check == false){
				alert("請正確輸入機率，須加總共100％");
				return ;
			}
			
			var item = getRandom();
			var idStr = "#" + item;
			var itemStr;
			switch(item){
				case 'box1':
					itemStr = "獎項一";
					break;
				case 'box2':
					itemStr = "獎項二";
					break;
				case 'box3':
					itemStr = "獎項三";
					break;
				case 'box4':
					itemStr = "獎項四";
					break;
				case 'box5':
					itemStr = "獎項五";
					break;
				default:
					itemStr = "";
					break;
			}
			alert("抽中獎項：" + itemStr + " ; 機率為：" + $(idStr).val()) + " %";
		}
		
		function getRandom(){
			var result = "";
			var p = Math.random();
			var cumulativeProbability = 0.0;
			var box1 = parseFloat($.trim($('#box1').val()))/100;
			var box2 = parseFloat($.trim($('#box2').val()))/100;
			var box3 = parseFloat($.trim($('#box3').val()))/100;
			var box4 = parseFloat($.trim($('#box4').val()))/100;
			var box5 = parseFloat($.trim($('#box5').val()))/100;
			var boxs = {box1,box2,box3,box4,box5}
			$.each(boxs, function( index, value ) {
				cumulativeProbability += value;
				if (p <= cumulativeProbability){
// 	 				alert("抽中獎項：" + index + ". 機率為：" + value);
	 				result = index;
	 				return false;
	 			}
			});
			return result;
		}
		
		function checkPercent(){
			var result = false;
			var box1 = $.trim($('#box1').val());
			var box2 = $.trim($('#box2').val());
			var box3 = $.trim($('#box3').val());
			var box4 = $.trim($('#box4').val());
			var box5 = $.trim($('#box5').val());
			var sunBox = 0;
			if(box1 != ''){
				sunBox = sunBox + (parseFloat(box1));
			}
			if(box2 != ''){
				sunBox = sunBox + parseFloat(box2);
			}
			if(box3 != ''){
				sunBox = sunBox + parseFloat(box3);
			}
			if(box4 != ''){
				sunBox = sunBox + parseFloat(box4);
			}
			if(box5 != ''){
				sunBox = sunBox + parseFloat(box5);
			}
			
			if((sunBox) == 100){
				result = true;
			}
			return result;
		}
	</script>
</body>
</html>