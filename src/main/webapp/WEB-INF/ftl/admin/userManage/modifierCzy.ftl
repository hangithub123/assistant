<@zzxy/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加修改管理员信息</title>
<@link href="assets/vendor/bootstrap/css/bootstrap.min.css"/>
<@link href="assets/css/main.css"/>
<@script src="publicJs/CheckText.js"/>
<@script src="publicJs/jquery-3.3.1.js"/>
<@script src="publicJs/popups.js"/>
<@script src="layer/layer.js"/>
<script type="text/javascript">
	$(function(){
		$("#role").attr("disabled","disabled");
	})
	function checkForm(){
		if(!checkFormItem(form.dlh,"C",50,0,5,"账号")) return false;
		if(!checkFormItem(form.mm,"C",50,0,5,"密码")) return false;
		if(!checkFormItem(form.email,"C",50,0,5,"邮箱")) return false;
		$("#role").removeAttr("disabled");
		$.post("/UIEditor/login/saveCzy",$("#form").serialize(),function(msg){
			if (msg==1) {
				 alert("保存成功！"); 
				 doCloseDialog('editCzyb');
			} 
		});
	    return true;
	}
	function refresh(){
		var form = document.forms[-1];
		form.target = "_self";
		form.submit();
	}
</script>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- TODO LIST -->
			<div class="panel">
				<form id="form" name="form" action="" method="post">
				<input type="hidden" id="id" name="id" value="<#if czyb.id??>${czyb.id}</#if>">
				<div class="panel-heading">
					<h3 class="panel-title"><b>操作员信息</b></h3>
					<div class="right">
						<input type="button" value="保存" class="btn btn-default btn-sm" onclick="return checkForm()"/>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">账号</td>
				      	<#if sfcz==1>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" readonly="readonly" class="form-control" id="dlh" name="dlh" value="<#if czyb.dlh??>${czyb.dlh}</#if>"/>
				      	</td>
				      	<#else>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="dlh" name="dlh" value="<#if czyb.dlh??>${czyb.dlh}</#if>"/>
				      	</td>
				      	</#if>
				      	<td style="width:15%;text-align: right;vertical-align: middle;">密码：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
							<input type="text" class="form-control" id="mm" name="mm" value="<#if czyb.mm??>${czyb.mm}</#if>"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">邮箱</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="email" name="email" value="<#if czyb.email??>${czyb.email}</#if>"/>
				      	</td>
				      	<td style="width:15%;text-align: right;vertical-align: middle;">使用状态：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
							<@select name="sybz" id="sybz" selectMap="${sybzMap }" selected="${czyb.sybz!'' }"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">角色</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<@select name="role" id="role" selectMap="${roleMap }" selected="${czyb.role }"/>
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
</html>