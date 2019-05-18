<@zzxy/>
<@html>
<@head>
</@head>
<body>
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
						<li><a href="${ctx}/tsxxb/messgePage" target="teacherMainWorkArea" class="active"><i class="lnr lnr-home"></i> <span>首页</span></a></li>
						<li>
							<a href="#subPages1" data-toggle="collapse" class="collapsed"><i class="fa fa-user"></i> <span>用户管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages1" class="collapse ">
								<ul class="nav">
									<li><a href="${ctx }/jsxxb/jsList" target="teacherMainWorkArea" class="">教师管理</a></li>
									<li><a href="${ctx }/xsxxb/xsList" target="teacherMainWorkArea" class="">学生管理</a></li>
									<li><a href="${ctx }/login/czyList" target="teacherMainWorkArea" class="">操作员管理</a></li>
								</ul>
							</div>
						</li>
						<li><a href="charts.html" class=""><i class="fa fa-book"></i> <span>课程管理</span></a></li>
						<li><a href="panels.html" class=""><i class="lnr lnr-chart-bars"></i> <span>资源管理</span></a></li>
						<li>
							<a href="#subPages2" data-toggle="collapse" class="collapsed"><i class="lnr lnr-dice"></i> <span>系统维护</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages2" class="collapse ">
								<ul class="nav">
									<li><a href="page-profile.html" class="">讨论</a></li>
									<li><a href="page-login.html" class="">答疑</a></li>
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
				<div class="container-fluid">
					<iframe id="teacherMainWorkArea" name="teacherMainWorkArea" frameborder=0 scrolling="auto" src="${ctx}/tsxxb/messgePage"></iframe>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->
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
</@html>
