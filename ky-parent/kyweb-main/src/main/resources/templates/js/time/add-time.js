/**
 * 增加时间
 */
function submitQuarzMessage() {
        if($(".time").val()==""){
            $(".err-msg").text("输入时间不能为空");
            return;
        }
        if($(".message").val()==""){
            $(".err-msg").text("定时语录不能为空");
            return;
        }
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if($(".messageTarget").val()!=""){
        if(!myreg.test($(".messageTarget").val())){
            $(".err-msg").text("发送邮件格式错误，提示邮箱可为空");
            return;
        }
    }
        $.ajax({
            type: "post",
            url: "/kyweb/timeController/addTime",    //向springboot请求数据的url
            data: {
                quartzTime: $(".time").val(),
                message: $(".message").val(),
                isShow:$("input[type='radio']:checked").val(),
                messageTarget:$(".messageTarget").val()
    },
        success: function (data) {
            if (data.result) {
                alert("添加信息成功");
                window.location.href="/kyweb/templates/ftl/time/showTime.ftl";
            } else {
                $(".err-msg").html("error:"+data.message);
            }
        }
    });
}

