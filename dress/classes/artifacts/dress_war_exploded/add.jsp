<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
		<script src="bootstrap/js/jquery-3.1.0.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/navbar.css" />
		<script src="js/navbar.js"></script>
		<link rel="stylesheet" href="css/add.css" />
		<script type="text/javascript" src="js/add.js"></script>
	</head>

	<body>
		<% 
			String remoteIp=request.getRemoteAddr();
			System.out.print("IP:" + remoteIp + " 访问了");
		%>
		<c:if test="${sessionScope.user.role != 'admin' }">
			<jsp:forward page="index.jsp"></jsp:forward>
		</c:if>
		<%@include file="jspt/navbar.jsp" %>
		<!--网站导航-->
		<div class="container">
			<div class="row text-right">
				<a href="index.jsp">书城首页</a>>
				<a href="user.jsp">管理员</a>><span>书本添加</span>
			</div>
		</div>
		<!--end网站导航-->

		<div class="container bookMsg" hidden="hidden">
			<a href="javascript:void(0)" id="closeBookMsg" class="glyphicon glyphicon-remove"></a>
			<div class="row">
				<div class="col-md-6 col-xs-12">
					<!--图片放大-->
					<div class="con-FangDa" id="fangdajing">
						<div class="con-fangDaIMg">
							<img src="img/books/1/1.jpg">
							<div class="magnifyingBegin"></div>
							<div class="magnifyingShow"><img src="img/books/1/1.jpg"></div>
						</div>
						<ul class="con-FangDa-ImgList">
							<!-- 图片显示列表 -->
							<li class="active"><img src="img/books/1/1.jpg" data-bigimg="img/books/1/1.jpg"></li>
							<li><img src="img/books/1/2.jpg" data-bigimg="img/books/1/2.jpg"></li>
							<li><img src="img/books/1/3.jpg" data-bigimg="img/books/1/3.jpg"></li>
							<li><img src="img/books/1/4.jpg" data-bigimg="img/books/1/4.jpg"></li>
						</ul>
					</div>
				</div>
				<div class="col-md-6 col-xs-12" class="details">
					<div class="bookname">时空穿越
						<span id="ckepop">
											<span class="jiathis_txt">分享到</span>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
						<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1" charset="utf-8"></script>
						</span>
						<script>
							$("#ckepop").css("position", "absolute");
							$("#ckepop").css("margin-left", "120px");
							$("#ckepop").css("margin-top", "10px");
						</script>
						<p>&nbsp;</p>
					</div>
					<span id="share">
										
									<div class="priceStr">
										<span class="str">单价</span>
					<span class="price">￥<span>9999</span></span>
				</div>
				<div class="sales">
					<span class="glyphicon glyphicon-map-marker"></span> 广州
					<br /> 月销量
					<span>999</span> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp; 累计评论
					<span>8888</span>
					<div>
						<hr />
					</div>
				</div>
				<div class="buy">
					<span class="input-group">
											<span class="input-group-btn" id="basic-addon1">
								  				<button class="btn btn-default" disabled="disabled">
								  					<span class="glyphicon glyphicon-minus"></span>
					</button>
					</span>
					<input type="text" class="form-control" placeholder="数量" aria-describedby="basic-addon1">
					<span class="input-group-btn" id="basic-addon3">
								  				<button class="btn btn-default"disabled="disabled">
								  					<span class="glyphicon glyphicon-plus"></span>
					</button>
					</span>
					</span>
					<span class="buyMax">最多可购买<a>5</a>件</span>
					<div class="priceSum">
						总价
						<span>￥0</span>
					</div>
					<div class="button">
						<button type="button" id="isadd"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button>
						<button type="button" id="isbuy">立即购买</button>
					</div>
				</div>
			</div>
		</div>
		</div>

		<div class="container max">
			<div class="row text-center">
				<h1>商品添加</h1>
				<br />
			</div>
			<div class="row">
<%--				<form action="AddBook" method="post" enctype="multipart/form-data" class="formmax">--%>
					<div class="col-md-3 col-xs-6 text-center input">
						<b>*</b> 商品名称
					</div>
					<div class="col-md-3 col-xs-6 input">
						<input type="text" name="name" />
						<span></span>
					</div>
					<div class="col-md-3 col-xs-6 text-center input">
						<b>*</b> 商品价格
					</div>
					<div class="col-md-3 col-xs-6 input">
						<input type="text" name="price" placeholder="￥" />
						<span></span>
					</div>

					<div class="col-md-3 col-xs-6 text-center input">
						<b>*</b> 商品数量
					</div>
					<div class="col-md-3 col-xs-6 input">
						<input type="text" name="pnum" placeholder="" />
						<span></span>
					</div>
					<div class="col-md-3 col-xs-6 text-center input">
						<b> </b> &nbsp;商品类型
					</div>
					<div class="col-md-3 col-xs-6 input">
						<input type="radio" name="dressclass" value="男装" />男装&nbsp;&nbsp;
						<input type="radio" name="dressclass" value="女装" />女装&nbsp;&nbsp;
					</div>
					<div class="col-md-3 col-xs-6 text-center input">
						<b> </b> &nbsp;描述
					</div>
					<div class="col-md-3 col-xs-6 input">
						<input type="text" name="description" placeholder="" />
						<span></span>
					</div>
					<div class="col-md-12 col-xs-12 text-center input" style="color:red;">
						注:"*"为必填
					</div>
					<div class="col-md-12 col-xs-12 text-center">
						<a id="yulan" href="javascript:void(0)" class="btn btn-default btn-lg" style="width: 105px;height: 48px;">预览</a>
						<input type="submit" class="btn btn-info btn-lg addBtn" value="添加商品"/>
					</div>
<%--				</form>--%>
			</div>
		</div>
		
		<%@include file="jspt/bottombar.jsp" %>
	</body>
</html>