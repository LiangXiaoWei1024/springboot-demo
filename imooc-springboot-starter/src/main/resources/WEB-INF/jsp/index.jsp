<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<html>
<head>
<meta http-equiv="Content-Type" content="multipart/form-data;charset=UTF-8" />
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>测试</title>
<script type="text/javascript" src="../../static/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../../static/js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" src="../../static/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>

<script type="text/javascript" src="../../static/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/css/style.css">
<!-- 		<link rel="stylesheet" type="text/css" href="../../static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../../static/css/city-picker.css">
		<link rel="stylesheet" type="text/css" href="../../static/css/public.css">
		<link rel="stylesheet" type="text/css" href="../../static/css/nav.css">
		<link rel="stylesheet" type="text/css" href="../../static/css/styleorder.css"> -->
		

<style type="text/css">
	.show{
	    padding: .75rem 1.25rem;
	    margin-bottom: 1rem;
	    border-radius: .25rem;
	    position: fixed;
	    top: 1rem;
	    background-color: white;
	    width: auto;
	    min-width: 93%;
	    border-width: 0;
	    /* right: 2.7rem; */
	    left: 2.7rem;
	    box-shadow: 0 2px 6px 0 rgba(114, 124, 245, .5);
	}
	
		.show1{
	    padding: 25rem 1.25rem;
	    margin-bottom: 1rem;
	    border-radius: .25rem;
	    position: fixed;
	    top: 4rem;
	    background-color: white;
	    width: auto;
	    min-width: 93%;
	    border-width: 0;
	    /* right: 2.7rem; */
	    left: 2.7rem;
	    box-shadow: 0 2px 6px 0 rgba(114, 124, 245, .5);
	}
	
	.msg{
       width: 43%;
	    height: 46%;
	    position: fixed;
	    top: 21%;
	    left: 22%;
	    overflow-y: scroll;
	    border: 1px solid #0e0110;
	}
	
	.send{
	    padding: 4rem 1.25rem;
	    margin-bottom: 1rem;
	    border-radius: .25rem;
	    position: fixed;
	    top: 38rem;
	    background-color: white;
	    width: auto;
	    min-width: 40%;
	    border-width: 0;
	    /* right: 2.7rem; */
	    left: 25.7rem;
	    box-shadow: 0 2px 6px 0 rgba(114, 124, 245, .5);
	}
</style>
</head>



<body >
	<div>
		<div class="show">
			<div style="position: fixed;left: 50.7rem;,top: 1rem"><span style="font-size: x-large;">xxx平台聊天系统</span></div>
			
			<div style="position: fixed;right: 15.7rem;,top: 1rem"><a href="/user/loginOut">退出登录</a></div>
			欢迎尊敬的<samp id="userName" style="color: red" th:text="${session.user.name}"></samp>会员登录
		</div>
		
		<div class="show1">
				<div style="position: fixed;width:97%;height:5%;top: 15%;">
					用户列表：
						<select id="selectId" name="goodsType" style="border-color: blue;width: 200px;height: 35px; border-radius: 5px;">
						</select>
				</div>
				
				<div class="msg" id="msgaa">
						
						
				</div>
				
				<div class="send">
					<div style="position: fixed;top:69%; left: 25%">
						<form action="">
							<input type="hidden" id="userId" th:value="${session.user.id}">
							<textarea id="text" rows="6" cols="70"></textarea>　
							<input id="sendBut" type="button" value="发送消息"></input>
						</form>
					</div>
				</div>
				
				<div style="position: fixed;width:97%;height:5%;top: 94%;text-align:center">地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096 电话：400-618-4000 传真：010-82935100

				京ICP备08001421号京公网安备110108007702</div>
		</div>
	</div>
</body>
<script type="text/javascript">

var websocket = null;
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
	$.ajax({ 

        type: "post", 

       url: "/user/aaa", 

       cache:false, 

       async:false, 
         success: function(data){ 
        	 var curWwwPath = window.document.location.href;
        	 var pathname= window.document.location.pathname;
        	 var pos = curWwwPath.indexOf(pathname);
        	 var localhostPath = curWwwPath .substring(0,pos).substring(7);
        	// alert(localhostPath);
        	 websocket = new WebSocket("ws://"+localhostPath+"/websocket/"+data.result.id+"");
        }  
	
	});
	
	/* $.post("/user/aaa",null,function(data){
		if(data.code == 1000) {
			alert(data.code);
		    
		}else{
			alert(data.message);
		}
	}); */
}
else{
    alert('Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function(){
    setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function(event){
    setMessageInnerHTML("open");
}

//接收到消息的回调方法
websocket.onmessage = function(event){
    setMessageInnerHTML(event.data);
}

//连接关闭的回调方法
websocket.onclose = function(){
    setMessageInnerHTML("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    document.getElementById('msgaa').innerHTML += innerHTML + '<br/>';

	//$(".msg")[0].innerHTML=$(".msg")[0].innerHTML+"<p>"+$("#userName")[0].innerHTML+":　　　　"+$("#text").val()+"　　　　　　"+now+"</p>";
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//发送消息
function send(){
    var message = document.getElementById('text').value;
    websocket.send(message);
}

$(function(){
	$.post("/user/selectUserList",null,function(data){
		if(data.code == 1000) {
			for (var i = 0; i < data.result.length; i++) {
				$("#selectId").append("<option value='"+data.result[i].id+"'>"+data.result[i].name+"</option>");
			}
		}else{
			alert(data.message);
		}
	});
	
	
	$("#sendBut").click(function(){
		/**
		 * 
		 * 获取当前时间
		 */
		function getNow(s) {
		    return s < 10 ? '0' + s: s;
		}

		var myDate = new Date();
		//获取当前年
		var year=myDate.getFullYear();
		//获取当前月
		var month=myDate.getMonth()+1;
		//获取当前日
		var date=myDate.getDate(); 
		var h=myDate.getHours();       //获取当前小时数(0-23)
		var m=myDate.getMinutes();     //获取当前分钟数(0-59)
		var s=myDate.getSeconds();  

		var now=year+'-'+getNow(month)+"-"+getNow(date)+" "+getNow(h)+':'+getNow(m)+":"+getNow(s);
		
		var msg = $("#text").val();
		if(msg == null || msg == "") {
			alert("不能发送空信息！")
			return;
		}
		
		
		$("#msgaa")[0].innerHTML=$("#msgaa")[0].innerHTML+"<p>我:　　　　"+$("#text").val()+"　　　　　　"+now+"</p>";
		$("#text").val("");
		var sendUserId = $("#userId").val();
		var sendUserName = $("#userName")[0].innerHTML;
		var sendeeUserId = $("#selectId option:selected").val();
		$.post("/user/sendMsg",{"time":now,"sendUserName":sendUserName,"sendeeUserId":sendeeUserId,"message":msg},function(data){
			
			
		});
		
	});
});
</script>
</html>