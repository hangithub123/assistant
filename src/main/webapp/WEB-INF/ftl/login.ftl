<@zzxy/>
<!doctype html>
<html lang="en" class="fullscreen-bg">
<head>
	<title>登录页面</title>
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
	<@script src="publicJs/jquery-3.3.1.js"/>
	<@script src="publicJs/CheckText.js"/>
	<@script src="publicJs/PublicScript.js"/>
	<script type="text/javascript">
		function checkForm(){
			if(!checkFormItem(form.dlh,"C",50,0,5,"账号")){
		        return false;
		      }
		      if(!checkFormItem(form.mm,"C",100,0,5,"密码")){
		        return false;
		      }
		    return true;
		}
	</script>
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><@img src="logo-dark.png"/></div>
								<p class="lead">Login to your account</p>
							</div>
							<form id="form" class="form-auth-small" action="${ctx}/login/userLogin" method="post">
								<div class="form-group">
									<label for="dlh" class="control-label sr-only">账号：</label>
									<input type="text" class="form-control" id="dlh" name="dlh" placeholder="账号"/>
								</div>
								<div class="form-group">
									<label for="mm" class="control-label sr-only">密码：</label>
									<input type="password" class="form-control" id="mm" name="mm" placeholder="密码">
								</div>
								<button type="submit" class="btn btn-primary btn-lg btn-block" onclick="return checkForm()">登录</button>
								<div class="bottom" style="margin-left: 300px">
									<span class="helper-text"><a href="${ctx}/login/registerPage">用户注册</a></span>
								</div>
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">网上助教系统</h1>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
</body>

</html>
