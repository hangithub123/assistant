<@zzxy/>
<@html>
<@head>
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
			<div class="panel-heading">
				<h3 class="panel-title"><b>资格认证</b></h3><font size="2px" color="red">请必须上传身份证照、学生证照，否则审核将不予通过！</font>
				<div class="right">
					<a href="###" class="btn btn-default btn-sm" onclick="doShowDialog('${ctx }/xszgrz/editZgrz','editZgrz','窗口','900px','400px')"> <span>增加</span></a>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>证件名称</th>
							<th>证件说明</th>
							<th>证件附件</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<#if list2??>
						<#list list2 as it>
						<tr>
							<td>${it_index+1 }</td>
							<td>${it.zjmc }</td>
							<td>${it.zjsm }</td>
							<td>${fjMap["${it.fj }"] }</td>
							<td>
								<a href="###" onclick="doShowDialog('${ctx }/xszgrz/editZgrz?id=${it.id}','editZgrz','窗口','900px','400px')"><i class="lnr lnr-pencil"></i> <span>修改</span></a>
								<a href="${ctx }/xszgrz/deleteZgrz?id=${it.id}" onclick="return qr()"><i class="lnr lnr-trash"></i> <span>删除</span></a>
							</td>
						</tr>
						</#list>
						</#if>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END TODO LIST -->
	</div>
</div>
</body>
</@html>
