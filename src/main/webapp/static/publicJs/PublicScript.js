//隐藏模式对话框
function doHideDialog() {	
	doHideDialogX(1);
}
//隐藏模式对话框

function doHideDialogX(id) {
	try{
		var popDg = queryFrame(top,"PopDialog"+id);
		if(popDg && popDg.frameElement.lhgDG){
			//2014年4月27日 路适远 调用关闭方法
			popDg.frameElement.lhgDG.cancel();
		}else{
			frameElement.lhgDG.cancel();
		}
	}catch(e){
		//2014年4月1日 16:28:00、路适远、处理如果当前的弹出层是公司自定义的弹出层，调用对应的关闭方法
		if(queryFrame(top,"popmsg"+id)){
		  closeMsg(id);
		}
	}
}
//显示居中模式对话框
function doShowDialog(width, height, title,functionName) {
	return doShowDialogX(1,width, height, title,functionName);
}
/**
 * 获取当前是否有弹出层,如果有弹出层，返回最新弹出的弹出层Frame对象
 * @param startWindow
 * @returns
 */
function getParentDg(startWindow){
	  var listFrame = startWindow.frames;
	  for(var i=(listFrame.length-1);i>=0;i--){ 
	    var varFrame = listFrame[i];
	    if(!varFrame) continue;
	    if(varFrame.frameElement.lhgDG){
	      return varFrame;
	    }
	    var listFa = varFrame.frames;
	    if(listFa.length>0){
	      var varReturn = getParentDg(varFrame);
	      if(varReturn!=null)return varReturn;
	    }
	  }
	  return null;
}
//显示居中模式对话框 [2007-12-12 增加showWhiteCover参数，表示是否雾化，当且仅当此参数指定为flase时，对top窗口不进行雾化处理]
//2014年3月31日 18:10:02 陈可可 添加xButtonFun的字段,点击关闭X时执行的方法
//functionName 
function doShowDialogX(id,width, height, title, functionName, showWhiteCover,xButtonFun){
	//统一调用顶层框架的弹出层方法，避免每个页面都要引入弹出层js
	return top.showDialogX(id,width, height, title, functionName, showWhiteCover,xButtonFun);
}
/**
 * 方法名:getFullPath
 * 作用:
 * 从当前窗口获取完整路径类似于:top.MainWorkArea.XX.XX 或者 top.PopDialogX
 * win_obj:窗口对象
 * */
function getFullPath(win_obj){
	var var_path="";
	while(win_obj!=top){
		var_path="." + win_obj.name + var_path;
		win_obj = win_obj.parent;
	}
	return "top"+var_path;
}
//弹出打开最终调用js,参数查看doShowDialogX
function showDialogX(id,width, height, title, functionName, showWhiteCover,xButtonFun) {
	if(showWhiteCover==undefined){
		showWhiteCover = true;//默认为加上蒙层
	}
	var dg;
	dg = new top.jQuery.dialog({ 
		id:id, 
		content:"url:about:blank",
		lock:showWhiteCover,
		title:title || "窗口",
		width:width || 500,
		height:height || 300,
		maxBtn : false,
		resize : false,
		btnBar : false,
		close:xButtonFun,
		init: function(){ //增加弹出窗口固定表头判断
		  try{
			if (null != top.jQuery("#FixedRegion", dg.dgDoc) && undefined != top.jQuery("#FixedRegion", dg.dgDoc)) {
				if (top.jQuery("#FixedRegion", dg.dgDoc).height()!=null) {
					 var height = top.jQuery("#FixedRegion", dg.dgDoc).height();
					 top.jQuery("#HiddenRegion", dg.dgDoc).css("height", height+"px");
				}
			}
		  }catch(e){
			  
		  }
		},
		onXclick : functionName
	});
	dg.show();
	if (typeof functionName == 'function') {
		dg.callBack = functionName;
	}
	return "PopDialog"+id;
}
//可指定位置模式对话框 [2007-12-12 showWhiteCover参数表示是否雾化，当且仅当此参数指定为flase时，对top窗口不进行雾化处理]
function doShowDialogY(id,width, height, pleft, ptop, title, functionName, showWhiteCover) {
	Popleft = pleft || (top.document.body.clientWidth - Popwidth) / 2;
	Poptop = ptop || (top.document.body.clientHeight - Popheight) / 2;	
	if(Popleft<0) Popleft = 0;
	if(Poptop<0) Poptop = 0;
	var dg;
	if(frameElement && frameElement.lhgDG) {
		dg = new frameElement.lhgDG.curWin.jQuery.dialog({ 
			id:id, 
			content:"url:about:blank",
			lock:showWhiteCover,
			title:title || "窗口",
			width:width || 500,
			height:height || 300,
			left : Popleft,
			top : Poptop,
			maxBtn : false,
			resize : false,
			btnBar : false,
			parent : frameElement.lhgDG
		});
		dg.show();
	} else {
		dg = new jQuery.dialog({ 
			id:id, 
			content:"url:about:blank",
			lock:showWhiteCover,
			title:title || "窗口",
			width:width || 500,
			height:height || 300,
			left : Popleft,
			top : Poptop,
			maxBtn : false,
			resize : false,
			btnBar : false
		});
		dg.show();
	}
	if (typeof functionName == 'function') {
		dg.callBack = functionName;
	}

	return "PopDialog"+id;
}

function doShowDialogUrl(_id, _width, _height, _url, _title, functionName) {
	var parentDg = getParentDg(top);
	var dg;
	var url = "url:" + _url;
	if(parentDg && parentDg.frameElement) {
		dg = new top.jQuery.dialog({ 
			id : _id, 
			content : url,
			lock : true,
			title : _title || "窗口",
			width : _width || 500,
			height : _height || 300,
			maxBtn : false,
			resize : false,
			btnBar : false,
			parent : parentDg.frameElement.lhgDG
		});
		dg.show();
	} else {
		dg = new jQuery.dialog({ 
			id : _id, 
			content : url,
			lock : true,
			title : _title || "窗口",
			width : _width || 500,
			height : _height || 300,
			maxBtn : false,
			resize : false,
			btnBar : false
		});
		dg.show();
	}
	if (typeof functionName == 'function') {
		dg.callBack = functionName;
	}
	return "PopDialog" + _id;
}

//显示居中模式对话框
function resizeDialog(width, height,title,functionName){
	return resizeDialogX(1,width, height,title,functionName);
}
//显示居中模式对话框
function resizeDialogX(id,width, height,title,functionName) {
	var lhgDG = frameElement.lhgDG;
	if(lhgDG){
		Popwidth = width || 500;
		Popheight = height || 300;	
		Popleft = (top.document.body.clientWidth - Popwidth) / 2;
		Poptop = (top.document.body.clientHeight - Popheight) / 2;
		if(Popleft<0) Popleft = 0;
		if(Poptop<0) Poptop = 0;
		if(title){
		    lhgDG.title(title);
		}
		if (typeof functionName == 'function') {
			lhgDG.callBack = functionName;
		}
//		lhgDG._maxState = true;
		lhgDG.position(Popleft+10,Poptop+3);
		lhgDG.size(Popwidth-20, Popheight-40);
	}else{
		//2014年4月1日 16:28:00、路适远、处理如果当前的弹出层是公司自定义的弹出层，调用对应的变化大小的方法
		if(queryFrame(top,"popmsg"+id)){
		  resizeMsg(id,width, height);
		}
	}
	
}

//屏幕居中的弹出窗口
function easyPopWindow(width, height, window_name) {
	var top, left, width, height;
	Popwidth = width;
	Popheight = height;
	Popleft = (window.screen.width - Popwidth) / 2;
	Poptop = (window.screen.height - Popheight) / 2;
	window.open("about:blank", window_name, "left=" + Popleft + ",top=" + Poptop + ",height=" + Popheight + ",width=" + Popwidth + ",toolbar=no,location=no,directories=no,status=no");
}


//不重复引入javascript
function loadScript(url) {
	if (!document.getElementById(url)) {
		var script = document.createElement("script");
		script.defer = false;
    //not sure of effect of this?
		script.type = "text/javascript";
		script.src = url;
		script.id = url;
		document.getElementsByTagName("head")[0].appendChild(script);
	}
}
function showModalDialogWithPara(url, title, width, height,isMaximize) {
	if(isMaximize==true){
		var width = window.screen.width;
		var height = window.screen.height;
	}else{
		var width = width || 800;
		var height = height || 600;
		var arg = {window:window, title:title, url:url, width:width, height:height};
	}
	return window.showModalDialog(url, arg, "dialogWidth=" + width + "px;dialogHeight=" + height + "px;scroll=0;help=0;status=0;");
}
function showModelessDialogWithPara(url, title, width, height,isMaximize) {
	if(isMaximize==true){
		var width = window.screen.width;
		var height = window.screen.height;
	}else{
		var width = width || 800;
		var height = height || 600;
		var arg = {window:window, title:title, url:url, width:width, height:height};
	}
	return window.showModelessDialog(url, arg, "dialogWidth=" + width + "px;dialogHeight=" + height + "px;scroll=0;help=0;status=0;");
}



/**
 * 获取离当前控件最近的指定控件
 */
function findLatestObject(obj,offset) {
    if(!offset) {
      offset = 0;
    }
	if (!obj) {
		return null;
	}
	if (typeof(obj)=="string") {
		var temps = document.getElementsByName(obj);
		if(temps.length==0) {
			return null;
		} else if (temps.length==1) {
			return temps[0];
		} else {
			for(var i=temps.length-1; i>=0; i--) {
				if(temps[i].sourceIndex<=event.srcElement.sourceIndex+offset) {
					return temps[i];
				}
			}
		}
	} else {
		if (!obj.length) {
			return obj;
		} else {
			for(var i=obj.length-1; i>=0; i--) {
				if(obj[i].sourceIndex<=event.srcElement.sourceIndex+offset){
					return obj[i];
				}				
			}
		}		
	}
	return null;
}

//js 模拟 StringBuffer
function StringBuffer(string) {
    this._buffer = [];
    this.append(string);
}

StringBuffer.prototype.append = function (string) {
    if (string) {
        //this._buffer.push(string); //用下面的兼容性好些，速度也快一点(IE6下测试的)
        this._buffer[this._buffer.length] = string;
    }
    return this;
}

StringBuffer.prototype.toString = function (separator) {
    return this._buffer.join(separator || "");
}

/**
 * 自动格式化输入的数字，千位分隔符
 * @param {s} 数字
 * @author 黄勇
 */
function number_format(str){
	if(/[^0-9\.\-]/.test(str)) return "必须是数字！";
         
	str=str.replace(/^(\d*)$/,"$1.");
	str=(str+"00").replace(/(\d*\.\d\d)\d*/,"$1");
	str=str.replace(".",",");
	var re=/(\d)(\d{3},)/;
	while(re.test(str))
		str=str.replace(re,"$1,$2");
                
	str=str.replace(/,(\d\d)$/,".$1");
	return "￥" + str.replace(/^\./,"0.")
 }

/*
 * 初始化表格顶部高度，为了配合固定表头使用。
 * 编写时间：2011-09-06
 * 胡冰
 */ 
function HiddenRegionTop(){
	try{
	    var dom = document.getElementById("FixedRegion");
	    var height = 0;
	    if(dom != null){
	    	jQuery.each(jQuery("#FixedRegion").children(),function(i,domEle){ 
	    		//2014年4月15日 路适远 获取元素的含padding、margin的高度
	    		height = jQuery(domEle).outerHeight(true) + height;
	    	});
	    }
	    document.getElementById("HiddenRegion").style.height=height+"px";
	}catch(e){
		
	}
}

function ClassToggle()
{
	//loadOpenWin('SearchRegion',30,5,1);
  jQuery('#SearchRegion').toggle('slow');
  jQuery('#SearchOpen').toggle('show');
  jQuery('#SearchClose').toggle('hidden');
}
//ajax中文乱码，采用请求加密方法处理
function encode(strValue){
	return window.encodeURI(strValue);
}

/**
 * 递归查询指定名称的frame
 * @param {Object} startWindow 查询的顶层窗口
 * @param {Object} frameName 要查询的frame名称
 * @return {TypeName} 
 * chenjianxin 2011-11-23
 * 20180606 孙康 跨域查询frame异常处理
 */
function queryFrame(startWindow,frameName){
  var listFrame = startWindow.frames;
  for(var i=0;i<listFrame.length;i++){     
    var varFrame = listFrame[i];
    if(!varFrame) continue;
    
    try{
		if(varFrame.name==frameName){
	      return varFrame;
	    }
    }catch(e){
    	 continue;
    }

    var listFa = varFrame.frames;
    if(listFa.length>0){
      var varReturn = queryFrame(varFrame,frameName);
      if(varReturn!=null)return varReturn;
    }
  }
  return null;
}
/**
* 打开工作流左侧功能空间
*/
function openWorkFlow(){
	top.document.getElementById("FuncTree").style.display="none";
	top.document.getElementById("Spliter").style.display="none";
	if(top.document.getElementById("i_left")){
		top.document.getElementById("i_left").style.display="none";
	}
	top.document.getElementById("Blank1").style.display="none";
	top.middle.frameSet.cols="208,12,6,*";
}

/**
* 关闭工作流界面
*/
function closeWorkFlow(){
	top.document.getElementById("FuncTree").style.display="";
	top.document.getElementById("Spliter").style.display="";
	if(top.document.getElementById("i_left")){
		top.document.getElementById("i_left").style.display="";
	}
	
	top.document.getElementById("Blank1").style.display="";
	top.middle.frameSet.cols="0,0,0,*";
}


/**
 * 陈可可 显示基础库注册对比数据
 * @return
 */
function showBgDetail(){
	//2013-11-27 22:22:36 韩康 如果模板中所有xx为空，则为新注册，则不进行数据对比
	var varJobj = jQuery("td[class!='TitleLine3'][class!='EditInforTdBg'][xx!='空'][xx!='']");
	if(varJobj.length>0){
		//处理如果不存在发生变更的td时，因为页面有不含有xx的td导致页面错误展示的问题   2013-12-12
		var varFlag = false;
		varJobj.each(function(index,objEle){
			if(jQuery(objEle).attr("xx")!=undefined || jQuery(objEle).attr("xx")!=null){
				varFlag = true;
				return varFlag;
			}
		});
		if(varFlag){//如果存在发生变更的td，说明需要反色显示变更数据
			jQuery("td[xx][bg]").each(function (i,varSelect){	
				var xxinfo = jQuery(this).attr("xx");
				var bginfo =  jQuery(this).attr("bg"); 
				if(xxinfo!=bginfo&&(xxinfo!=''||bginfo!='')){
					//this.style.color="#ff0000";
					this.style.cursor="pointer";
					this.title="变更前：("+xxinfo+")";
					this.style.backgroundColor="#FFC8B4";
				}
			});
		}
	}
}
/**
 * 调用蒙层
 * @param id 需要蒙层的区域的ID
 * @param msg 蒙层文字提示
 * @return
 */
function ajax_cover(id,msg){
   var divexist_obj=   jQuery('#'+id+'_loading');
   //增加如果要生成的蒙层存在则重置
	if(divexist_obj!=null&&!divexist_obj!=undefined){
		divexist_obj.remove();	
	}
	
	if(msg==null||msg==""||msg==undefined){
		msg = "";
	}
	rid='#'+id;
	if(jQuery(rid)&&jQuery(rid).css('display')=='none')return;
	height=jQuery(rid).outerHeight(true);
	width=jQuery(rid).outerWidth(true);
	var mainHeight = top.document.body.clientHeight;
	var marginTop = (height-32)/2;
	if(mainHeight>300 && marginTop > mainHeight){
		marginTop = mainHeight/2;
	}
	jQuery(rid).after("<div id='"+id+"_loading' style='z-index:9999' class='ajax_cover'><div class='ajava_cover_img' style='margin-top:"+marginTop+"px;'></div>"+msg+"</div>");
	jQuery(rid+"_loading").height(height);
	jQuery(rid+"_loading").width(width);
	if(jQuery(rid).offset()){
		jQuery(rid+"_loading").css('left',jQuery(rid).offset().left);
		jQuery(rid+"_loading").css('top',jQuery(rid).offset().top);
	}else{
		jQuery(rid+"_loading").css('left',0);
		jQuery(rid+"_loading").css('top',0);
	}
	jQuery(rid+"_loading").css("display","block");
}
/**
* 关闭蒙层
*/
function ajax_cover_remove(id){
	if(!jQuery('#'+id+'_loading') && jQuery(rid).css('display')=='none')return;
	jQuery('#'+id+'_loading').remove();	
}

/**
 * 打开弹出层
 * @param id 弹出层的ID
 * @param width 宽度
 * @param height 高度
 * @param title 标题
 * @param functionName 回调函数
 * @param showWhiteCover 是否有蒙层 只有false不展示蒙层
 * @returns {String} 弹出层的iframe对象ID
 */
function showMsg(id,width,height,title,functionName,showWhiteCover){
	var Popwidth = width || 500;//弹出层的宽度
	var Popheight = height || 250;//弹出层的高度
	var Popleft = (jQuery(top.window).width() - Popwidth) / 2;//弹出层所在的位置left距离
	var Poptop = (jQuery(top.window).height() - Popheight) / 2;//弹出层所在的位置top距离
	if(Popleft<0) Popleft = 0;
	if(Poptop<0) Poptop = 0;
	title=title||"";
	if(top.document.getElementById("xMsg"+id)==undefined || top.document.getElementById("xMsg"+id)==null){
		new top.xWin(id, Popwidth, Popheight, Popleft, Poptop, title, "");
	}
	top.openthediv(id,Popwidth, Popheight, Popleft, Poptop, title, functionName, showWhiteCover);
	return "popmsg"+id;
}
/**
 * 关闭弹出层
 * @param id 需要关闭弹出层的ID
 */
function closeMsg(id){
	top.cls(id);
}
/**
 * 最大化弹出层
 * @param id 需要最大化弹出层的ID
 */
function maxMsg(id){
	top.max(id);
}
/**
 * 调整弹出层大小
 * @param id
 * @param width 弹出层宽度
 * @param height 弹出层高度
 */
function resizeMsg(id,width,height){
	var Popwidth = width || 500;//弹出层的宽度
	var Popheight = height || 250;//弹出层的高度
	var Popleft = (jQuery(top.window).width() - Popwidth) / 2;//弹出层所在的位置left距离
	var Poptop = (jQuery(top.window).height() - Popheight) / 2;//弹出层所在的位置top距离
	if(Popleft<0) Popleft = 0;
	if(Poptop<0) Poptop = 0;
	top.setMsgSize(id,Popwidth,Popheight,Popleft,Poptop);
}

/**
 * 方法名称：showZdyDialog
 * 功能描述：显示对话框
 * 参数说明：varURL 窗口显示的页面路径 
 *           vArguments 传递的参数
 *           varWidth  宽度
 *           varHeight  高度
 * 返回值： 窗口返回的window.returnValue
 * 编写人： 陈建新  2011-9-23
 */
function showZdyDialog(varURL,vArguments,varWidth,varHeight){
	if(vArguments==null||vArguments=="")vArguments=window;
	//var top,left;
  //left=(window.screen.width-varWidth)/2;
  //top=(window.screen.height-varHeight)/2-40;
  var varFeatures = "";
  varFeatures += "dialogHeight:"+varHeight+"px;";
  //varFeatures += "dialogLeft:"+left+";";
  //varFeatures += "dialogTop:"+top+";";
  varFeatures += "dialogWidth:"+varWidth+"px;";
  varFeatures += "help:no;resizable:yes;status:no;scroll:yes";
  //alert(varFeatures);
  return window.showModalDialog(varURL,vArguments,varFeatures);
}

/**
 * unicode编码与中文互转,一般在JS中使用,HTML中会自动显示为中文
 */
var UnicodeUtil = {
	    on: function(str) {
	        var a = [],
	        i = 0;
	        for (; i < str.length;) a[i] = str.charCodeAt(i++);
	        return "&#" + a.join(";&#") + ";"
	    },
	    un: function(str) {
	        return str.replace(/&#(x)?([^&]{1,5});?/g,
	        function(a, b, c) {
	            return String.fromCharCode(parseInt(c, b ? 16 : 10))
	        })
	    }
};

/**
 * 屏蔽页面中不可编辑文本框中的backspace按钮事件
 * 需要使用时：在页面脚本中写上
 * document.onkeydown = keydown;
 * */
function keydown(e) {
    var isie = (document.all) ? true : false;
    var key;
    var ev;
    if (isie){ //IE和谷歌浏览器
        key = window.event.keyCode;
        ev = window.event;
    } else {//火狐浏览器
        key = e.which;
        ev = e;
    }
    if (key == 8) {//IE和谷歌浏览器
        if (isie) {
            if (document.activeElement.readOnly == undefined || document.activeElement.readOnly == true) {
                return event.returnValue = false;
            } 
        } else {//火狐浏览器
            if (document.activeElement.readOnly == undefined || document.activeElement.readOnly == true) {
                ev.which = 0;
                ev.preventDefault();
            }
        }
    }
}
//获取当前浏览器类型及版本
function getBrowser(){
	 var userAgent = navigator.userAgent,   
     rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
     rFirefox = /(firefox)\/([\w.]+)/,   
     rOpera = /(opera).+version\/([\w.]+)/,   
     rChrome = /(chrome)\/([\w.]+)/,   
     rSafari = /version\/([\w.]+).*(safari)/;  
     var browser;  
     var version;  
     var ua = userAgent.toLowerCase();  
     function uaMatch(ua) {  
         var match = rMsie.exec(ua);  
         if (match != null) {  
             return { browser : "IE", version : match[2] || "0" };  
         }  
         var match = rFirefox.exec(ua);  
         if (match != null) {  
             return { browser : match[1] || "", version : match[2] || "0" };  
         }  
         var match = rOpera.exec(ua);  
         if (match != null) {  
             return { browser : match[1] || "", version : match[2] || "0" };  
         }  
         var match = rChrome.exec(ua);  
         if (match != null) {  
             return { browser : match[1] || "", version : match[2] || "0" };  
         }  
         var match = rSafari.exec(ua);  
         if (match != null) {  
             return { browser : match[2] || "", version : match[1] || "0" };  
         }  
         if (match != null) {  
             return { browser : "", version : "0" };  
         }  
     }  
     var browserMatch = uaMatch(userAgent.toLowerCase());  
     return browserMatch.browser; 
}
/*
 * @discription:  清理当前框架下面的所有的页面离开事件
 * @author: 路适远
 * @date: 2014-2-26
 * @params: 
 *  varframe：frame  从哪个框架开始清理页面离开事件
 * @return:
 */
function unbindLeave(varframe){
    var listFrame = varframe.frames;
   	for(var i=0;i<listFrame.length;i++){
   	    var varFrame = listFrame[i];
   	    if(varFrame.unbindCheckLeave!=null){
   	    	varFrame.unbindCheckLeave();
   	    }
   	    var listFa = varFrame.frames;
   	    if(listFa.length>0){
   	      unbindLeave(varFrame);
   	    }
   	}
}
/*
 * @discription:  获取传入的窗口所在的弹出层窗口对象，如果查询不到返回top
 * @author: 路适远
 * @date: 2014-4-2
 * @params: 
 *  in_win  -  传入的窗口对象
 * @return:
 */
function getParentDialog(in_win){
	while(in_win!=top){
		if(in_win.frameElement && in_win.frameElement.lhgDG){
			break;
		}
		in_win = in_win.parent;
	}
	return in_win;
}
var var_Timer;//记录弹出层延时加载timer对象
/*
 * @discription:  处理在弹出层加载完成之后调用传入的function
 * @author: 路适远
 * @date: 2014-4-2
 * @params: 
 *  functionName  -  传入的需要执行的function
 * @return:
 */
function doDialogReady(functionName){
	if(functionName==null) return ;
	/*//获取当前窗口所在的弹出层窗口对象
	var var_element = getParentDialog(window);
	if(var_element == top){
		return ;
	}
	var var_LhgDg = var_element.frameElement.lhgDG;
	if(var_Timer){
		clearTimeout(var_Timer);
	}
	//如果当前窗口在弹出层中，判断弹出窗口是否加载完成
	if(var_LhgDg){
		if(var_LhgDg.isReady()){//弹出层加载完成调用传入的方法
		  if(jQuery){
				jQuery(function(){
					functionName.call();
				});
			}else{
				functionName.call();
			}
		}else{
			//弹出层加载没有完成，延时100ms
			var_Timer = setTimeout(function(){
				doDialogReady(functionName);
			},100);
		}
	}else{*/
		//如果当前窗口不在弹出层中，在页面加载完成后调用
		if(jQuery){
			jQuery(function(){
				functionName.call();
			});
		}else{
			functionName.call();
		}
		
//	}
}

/*
 * @discription:  处理根据弹出页面ID直接赋值跳转路径页面
 * @author: 解盼
 * @date: 2014-4-11
 * @params: 
 *  id  -  弹出页面ID
 *  inSrc - 跳转页面路径
 * @return:
 */
function setDialogPage(id,inSrc){
  var frame = queryFrame(top,"PopDialog"+id);
  if(frame && frame.frameElement!=null){	  
	  frame.frameElement.src = inSrc;
  }
}
/*
 * @discription:  将传入的字符进行URI编码
 * @author: 路适远
 * @date: 2014-4-20
 * @params: 
 *  inStr  - 输入的字符串
 * @return:
 */
function encodeURIEx(in_Str){
	if(in_Str && in_Str!="") {
		in_Str = encodeURIComponent(in_Str.replaceAll("%","％"));
	}
	return in_Str;
}

/*
 * @discription:  弹出CA登陆页面
 * @author: 解盼
 * @date: 2014-4-26
 * @params: 
 *  functionName  - 回调函数名称
 * @return:
 */
function showCaLogin(functionName){
	var var_fullPath="";
	var callName="";
	var xtPath="";
	var caPath="";
	//1、得到当前页面Path
	var_fullPath=getFullPath(window);
	callName=var_fullPath + "." + functionName;
	//2、得到contextPath
	xtPath=getContextPath();
	
	caPath=xtPath + "ca/bjca/CaIndex_.jsp?callName="+callName;
	//alert(caPath);
	var_fullPath.target=doShowDialogX("caLogin",400,280,'CA验证'); 
	setDialogPage("caLogin",caPath);
}

/*
 * @discription:  得到contextPath
 * @author: 解盼
 * @date: 2014-4-26
 * @params: 
 * @return:
 */
function getContextPath() {
	var xtPath="";
	var name = "script/PublicScript.js";
	var scripts = document.getElementsByTagName('script');
	for ( var i = 0; i < scripts.length; i++) {
		var src = scripts[i].getAttribute('src');
		if (src) {
			var index = src.lastIndexOf(name);
			if(index>0){
				xtPath = src.substr(0, index);
				break;
			}
		}
	}
	return xtPath;
}

/*
 * @discription:  obj对象转换为Json字符串
 * @author: 解盼
 * @date: 2014-4-26
 * @params: 
 *  obj  - obj
 * @return:
 */
function objToJson(obj){
	var strJson = "{";
	for( prop in obj ) {
		strJson += "\"" + prop + "\"" + ":" + '"' + obj[prop] + '",';
	}
	if(strJson.indexOf(",")!=-1){
		strJson = strJson.substring(0,strJson.length-1);
	}
	strJson += "}";
	return strJson;
}

/*
 * @discription:  隐藏当前页面
 * @author: 解盼
 * @date: 2014-4-26
 * @params: 
 *  id  - id
 * @return:
 */
function hiddenDialogX(id){
	if(top.document.getElementById("lhgdgCover_"+id)){
		top.document.getElementById("lhgdgCover_"+id).style.display="none";
	}
	if(top.document.getElementById("lhgdlg_"+id)){
		top.document.getElementById("lhgdlg_"+id).style.display="none";
  }
}

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
}
/**
 * 设置cookie 
 * 参数说明：
 * name：cookie的名字
 * value:cookie的值
 * time：cookie有效时间(格式参看getsec)
 */
function setCookie(name,value,time){
	if(time==undefined){
		time = "d30";
	}
	var exp = new Date();
	var strsec = getsec(time); 
	exp.setTime(exp.getTime() + strsec*1); 
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
/**
 * 参数说明：
 * s20是代表20秒 
 * h是指小时，如12小时则是：h12 
 * d是天数，30天则：d30 
 */
function getsec(str){
   var str1=str.substring(1,str.length)*1; 
   var str2=str.substring(0,1); 
   if (str2=="s") { 
        return str1*1000; 
   } else if (str2=="h") { 
       return str1*60*60*1000; 
   } else if (str2=="d") { 
       return str1*24*60*60*1000; 
   } 
} 
//获取cookie值
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]); 
    else 
        return null; 
}

/**
 * 检查是否为空
 * @param val
 * @auto 崔智景
 * @return true为空 false不为空
 */
function isEmpty(val){
	val=$.trim(val);//去空
	if(val==''){
		return true;
	}
	if(val==null){
		return true;
	}
	if(val=='null'){
		return true;
	}
	if(val=='undefined'){
		return true;
	}
	if(val==undefined){
		return true;
	}
	if(!/[^(^\s*)|(\s*$)]/.test(val)){
		return true;
	}
	return false;
}
/**
*项目监控控制按钮操作的JS 修改：2015年11月10日15:16:20 许先达 增加参数判断
2016-06-24 崔智景 增加span监控
*/
function xmjcControl(lb){
	var var_lbbs = "XMJC"
	if(lb!=undefined){
		var_lbbs = lb;
	}
	jQuery("input[xmjk='"+var_lbbs+"']").remove();
	jQuery("a[xmjk='"+var_lbbs+"']").remove();
	jQuery("li[xmjk='"+var_lbbs+"']").remove();
	jQuery("span[xmjk='"+var_lbbs+"']").remove();
}

/**
*项目监控控制按钮操作的JS 针对流程监控所有操作类按钮隐藏  2017-3-13 孙康
*/
function xmjkControl(lb){
	var var_lbbs = "XMJK_ZLC"
	if(lb!=undefined){
		var_lbbs = lb;
	}
	jQuery("input[xmjk_zlc='"+var_lbbs+"']").remove();
	jQuery("a[xmjk_zlc='"+var_lbbs+"']").remove();
	jQuery("li[xmjk_zlc='"+var_lbbs+"']").remove();
	jQuery("span[xmjk_zlc='"+var_lbbs+"']").remove();
}

/**
 * 按钮设置为为不可点击,加上蒙层
 */
 function setBtn_Cover(btn,area){
   setTimeout(function(){btn.disabled=true;btn.value='提交中...'},20);
   if(area!=undefined){
     ajax_cover(area,'保存中...，请稍等......');
   }
   return true;
 }
 
//初始提醒计时器
 if (document.all) {
   try{
    window.attachEvent('onload', top.cstxdsq());
   } catch(e) { }
 }else {
   try{
    window.addEventListener('load', top.cstxdsq(), false);
   } catch(e) { }
 }
