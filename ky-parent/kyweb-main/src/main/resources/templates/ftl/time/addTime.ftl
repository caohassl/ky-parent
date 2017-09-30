<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加计时器</title>
</head>
<link href="/kyweb/templates/css/time/add-time.css" type="text/css" rel="stylesheet">
<link href="/kyweb/templates/css/common/common.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/kyweb/templates/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/kyweb/templates/js/time/add-time.js"></script>
<body>
<body>
<div class="mess-main">
    <p class="quarz-statement">请输入定时语录：</p><br/>
<input type="text" class="center time" name="quartzTime" placeholder="时间yyyy-MM-dd HH:mm:ss"/><input type="text"  class="time messageTarget" placeholder="请输入目标邮箱,非必填"/><br>
    <textarea  class="center message" nam="message" placeholder="请输入定时语录"></textarea><br>
        是否公开：<input type="radio" name="isShow" value="1" checked="checked">是 <input type="radio" name="isShow" value="0">否

<button type="button" class="center messcommit" onclick="submitQuarzMessage();">提交</button>
    <p class="err-msg" style="color: red;"></p>
</div>
<a href="/kyweb/templates/ftl/time/showTime.ftl"><button style="    position: absolute;top: 18px;left: 69%;">跳回首页</button></a>
</body>
</html>