<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->

<html>
<head>
<meta http-equiv="Content-Type" content="multipart/form-data;charset=UTF-8" />
<title>测试</title>
<script type="text/javascript" src="../../static/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../../static/js/jquery-easyui-1.4.1/themes/icon.css" />


<script type="text/javascript" src="../../static/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../static/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<link rel="stylesheet" type="text/css" href="../../static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../../static/css/public.css">
<link rel="stylesheet" type="text/css" href="../../static/css/styleloginsign.css">
<body style="background-color: #f2f2f2;">
		<div class="logincontent">
			<div class="loginnav">
				<nav class="nav navbar-default">
					<div class="container">
						<div class="navbar-header">
							<a class="navbar-brand" href="#"><img src="/static/img/icon/logo.png"></a>
							<span class="logintitle">用户登录</span> 
						</div>
					</div>
				</nav>
			</div>

			<section class="mainlogin">
				<div class="container">
					<div class="col-md-4 col-md-offset-7 loginbox">
						<h4>用户登录   <samp id="tt" style="color: red"></samp></h4> 
						<form class="form-horizontal" id="loginForm" action="customerAction_login.action" method="post">
							<div id="format1">
								<div class="form-group">
									<label for="inputaccount" class="col-sm-3 control-label" >用户名</label>
									<div class="col-sm-8">
										<input type="text" name="name" class="form-control"  required placeholder="请输入用户名"  >
									</div>
								</div>
								<div class="form-group">
									<label for="inputpassword" class="col-sm-3 control-label">密码</label>
									<div class="col-sm-8">
										<input class="form-control" type="password" name="password" required placeholder="请输入密码">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-4">
										<div class="checkbox">
											<input type="checkbox"><span class="size12">　记住用户名</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-offset-3 col-md-8">
								<a class="btn btn-danger"  id="subLogin" >登录</a>
							</div>
						</form>
					</div>
				</div>
			</section>

			<footer>
				<section class="copyright size12">
					<div class="container">
						<p class="text-center">地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096 电话：400-618-4000 传真：010-82935100 </p>
						<p class="text-center">京ICP备08001421号京公网安备110108007702</p>
					</div>
				</section>
			</footer>
		</div>
	</body>
<script type="text/javascript">
$(function(){
	
	$("input[name=name]").val(getCookie("userName"))
	$("input[name=password]").val(getCookie("userPassword"))
	
	$("#subLogin").click(function(){
		var name = $("input[name=name]").val();
		var password = $("input[name=password]").val();
		if(name != "" && name != null && password != "" && password != null  ) {
			$("#tt")[0].innerText ="";
		}else{
			$("#tt")[0].innerText ="请输入用户名或密码！"
			return;
		}
		$.post("/user/login",{"name":name,"password":password},function(data){
			 
			if(data.code == 1000) {
				window.location.href = "/sys/index"
			}else{
				$("#tt")[0].innerText =data.message;
			}
		});
	})
	
});

function getCookie(name)
{    
    var offset,cookieValue;
    var search=name+"=";
    if(document.cookie.length>0)
    {
        offset=document.cookie.indexOf(search);
        if(offset!=-1)
        {
            offset += search.length;   
            end = document.cookie.indexOf(";", offset);   
            if (end == -1) 
                end = document.cookie.length;   
            cookieValue=unescape(document.cookie.substring(offset, end));
        }
    }
    return cookieValue;
} 

</script>
</html>