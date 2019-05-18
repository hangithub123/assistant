<@zzxy/>
<@html>
<@head>
	<@CheckTextJs/>
	<@fileJs/>
	<script type="text/javascript">
		$(function(){
			$("#sybz").attr("disabled","disabled");
			var sex=$("[name=sex]").attr("value");
			if (sex != null || sex != '') {
				$("[name=sex] option[value="+sex+"]").attr("selected",true);
			}
		})
		function checkForm(){
			if(!checkFormItem(form.name,"C",50,0,5,"姓名")) return false;
			if(!checkFormItem(form.sex,"C",2,0,5,"性别")) return false;
			if(fjcount())return false;
			if(!checkFormItem(form.sfzh,"C",18,0,4,"身份证号")) return false;
			if(!checkFormItem(form.xh,"C",50,0,5,"学号")) return false;
		    if(!checkFormItem(form.xybh,"C",80,0,5,"学院")) return false;
		    if(!checkFormItem(form.zybh,"C",80,0,5,"专业")) return false;
		    if(!checkFormItem(form.mobile,"N",11,0,4,"手机号")) return false;
		    $("#upload").click();
		    $("#sybz").removeAttr("disabled");
			$.post("${ctx }/xsxxb/saveJsxx",$("#form").serialize(),function(msg){
				if (msg==1) {
					alert("保存成功！");
					myrefresh();
				}
			});
		    return true;
		}
		function myrefresh(){
			var form = document.forms[0];
			form.target = "_self";
			form.submit();
		}
		
	</script>
</@head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- TODO LIST -->
			<div class="panel">
				<form id="form" name="form" action="" method="post" >
				<input type="hidden" id="id" name="id" value="<#if (xsxxb.id)??>${xsxxb.id}</#if>">
				<input type="hidden" id="zt" name="zt" value="<#if (xsxxb.zt)??>${xsxxb.zt}</#if>">
				<input type="hidden" id="wjb" name="wjb" value="<#if wjb??>${wjb.wjm!''}</#if>">
				<div class="panel-heading">
					<h3 class="panel-title"><b>基本信息</b></h3>
					<div class="right">
						<#if xsxxb.zt != JSXXB_ZT_SHZ>
						<input type="button" value="保存" class="btn btn-default btn-sm" onclick="return checkForm()"/>
						</#if>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-bordered">
				      <tr>
				      	<td style="width:15%;text-align: right;vertical-align: middle;">帐号：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" readonly="readonly" class="form-control" id="dlh" name="dlh" value="${xsxxb.dlh}"/>
				      	</td>
				      	<td rowspan="3"   style="width:15%;text-align: right;vertical-align: middle;">照片：</td>
				      	<td rowspan="3" style="width:35%;text-align: left;vertical-align: middle;">
				      		<img src="${ctx}/file/download?fileName=${(wjb.wjm)!''}" style="width:200px;height:200px" />
				      		<@fileUpload wdname="fj"/>
				      	</td>
				      </tr>
				      <tr>
				      	<td style="width:15%;text-align: right;vertical-align: middle;">姓名：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="name" name="name" value="<#if xsxxb.name??>${xsxxb.name}</#if>"/>
				      	</td>
				      </tr>
				      <tr>
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">性别：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">    		
				      		<select class="form-control input-sm" name="sex" id="sex" value="<#if xsxxb.sex??>${xsxxb.sex!''}</#if>">
								<option></option>
								<#list sexMap.keySet() as key>
								<option value="${key }" >${sexMap.get(key) }</option>
								</#list>
							</select>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">身份证号：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="sfzh" name="sfzh" value="<#if xsxxb.sfzh??>${xsxxb.sfzh}</#if>"/>
				      	</td>
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">学号：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="xh" name="xh" value="<#if xsxxb.xh??>${xsxxb.xh}</#if>"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">学院：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="xybh" name="xybh" value="<#if xsxxb.xybh??>${xsxxb.xybh}</#if>"/>
				      	</td>
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">专业：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="zybh" name="zybh" value="<#if xsxxb.zybh??>${xsxxb.zybh}</#if>"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">手机号码：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" class="form-control" id="mobile" name="mobile" value="<#if xsxxb.mobile??>${xsxxb.mobile}</#if>"/>
				      	</td>
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">邮箱：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<input type="text" readonly="readonly" class="form-control" id="email" name="email" value="<#if xsxxb.email??>${xsxxb.email}</#if>"/>
				      	</td>
				      </tr>
				      <tr style="height: 80px">
				      	<td   style="width:15%;text-align: right;vertical-align: middle;">状态：</td>
				      	<td style="width:35%;text-align: left;vertical-align: middle;">
				      		<@select name="sybz" id="sybz" selectMap="${sybzMap }" selected="${xsxxb.sybz }"/>
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
