$(function(){
	adaptive();
})
function adaptive(){
	var $iframe = parent.frames['teacherMainWorkArea']
	var height=$(document.body).outerHeight(true);
	var width=$(document.body).outerWidth(true);
	if($iframe==null){$iframe=parent.parent.frames['teacherMainWorkArea']}
	if(height<560){height=560}
	if(width>1300){width=1300}
	console.log("iframe自适应高度="+height);
	console.log("iframe自适应宽度="+width);
	$iframe.frameElement.width=width;
	$iframe.frameElement.height=height;
}