<@zzxy/> <@html> <@head> <@popupsJs/>
<script type="text/javascript">
	function tj(url){
		doShowDialog(url,null,"窗口");
	}
	function myrefresh(){
		var form = document.forms[0];
		form.target = "_self";
		form.submit();
	}
	$(function(){
		$("input[type='text']").attr("readonly","readonly");
	})
</script>
</@head>
<body>
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<!-- TODO LIST -->
						<div class="panel">
							<form id="form" name="form" action="" method="post">
								<input type="hidden" id="id" name="id"
									value="<#if (jsxxb.id)??>${jsxxb.id}</#if>">
								<div class="panel-heading">
									<h3 class="panel-title">
										<b>基本信息</b>
									</h3>
									<div class="right">
										<#if (jsxxb.zt)??> <#if jsxxb.zt==JSXXB_ZT_WSH> <a href="###"
											class="btn btn-default btn-sm"
											onclick="return tj('${ctx}/jsxxb/tjJsxx?jsId=${(jsxxb.id)!''}')">提交</a>
										</#if> </#if>
									</div>
								</div>
								<div class="panel-body">
									<table class="table table-bordered">
										<tr>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">帐号：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" readonly="readonly" class="form-control"
												id="dlh" name="dlh"
												value="<#if jsxxb.dlh??>${jsxxb.dlh}</#if>" />
											</td>
											<td rowspan="3"
												style="width: 15%; text-align: right; vertical-align: middle;">照片：</td>
											<td rowspan="3"
												style="width: 35%; text-align: left; vertical-align: middle;">
												<img src="${ctx}/file/download?fileName=${(wjb2.wjm)!''}"
												style="width: 200px; height: 200px" />
											</td>
										</tr>
										<tr>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">姓名：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="name"
												name="name" value="<#if (jsxxb.name)??>${jsxxb.name}</#if>" />
											</td>
										</tr>
										<tr>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">性别：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="sex" name="sex"
												value="<#if (jsxxb.sex)??>${SEX_Map.get(jsxxb.sex)}</#if>" />
											</td>
										</tr>
										<tr style="height: 80px">
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">身份证号：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="sfzh"
												name="sfzh" value="<#if (jsxxb.sfzh)??>${jsxxb.sfzh}</#if>" />
											</td>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">手机号码：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="mobile"
												name="mobile"
												value="<#if (jsxxb.mobile)??>${jsxxb.mobile}</#if>" />
											</td>
										</tr>
										<tr style="height: 80px">
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">学院：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="xybh"
												name="xybh" value="<#if (jsxxb.xybh)??>${jsxxb.xybh}</#if>" />
											</td>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">专业：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="zybh"
												name="zybh" value="<#if (jsxxb.zybh)??>${jsxxb.zybh}</#if>" />
											</td>
										</tr>
										<tr style="height: 80px">
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">职称：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="zc" name="zc"
												value="<#if (jsxxb.zc)??>${jsxxb.zc}</#if>" />
											</td>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">学历：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" class="form-control" id="xl" name="xl"
												value="<#if (jsxxb.xl)??>${jsxxb.xl}</#if>" />
											</td>
										</tr>
										<tr style="height: 80px">
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">邮箱：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" readonly="readonly" class="form-control"
												id="email" name="email"
												value="<#if jsxxb.email??>${jsxxb.email}</#if>" />
											</td>
											<td
												style="width: 15%; text-align: right; vertical-align: middle;">状态：</td>
											<td
												style="width: 35%; text-align: left; vertical-align: middle;">
												<input type="text" readonly="readonly" class="form-control"
												id="sybz" name="sybz"
												value="<#if jsxxb.sybz??>${CZYB_SYBZ_Map.get(jsxxb.sybz)}</#if>" />
											</td>
										</tr>
									</table>
								</div>
							</form>
						</div>
						<!-- END TODO LIST -->
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<!-- TODO LIST -->
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">
									<b>资格认证</b>
								</h3>
								<font size="2px" color="red">请必须上传身份证照、学历和职称证明，否则审核将不予通过！</font>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>序号</th>
											<th>证件名称</th>
											<th>证件说明</th>
											<th>证件附件</th>
										</tr>
									</thead>
									<tbody>
										<#if list2??> <#list list2 as it>
										<tr>
											<td>${it_index+1}</td>
											<td>${it.zjmc }</td>
											<td>${it.zjsm }</td>
											<td><a
												href="${ctx}/file/download?fileName=${fjMap.get(it.fj) }">${fjMap["${it.fj }"] }</a>
											</td>

										</tr>
										</#list> </#if>
									</tbody>
								</table>
							</div>
						</div>
						<!-- END TODO LIST -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</@html>
