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
</head>
<body >
   <form  id="studentAddForm"  > <!-- action="http://localhost:8080/sys/scFile" method="POST" enctype="multipart/form-data"-->
	名字：
	<input id="name" name="name" type="text"><br></br>
	头像：
	<input id="file" name="file" type="file">
	</br>
	<input id="but" type="button" value="添加" >
  </form>
        <div style="width: 200px;height: 200px;position: absolute;top: 200px;">
        	<a href="http://localhost:8080/aaa/9b5ac22eb0b44f2a891614cbec78d616.jpg" download="美女">下载</a><br></br>
        	<img id="aaa"   >
        	
        </div>
</body>
<script type="text/javascript">
$(function(){
	$("#but").click(function(){
		var formData = new FormData();
		
		if($("#studentAddForm")[0][1].files[0] != null && $("#studentAddForm")[0][0].value != null && $("#studentAddForm")[0][0].value != "") {
		    formData.append("file",$("#studentAddForm")[0][1].files[0]);
		    formData.append("name",$("#studentAddForm")[0][0].value);
		}else{
			alert("请传入文件和名字")
			return;
		}
		 $.ajax({
             url: 'http://localhost:8080/sys/scFile',
             type: 'POST',
             cache: false,
             data: formData,
             processData: false,
             contentType: false,
             success: function (data) {
            	 if(data.err != null && data.err != "" && data.err != undefined) {
            		 alert(data.err);
            		 return;
            	 }else{
            		 $("#aaa")[0].height="200";
            		 $("#aaa")[0].width="200";
	                 $("#aaa")[0].src="http://localhost:8080/aaa/"+data.data+""
            	 }
             },
             error: function (data) {
                 console.log("失败");
             }

         });
	});
		
});
	/* function checkForm(){
		var formData = $("#studentAddForm").serialize();
		  $.ajax({
              url: 'http://localhost:8080/sys/scFile',
              type: 'POST',
              cache: false,
              contentType : "multipart/form-data;charset=utf-8",
              data: {"file":$("#studentAddForm")[0][1].files[0],"name":$("#studentAddForm")[0][0].value},
              processData: false,
              contentType: false,
              success: function (data) {
                  $("#aaa")[0].src="http://localhost:8080/aaa/"+data.data+""
              },
              error: function (data) {
                  console.log("失败");
              }

          });
	} */
</script>
</html>