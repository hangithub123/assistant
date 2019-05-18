<html>
<head>
	<@xy.link  href="assets/css/main.css"/>  
	<@xy.link  href="assets/css/googlefonts.css"/>  
	<@xy.link  href="assets/vendor/font-awesome/css/font-awesome.min.css"/>  
	<@xy.link  href="assets/vendor/bootstrap/css/bootstrap.min.css"/>  
	<@xy.link  href="assets/vendor/linearicons/style.css"/>  
	<@xy.script src="layui/layui.all.js"/> 
	<@xy.script src="publicJs/jquery-3.3.1.js"/>
	<@xy.script src="publicJs/popups.js"/> 
</head>
<script type="text/javascript">
	function colseFunction(id){
		doCloseDialog(id);//弹出层的id
	}
</script>
<body style="text-align:center;margin:0;">
<div class="panel" >
	<div class="panel-body" style="height:50%;margin:0;">
		 <font size="4">${Message!'出错'}</font>
	</div>
	<div class="panel-footer" style="height:30%;margin:0;">
		<button type="button" class="btn btn-info" onclick="colseFunction('${colseId!''}')">确定</button>
	</div>
</div>
</body>
</html>