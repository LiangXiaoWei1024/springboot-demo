<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
<title>Aliplayer在线配置</title>
<link rel="stylesheet" href="//g.alicdn.com/de/prismplayer/2.7.2/skins/default/aliplayer-min.css" />
<script type="text/javascript" charset="utf-8" src="//g.alicdn.com/de/prismplayer/2.7.2/aliplayer-min.js"></script>
 <style type="text/css">
    	 html,
        body,
        #container {
          width: 100%;
          height: 100%;
        }
    </style>
</head>
<body>
<div class="c" id="a" style="width: 1000px;height: 600px;position: absolute;left: 20%;top: 10%">
<div class="prism-player" id="player-con"></div>
</div>
<script>
var player = new Aliplayer({
  "id": "player-con",
  "source": "http://localhost:8080/static/img/210fdfcf8c60a11e13067b930cdba4ca.mp4",
  "width": "100%",
  "height": "100%",
  "autoplay": true,
  "isLive": false,
  "rePlay": false,
  "playsinline": true,
  "preload": true,
  "controlBarVisibility": "hover",
  "useH5Prism": true
}, function (player) {
    console.log("播放器创建了。");
  }
);
</script>
</body>
</html>