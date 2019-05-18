<@zzxy/>
<html>
<head>
	<@link  href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
	<@script src="publicJs/jquery-3.3.1.js"/>
	<@script src="publicJs/ajaxUtils.js"/>
	<@script src="fileJs/addui.js"/>
	<@script src="layer/layer.js"/>
</head>
<body>
	<div class="container">
		<div class="panel panel-info">
			<div class="panel-body">
				<font size="5">新增ui片段</font>
				<a href="###" class="btn btn-info" onclick="return submitFun(this)" style="float:right">提交</a>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					ui片段
				</h3>
			</div>
			<div id="Area1" class="panel-body" >
				<input type="text" id="uicode_name" class="form-control" placeholder="ui名称" style="width:30%;float:left;">
				<input type="text" id="uicode_ms" class="form-control" placeholder="描述" style="width:30%;float:left;">
				<select class="form-control" id="uicode_type" style="width:30%;float:right;">
					<option value="-1">类型</option>
					<option value="1">包涵组件</option>
					<option value="2">纯ui</option>
				</select>
				<br/><br/>
				<textarea id="uiCode" class="form-control" placeholder="uicode" ></textarea>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					showui片段
				</h3>
			</div>
			<div id="Area2" class="panel-body" >
				<input type="text" id="showui_name" class="form-control" placeholder="showui名称" style="width:30%;"/><br/>
				<textarea id="showui" class="form-control" placeholder="showui片段" ></textarea>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					js函数
				</h3>
			</div>
			<div id="Area3" class="panel-body" >
				<textarea id="jsFun" class="form-control" placeholder="js函数" ></textarea>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					静态文件
				</h3>
			</div>
			<div  class="panel-body" >
				<textarea id="staticFile" class="form-control" placeholder="静态文件" ></textarea>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					java文件
					<a href="###" class="btn btn-default btn-sm" onclick="return addproperty(this)" >增加属性</a>
				</h3>
			</div>
			<div  class="panel-body" >
				<div id="java_property">
					<input type="text"  name="java_propertyname"  class="form-control" placeholder="属性名" style="width:30%;float:left;">
					<input type="text"  name="java_propertytype" class="form-control" placeholder="属性类型" style="width:30%;float:left;">
					<select class="form-control"  name="java_isinit"  style="width:30%;float:right;">
						<option value="2">是否需要初始化</option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
				</div>
				<br/><br/>
				<textarea id="java_method" class="form-control" placeholder="方法" ></textarea>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					page属性代码
				</h3>
			</div>
			<div  class="panel-body" >
				<textarea id="pagecode" class="form-control" placeholder="所需属性（全写属性代码）" ></textarea>
			</div>
		</div>
		
	</div>
</body>
</html>