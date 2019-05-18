<@zzxy/>
<@html>
<@head>
	<@splitpageCssAndJs/>
	<@popupsJs/>
</@head>

<body>
<div class="row">
	<div class="col-md-12">
		<!-- TODO LIST -->
		<div class="panel">
			<form id="form" name="form" action="${ctx }/jsxxb/jsList" method="post">
			<div class="panel-heading">
				<h3 class="panel-title"><b>教师信息列表</b></h3>
				<div class="right">
					<input type="submit" value="查询" class="btn btn-default btn-sm"/>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-bordered">
			      <tr>
			      	<td style="width:8%;text-align: right;vertical-align: middle;">帐号：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">
			      		<input type="text" class="form-control" id="dlh" name="dlh" value="<#if jsxxb.dlh??>${jsxxb.dlh}</#if>"/>
			      	</td>
					<td style="width:8%;text-align: right;vertical-align: middle;">姓名：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">
			      		<input type="text" class="form-control" id="name" name="name" value="<#if jsxxb.name??>${jsxxb.name}</#if>"/>
			      	</td>
			      	<td   style="width:8%;text-align: right;vertical-align: middle;">使用状态：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">    		
			      		<select class="form-control input-sm" name="sybz" id="sybz" value="<#if jsxxb.sybz??>${jsxxb.sybz}</#if>">
							<option></option>
							<#list sybzMap.keySet() as key > 
							<option value="${sybzMap.get(key)}">${key}</option>
							</#list>
						</select>
			      	</td>
			      </tr>
			      <tr>
			      	<td   style="width:8%;text-align: right;vertical-align: middle;">学院：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">
			      		<input type="text" class="form-control" id="xybh" name="xybh" value="<#if jsxxb.xybh??>${jsxxb.xybh}</#if>"/>
			      	</td>
			      	<td   style="width:8%;text-align: right;vertical-align: middle;">专业：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">
			      		<input type="text" class="form-control" id="zybh" name="zybh" value="<#if jsxxb.zybh??>${jsxxb.zybh}</#if>"/>
			      	</td>
			      </tr>
			     </table>
			</div>
			</form>
			<div class="panel-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>账号</th>
							<th>姓名</th>
							<th>身份证号</th>
							<th>电话</th>
							<th>学院</th>
							<th>专业</th>
							<th>使用状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<#if list??>
						<#list list as it>
						<tr>
							<td>${it_index+1}</td>
							<td>${it.dlh }</td>
							<td>${it.name }</td>
							<td>${it.sfzh }</td>
							<td>${it.mobile }</td>
							<td>${it.xybh }</td>
							<td>${it.zybh }</td>
							<td>${CZYB_SYBZ_Map.get(it.sybz)}</td>
							<td>
								<a href="###" onclick="doShowDialog('${ctx }/jsxxb/showjszl2?dlh=${it.dlh}','showjszl2','窗口','1200px','680px')"><i class="lnr lnr-pencil"></i> <span>详情</span></a>
							</td>
						</tr>
						</#list>
						</#if>
					</tbody>
				</table>
				<@splitpage/>
			</div>
		</div>
		<!-- END TODO LIST -->
	</div>
</div>
</body>
</@html>
