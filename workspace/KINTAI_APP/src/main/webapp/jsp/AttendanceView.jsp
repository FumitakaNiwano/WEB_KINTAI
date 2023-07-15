<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // Servletのデータ受け取り
  request.setCharacterEncoding("UTF8");

String strAttendanceTime = (String)request.getAttribute("attendanceTime");
if(strAttendanceTime == null){
	strAttendanceTime = "";
}
  
  if(request.getAttribute("message")!=null){
	  String strMessage = (String)request.getAttribute("message");
  }
  
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  
<script type="text/javascript">

<!-- 現在時刻の表示処理 -->
function disp(){

	var WeekChars = [ "日", "月", "火", "水", "木", "金", "土" ];
	
	var now = new Date();

	var year  = now.getFullYear();                    // 年
	var month = ("0"+(now.getMonth() + 1)).slice(-2); // 月
	var date  = ("0"+(now.getDate())).slice(-2);  // 日
	var day   = now.getDay();                         // 曜日
	var hour  = now.getHours();                       // 時
	var min   = now.getMinutes();                     // 分
	var sec   = now.getSeconds();                     // 秒

	// 数値が1桁の場合、頭に0を付けて2桁で表示する指定
	if(hour < 10)  { hour = "0" + hour; }
	if(min < 10)   { min  = "0" + min; }
	if(sec < 10)   { sec  = "0" + sec; }

	var date   = year + "/" + month + "/" + date + " " + "(" + WeekChars[day] + ")";
	var watch1 = hour + ':' + min + ':' + sec;

	var id1 = document.getElementById('nowDate');
	var id2 = document.getElementById('nowTime');

	id1.innerHTML = date;
	id2.innerHTML = watch1;

	setTimeout("disp()", 1000);
}

var msg = '<%=request.getAttribute("msg") %>';
if(msg != "null"){
	alert(msg);
}

</script>



<body onLoad="disp()" name="aa">
	<div id="nowDate"></div>
	<div id="nowTime"></div>
	
	<!-- servletクラスのdoGet()を呼び出す -->
	<!-- formで囲っているデータがservletに送られる -->
	<form action="AttendanceServlet" method="post">
		<button type="submit" name="attndance_submit">出勤</button>
		
	</form>
	
	<p><%=strAttendanceTime%></p>
	
	
	<!-- servletクラスのdoPost()を呼び出す -->
	<form action="AttendanceServlet" method="post">
		<button type="submit" name="leaving_work_submit">退勤</button>
	</form>
	
	<form action="AttendanceServlet" method="post">
		<button type="submit" name="no">無</button>
	</form>
    
</body>


</html>