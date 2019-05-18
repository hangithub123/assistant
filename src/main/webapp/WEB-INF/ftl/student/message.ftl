<@zzxy/>
<@html>
<@head>
</@head>
<body>
<div class="row">
	<div class="col-md-12">
		<!-- TODO LIST -->
		<div class="panel">
			<div class="panel-heading">
				<h3 class="panel-title">提示消息</h3>
				<div class="right">
					<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
					<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
				</div>
			</div>
			<div class="panel-body">
				<ul class="list-unstyled todo-list">
					<#if list??>
					<#list list as it>
					<li>
						<label class="control-inline fancy-checkbox">
							<input type="checkbox"><span></span>
						</label>
						<p>
							<span class="short-description">${it.tsnr }</span>
						</p>
						<div class="controls">
							<a href="${ctx }/tsxxb/ycTsxx?tsId=${it.id}"><i class="icon-arrows icon-arrows-circle-remove">移除</i></a>
						</div>
					</li>
					</#list>
					</#if>
				</ul>
			</div>
		</div>
		<!-- END TODO LIST -->
	</div>
</div>
</body>
</@html>