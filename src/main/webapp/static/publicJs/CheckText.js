/* 时间：2015/09/19
 * 功能：校验时间
 * 程序：
 * 修改人：  李名岩
 * 主要验证项目：
 * startDate,开始时间
 * endDate,结束时间
 * msg,信息
 * nowDateCheck 是否跟现在时间做检测
 */
function compareTime(startDate, endDate,msg,DateCompareCheck,CompareDate) {   
 if (startDate.value.length > 0 && endDate.value.length > 0) {   
    var startDateTemp = startDate.value.replace(/\//g,'');   
    var endDateTemp = endDate.value.replace(/\//g,'');   
    var allStartDate=null;
    var allEndDate=null;
    if(startDate.value.length>10){
      allStartDate = startDateTemp.replace(/:/g,'');   
      allEndDate = endDateTemp.replace(/:/g,'');  
    }else{
    	allStartDate=startDateTemp;
    	allEndDate=endDateTemp;
    }
if (allStartDate >= allEndDate) {   
        if(msg!=null){
            popAlert(startDate, msg);
            setFormItemFocus(startDate);
        }else{
    	   popAlert(startDate,"开始时间不能大于结束时间");
           setFormItemFocus(startDate);
        }
        return false;   
} else if(DateCompareCheck){  
		if(startDateTemp>CompareDate){
		  popAlert(startDate,"开始时间不能大于当前时间");
	      setFormItemFocus(startDate);
	      return false;
		}else{
		   return true;   
		}
} else{
    	   
         return true;   
 } 
} else {
	
     return true;   
      }   
}   


/* 时间：2015/09/22
 * 功能：校验当前时间与表单中的时间
 * 程序：
 * 修改人：  李名岩
 * 主要验证项目：
 * startDate,开始时间
 * endDate,结束时间
 * msg,信息
 */
function compareCurrTime(currDate, formDate,msg) {   
  if (currDate.value.length > 0 && formDate.value.length > 0) {   
    var currDateTemp = currDate.value.replace(/\//g,'');   
    var formDateTemp = formDate.value.replace(/\//g,'');   
    var allCurrDate=null;
    var allFormDate=null;
    if(currDate.value.length>10){
      allCurrDate = currDateDateTemp.replace(/:/g,'');   
      allFormDate = formDateTemp.replace(/:/g,'');  
    }else{
    	allCurrDate=currDateTemp;
    	allFormDate=formDateTemp;
    }
    if (allCurrDate >= allFormDate) {   
      popAlert(formDate,msg+"必须大于当前时间");
      setFormItemFocus(formDate);
      return false;   
    } else {   
      return true;   
    }   
  } else {   
    return true;   
  }   
}   
/* 时间：2002/08/27
 * 功能：验证表单函数
 * 程序：
 * 修改人：  祝卫华
 * 修改日期：2003/09/09
 * 修改内容：增加了对正负数的判定内容
 * 主要验证项目：
 * CharType,表单数据类型 "C" 为字符串，"N" 为数字,"D"为日期类型, "M"为必需输入字母 "NorM"数值或字母。
 * MaxLength(最大长度) 整型数字 0表示不限制最大长度
 * Precision(精度) 整型数字 表示保留小数位的长度,0表示是整数
 * DefiniteLengthMark(定长标志) 整型数字   0（定长不允许空允许负）/1（不定长不可空允许负）/2（不定长允许空允许负）/3（定长允许空允许负）
 *                                         4（定长不允许空不允许负）/5（不定长不可空不允许负）/6（不定长允许空不允许负）/7（定长允许空不允许负）
 * Describe(表单描述) 比如：电话号码，用户姓名等等,必须写
 */


  function setFormItemFocus(FormItemName) {
	  try
	  {
		if(!FormItemName.readOnly&&!FormItemName.disabled) {
			if (FormItemName.tagName.toLowerCase() == "select") {
				FormItemName.setAttribute("bg_color", FormItemName.style.backgroundColor);
				FormItemName.style.backgroundColor = "red";
			} else {
				FormItemName.setAttribute("bl_color", FormItemName.style.borderLeftColor);
				FormItemName.setAttribute("br_color", FormItemName.style.borderRightColor);
				FormItemName.setAttribute("bt_color", FormItemName.style.borderTopColor);
				FormItemName.setAttribute("bb_color", FormItemName.style.borderBottomColor);
				FormItemName.style.borderColor = "red";
			}
			FormItemName.focus();
		}
	  }
	  catch (e)
	  {
	  }
  }

 var checkFormItemPop=true;
 var checkmsg=null;
  /**
   *修改人：李名岩
   *修改日期：2016/09/23
   *修改内容：无提醒
   */
 function checkFormItemUnPop(FormItemName,CharType,MaxLength,Precision,DefiniteLengthMark,Describe){
	 var returnObj=new Object();
	checkFormItemPop=false;
	var check=checkFormItem(FormItemName,CharType,MaxLength,Precision,DefiniteLengthMark,Describe);
	returnObj.checkFlag=check;
	returnObj.checkmsg=checkmsg;
	checkFormItemPop=true;
	return	returnObj;
 }
  
 /**
 *修改人：闫志刚
 *修改日期：2004/11/18
 *修改内容：增加了DefiniteLengthMark是5、6、7的长度的信息的校验，修改浮点数的校验，增加对浮点数整数位的长度校验
 */
function checkFormItem(FormItemName,CharType,MaxLength,Precision,DefiniteLengthMark,Describe){

	
	
	
  try {

    var TValue;
    TValue=FormItemName.value;
    //得到文本框去掉头尾空格的值
    TValue=TValue.replace(/(^\s*)|(\s*$)/g, "");
    //去掉文本框头尾空格
    FormItemName.value=TValue;
    //允许空，只要文本框为空返回true
   
    if((DefiniteLengthMark==2||DefiniteLengthMark==3||DefiniteLengthMark==6||DefiniteLengthMark==7)&&(TValue.length==0)) {
      return(true);
    }
    //不允许空，只要文本框为空返回false
    if((DefiniteLengthMark==0||DefiniteLengthMark==1||DefiniteLengthMark==4||DefiniteLengthMark==5)&&(TValue.length==0)) {
	 if(checkFormItemPop){    
      popAlert(FormItemName, Describe+"不允许为空！");
      setFormItemFocus(FormItemName);
	 }else{
		 checkmsg=Describe+"不允许为空！";
	 }
      return(false);
    }
    //不允许负，只要文本框为负数返回false
    if(DefiniteLengthMark==4||DefiniteLengthMark==5||DefiniteLengthMark==6||DefiniteLengthMark==7) {
      if (TValue.indexOf("-") == 0) {
      
      if(checkFormItemPop){
        popAlert(FormItemName, Describe+"不允许为负数！");
		setFormItemFocus(FormItemName);
      }else{
 		 checkmsg=Describe+"不允许为负数！";
      }
        return(false);
      }
    }

     //----------------------------------------
   //检查字母类型
    if(CharType=="M") {
    //检查输入类型，必须为字母

    //ASCII码验证
    var charCode = "";
    for (var i = 0; i < TValue.length; i++) {
        charCode = TValue.charCodeAt(i);
        if ((charCode < 65 && charCode != 65)
            || (charCode > 90 && charCode < 97)
            || charCode > 122) {
        if(checkFormItemPop){
            popAlert(FormItemName, Describe+"项填写必须输入字母，请检查！");
           try {
        		   setFormItemFocus(FormItemName);
           			} catch (ex) {
           		}
        	}else{
        		 checkmsg=Describe+"项填写必须输入字母，请检查！";
            }
           return(false);
        }
    }

    //正则表达式验证
    var ZZ_TValue=TValue.replace(/(^[A-Za-z]+$)/g, "");
    if(ZZ_TValue.length != 0 ){
    	  if(checkFormItemPop){   
		    	popAlert(FormItemName, Describe+"项填写必须输入字母，请检查！");
		           try {
		        	   setFormItemFocus(FormItemName);
		        } catch (ex) {
		        }
        }else{
        		checkmsg=Describe+"项填写必须输入字母，请检查！";
        }
        return(false);
     }

   }
    
    //检查字母类型
   if(CharType=="NorM") {
    //检查输入类型，必须为字母
   var ZZ_TValue=TValue.replace(/(^[0-9a-zA-Z]+$)/g, "");
	    if(ZZ_TValue.length != 0 ){
	   if(checkFormItemPop){
           popAlert(FormItemName, Describe+"项填写必须输入字母或数字，请检查！");
	        try {
	        	   setFormItemFocus(FormItemName);
	        } catch (ex) {
	        }
        }else{
        		checkmsg=Describe+"项填写必须输入字母或数字，请检查！";
        }
        return(false);
     }
   }
    
    
    
   //--------------------------------------
    //检查字符串(长度和定长)。
    if(CharType=="C" || CharType=="M"||CharType=="NorM") {
      var bytesCount = 0;
      for (var i = 0; i < TValue.length; i++){
        var cur_char = TValue.charAt(i);
    	 if (/^[\u4e00-\u9fa5]+$/.test(cur_char)) {//匹配utf-8中文字符或者中文符号
    	  bytesCount += 3;
      }else{
    	  bytesCount += 1;
        }
      }
      if((DefiniteLengthMark==0||DefiniteLengthMark==3||DefiniteLengthMark==4||DefiniteLengthMark==7) && getStrLength(TValue)!=MaxLength) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"规定长度是"+MaxLength+"个字符，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"规定长度是"+MaxLength+"个字符，请检查！";
          }
        return(false);
      }
      if(MaxLength!=0&&(getStrLength(TValue)>MaxLength||bytesCount>4000) && (DefiniteLengthMark==1||DefiniteLengthMark==2||DefiniteLengthMark==5||DefiniteLengthMark==6)) {
    	 if(checkFormItemPop){
    	  popAlert(FormItemName, Describe+"超过最大长度，允许的最大长度是"+MaxLength+"个字符！");
    	  setFormItemFocus(FormItemName);
    	 }else{
     		checkmsg=Describe+"超过最大长度，允许的最大长度是"+MaxLength+"个字符！";
         }
        return(false);
      }
    }
    //检查数字类型(长度，精度，定长)
    if(CharType=="N") {
      //检查整型数字
      if(Precision==0&&!isInteger(TValue)) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项填写必须输入整数，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项填写必须输入整数，请检查！";
          }
        return(false);
      }
      if(Precision==0&&isInteger(TValue)&&MaxLength!=0&&(DefiniteLengthMark==0||DefiniteLengthMark==3||DefiniteLengthMark==4||DefiniteLengthMark==7)&&getStrLength(TValue)!=MaxLength) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项长度必须是"+MaxLength+"，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项长度必须是"+MaxLength+"，请检查！";
          }
        return(false);
      }
      if(Precision==0&&isInteger(TValue)&&MaxLength!=0&&(DefiniteLengthMark==1||DefiniteLengthMark==2||DefiniteLengthMark==5||DefiniteLengthMark==6)&&getStrLength(Math.abs(TValue)+'')>MaxLength&&TValue<0) {
    	  if(checkFormItemPop){
	        popAlert(FormItemName, Describe+"项输入整数长度不能超过"+MaxLength+"，请检查！");
	        setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项输入整数长度不能超过"+MaxLength+"，请检查！";
          }
        return(false);
      }else if(Precision==0&&isInteger(TValue)&&MaxLength!=0&&(DefiniteLengthMark==1||DefiniteLengthMark==2||DefiniteLengthMark==5||DefiniteLengthMark==6)&&getStrLength(TValue)>MaxLength){
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项输入整数长度不能超过"+MaxLength+"，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项输入整数长度不能超过"+MaxLength+"，请检查！";
          }
	      return(false);
      }
      //检查整型数字结束

      //检查实数
      if(Precision!=0&&!isFloat(TValue)) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项必须输入数字，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项必须输入数字，请检查！";
          }
        return(false);
      }
      if(Precision!=0&&isFloat(TValue)&&MaxLength!=0&&(DefiniteLengthMark==0||DefiniteLengthMark==3||DefiniteLengthMark==4||DefiniteLengthMark==7)&&getStrLength(TValue.replace("." , ""))!=MaxLength) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字");
    		  //alert(Describe+"项输入数字长度必须是"+MaxLength+"位，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字";
          }
        return(false);
      }
      if(Precision!=0&&isFloat(TValue)&&MaxLength!=0&&(DefiniteLengthMark==1||DefiniteLengthMark==2||DefiniteLengthMark==5||DefiniteLengthMark==6)&&getStrLength(TValue.replace("." , "")) > MaxLength) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字");
    		  //popAlert(FormItemName, Describe+"项输入数字长度不能超过"+MaxLength+"位，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字";
          }
        return(false);
      }
      if(Precision!=0&&isFloat(TValue)&&!checkPrecision(TValue,Precision)) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字");
    		  // alert(Describe+"项输入数字的小数部分不能超过"+Precision+"位，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字";
          }
        return(false);
      }
      if(Precision!=0&&isFloat(TValue)&&!checkZSBF(TValue,MaxLength,Precision)) {
    	  if(checkFormItemPop){
    		  popAlert(FormItemName, Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字");
    		  // alert(Describe+"项输入数字的整数部分不能超过"+(MaxLength-Precision)+"位，请检查！");
    		  setFormItemFocus(FormItemName);
    	  }else{
      		checkmsg=Describe+"项输入数字报错，整数位最多：" + (MaxLength-Precision) + "位；小数位最多：" + Precision + "位；共：" + MaxLength + "位有效数字";
          }
        return(false);
      }

    }
    /*验证日期类型张显达书写于2003年1月5日*/
    if(CharType=="D") {
      //首先整理日期的格式
      var i;
      var strYear;
      var strMonth;
      var strDate;

	  if (TValue.match("^([12]\\d{3}/[01]\\d/[0123]\\d)$")!=null||TValue.match("^([12]\\d{3}/[01]\\d/[0123]\\d\\s[012]\\d:[0-5]\\d)$")!=null
		  ||TValue.match("^([12]\\d{3}/[01]\\d/[0123]\\d\\s[012]\\d:[0-5]\\d:[0-5]\\d)$")!=null){
		  if (MaxLength!=0) {
		    var NTValue = TValue.replace(/\//g,"");
		    NTValue = NTValue.replace(/\s/g,"");
		    NTValue = NTValue.replace(/:/g,"");
		    if (getStrLength(NTValue)>MaxLength) {
		    	  if(checkFormItemPop){	
		    		  popAlert(FormItemName, Describe+"超过了允许的长度！");
			    	setFormItemFocus(FormItemName);
		    	  }else{
		        		checkmsg=Describe+"超过了允许的长度！";
		          }
			    return false;
		    }
		  }
	  } else if (TValue.match("^([12]\\d{3}[01]\\d[0123]\\d)$")!=null||TValue.match("^([12]\\d{3}[01]\\d[0123]\\d[012]\\d[0-5]\\d)$")!=null
		  ||TValue.match("^([12]\\d{3}[01]\\d[0123]\\d[012]\\d[0-5]\\d[0-5]\\d)$")!=null){
        if (MaxLength!=0) {
			if (getStrLength(TValue)>MaxLength) {
				  if(checkFormItemPop){
					  popAlert(FormItemName, Describe+"超过了允许的长度！");
					  setFormItemFocus(FormItemName);
				  }else{
		        		checkmsg=Describe+"超过了允许的长度！";
			        }
			    return false;
		    }
		  }
	  } else {
		  if(checkFormItemPop){
			  popAlert(FormItemName, Describe+"格式不合法");
			  setFormItemFocus(FormItemName);
		  }else{
      		checkmsg=Describe+"格式不合法";
	        }
		return false;
	  }
    }

    backBorderColor(FormItemName);
    checkmsg=null;
    return(true);
  } catch( e ) {
    alert(Describe+"发生异常错误，请检查！");
    var blFlag=confirm("忽略" + Describe + "的错误吗？可能会造成数据不完整或者错误！" );
    if ( blFlag == true ) {
    checkmsg=null;
      return ( true );
    } else {
      return(false);
    }
  }
}

function backBorderColor(item) {
	if (item.tagName.toLowerCase() == "select") {
		item.style.backgroundColor = "";//item.getAttribute("bg_color");
	} else {
		item.style.borderLeftColor = item.getAttribute("bl_color");
		item.style.borderRightColor = item.getAttribute("br_color");
		item.style.borderTopColor = item.getAttribute("bt_color");
		item.style.borderBottomColor = item.getAttribute("bb_color");
	}
}

/* 得到字符串的长度  */
/*
function getStrLength(str)
{
 var sLength,i;
 sLength=0;
 for(i=0;i<str.length;i++)
 {
  if(Math.abs(str.charCodeAt(i))<=255)
   sLength=sLength+1;
  else
   sLength=sLength+2;
 }
 return(sLength);

}*/

/* 得到字符串的长度
* 修改：
* 时间：2002/08/27
*/
function getStrLength(str)
{
 /* var winnt_chinese,sLength,i;
  var TestString="信源";
  if  (TestString.length==2){
    winnt_chinese =true;
  }else{
    winnt_chinese =false;
  }
  if (winnt_chinese){
    var l, t, c,kk;
    l = str.length;
    t = l;
    for (kk=0;kk<l;kk++)
    {
      c = str.charCodeAt(kk);
      if (c < 0){
        c = c + 65536;
      }
      if (c > 255){
        t = t + 1;
      }
    }
    sLength = t;
  }else{
    sLength = str.length;
  }
  return(sLength);*/
  return str.length;
}


/* 验证整数  */
function isInteger(str)
{
  if(isNaN(str))
  {
    return(false);
  }
  else
  {
    if(str.search("[.*|+]")!=-1)
    {
      return(false);
    }
  }
  return(true);
}
/* 验证实型数字  */
function isFloat(str)
{
  //alert(isNaN(parseFloat(str)));
//  return(!isNaN(parseFloat(str)));
  return(!isNaN(str));
}
/* 验证精度  */
function checkPrecision(str,n) {
  try{
    var tureorfalse,i,PrecisionLength;
    PrecisionLength=0;
    if(!isNaN(parseFloat(str))) {
      for(i=0;i<str.length;i++) {
        if(str.charAt(i)==".")  {
        PrecisionLength=str.length-i-1;
        break;
      }
    }
    } else {
      return(false);
    }
    if(PrecisionLength<=n) {
      return(true);
    } else {
      return(false);
    }
  } catch( e ){
    return (false);
  }
}

/**
*编写人：闫志刚
*编写日期：2004/11/18
*功能：校验浮点数整数部分长度
*/
function checkZSBF(str,MaxLength,Precision) {
	try
	{
		var Zsbflength;
		if (str.indexOf(".")!=-1) {
          Zsbflength = str.indexOf(".");
		} else {
		  Zsbflength = str.length;
		}
		if (Zsbflength > MaxLength - Precision) {
			return false;
		} else {
		  return true;
		}
	}
	catch (e)
	{
		return (false);
	}
}


/* 增加：
* 时间：2002/09/16
*/

/* 检查两文本框不能同时为空
* 不同时为空为true 反之为false并提示
*/
function checkNotEmptySameTime(text1Name,text2Name,text1Describe,text2Describe){
  if (text1Name.value==""&&text2Name.value==""){
    alert(text1Describe+"和"+text2Describe+"不能同时为空！");
    return false;
  }
  else{return true;}
}
/* 检查两文本框同时只能填写一个*/
function checkWriteOneSameTime(text1Name,text2Name,text1Describe,text2Describe){
  if (text1Name.value!=""&&text2Name.value!=""){
    alert(text1Describe+"和"+text2Describe+"不能同时填写！");
    return false;
  }
  else{
    return true;
  }
}

/*得去掉开头所有的0的字符串，返回值为字符串*/
/*王琳 2003/4/10*/
function getDelLeftZeroStr(str){
  var strReturnvalue="";
  var i=0;
  var strTempvalue="";
  var blHaveCheckNotZero=false;

  if(isNaN(str)) {
    return("");
  }else{
    for (i=0;i<str.length;i++){
      //逐位取值
      strTempvalue=str.substring(i,i+1);
      if (strTempvalue!="0"){
            strReturnvalue=strReturnvalue+strTempvalue;
                blHaveCheckNotZero=true;
          }
          //只要检测到不是0的字符就切断判断，返回字符串
          if (blHaveCheckNotZero){
            strReturnvalue=strReturnvalue+str.substring(i+1,str.length);
            break;
          }
    }
  }
  return(strReturnvalue);
}
/*
 *编写：乔爱国
 *功能：校验文本输入域(textarea)的长度
 *输入：textarea，文本输入域的id
 *      length，文本输入域的最大长度,小于一时,不限制文本长度
 *      msg,提示信息中的文本域名称
 *      hint,用于在页面显示可输入字符个数的input句柄
 *      type,0/null 可为空，1-不可为空
 *闫志刚 2008/04/17 修改：修复length比较大时ie报是否终止js运行的提示
 */

function CheckTextarea(textarea,length,msg,hint,type) { // v2.0
  if(length<=0) {
    return;
  }
  if(type==1 && (textarea.value==null || textarea.value=="")){//校验是否为必填
	 popAlert(textarea, msg+"不能为空!");
	 setFormItemFocus(textarea);
	 return false;
  }
  try {
    var charLength = getStrLength(textarea.value);
    if(charLength>length*1) {
      popAlert(textarea, "字符数超过限制："+msg+"的最大长度为"+length+"个字符！");
      setFormItemFocus(textarea);
	  var i=0;
	  var j;
	  for (j=textarea.value.length;i<charLength-length;j--) {
	    i += getStrLength(textarea.value.substring(j-1,j));
	  }
      textarea.value=textarea.value.substring(0,j);
	  if(hint!=null)
	    hint.value=length-getStrLength(textarea.value);
	  return false;
    } else {
      if(hint!=null)
        hint.value=length-getStrLength(textarea.value);
      return true;
    }
  } catch(E) {
    alert(E);
  }
}

/*
 *编写：李晓义
 *功能：校验页面文件上传控件的文件扩展名是否为指定格式
 *输入：listExt : 扩展名的数组，如为null,则为默认的gif/jpg/ico格式
 *      srchStr : 页面文件上传文件的绝对路径名
 *输出：如果匹配，返回true; 否则返回false
 *
 */
function fileIsMatched(listExt, id) {
  var i = 0;
  var reg = new RegExp();
  var re = "^([a-zA-Z]:){1}(\\\\[^\\\\/:\\*\\?\"<>\\|]*)*\\\\[^\\\\/:\\*\\?\"<>\\|]*\\.(";
  //默认的符合要求的文件扩展名
  var fileExt = "gif jpg ico";;
  var srchStr = id.value;
  //允许不填写文件
  if (srchStr == "") {
    return true;
  }
  //根据制定的文件扩展名构造正则表达式的扩展名部分
  if (listExt != null) {
    for (i = 0; i < listExt.length; i++) {
      re += listExt[i] + "|";
    }
    fileExt = listExt.toString();
  } else {
   re += "gif|ico|jpg";
  }
  re += ")$"
  reg.compile(re);
  if (srchStr.match(reg) != null) {
    return true;
  } else {
  	alert("请检验图标文件！\n\n提示：指定文件格式为：" +
  	      fileExt + "\n      不能含有空格" +
  	                "\n      不能含有下列字符: \\,/,:,*,?,\",<,>,|");
    id.value = "";
    setFormItemFocus(id);
  	return false;
  }
}

/*
 *编写：
 *功能：去除表单中文本框和文本域的内容 两端的空格
 *输入：表单名称
 *
 *输出：
 *
 */
 function removeSpace(Form) {
   var allElements = Form.elements;
   var count = allElements.length;
   for (var i = 0; i < count; i++) {
     if (allElements[i].type == "text" || allElements[i].type == "textarea") {
       allElements[i].value = allElements[i].value.replace(/(^\s*)|(\s*$)/g, "");;
     }
   }
 }

 /* 时间：2005/01/10
 * 功能：验证表单file项文件路径的合法性
 * 程序：
 * 修改人： 闫志刚
 * 修改日期：
 * 修改内容：
 * 主要验证项目：
 * MaxLength (最大长度) 整型数字 0表示不限制最大长度
 * CheckType  1  表示校验整个文件路径的最大长度不超过MaxLength，并且不许为空
 *            2  表示校验整个文件路径的最大长度不超过MaxLength，并且可为空
 *            3 表示校验文件名的最大长度不超过MaxLength，并且不许为空
 *            4 表示校验文件名的最大长度不超过MaxLength，并且可为空
 * FileType  文件的格式  可以是多种格式时以“|”连接，如："jpg|gif|hbm",不限制类型是".*"
 * Describe(表单描述) 比如：杆塔图片等等,必须写
 */
 function checkFile (FormItemName,MaxLength,CheckType,FileType,Describe) {
   try {
	   var TValue;
       TValue=FormItemName.value;
       //得到文本框去掉头尾空格的值
       TValue=TValue.replace(/(^\s*)|(\s*$)/g, "");
	   TValue=TValue.replace(/\//g, "\\");
       //去掉文本框头尾空格
       try{
	       FormItemName.value=TValue;
       }catch(e){}
	   //允许空，只要文本框为空返回true
	   if ((CheckType==2||CheckType==4)&&TValue.length==0) {
		   backBorderColor(FormItemName);
		   return (true);
	   }
	   //不允许空，只要文本框为空返回false
	   if ((CheckType==1||CheckType==3)&&TValue.length==0) {
		   popAlert(FormItemName, Describe+"不允许为空！");
           setFormItemFocus(FormItemName);
		   return (false);
	   }
	   //检查长度
	   TValue = TValue.replace(/[^x00-xff]/g,"aa");
	   if ((CheckType==1||CheckType==2)&&TValue.length>MaxLength) {
		   popAlert(FormItemName, Describe+"超过最大长度，允许的最大长度是"+MaxLength+"！");
           setFormItemFocus(FormItemName);
		   return (false);
	   }
	   if ((CheckType==3||CheckType==4)) {
          var index = TValue.lastIndexOf("\\");
		  var fileNameLength = TValue.length - index - 1;
		   if (fileNameLength>MaxLength) {
		     popAlert(FormItemName, Describe+"项的文件名超过最大长度，允许的最大长度是"+MaxLength+"！");
             setFormItemFocus(FormItemName);
		     return (false);
		   }
	   }
	  
       // 校验路径及文件格式
	   var reg = new RegExp();
       var re = "^([a-zA-Z]:){1}(\\\\[^\\\\/:\\*\\?\"<>\\|]*)*\\\\[^\\\\/:\\*\\?\"<>\\|]+\\.(";
       if(FormItemName.value.length>2&&FormItemName.value.substring(0,2)=="\\\\")  {
	     re = "^([\\\\]){1}(\\\\[^\\\\/:\\*\\?\"<>\\|]*)*\\\\[^\\\\/:\\*\\?\"<>\\|]+\\.(";
	   }
	   re += FileType.toLowerCase();
	   re += ")$"
       reg.compile(re);
	   TValue = FormItemName.value;
	   var kzmIndex = TValue.lastIndexOf(".") + 1;
	   var strKzm = TValue.substr(kzmIndex);
       TValue = TValue.substr(0,kzmIndex) + strKzm.toLowerCase();
	   if (TValue.match(reg) != null) {
		 backBorderColor(FormItemName);
         return true;
       } else {
		   re = "^(" + FileType.toLowerCase() + ")$";
           reg.compile(re);
		   if (strKzm.match(reg)!=null) {
			   popAlert(FormItemName, "请检验"+Describe+"！\n\n提示：指定文件路径" +
  	                 "不能含有下列字符: \\,/,:,*,?,\",<,>,|");
		   } else {
			   popAlert(FormItemName, "请检验"+Describe+"！\n\n提示：指定文件格式应为：\""+FileType.replace(/\|/g,"或")+"\"！");
		   }
         setFormItemFocus(FormItemName);
  	     return false;
       }
   } catch(e) {
	   alert(Describe+"发生异常错误，请检查！\n\n提示："+e.description);
   }
 }
 /**
  * 身份证简单验证版本  2017-4-12 孙康
  * @param code
  */
 function IdentityCodeValid(code) {
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var pass= true;
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(code) === false)  
   {  
	   pass=false;  
   }  
   return pass;
}

/*
    功能：校验身份证
    我国现行居民身份证是全国统一编号，由十五位阿拉伯数字组成，每个公民是一人一号，同年、同月、同日所出生的按地区人数，按县固定次序进行合理分配顺序号代码。做到不重、不漏、不错。编号排列的含义是：
    1、第l一6位数为行政区划代码；
    2、第7—12位数为出生日期代码；
    3、第13---15位数为分配顺序代码；
    (1)、行政区划代码，是指公民第一次申领居民身份证时的常住户口所在地的行政地区。
    (2)、出生日期代码，第7—8位数代表年份(年份前面二位数省略)，第9—10位数代表月份(月份为l位数的前面加零)。第11一12位数代表日期(日期为1位数的前面加零)。
    (3)、分配顺序代码，是按人口数统一合理分配以固定顺序给予每个人的顺序号，最末一位数是奇数的分配给男性，偶数分配给女性。
    新式身份证号码是否还和旧式身份证号码一样，男的尾号为单数，女的尾号为双数？
        国务院规定，自1999年10月1日起在全国建立和实行公民身份证号码制度。
        公民身份证号码按照GB11643—1999《公民身份证号码》国家标准编制，由18位数字组成：前6位为行政区划分代码，第7位至14位为出生日期码，第15位至17位为顺序码，第18位为校验码。
         第18位号码是校验码，目的在于检测身份证号码的正确性，是由计算机随机产生的，所以不再是男性为单数，女性为双数。
*/
/*
  参数说明：iden 身份证控件
            DefiniteLengthMark: 标志可否为空。0可以为空，1为必填，默认为0
*/
function CheckShenFZ(iden,DefiniteLengthMark){
	var temp = iden.value;
	temp = temp.replace(/^\s*|\s*$/g,"");
	iden.value = temp;
	var myLength;
	if (DefiniteLengthMark == 0 || DefiniteLengthMark == 1) {
	  myLength = DefiniteLengthMark;
	} else {
		myLength = 0;
	}
	if (myLength == 0 && temp.length < 1) {
		backBorderColor(iden);
		return true;
	}

	var len = temp.length, re;
	if (len == 15 || len == 18) {
		re = new RegExp(/^((\d{15})$)|(\d{6}(19|20)\d{9}(\d|X))$/);
		if(!temp.match(re)){
		   alert("身份证里面的出生年份不对或者最后一位必须是大写X");
		   setFormItemFocus(iden);
		   return false;
		}
	} else {
		alert("身份证号码输入的数字长度不对！");
		return false;
	}
  if (temp.length == 15) {
   	//旧版身份证
    if (!(/^\d{15}$/.test(temp))) {
    	alert("15位身份证号码只能含有数字，请检查！");
    	setFormItemFocus(iden);
    	return false;
    }else{
       if(!isDate("19" + temp.substring(6, 8), temp.substring(8, 10), temp.substring(10, 12))) {
		   alert("身份证号 " + temp + " 里出生日期(" + temp.substring(6, 12) + ")不对！");
		   setFormItemFocus(iden);
		   return false;
		}
    }
	} else if (temp.length == 18) {
	  //新版身份证
	  if(!(/^\d{17}\w$/.test(temp))) {
	  	alert("18位身份证号码出错，请检查！" + "\n"
	  	      + "提示：身份证号码前17位只能为数字");
	  	setFormItemFocus(iden);
	  	return false;
	  }else {
            if (!isDate(temp.substring(6, 10), temp.substring(10, 12), temp.substring(12, 14))) {
				alert("输入的身份证号 " + temp + " 里出生日期(" + temp.substring(6, 14) + ")不对！");
				setFormItemFocus(iden);
				return false;
			}
	  }
  } else {
	  alert("身份证号码长度不正确！");
	  setFormItemFocus(iden);
	  return false;
  }
  backBorderColor(iden);
  return true;
}
/**
 * 身份证
 *
 * 参数 sfz 身份证表单项
 * 参数 bt 是否必填: 1-必填, 0-可为空
 */
function checkIdcard(sfz, bt) {
    var idcard = sfz.value;
	var idcardlength = sfz.value.length;
	if(bt == 0 && idcardlength == 0) {
		backBorderColor(sfz);
	    return true;
	} else if(idcardlength == 0) {
		popAlert(sfz, Describe+"不允许为空！");
		//alert("身份证号码不能为空!");
		setFormItemFocus(sfz);
	    return false;
	}
	//var Errors=new Array(
	//	"验证通过!",
	//	"身份证号码位数不对!",
	//	"身份证号码出生日期超出范围或含有非法字符!",
	//	"身份证号码校验错误!",
	//	"身份证地区非法!"
	//);
	var Errors=new Array(
		"验证通过!",
		"身份证号码不正确!",
		"身份证号码不正确!",
		"身份证号码不正确!",
		"身份证号码不正确!"
	);
	if(idcardlength != 15 && idcardlength != 18) {
		popAlert(sfz, Describe+Errors[1]);
		//alert(Errors[1]);
		setFormItemFocus(sfz);
	    return false;
	}
	var area = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
    var Y,JYM;
	var S,M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	if(area[parseInt(idcard.substr(0,2))]==null) {
		popAlert(sfz, Describe+Errors[4]);
	    //alert(Errors[4]);
	    setFormItemFocus(sfz);
	    return false;
	}
	if(idcardlength == 15) {
		if ((parseInt(idcard.substr(6,2))+1900) % 4 == 0 ||
		    ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 &&
		     (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )) {
		    ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
		} else {
		    ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;
		}
		if(!ereg.test(idcard)) {
			popAlert(sfz, Describe+Errors[2]);
		    //alert(Errors[2]);
		    setFormItemFocus(sfz);
		    return false;
		}
	} else if(idcardlength == 18) {
		if(parseInt(idcard.substr(6,4)) % 4 == 0 ||
		   (parseInt(idcard.substr(6,4)) % 100 == 0 &&
		    parseInt(idcard.substr(6,4))%4 == 0)) {
		    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
		} else {
		    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
		}
		if(ereg.test(idcard)) {
			S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
				+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
				+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
				+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
				+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
				+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
				+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
				+ parseInt(idcard_array[7]) * 1
				+ parseInt(idcard_array[8]) * 6
				+ parseInt(idcard_array[9]) * 3 ;
			Y = S % 11;
			M = "F";
			JYM = "10X98765432";
			M = JYM.substr(Y,1);
			if(M != idcard_array[17]) {
				popAlert(sfz, Describe+Errors[3]);
			    //alert(Errors[3]);
			    setFormItemFocus(sfz);
		    	return false;
			}
		} else {
			popAlert(sfz, Describe+Errors[2]);
		    //alert(Errors[2]);
		    setFormItemFocus(sfz);
		    return false;
		}
	}
	backBorderColor(sfz);
	return true;
}

    //判断是否合格的日期
	function isDate(year, month, day) {
		//alert("2: "+year + " " + month  + " " + day);
		year = Number(year);
		month = Number(month);
		day = Number(day);
		//alert("3: "+year + " " + month  + " " + day);
		var test = new Date(year, month - 1, day);
		//alert(test.getFullYear() + " "+test.getMonth() +"  "+test.getDate());
		if ((test.getFullYear() == year) && (month == test.getMonth() + 1) && (day == test.getDate()))
			return true;
		else
			return false;
	}

/*
  验证邮件地址是否合法
*/
/*
  参数说明：email 邮件地址
           DefiniteLengthMark: 标志可否为空。0可以为空，1为必填，默认为0
*/
function   CheckEamil(email,DefiniteLengthMark,MaxLength,Describe){
    Describe = Describe||"电子邮箱";
	var temp = email.value;
	temp = temp.replace(/^\s*|\s*$/g,"");
	email.value = temp;
	if(DefiniteLengthMark == 0 && temp.length ==0){
	   backBorderColor(email);
	   return true;
	}
	if(DefiniteLengthMark == 1 && temp.length <=0){
	   popAlert(email,Describe+"不能为空！");
	   setFormItemFocus(email);
	   return false;
	}else if(DefiniteLengthMark == 0 && temp.length == 0){
	   backBorderColor(email);
	   return true;
	}
	if(getStrLength(email.value)>MaxLength) {
	  popAlert(email,Describe+"项输入整数长度不能超过"+MaxLength+"，请检查！");
	  setFormItemFocus(email);
	  return false;
	}
    if(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(temp)) {
    	  popAlert(email,Describe+"格式不正确！");
          setFormItemFocus(email);
          return false;
    }
    backBorderColor(email);
    return true;
}

/*
  验证手机号码是否合法
*/
/*
  参数说明：mobile 手机号码
           DefiniteLengthMark: 标志可否为空。0可以为空，1为必填，默认为0
           Reg:传入校验的正则表达式，可以为空 2014-02-27黄敏 
*/

function  CheckMobile(mobile,DefiniteLengthMark,Describe,Reg){
   Describe = Describe||"手机号码";
	var temp = mobile.value;
	temp = temp.replace(/^\s*|\s*$/g,"");
	mobile.value = temp;
	if(DefiniteLengthMark == 0 && temp.length ==0){
		backBorderColor(mobile);
	   return true;
	}
	if(DefiniteLengthMark == 1 && temp.length <=0){
	   popAlert(mobile,Describe+"不能为空！");
	   setFormItemFocus(mobile);
	   return false;
	}
	if(temp.match("^(1\\d{10})$")==null){
	   popAlert(mobile,"请输入正确的"+Describe+"!");
       setFormItemFocus(mobile);
       return false;
    }
	if(Reg!=null){//用传入的正则表达式进行校验  
		var re = new RegExp(Reg);
		if(!re.test(temp)){
			popAlert(mobile,"请输入正确的"+Describe+"!");
			setFormItemFocus(mobile);
		  	return false;
		}
	}
	backBorderColor(mobile);
   return true;
}

/**
 * 校验银行帐号
 * @param accout 帐号表单
 * @param notNull 是否必填 0：允许为空，1：必填
 * @param maxLength 最大长度
 * @param describe 表单描述
 * @return 是否校验通过
 */
function checkBankAccount(accout, notNull, maxLength, describe) {
   	describe = describe || "帐号";
	var temp = accout.value;
	temp = temp.replace(/^\s*|\s*$/g,"");
	accout.value = temp;
	if(notNull == 0 && temp.length == 0){
		backBorderColor(accout);
	  	return true;
	}
	if(notNull == 1 && temp.length <= 0){
	   alert(describe + "不能为空！");
	   setFormItemFocus(accout);
	   return false;
	}
	if(temp.length > maxLength){
	   alert(describe + "超过最大长度，允许的最大长度为" + maxLength + "！");
	   setFormItemFocus(accout);
	   return false;
	}
	var regu = "^[a-zA-Z0-9]+$";
	var re = new RegExp(regu);
	if (!re.test(temp)) {
		alert(describe + "只能输入数字或字符！");
	   	setFormItemFocus(accout);
		return false;
	}
	backBorderColor(accout);
   	return true;
}

/**
 * 判断一下str中是否含有全角的字符或汉字，含有则返回true，不含有则返回false
 */
function haveQuanjiao(str) {
  var tempStr = str.replace(/(^\s*)|(\s*$)/g, "");
  return getStrLength(tempStr)!=tempStr.length;
}

/*
 * @discription: 当前浏览器的相关方法，判断浏览器类型
 * @author: wanglijun
 * @date: 2011/11/25
 * @params:
 * @return:
 */
var Browser = {
	isChrome: function() {
		return navigator.userAgent && navigator.userAgent.indexOf("Chrome") > -1;
	},

	isFirefox: function() {
		return document.implementation && navigator.userAgent.indexOf("Firefox") > -1;
	},

	isIE: function() {
		return window.ActiveXObject && navigator.userAgent.indexOf("MSIE") > -1;
	},

	isIEMobile: function() {
		return window.ActiveXObject && navigator.userAgent.indexOf("IEMobile") > -1;
	},

	isKonqueror: function() {
		return navigator.userAgent && navigator.userAgent.indexOf("Konqueror") > -1;
	},

	isOpera: function() {
		return navigator.userAgent && navigator.userAgent.indexOf("Opera") > -1;
	},

	isOperaMini: function() {
		return navigator.userAgent && navigator.userAgent.indexOf("Opera Mini") > -1;
	},

	isOperaMobile: function() {
		return navigator.userAgent && navigator.userAgent.indexOf("Opera Mobi") > -1;
	},

	isSafari: function() {
		return navigator.userAgent && navigator.vendor && navigator.userAgent.indexOf("Safari") > -1;
	}
};

/*
 * @discription: 获取当前web应用的ContextPath
 * @author: wanglijun
 * @date: 2011/11/25
 * @params:
 * @return:
 *  {string} contextPath
 */
function getScriptLocation() {
	var location = "";
	var name = "static/publicJs/CheckText.js";

	var scripts = document.getElementsByTagName('script');
	for (var i = 0; i < scripts.length; i++) {
		var src = scripts[i].getAttribute('src');
		if (src) {
			var index = src.lastIndexOf(name);
			// set path length for src up to a query string
			var length = src.lastIndexOf('?');
			if (length < 0) {
				length = src.length;
			}
			// is it found, at the end of the URL?
			if ((index > -1) && (index + name.length == length)) {
				location = src.slice(0, length - name.length);
				break;
			}
		}
	}
	return location;
}

/*
 * @discription: 加载依赖js的方法
 * @author: wanglijun
 * @date: 2011/11/25
 * @params:
 *  src - {string} js文件在web应用中得相对路径
 * @return:
 */
function include(src) {
	var path = getScriptLocation() + src;
	if (Browser.isIE() || Browser.isSafari()) {
		document.write("<script src=\"" + path + "\" type=\"text/javascript\"></script>");
	} else {
		var s = document.createElement("script");
		s.setAttribute("type", "text/javascript");
		s.src = path;
		var head = document.getElementsByTagName("head").length ?
			document.getElementsByTagName("head")[0] : document.body;
		head.appendChild(s);
	}
};

/*
 * @discription: 显示验证结果浮动层popHInt
 * @author: wanglijun
 * @date: 2011/11/25
 * @params:
 *  item - {Element} 被验证元素
 *  desc - {string} 提示信息
 * @return:
 */
function popAlert(item, desc) {
	

	 
		Pophint.popHint(item, desc);
	

}

/**
 * 判断固定电话、传真号码、手机号码
 * MaxLength，最大长度
 * num=0,允许为空，1为不允许为空
 */
function isPhone(phone,MaxLength,num,name){
	var TValue=phone.value;
    //得到文本框去掉头尾空格的值
    TValue=TValue.replace(/(^\s*)|(\s*$)/g, "");
    if(TValue.length>0){
		var p1 = /^(([0\+]\d{2,3}(-)?)?(0\d{2,3})(-)?)?(\d{7,8})((-)?(\d{3,}))?$/;
		if(!p1.test(TValue)){
			popAlert(phone,name+"的格式不正确！");
			setFormItemFocus(phone);
			return false;
		}else{
		    backBorderColor(phone);
		    if(MaxLength!=0&&getStrLength(TValue)>MaxLength){
		    	popAlert(phone,name+"最大长度是"+MaxLength+"！");
				setFormItemFocus(phone);
				return false;
			}
			return true;
		}
	}
	if(num==0){
		return true;
	}else{
		popAlert(phone,name+"不允许为空！");
		setFormItemFocus(phone);
		return false;
	}
}
/**
 * 校验Form中的自定义标签
 * @param varForm Form对象
 * @return
 * 前台标签格式：
 * 
 */
function checkFormLabel(varForm){
	  var list = varForm.elements;
	  for(var i=0;i<list.length;i++){
	     var varItem = list[i];
	     var dataType = varItem.getAttribute("dataType");//数据类型 表单数据类型 "C" 为字符串，"N" 为数字,"D"为日期类型, "M"为必需输入字母email/phone/textarea
	     if(dataType==null || dataType==""){
	    	 continue;
	     }
	     var dataLength = varItem.getAttribute("dataLength");//数据长度、精度（最大长度+"."+精度）
	     var maxLength;
	     var precision;
	     if(dataLength!=undefined && dataLength.indexOf(".")!=-1){
	       var strDatas = dataLength.split("."); 
	       maxLength = strDatas[0];
	       precision = strDatas[1];
	     }else{
	         maxLength = dataLength;
	         precision = 0; 
	     }
	     //0（定长不允许空允许负）/1（不定长不可空允许负）/2（不定长允许空允许负）/3（定长允许空允许负）/ 4（定长不允许空不允许负）
	     //5（不定长不可空不允许负）/6（不定长允许空不允许负）/7（定长允许空不允许负）
	     var dataCtrl = varItem.getAttribute("dataCtrl");
	     //描述
	     var dataDesc = varItem.getAttribute("dataDesc");
	     if(dataType=="email"){       
	       if(!CheckEamil(varItem,dataCtrl,maxLength,dataDesc))return false;  
	     }else if(dataType=="phone"){
	        if(!isPhone(varItem,maxLength,dataCtrl,dataDesc))return false; 
	     }else if(dataType=="textarea"){//dataCtrl=1不能为空
	    	 if(!CheckTextarea(varItem,maxLength,dataDesc,null,dataCtrl))return false; 
	     }else if(dataType=="file"){
	    	 if(dataCtrl==1){//必填
	    		 if(!checkFile (varItem,maxLength,1,varItem.getAttribute("fileType"),dataDesc))return false; 
	    	 }else{//非必填
		    	 if(!checkFile (varItem,maxLength,2,varItem.getAttribute("fileType"),dataDesc))return false;
	    	 }
	     }else{
		     if(!checkFormItem(varItem,dataType,maxLength,precision,dataCtrl,dataDesc))return false;
	     }
	  }
	  return true;
}

//检验组织机构代码值 
function checkZzjgdmh(obj, bt) {
   var code = obj.value; // 检验组织机构代码号
   var tishi = "请输入正确的组织机构代码证号。(注：字母请大写)";
   if(bt == 0 && code.length == 0) {
       return true;
   } else if(code.length == 0) {
       alert(tishi);
       obj.focus();
       return false;
   }
   //若当前有十位数字说明含有"—" 形如xxxxxxxx-x 黄敏 2014-03-01
   if(code.length == 10){
	   if(code.charAt(8)=="-"){
		   code=code.replace("-","");
	   }
   }
   var reg = /^$|^[0-9|A-Z]{8}[0-9|X]$/; 
   var str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
   var params = "3,7,9,10,5,8,4,2".split(","); 
   var sum = 0; 

   if(reg.test(code)==false){ 
       alert(tishi); 
       obj.focus();
       return false;
   } else {
       for(var i=0; i <code.length-1; i++){ 
           //取字符串前8位的每位数字 
           var temp = code.charAt(i); 
           //当数字为"0"到"9"时 
           if(str.indexOf(temp) == -1){ 
           //当数字为0到9时,计算每位数字与参数的积并累加求和 
               sum = sum + parseInt(temp)*params[i]; 
           }else{ 
               //当数字为"A"到"Z"时,计算每位数字与参数的积并累加求和 
               sum = sum + (str.indexOf(temp)+10)*params[i]; 
           } 
       }  
       if((code.length!=0)){ 
           if((11-sum%11)==10) {
               if(code.charAt(8)!="X"){
                   alert("请输入正确的组织机构代码证号。(注：字母请大写)");
                   obj.focus();
                   return false; 
               } else {
                   return true;
               }
           }else if((11-sum%11)==11) {
               if(code.charAt(8)!="0") {
                   alert("请输入正确的组织机构代码证号。(注：字母请大写))"); 
                   obj.focus();
                   return false;
               } else {
                   return true;
               }
           }else if((11-sum%11)!=code.charAt(8)){
               alert("请输入正确的组织机构代码证号。(注：字母请大写)"); 
               obj.focus();
               return false;
           }
       }
   } 
   return true;
}  

//引入css样式
function loadCss(url) {
	if (!document.getElementById(url)) {
		var link = document.createElement("link");
		link.setAttribute("rel", "stylesheet");  
	    link.setAttribute("type", "text/css");  
	    link.setAttribute("href", url); 
	    link.setAttribute("id", url);
		document.getElementsByTagName("head")[0].appendChild(link);
	}
}



function backBorderColor(item) {
	if (item.tagName.toLowerCase() == "select") {
		item.style.backgroundColor = "";//item.getAttribute("bg_color");
	} else {
		item.style.borderLeftColor = item.getAttribute("bl_color");
		item.style.borderRightColor = item.getAttribute("br_color");
		item.style.borderTopColor = item.getAttribute("bt_color");
		item.style.borderBottomColor = item.getAttribute("bb_color");
	}
}


/**
 * zui提示DIV显示
 * @param tagetId  目标元素ID
 * @param mesage  信息
 * @param type 类型 null，error:0 ，success:1，warning:2
 */
function showMesage(tagetId,mesage,type) {
	var parentDiv=tagetId+"_mesage_div";
	var mesageDiv=tagetId+"_mesage_help_div";
	if(type==null){
		$("#"+parentDiv).attr("class","control  message-normal");
		$("#"+mesageDiv).attr("class","help-block message-normal");
		$("#"+mesageDiv).text(mesage);
		$("#"+mesageDiv).show();
	}else if(type==0){
		$("#"+parentDiv).attr("class","control has-error");
		$("#"+mesageDiv).attr("class","help-block message-error");
		$("#"+mesageDiv).text(mesage);
		$("#"+mesageDiv).show();
	}else if(type==1){
		$("#"+parentDiv).attr("class","control has-success");
		$("#"+mesageDiv).attr("class","help-block message-success");
		$("#"+mesageDiv).text(mesage);
		$("#"+mesageDiv).show();
	}else if(type==2){
		$("#"+parentDiv).attr("class","control has-warning");
		$("#"+mesageDiv).attr("class","help-block message-warning");
		$("#"+mesageDiv).text(mesage);
		$("#"+mesageDiv).show();
	}
}

/**
 * zui提示DIV隐藏
 * @param type 类型 null (所有)，error:0 ，success:1，warning:2
 */
function hideMesageByType(type) {

	if(type==null){

		$(".has-error").each(function(){
			$(this).removeClass("has-error");
		});
		
		$(".has-success").each(function(){
			$(this).removeClass("has-success");
		});
		
		$(".has-warning").each(function(){
			$(this).removeClass("has-warning");
		});
		
		$(".message-normal").each(function(){
			$(this).hide();
			$(this).text("");
		});
		
		$(".message-error").each(function(){
			$(this).hide();
			$(this).text("");
		});
		
		$(".message-success").each(function(){
			$(this).hide();
			$(this).text("");
		});
		
		$(".message-warning").each(function(){
			$(this).hide();
			$(this).text("");
		});
		
	}else if(type==0){

		$(".has-error").each(function(){
			$(this).removeClass("has-error");
		});
		$(".message-error").each(function(){
			$(this).hide();
			$(this).text("");
		});
		
	}else if(type==1){
		$(".has-success").each(function(){
			$(this).removeClass("has-success");
		});
		
		$(".message-success").each(function(){
			$(this).hide();
			$(this).text("");
		});
		
	}else if(type==2){
		
		$(".has-warning").each(function(){
			$(this).removeClass("has-warning");
		});
		
		$(".message-warning").each(function(){
			$(this).hide();
			$(this).text("");
		});
	}
}

/**
 * zui提示DIV隐藏
 * @param tagetId  目标元素ID
 * @param type 类型 null，error 0 ，success 1，warning 2
 */
function hideMesageById(tagetId) {
	var parentDiv=tagetId+"_mesage_div";
	var mesageDiv=tagetId+"_mesage_help_div'";
	if(type==null){
		$("#"+parentDiv).attr("class","control  message-normal");
		$("#"+mesageDiv).attr("class","help-block message-normal");
		$("#"+mesageDiv).hide();
		$("#"+mesageDiv).text("");
	}
}

/**
 * 2017-6-3 孙康
 * 校验多个手机号
 * @param mobile  校验元素
 * @param DefiniteLengthMark 标志可否为空。0可以为空，1为必填，默认为0
 * @param Describe 描述
 * @param SplitFlag 分隔符
 * @param Bz  p 座机 ,m 手机, pm 手机和座机
 * @returns {Boolean}
 */
function CheckMultiMobile(FormItem,DefiniteLengthMark,Describe,SplitFlag,Bz){
	var mobile = FormItem.value;
	if(DefiniteLengthMark == 0 && mobile.length ==0){
	   return true;
	}
	if(DefiniteLengthMark == 1 && mobile.length <=0){
	   popAlert(FormItem,Describe+"不能为空！");
	   setFormItemFocus(FormItem);
	   return false;
	}
	if(mobile.length >0){
		var mobileStrs= new Array(); //定义一数组 
		mobileStrs=mobile.split(SplitFlag); //字符分割 
		for (i=0;i<mobileStrs.length ;i++ ){ 
			var temp = mobileStrs[i];
			temp = temp.replace(/^\s*|\s*$/g,"");
			var re_mobile = /^1[3|4|5|7|8][0-9]{9}$/; //手机号验证规则
			var re_phone = /^0\d{2,3}-?\d{7,8}$/; //座机验证规则
			if(Bz=="p"){
				if(!re_phone.test(temp)){
				   popAlert(FormItem,"请输入正确的"+Describe+"!");
			       setFormItemFocus(FormItem);
			       return false;
				}
			}else if (Bz=="m"){
				if(!re_mobile.test(temp)) {
				   popAlert(FormItem,"请输入正确的"+Describe+"!");
			       setFormItemFocus(FormItem);
			       return false;
				}
			}else if (Bz=="pm"){
				if(re_mobile.test(temp) || re_phone.test(temp)){
					continue;
			    }else{
			       popAlert(FormItem,"请输入正确的"+Describe+"!");
			       setFormItemFocus(FormItem);
			       return false;
			    }
			}	
		} 
	}
	return true;
}




//加载依赖js：pophint.js
include("static/publicJs/Pophint.js");
//加载依赖的样式 popHint.css
loadCss("/UIEditor/static/fileCss/popHint.css");