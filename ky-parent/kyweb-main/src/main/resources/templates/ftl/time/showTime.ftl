<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>展示计时器</title>
</head>
<link href="/kyweb/templates/css/time/add-time.css" type="text/css" rel="stylesheet">
<link href="/kyweb/templates/css/common/common.css" type="text/css" rel="stylesheet">
<link href="/kyweb/templates/css/time/show-time.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/kyweb/templates/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/kyweb/templates/js/time/show-time.js"></script>
<body>
<div class="msgMain">
    <h1>正在载入.......</h1>
</div>
<div class="ye"><input type="hidden" class="currPage" value=0 />
    <button class='last' value=0 onclick="showMessage(this.value)">上一页</button>

    <button value=1 class='next' onclick="showMessage(this.value)">下一页</button>
    <p style="float: right;margin-right: 43%;">当前<em class="nowPage"></em>页,共<em class="totalPage"></em>页</p>
</div>
<a href="/kyweb/templates/ftl/time/addTime.ftl"><button style="top: 5%;left: 89%;position: absolute;">添加</button></a>
</body>
</html>