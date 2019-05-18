//弾出指定祝圏解析器返回的更面。
function doShowDialog(url,id,title,width,height){
		if(width==null||width==""){width='250px';}
		if(height==null||height==""){height='250px';}
		layer.open({
			 offset:  ['0px', '0px'],
			 id: "PopDialogtest"+id,
			 type:2,
			  title: title,
			  content: url,
			  area: [width, height]
			});  
	}

//关闭弹出层
function doCloseDialog(id){
	var index = parent.layer.getFrameIndex(id);
	window.parent.location.reload();
	parent.layer.close(index);
}
 

