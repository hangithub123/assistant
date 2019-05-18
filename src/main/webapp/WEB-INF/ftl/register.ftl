<@zzxy/>
<!doctype html>
<html lang="en" class="fullscreen-bg">
<head>
	<title>注册页面</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<@link  href="assets/css/bootstrap.min.css"/>
	<@link  href="assets/vendor/font-awesome/css/font-awesome.min.css"/>
	<@link  href="assets/vendor/linearicons/style.css"/>
	<!-- MAIN CSS -->
	<@link  href="assets/css/main.css"/>
	<@link  href="assets/css/googleapis.css"/>
	<style type="text/css">
		.box {
		  -moz-box-shadow: 1px 2px 10px 0 rgba(0, 0, 0, 0.1);
		  -webkit-box-shadow: 1px 2px 10px 0 rgba(0, 0, 0, 0.1);
		  box-shadow: 1px 2px 10px 0 rgba(0, 0, 0, 0.1);
		  -webkit-border-radius: 5px;
		  -moz-border-radius: 5px;
		  border-radius: 5px;
		  width: 30%;
		  height: 600px;
		  margin: 0 auto;
		  background-color: #fff; }
	    .box .center {
	      width: 99%;
	      height: 100%;
	      padding: 30px 30px;
	      text-align: center; }
	</style>
	<@script src="publicJs/CheckText.js"/>
	<@script src="publicJs/ajaxUtils.js"/>
	<@script src="publicJs/jquery-3.3.1.js"/>
	<@script src="layer/layer.js"/>
	<script type="text/javascript">
		
		var isyzm;
		var iszh;
		
		$(function(){
			existZH();
		})
		
		function existZH(){
			$("#dlh").blur(function(){
				var msg = $(this).next(".msg");
				msg.html("");
				var data = new Object();
				data.dlh = $("#dlh").val();
				var url = "/UIEditor/login/existZH";
				var result = getDataByUrlParam(url,data);
				if(result.flag=='ok'){msg.html("该账户名已存在，请重新输写！");iszh = true;return true;}else{msg.html("");iszh = false;return false;}
			})
		}
	
		function hqYzm(){
			var data = new Object();
			data.email = $("#email").val();
			var url = "/UIEditor/login/hqYzm";
			var result = getDataByUrlParam(url,data);
			if(result.flag=='ok'){layer.alert("验证码发送成功！");isyzm = true;return true;}else{layer.alert("验证码发送失败！");isyzm = false;return false;}
		}
		
		function checkForm(){
			if(!CheckEamil(form.email,1,30,"电子邮箱")) return false;
			if(!checkFormItem(form.yzm,"C",50,0,5,"验证码")) return false;
			if(!checkFormItem(form.dlh,"C",50,0,5,"账号")) return false;
		    if(!checkFormItem(form.mm,"C",100,0,5,"密码")) return false;
		    if(!checkFormItem(form.qrmm,"C",100,0,5,"确认密码")) return false;
		    var mm = $("#mm").val();
		    var qrmm = $("#qrmm").val();
		    if(mm != qrmm) { layer.alert("确认密码与密码不相等，请重新输入！"); return false;}
		    if(!isyzm) { layer.alert("验证码发送失败！"); return false;}
		    if(iszh) { layer.alert("该账户名已存在，请重新输写！"); return false;}
		    if(!registerFun()) return false;
		    return true;
		}
		
		function registerFun(){
			var data = new Object();
			data.email = $("#email").val();
			data.yzm = $("#yzm").val();
			data.dlh = $("#dlh").val();
			data.mm = $("#mm").val();
			data.role = $("select").val();
			var url = "/UIEditor/login/register";
			var result = getDataByUrlParam(url,data);
			if(result.flag=='ok'){alert("注册成功，请登录！");return true;}else{layer.alert("验证码错误，注册失败！");return false;}
		}
	</script>
</head>

<body>
	<div id="wrapper">
	<div class="vertical-align-wrap">
	<div class="vertical-align-middle">
	<div class="box">
	<div class="center">
		<div class="header">
			<div class="logo text-center"><@img src="logo-dark.png"/></div>
			<p class="lead">新用户注册</p>
		</div>
		<form id="form" class="form-auth-small" action="${ctx }/login/homePage" method="post">
			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
				<input class="form-control" id="email" name="email" placeholder="邮箱" type="email">
			</div>
			<br>
			<div class="input-group">
				<input class="form-control" type="text" id="yzm" name="yzm" placeholder="验证码" >
				<span class="input-group-btn"><button class="btn btn-sm" type="button" style="background-color:#EEEEEE;padding:7px 2px" onclick="return hqYzm()">获取验证码</button></span>
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-user"></i></span>
				<input class="form-control" id="dlh" name="dlh" placeholder="账号" type="text">
				<span class="msg" style="color: red;size: 2px"></span>
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-lock"></i></span>
				<input class="form-control" id="mm" name="mm" placeholder="密码" type="password">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-retweet"></i></span>
				<input class="form-control" id="qrmm" name="qrmm" placeholder="确认密码" type="password">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon" style="font-size:12px">角色</span>
				<select class="form-control input-sm" name="role" id="role">
					<option value="1">学生</option>
					<option value="2">教师</option>
				</select>
			</div>
			<br><br>
			<button type="submit" class="btn btn-primary btn-lg btn-block" onclick="return checkForm()" >注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</button>
		</form>
	</div>
	</div>
	</div>
	</div>
	</div>
	
						
</body>

</html>
