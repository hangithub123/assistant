<@zzxy/>
<html>
<head>
	<@link  href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>  
	<@link  href="fileCss/fileCss.css"/>  
	<@script src="publicJs/jquery-3.3.1.js"/>
	<@script src="publicJs/ajaxUtils.js"/>
	<@script src="layer/layer.js"/>
	<@script src="fileJs/createFile.js"/>
	<@script src="fileJs/foreach.js"/>
</head>
<body>
	<div class="container" >
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					带有 title 的面板标题
				</h3>
			</div>
			<div id="topArea"  class="panel-body">
				<label class="control-label" for="htmlpath"><font color="#3c763d">前端文件路径:</font></label>
			 	<input id="htmlpath" type="text" value="F:/ebs/webapp/html/index.html"/> &nbsp;
			 	<label class="control-label" for="javapath"><font color="#8a6d3b">后端文件路径:</font></label>
				<input id="javapath" type="text" value=""/> &nbsp;
				<a href="###" class="btn btn-default" onclick="produceFile(this)">生成文件</a> &nbsp;
				<a href="###" class="btn btn-default">查看源码</a>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
				<a href="###" onclick="return insertui(this)" class="btn btn-info">插入</a>&nbsp;
				<a href="${ctx}/index/selectui"  onclick="selectui(this)" class="btn btn-info">添加ui片段</a>
			</div>
			<div id="uiChangeArea" class="panel-body">
				<ul>
					<#list uilist as uicode >
						<div  name="uiChangeArea_${uicode.UINAME}"  isSelect=0  href="###" onclick="uiSelect(this)" title="${uicode.MS}" class="btn btn-default btn-sm" >${uicode.SHOW_NAME!'SHOW_NAME不能为空'}
							<pre name="showuicode" uiname="${uicode.UINAME}" style="display:none;" >${uicode.SHOWCODE!'SHOWCODE不能为空'}</pre>
							<pre name="code" codename="${uicode.UINAME}" style="display:none;" >${uicode.UICODE!'UICODE不能为空'}</pre>
						</div>
					</#list>
				</ul>
			</div>
		</div>
		<div style="float:left;width:59%;height:auto;" id="uiShowpanel" class="panel panel-success" >
			<div  class="panel-body">
				<div id="uiShowArea" style="width:100%;">
				</div>
			</div>
		</div>
		<div style="float:right;width:40%;height:300px;" class="panel panel-success" >
		<div class="panel-heading">选中左侧ui片段进行编辑</div>
			<div id="editui" class="panel-body">
			</div>
		</div>
	</div>
</body>
</html>