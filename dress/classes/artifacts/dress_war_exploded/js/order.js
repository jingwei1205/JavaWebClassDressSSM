myOrder("all",1);
function myOrder(type, pagenum){
    $.ajax({
        url: "myShow",
        type: "post",
        data: {
            type: type,
            pagenum: pagenum,
        },
        dataType: "json",
        success: function (t) {
            if (t.state == "true") {
                var arr=t.orders;
                for (var i = 0; i < t.orders.length; i++) {
                    var orderstate;
                    var btntype = "";
                    if (t.ostate[i] == "-1") {
                        btntype = "<button type='button' class='btn btn-danger btn-sm cancel'>取消</button>"
                        orderstate = "待付款";
                    } else if (t.ostate[i] == "-2") {
                        btntype = "<button type='button' class='btn btn-default btn-sm' disabled='disabled'>不可操作</button>";
                        orderstate = "待付款";
                    } else if (t.ostate[i] == "-3") {
                        btntype = "<button type='button' class='btn btn-default btn-sm' disabled='disabled'>不可操作</button>";
                        orderstate = "待评价";
                    } else if (t.ostate[i] == "-4") {
                        btntype = "<button type='button' class='btn btn-danger btn-sm tytk'>同意退款</button>";
                        orderstate = "退款中";
                    } else if (t.ostate[i] == "1") {
                        btntype = "<button type='button' class='btn btn-danger btn-sm delete'>删除</button>";
                        orderstate = "已完成";
                    } else if (t.ostate[i] == "2") {
                        btntype = "<button type='button' class='btn btn-danger btn-sm delete'>删除</button>";
                        orderstate = "已取消";
                    } else if (t.ostate[i] == "3") {
                        btntype = "<button type='button' class='btn btn-danger btn-sm delete'>删除</button>";
                        orderstate = "已退款";
                    } else {
                        btntype = "<button type='button' class='btn btn-danger btn-sm delete'>删除</button>";
                        orderstate = "有异常";
                    }
                    var h = "<div class='order row'>"
                        + "<div class='col-md-2 oid' align='center'><img src="+t.dressURL[i]+" width='50%'/><hr /></div>"
                        + "<div class='col-md-2 pro_id' align='center'>" + t.dressName[i] + "</div>"
                        + "<div class='col-md-2 uid' align='center'>" + t.dressPrice[i] + "</div>"
                        + "<div class='col-md-2 pnum' align='center'>x " + t.pnum[i] + "</div>"
                        + "<div class='col-md-2 logistics' align='center'>"
                        + "<a href='javascript:void(0)'>" +t.pnum[i]*t.dressPrice[i] + "</a>"
                        + "</div>"
                        + "<div class='col-md-2 operation' align='center'>"
                        + btntype
                        + "</div>"
                        + "</div>";
                    $(".myorder").find(".mytable").after(h);
                }
                if (t.orders.length == 0) {
                    var h = "<div class='text-center'>还没有订单哦</div>"
                    $(".myorder").find(".mytable").after(h);
                }
            }
        }
    });
}