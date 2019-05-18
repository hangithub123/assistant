<@zzxy/>
<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<@link href="assets/vendor/bootstrap/css/bootstrap.min.css"/>
	<@link href="assets/vendor/font-awesome/css/font-awesome.min.css"/>
	<@link href="assets/vendor/linearicons/style.css"/>
	<!-- MAIN CSS -->
	<@link href="assets/css/main.css"/>
	<!-- MAIN JS -->
	<@script src="publicJs/jquery-min.js"/>
	<@script src="publicJs/jquery-ui.min.js"/>
	<@script src="publicJs/ajaxUtils.js"/>
	<@script src="layer/layer.js"/>
	<@script src="fileJs/foreach.js"/>
	<@script src="fileJs/dragUI.js"/>
</head>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<div id="navbar-menu">
				<label class="control-label" for="htmlpath"><font color="#3c763d">前端文件路径:</font></label>
			 	<input id="htmlpath" type="text" value="F:/ebs/webapp/html/index.html"/> &nbsp;
			 	<label class="control-label" for="javapath"><font color="#8a6d3b">后端文件路径:</font></label>
				<input id="javapath" type="text" value=""/> &nbsp;
				<a href="###" class="btn btn-default" onclick="produceFile(this)">生成文件</a> &nbsp;
				<a href="###" class="btn btn-default">查看源码</a>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="tables.html" class="active"><i class="lnr lnr-pencil"></i> <span>文件编辑</span></a></li>
						<li><a href="icons.html" class=""><i class="lnr lnr-map"></i> <span>文件布局</span></a></li>
						<li><a href="typography.html" class=""><i class="lnr lnr-book"></i> <span>UI录入</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">组装原型</h3>
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">UI</h3>
								</div>
								<div class="panel-body">
									<ul>
										<#list uilist as uicode >
											<div  name="uiChangeArea_${uicode.UINAME}"  isSelect=0   title="${uicode.MS}" class="btn btn-default" >${uicode.SHOW_NAME!'空'}
												<pre name="showuicode" uiname="${uicode.UINAME}" style="display:none;" >${uicode.SHOWCODE!'空'}</pre>
											</div>
										</#list>
							   	</ul>
								</div>
							</div>
							<!-- END BORDERED TABLE -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<!-- BORDERED TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">编辑区</h3>
								</div>
								<div class="panel-body" id="editArea">
									<!-- <table class="table table-bordered">
										<thead>
											<tr>
												<th>#</th>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Username</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>Steve</td>
												<td>Jobs</td>
												<td>@steve</td>
											</tr>
											<tr>
												<td>2</td>
												<td>Simon</td>
												<td>Philips</td>
												<td>@simon</td>
											</tr>
											<tr>
												<td>3</td>
												<td>Jane</td>
												<td>Doe</td>
												<td>@jane</td>
											</tr>
										</tbody>
									</table> -->
								</div>
							</div>
							<!-- END BORDERED TABLE -->
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2019 -郑州信源信息技术有限股份公司</p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
</body>
</html>