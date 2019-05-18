<@zzxy/>
<!doctype html>
<html lang="en">
<head>
	<title>网上助教系统</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<@link href="assets/vendor/bootstrap/css/bootstrap.min.css"/>
	<@link href="assets/vendor/font-awesome/css/font-awesome.min.css"/>
	<@link href="assets/vendor/linearicons/style.css"/>
	<@link href="assets/vendor/chartist/css/chartist-custom.css"/>
	<!-- MAIN CSS -->
	<@link href="assets/css/main.css"/>
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<@link href="assets/css/demo.css"/>
	<!-- GOOGLE FONTS -->
	<@link  href="assets/css/googleapis.css"/>
	<@script src="publicJs/jquery-3.3.1.js"/>
	<script type="text/javascript">
		$(function(){
			if($("#sybz").val()==0){
				alert("请到个人中心完善信息并提交通过后才能进行后续的一系列操作！");
			}
		})
	</script>
</head>

<body>
	<input type="hidden" id="sybz" value="${czySybz }">
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top" style="height:75px;">
			<div class="brand" style="padding-bottom: 24px;">
				<a href="index.html"><img src="/UIEditor/static/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
				</div>
				<div class="navbar-btn navbar-btn-right">
					<a class="btn btn-info btn-sm" href="${ctx }/login/exitLogin"><i class="fa fa-rocket"></i> <span>退出</span></a>
				</div>
				<div id="navbar-menu" class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user-circle-o"></i><span>${czyDlh }</span></a>
					</li>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="${ctx}/jsxxb/messgePage" target="teacherMainWorkArea" class="active"><i class="lnr lnr-home"></i> <span>首页</span></a></li>
						<li><a href="NewFile.html" class=""><i class="lnr lnr-code"></i> <span>课程管理</span></a></li>
						<li><a href="charts.html" class=""><i class="lnr fa-rotate-left"></i> <span>学生管理</span></a></li>
						<li><a href="panels.html" class=""><i class="lnr lnr-chart-bars"></i> <span>教学</span></a></li>
						<li><a href="notifications.html" class=""><i class="lnr lnr-file-empty"></i> <span>作业收集</span></a></li>
						<li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-dice"></i> <span>互动学习</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="${ctx }/login/registerPage" class="">讨论</a></li>
									<li><a href="page-login.html" class="">答疑</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#subPages1" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>个人中心</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages1" class="collapse ">
								<ul class="nav">
									<li><a href="${ctx }/jsxxb/showjszl?dlh=${czyDlh}" class="">个人资料</a></li>
									<li><a href="${ctx }/jsxxb/modifierJsxx" class="">资料完善与修改</a></li>
									<li><a href="${ctx }/jxzgrz/modifierJxzgrz" class="">资格认证</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content" style="padding-top: 10px;padding-left: 4px;padding-right: 4px;padding-bottom: 10px">
				<iframe id="teacherMainWorkArea" name="teacherMainWorkArea" frameborder=0 scrolling="auto" src="${ctx}/jsxxb/messgePage"></iframe>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<@script src="assets/vendor/bootstrap/js/bootstrap.min.js"/>
	<@script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"/>
	<@script src="assets/scripts/klorofil-common.js"/>
</body>
<script>
$(function(){
	var $WorkAreaIframe = $("#teacherMainWorkArea");
	var height=document.documentElement.clientHeight;
	var width=document.documentElement.clientWidth;
	$WorkAreaIframe.attr("height",height-4);
	$WorkAreaIframe.attr("width",width-4);
})
</script>
</html>
