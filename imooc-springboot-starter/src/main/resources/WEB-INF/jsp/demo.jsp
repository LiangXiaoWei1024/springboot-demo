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

 <style>
 
        canvas{
            background:#9AA4FF;
        }
 
    </style>

</head>



<body style="width: 100%;height: 100%" >
		

<canvas id="canvas" width="800" height="500"></canvas>

		<!-- <div style="width: 98%;height: 98%;border: 1px solid #000">
			<div style="width: 300px;height: 500px;border: 1px solid #000;left: 0%;top: 10%;position:relative ;margin: 0 auto;"></div>
		</div> -->
</body>

<script type="text/javascript">
 
var canvas = document.getElementById('canvas'); // 得到画布
var ctx = canvas.getContext('2d'); // 得到画布的上下文对象
var flag = false;
var x = 0; // 鼠标开始移动的位置X
var y = 0; // 鼠标开始移动的位置Y
var url = ''; // canvas图片的二进制格式转为dataURL格式

/* 为canvas绑定mouse事件 */
$('canvas').mousedown(function(e){
    flag = true;
    x = e.offsetX; // 鼠标落下时的X
    y = e.offsetY; // 鼠标落下时的Y
}).mouseup(function(e){
    flag = false;
    url = $('canvas')[0].toDataURL(); // 每次 mouseup 都保存一次画布状态
}).mousemove(function(e){
	drawCircle(e); // 绘制方法
	 url = $('canvas')[0].toDataURL();
});
//画笔工具	
function drawPencil(e){
    if(flag){
        ctx.lineTo(e.offsetX,e.offsetY);
        ctx.stroke(); // 调用绘制方法 
    }else{
        ctx.beginPath();
        ctx.moveTo(x,y);
    }
}
//画矩形
function drawRect(e){
    if(flag){
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.beginPath();
        ctx.strokeRect(x,y,e.offsetX-x,e.offsetY-y);
    }
}
//画直线
function drawLine(e){
    if(flag){
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.beginPath();
        ctx.moveTo(x,y);
        ctx.lineTo(e.offsetX,e.offsetY);
        ctx.stroke();   
    }
}
//画圆circle
function drawCircle(e){
    if(flag){
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.beginPath();
        var rx = (e.offsetX-x)/2;
        var ry = (e.offsetY-y)/2;
        var r = Math.sqrt(rx*rx+ry*ry);
        ctx.arc(rx+x,ry+y,r,0,Math.PI*2); // 第5个参数默认是false-顺时针
        loadImage();
        ctx.stroke();
    }
}
//将 loadImage() 在清空画布后调用
 function loadImage(){
    var img = new Image();
    img.src = url;
    ctx.drawImage(img,0,0,canvas.width,canvas.height);
} 
 
</script>



</html>