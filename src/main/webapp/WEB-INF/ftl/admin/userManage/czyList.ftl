<@zzxy/>
<@html>
<@head>
	<@splitpageCssAndJs/>
	<@popupsJs/>
	<script type="text/javascript">
		function qr() {
			if(confirm('您确定要删除吗？')){
				return true;
			}else{
				return false;
			}
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
			<form id="form" name="form" action="${ctx }/login/czyList" method="post">
			<div class="panel-heading">
				<h3 class="panel-title"><b>操作员信息列表</b></h3>
				<div class="right">
					<input type="submit" value="查询" class="btn btn-default btn-sm"/>
					<a href="###" class="btn btn-default btn-sm" onclick="doShowDialog('${ctx }/login/editCzyb','editCzyb','窗口','900px','400px')"> <span>增加</span></a>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-bordered">
			      <tr>
			      	<td style="width:8%;text-align: right;vertical-align: middle;">帐号：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">
			      		<input type="text" class="form-control" id="dlh" name="dlh" value="<#if (czyb.dlh)??>${czyb.dlh}</#if>"/>
			      	</td>
					<td style="width:8%;text-align: right;vertical-align: middle;">角色：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">
			      		<@select name="role" id="role" selectMap="${roleMap }" selected="${(czyb.role)!mrz }"/>
			      	</td>
			      	<td   style="width:8%;text-align: right;vertical-align: middle;">使用状态：</td>
			      	<td style="width:25%;text-align: left;vertical-align: middle;">    		
			      		<@select name="sybz" id="sybz" selectMap="${sybzMap }" selected="${(czyb.sybz)!mrz }"/>
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
							<th>密码</th>
							<th>邮箱</th>
							<th>角色</th>
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
							<td>${it.mm }</td>
							<td>${it.email }</td>
							<td>${CZYB_ROLE_Map.get(it.role)}</td>
							<td>${CZYB_SYBZ_Map.get(it.sybz)}</td>
							<td>
								<a href="###" onclick="doShowDialog('${ctx }/login/editCzyb?id=${it.id}','editCzyb','窗口','900px','400px')"><i class="lnr lnr-pencil"></i> <span>修改</span></a>
								<a href="${ctx }/login/deleteCyb?id=${it.id}" onclick="return qr()"><i class="lnr lnr-trash"></i> <span>删除</span></a>
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
