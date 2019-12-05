$(function(){

	/*用户管理*/
	// 寻找用户账户
	$("#page0").find("input[name='deleuserdate']").next().click(function(){
		var seltype = $("input[name='deleuser']:checked").val();
		var val = $("#page0").find("input[name='deleuserdate']").val();
		// var arr = new Array();
		// arr = val.split("");
		// val = "";
		// for(var i = 0; i < arr.length; i ++){
		// 	val = val + "u" + arr[i].charCodeAt(0).toString(16);
		// }
		$.ajax({
			url  : "searchUser",
			type : "post",
			data : "type=" + seltype
				   + "&val=" + val,
			success : function(t) {
				if (t.state == "true") {
					var $te = $("#deleUserDate").find("td");
					$(".deleuserdateDiv").show(500);
					$(".notFindUser").hide(100);
					$te.eq(1).text(t.id);
					$te.eq(3).text(t.username);
					$te.eq(5).text(t.name);
					$te.eq(7).text(t.gender == "m" ? "男" : "女");
					$te.eq(9).text(t.email);
					$te.eq(11).text(t.telephone);
					$te.eq(13).text(t.role == "admin" ? "管理员" : "vip");
					$te.eq(15).text(t.introduce);
					$te.eq(17).text(t.userstate == "0" ? "冻结" : "正常");
					$te.eq(19).text(t.regtime);
					$te.eq(21).text(t.address);
				} else {
					$(".deleuserdateDiv").hide(500);
					$(".notFindUser").show(100);
					$(".notFindUser").text("没有搜索到用户")
				}
			}
		});

	});

	// 删除用户按钮
	$(".deleuserdateDiv").find("button").click(function(){
		var username = $("#deleUserDate").find("td").eq(3).text();
		var id = $("#deleUserDate").find("td").eq(1).text();
		if(confirm("是否删除 \n用户名：" + username + "\nid：" + id)){
			$.ajax({
				url:"DeleteUser",
				type:"post",
				data:"type=mod&id=" + id,
				success:function(t) {
					if (t.state == "true") {
						alert("删除成功");
						$(".deleuserdateDiv").hide();
					} else {
						alert("删除失败,该用户可能还有未完成的订单！请订单完成后注销该用户！");
					}
				}
			})
		}
	});

	/*修改用户资料*/
	// 查询
	$("#moduser").find("input[name='modiuserdate']").blur(function(){
		var stype = $("#moduser").find("input[name='moduser']:checked").val();
		var val = $(this).val();
		// arr = val.split("");
		// val = "";
		// for(var i = 0; i < arr.length; i ++){
		// 	val = val + "u" + arr[i].charCodeAt(0).toString(16);
		// }
		$.ajax({
			url:"searchUser",
			type:"post",
			data:"type=" + stype + "&val=" + val,
			success:function(t) {
				if (t.state == "true") {
					var $te = $(".modiuserdataDiv").find("td").find("input");
					$(".modiuserdataDiv").show(500);
					$(".notFindUsertomod").hide(100);
					$te.eq(0).val(t.id);
					$te.eq(1).val(t.username);
					$te.eq(2).val(t.name);
					if (t.gender == "m") {
						$te.eq(3).attr("checked", "checked");
					} else {
						$te.eq(4).attr("checked", "checked");
					}
					$te.eq(5).val(t.email);
					$te.eq(6).val(t.telephone);
					$te.eq(7).val(t.introduce);
					if (t.userstate == "0") {
						$te.eq(9).attr("checked", "checked");
					} else {
						$te.eq(8).attr("checked", "checked");
					}
					if (t.role == "admin") {
						$te.eq(10).attr("checked", "checked");
					} else {
						$te.eq(11).attr("checked", "checked");
					}
					$te.eq(12).val(t.regtime);
				} else {
					$(".modiuserdataDiv").hide(500);
					$(".notFindUsertomod").show(100);
				}
			}
		});
	});

	// 修改按钮 
	$(".modiuserdataDiv").find("button").click(function(){
		if(confirm("确认修改")){
			var p = $(".modiuserdataDiv");
			var id = p.find("input[name='id']").val();
			var username = p.find("input[name='username']").val();
			var name = p.find("input[name='name']").val();
			var gender = p.find("input[name='gender']:checked").val();
			var email = p.find("input[name='email']").val();
			var telephone = p.find("input[name='telephone']").val();
			var introduce = p.find("input[name='introduce']").val();
			var userstate = p.find("input[name='state1']:checked").val();
			var role = p.find("input[name='role']").val();
			$.ajax({
				url:"ModifyUser",
				type:"post",
				data:"username="+ username
				+ "&id=" + id
				+ "&name=" + name
				+ "&gender=" + gender 
				+ "&email=" + email 
				+ "&telephone=" + telephone
				+ "&introduce=" + introduce
				+ "&userstate=" + userstate
				+ "&role=" + role,
				success:function(t){
					if(t.state == "true"){
						alert("用户修改成功");
						$(".modiuserdataDiv").hide();
					}else{
						alert("修改失败");
					}
				}
			});
		}
	});

	/*订单管理*/

	// 删除
	$(".ordersAdmin").on("click", ".delete",function(){
		var oid = $(this).parents(".order").find(".oid").text();
		var $parent = $(this).parents(".order");
		if(confirm("是否删除订单?\n订单id:" + oid)){
			$.post("ChangeOrderPaystate", "type=delete&id=" + oid, function(t){
				if(t == "true"){
					alert("已删除");
					$parent.remove();
				}
			});
		}
	});

	// 同意退款
	$(".ordersAdmin").on("click", ".tytk",function(){
		var oid = $(this).parents(".order").find(".oid").text();
		var $parent = $(this).parents(".order");
		if(confirm("是否同意退款?\n订单id:" + oid)){
			$.post("ChangeOrderPaystate", "type=tytk&id=" + oid, function(t){
				if(t == "true"){
					alert("已完成");
					$parent.remove();
				}
			});
		}
	});

	// 取消订单
	$(".ordersAdmin").on("click", ".cancel",function(){
		var oid = $(this).parents(".order").find(".oid").text();
		var $parent = $(this).parents(".order");
		if(confirm("是否取消订单?\n订单id:" + oid)){
			$.post("ChangeOrderPaystate", "type=cancel&id=" + oid, function(t){
				if(t == "true"){
					alert("已完成");
					$parent.remove();
				}
			});
		}
	});

	// 上一页按钮
	$(".ordersAdmin").find(".prve").click(function(){
		var type = $(".ordersAdmin").find("input[name='state']:checked").val();
		var t = $(this).next().text();
		var pagenum = parseInt(t.substring(1,6));
		if(pagenum == 1){
			alert("前面没有了");
		}else{
			orderShow(type,pagenum - 1);
		}
	});

	// 下一页按钮
	$(".ordersAdmin").find(".next").click(function(){
		var type = $(".ordersAdmin").find("input[name='state']:checked").val();
		var t = $(this).prev().text();
		var t2 = $(this).prev().text().split("/")[1];
		var pagesum = parseInt(t2.substring(1,4));
		var pagenum = parseInt(t.substring(1,6));
		if(pagenum == pagesum){
			alert("后面没有了");
		}else{
			orderShow(type,pagenum + 1);
		}
	});

	// 显示订单状态单选按钮
	$(".ordersAdmin").find("input[name='state']").click(function(){
		orderShow($(this).val() + "",1);
	});

	// 显示订单函数
	orderShow("all",1);
	function orderShow(type, pagenum){
		$.ajax({
			url: "OrderShow",
			type: "post",
			data: {
				type: type,
				pagenum: pagenum
			},
			dataType: "json",
			// "type=" + type + "&pagenum=" + pagenum,
			success: function (t) {
				if (t.state == "true") {
					$(".ordersAdmin").find("span").text("第" + pagenum + "页/共" + t.pagesum + "页");
					$(".order").remove();
                    var arr=t.orders;
					for (var i = 0; i < t.orders.length; i++) {
					    //alert(t.orders[i].userid);
					    // alert(t.orders[i]);
                        // alert(t.orders[i].getUser().getId());
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
							+ "<div class='col-md-2 oid'>" + t.orders[i].id + "</div>"
							+ "<div class='col-md-2 pro_id'>" + t.dressid[i] + "</div>"
							+ "<div class='col-md-2 uid'>" + t.userid[i] + "</div>"
							+ "<div class='col-md-2 pnum'>x " + t.pnum[i] + "</div>"
							+ "<div class='col-md-2 logistics'>"
							+ "<a href='javascript:void(0)'>" + orderstate + "</a>"
							+ "</div>"
							+ "<div class='col-md-2 operation'>"
							+ btntype
							+ "</div>"
							+ "</div>";
						$(".ordersAdmin").find(".ordertop").after(h);
					}
					if (t.orders.length == 0) {
						var h = "<div class='text-center'>还没有订单哦</div>"
						$(".ordersAdmin").find(".ordertop").after(h);
					}
				}
			}
		});
	}


	// 商品管理
	// 删除查询
	$("#delepro").find(".soso").click(function(){
		var id = $(this).prev().val();
		var $btn = $(this);
		$.ajax({
			url: "DelePro",
			type: "post",
			data: "type=so&id=" + id,
			datatype :"text",
			success: function (t) {
				if (t.state == "true") {
					$btn.next().html("<a style='color:blue;' href='showDress?id=" + t.id + "'>已搜到信息,点击查看>></a>");
				} else
					$btn.next().html("<a style='color:red;' href='javascript:void(0)'>未搜索到任何信息</a>");
			}
		});
	});

	// 删除
	$("#delepro").find(".dele").click(function(){
		var id = $(this).prevAll("input[name='proID']").val();
		alert(id);
		if(confirm("是否删除？\nid=" + id)){
			$.ajax({
				url:"DelePro",
				type:"post",
				data:"type=dele&id=" + id,
				datatype:"text",
				success:function(t) {
					if (t.state == "true") {
						alert("删除成功")
						$btn.prev().html("");
					} else {
						alert("删除失败")
					}
				}
			});
		}
	});

	//修改查询赋值
	$("#modipro").find(".soso").click(function(){
		var p=$(".modipro");
		var id = $(this).prevAll("input[name='modiIdentify']").val();
		var dressname=p.find("input[name='']").val();
		var category=p.find("input[name='']").val();
		var price=p.find("input[name='']").val();
		// var sales=p.find("input[name='']").val();
		var description=p.find("input[name='']").val();

		var $btn = $(this);
		if(confirm("确认查询")) {
			$.ajax({
				url: "DelePro",
				type: "post",
				data: "type=so&id=" + id,
				datatype: "text",
				success: function (t) {
					if (t.state == "true") {
						// alert(t.category+t.sales+t.price+t.description);
						$btn.next().html("<a style='color:blue;' href='showDress?id=" + t.id + "'>已搜到信息,点击查看>></a>");
						var $te = $(".productsDiv").find("td").find("input");
						$(".productsDiv").show(500);
						$te.eq(0).val(t.id);
						$te.eq(1).val(t.dressname);
						if(t.category=="男装")
							$te.eq(2).attr("checked","checked");
						else {
							$te.eq(3).attr("checked","checked");
						}
						$te.eq(4).val(t.price);
						$te.eq(5).val(t.sales);
						$te.eq(6).val(t.description);
					} else {
						$btn.next().html("<a style='color:red;' href='javascript:void(0)'>未搜索到任何信息</a>");
						$(".productsDiv").hide(500);
					}
				}
			});
		}
	});

	//修改
	$("#modipro").find(".revise").click(function() {
		if(confirm("确认修改")) {
			var p = $(".productsDiv");
			var $te = $(".productsDiv").find("td").find("input");
			var dressid = $te.eq(0).val();
			var dressname = $te.eq(1).val();
			var category = p.find("input[name='dressclass']:checked").val();
			var price = $te.eq(4).val();
			var description = $te.eq(6).val();
			// alert(id);
			// alert(category);
			// alert(price);
			// alert(description);
			// alert(dressname);
			$.ajax({
				url:"modifyDress",
				type:"post",
				data:{
					dressid:dressid,
					dressname:dressname,
					category:category,
					price : price,
					description : description
				},
				dataType :"json",
				// date:"dressid="+id
				// 	+"&dressname="+dressname
				// 	+"&category="+ category
				// 	+"&price="+ price
				// 	+"&description="+description,
				success : function(t) {
					alert(t.state);
				    if(t.state=="true"){
				    	alert("商品修改成功");
					}else {
				    	alert("修改失败");
					}
				}
			})
		}
	});
});