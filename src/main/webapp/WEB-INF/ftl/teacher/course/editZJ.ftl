<@zzxy/>
<@html>
<@head>
<@CheckTextJs/>
<@popupsJs/>
<script type="text/javascript">
	function checkForm(){
		if(!checkFormItem(form.zjmc,"C",50,0,5,"章节名称")) return false;
		if(!checkFormItem(form.zjjj,"C",200,0,5,"章节简介")) return false;
		$.post("/UIEditor/course/saveZJ",$("#form").serialize(),function(msg){
			if (msg==1) {
				 alert("保存成功！"); 
				 doCloseDialog('editZJ');
			} 
		});
	    return true;
	}
</script>
</@head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- TODO LIST -->
			<div class="panel">
				<form id="form" name="form" action="" method="post">
				<input type="hidden" id="id" name="id" value="<#if zjb.id??>${zjb.id}</#if>">
				<input type="hidden" id="kcbh" name="kcbh" value="<#if zjb.kcbh??>${zjb.kcbh}</#if>">
				<input type="hidden" id="cshrq" name="cshrq" value="<#if zjb.cshrq??>${zjb.cshrq}</#if>">
				<div class="panel-heading">
					<h3 class="panel-title"><b>添加章节信息</b></h3>
					<div class="right">
						<input type="button" value="保存" class="btn btn-default btn-sm" onclick="return checkForm()"/>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">章节名称</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="zjmc" name="zjmc" value="<#if zjb.zjmc??>${zjb.zjmc}</#if>"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">章节简介：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;" colspan="3">
				      		<textarea class="form-control" id="zjjj" name="zjjj" value="<#if zjb.zjjj??>${zjb.zjjj}</#if>" rows="4"><#if zjb.zjjj??>${zjb.zjjj}</#if></textarea>
				      	</td>
				      </tr>
				     </table>
				</div>
				</form>
			</div>
			<!-- END TODO LIST -->
		</div>
	</div>
	
</body>
</@html>