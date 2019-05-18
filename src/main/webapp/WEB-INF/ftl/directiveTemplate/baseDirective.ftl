<#-- html -->
<#macro html>
	<!doctype html>
	<html lang="en">
	<#nested>
	</html>
</#macro>

<#-- head -->
<#macro head>
	<head>
		<title>网上助教系统</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<@link href="assets/vendor/bootstrap/css/bootstrap.min.css"/>
		<@link href="assets/vendor/font-awesome/css/font-awesome.min.css"/>
		<@link href="assets/vendor/linearicons/style.css"/>
		<@link href="assets/vendor/chartist/css/chartist-custom.css"/>
		<@link href="assets/css/main.css"/>
		<@link href="assets/css/demo.css"/>
		<@link  href="assets/css/googleapis.css"/>
		<@script src="publicJs/jquery-3.3.1.js"/>
		<@script src="assets/vendor/bootstrap/js/bootstrap.min.js"/>
		<@script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"/>
		<@script src="assets/scripts/klorofil-common.js"/>
		<@script src="publicJs/adaptive.js"/>
		<#nested>
	</head>
</#macro>
<#macro body>
<#nested>
</#macro>
<#-- script -->
<#macro script src >
	<script src="/UIEditor/static/${src}" type="text/javascript" charset="utf-8"></script>
</#macro>

<#-- link -->
<#macro link href >
	 <link href="/UIEditor/static/${href}" rel="stylesheet" > </link>
</#macro>

<#-- img -->
<#macro img src >
	 <img src="/UIEditor/static/images/${src}"></img>
</#macro>

<#-- 表单校验所需的js -->
<#macro CheckTextJs >
	<@script src="publicJs/CheckText.js"/>
</#macro>

<#-- 弹出层所需的js -->
<#macro popupsJs >
	<@script src="layer/layer.js"/>
	<@script src="publicJs/popups.js"/>
</#macro>

<#-- 使用分页所需的css和js -->
<#macro splitpageCssAndJs>
	<@link  href="layui/css/layui.css"/>
	<@script src="layui/layui.all.js"/>
	<@script src="publicJs/splitPage.js"/>
</#macro>

<#-- 分页 -->
<#macro splitpage>
	<#if pageParams??>
	<a  id="splitTurntoPage" page="${pageParams.page}" pageSize="${pageParams.pageSize}"
	 total="${pageParams.total}">
		<span id="splitTurntoPageSpan"></span>
	</a>
	<div align="right" id="splitpage"></div>
	<#else>
	<span>分页对象不能为空</span>
	</#if>
</#macro>

<#-- 文件上传所需的js -->
<#macro fileJs>
	<@script src="publicJs/ajaxUtils.js"/>
	<@script src="publicJs/fileUAD.js"/>
</#macro>

<#--文件上传 -->
<#macro fileUpload wdname>
	<input  type="file" name="file" id="file" value="<#if wjb??>${(wjb.wjm)!''}</#if>"/><#if wjb??>${wjb.wjm!''}</#if>
	<#nested>
	<input type="hidden" id="${wdname}" name="${wdname}" value="${wdid!''}"/>
    <input type="hidden" id="upload" value="上传附件" onclick="uploadFile('${ctx}/file/upload?${wdname}=${wdid!''}')"/>
</#macro>

<#--文件下载 -->
<#macro fileDownload filename>
	<a href="${ctx}/file/download?fileName=${filename!''}">${filename!''}</a>
</#macro>

<#-- 日期格式化 -->
<#macro date time>
	<#if time?length==4>
		${time?substring(0,4)}年
	<#elseif time?length==8>
		${time?substring(0,4)}年${time?substring(4,6)}月${time?substring(6,8)}日
	<#elseif time?length==14>
		${time?substring(0,4)}年${time?substring(4,6)}月${time?substring(6,8)}日 ${time?substring(8,10)}:${time?substring(10,12)}:${time?substring(12,14)}
	<#else>
		${time}
	</#if>
</#macro>

<#-- 下拉框,注意map需要转为json -->
<#macro select id name selectMap selected>
	<select id="${id}" name="${name}" class="form-control">
		<option>请选择</option>
	</select>
	<script>
	$(function(){
	   var selected=${selected}+"";
	   var strmap=${selectMap};
	   var select=${id};
	   for(var key in strmap){
		   var option =new Option(strmap[key],key);
		   if(key==selected){
			   option.selected=true;
			 }
		   select.add(option);
	   }
	});
	</script>
</#macro>
<#--日期组件 id不可空-->
<#macro date id name value width>
<div class="input-group" style="width:${width!'200px'}">
	<input class="form-control" id="datepicker" placeholder="日期选择" type="text">
	 <span class="input-group-addon"><i class="lnr lnr-calendar-full"></i></span>
</div>
  <input type="hidden" id="${id}" name="${name}"  value="${value}"  />
	<script>
	$(function(){
		layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#datepicker',//指定元素
		    format:'yyyy-MM-dd HH:mm:ss',
		    type: 'datetime',
		    theme: 'grid',
		    done: function(value, date, endDate){
		    	value=value.replace(/\ +/g,"");
		    	value=value.replace(/(:)/g,"");
		    	value=value.replace(/(-)/g,"");
		    	$('#${id}').val(value);
		    }
		  });
		});
	});
	</script>
</#macro>
