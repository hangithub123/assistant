<@zzxy/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资格证书证明</title>
<@link href="assets/vendor/bootstrap/css/bootstrap.min.css"/>
<@link href="assets/css/main.css"/>
<@script src="publicJs/CheckText.js"/>
<@script src="publicJs/jquery-3.3.1.js"/>
<@script src="publicJs/ajaxUtils.js"/>
<@script src="publicJs/fileUAD.js"/>
<@script src="publicJs/popups.js"/>
<@script src="layer/layer.js"/>
<script type="text/javascript">
	function checkForm(){
		if(!checkFormItem(form.zjmc,"C",50,0,5,"证明名称")) return false;
		if(fjcount())return false;
		if(!checkFormItem(form.zjsm,"C",200,0,5,"证件说明")) return false;
		$("#upload").click();
		$.post("/UIEditor/xszgrz/saveXszgrz",$("#form").serialize(),function(msg){
			if (msg==1) {
				 alert("保存成功！"); 
				 doCloseDialog('editZgrz');
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
				<input type="hidden" id="id" name="id" value="<#if xszgrz.id??>${xszgrz.id}</#if>">
				<input type="hidden" id="xsbh" name="xsbh" value="<#if xszgrz.xsbh??>${xszgrz.xsbh}</#if>">
				<input type="hidden" id="wjb" name="wjb" value="<#if wjb??>${wjb.wjm!''}</#if>">
				<div class="panel-heading">
					<h3 class="panel-title"><b>资格证书证明</b></h3>
					<div class="right">
						<input type="button" value="保存" class="btn btn-default btn-sm" onclick="return checkForm()"/>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">证件名称</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="zjmc" name="zjmc" value="<#if xszgrz.zjmc??>${xszgrz.zjmc}</#if>"/>
				      	</td>
				      	<td style="width:15%;text-align: right;vertical-align: middle;">证件附件：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
							<@fileUpload wdname="fj"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">证件说明：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;" colspan="3">
				      		<textarea class="form-control" id="zjsm" name="zjsm" value="<#if xszgrz.zjsm??>${xszgrz.zjsm}</#if>" rows="4"><#if xszgrz.zjsm??>${xszgrz.zjsm}</#if></textarea>
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