function generageQuartzHtml(obj) {
    var quartzHtml="";
    for(var i=0;i<obj.length;i++){
        quartzHtml=quartzHtml+'<div class="nmAndMsg">'+
            '<div class="personalNm"><p>'+obj[i].userName+'：</p></div>'+
            '<div class="msg"><p>'+obj[i].message+'，胜利就在'+obj[i].quartzTime+'</p>'+
            '<div class="msgTime">'+obj[i].insertTime+'</div>'+
            '</div></div>';
    }
    return quartzHtml;
}

function showMessage(currPage){
    $.ajax({
        type: "post",
        url: "/kyweb/timeController/showTime",    //向springboot请求数据的url
        data: {
            currPage: currPage
        },
        success: function (data) {
            if (data.result) {
                $(".msgMain").html("");
                var html= generageQuartzHtml(data.msg);
                $(".currPage").val(data.page.currPage);
                $(".nowPage").text(data.page.currPage+1);
                $(".totalPage").text(data.page.totalPage);
                $(".last").val(data.page.currPage-1);
                $(".next").val(data.page.currPage+1);
                $(".last").attr("disabled",false);
                $(".next").attr("disabled",false);
                if(currPage==0){
                    $(".last").attr("disabled",true);
                }if(1+data.page.currPage==data.page.totalPage){
                    $(".next").attr("disabled",true);
                }
                $(".msgMain").html(html);
            } else {
                $("h1").html(data.msg);
            }
        }
    });
}

$(function(){
    showMessage($(".currPage").val());
});