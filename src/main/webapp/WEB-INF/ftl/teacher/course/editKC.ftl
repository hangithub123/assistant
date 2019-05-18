<@zzxy/>
<@html>
<@head>
<@CheckTextJs/>
<@popupsJs/>
<@fileJs/>
<script type="text/javascript">
	function checkForm(){
		if(!checkFormItem(form.kcmc,"C",50,0,5,"课程名称")) return false;
		if(fjcount())return false;
		if(!checkFormItem(form.kcjj,"C",200,0,5,"课程简介")) return false;
		$("#upload").click();
		$.post("/UIEditor/course/saveKC",$("#form").serialize(),function(msg){
			if (msg==1) {
				 alert("保存成功！"); 
				 doCloseDialog('editKC');
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
				<input type="hidden" id="id" name="id" value="<#if kcb.id??>${kcb.id}</#if>">
				<input type="hidden" id="kczt" name="kczt" value="<#if kcb.kczt??>${kcb.kczt}</#if>">
				<input type="hidden" id="jsbh" name="jsbh" value="<#if kcb.jsbh??>${kcb.jsbh}</#if>">
				<input type="hidden" id="jsxm" name="jsxm" value="<#if kcb.jsxm??>${kcb.jsxm}</#if>">
				<input type="hidden" id="cshrq" name="cshrq" value="<#if kcb.cshrq??>${kcb.cshrq}</#if>">
				<input type="hidden" id="wjb" name="wjb" value="<#if wjb??>${wjb.wjm!''}</#if>">
				<div class="panel-heading">
					<h3 class="panel-title"><b>添加课程信息</b></h3>
					<div class="right">
						<input type="button" value="保存" class="btn btn-default btn-sm" onclick="return checkForm()"/>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">课程名称</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="kcmc" name="kcmc" value="<#if kcb.kcmc??>${kcb.kcmc}</#if>"/>
				      	</td>
				      	<td style="width:15%;text-align: right;vertical-align: middle;">课程封面：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
							<@fileUpload wdname="fj"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">课程简介：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;" colspan="3">
				      		<textarea class="form-control" id="kcjj" name="kcjj" value="<#if kcb.kcjj??>${kcb.kcjj}</#if>" rows="4"><#if kcb.kcjj??>${kcb.kcjj}</#if></textarea>
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