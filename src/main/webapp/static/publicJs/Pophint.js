/*
 * @discription:
 * @author: wanglijun
 * @version: 2.1
 * @date: 2011/11/25
 * @requires: 
 */
 
Pophint = {
	
	/*
	 * @discription: ��ȡ��Ԫ�ش�Сλ��
	 * @author: 
	 * @date: 
	 * @params:
	 *  node - {Element} ����֤Ԫ�� 
	 * @return:
	 *	{x: x, y: y, w: w, h: h} ����
	 */
	getCoords : function(node){
		var x = node.offsetLeft;
		var y = node.offsetTop;
		var w = node.offsetWidth;
		var h = node.offsetHeight;
		var parent = node.offsetParent;
		while (parent != null) {
			x += parent.offsetLeft;
			y += parent.offsetTop;
			parent = parent.offsetParent;
		}
		
		if(w == 0) {
			w = 160;
		}
		if(h == 0) {
			h = 20;
		}
		return {x: x, y: y, w: w, h: h};
	},

	/*
	 * @discription: ��������ʾ
	 * @author: 
	 * @date: 
	 * @params:
	 *  obj - {Element} ����֤Ԫ�� 
	 *  msg - {string} ��ʾ��Ϣ
	 *  initValues - {Element} ��ʼֵ
	 * @return:
	 *	{x:x,y:y} ����
	 */
	popHint : function(obj, msg, initValues) {
		var
		_obj = obj,
		_objHint = _obj.ownerDocument.getElementById("popHint");
		if (typeof _objHint == "undefined") {
			_objHint = _obj.ownerDocument.createElement("div");
			_objHint.id = "popHint";
		}
		_msg = msg,
		_init = initValues;
		_obj.oldCalss = _obj.className;

		// ��ʼ��ʧ��...
		if(_obj==undefined || _msg==undefined || _msg=="") {
			return;
		}

		// ���ó�ʼֵ
		_init = _init==undefined ? {_type : "wrong", _event : "click"} : _init;
		// obj������ɼ������õ�������Ϊobj��Ԫ��
		if(_obj.style.display=='none' || _obj.style.visibility=='hidden' || _obj.getAttribute('type')=='hidden') {
			_obj = _obj.parentNode;
		}

		var
		_type = null,
		_event = null,
		_place = this.getCoords(_obj),
		_marTop = null,
		_marWidth = null,
		_objText = _obj.ownerDocument.getElementById("popHintText");

		// ��ʼ��
		init = function() {
			var _hint = _obj.getAttribute("hint");
			if(_hint=="false") {
				return;
			}

			// �е�ʱ��initValues��Ϊ��.����ֻ����һ��ֵ...���ⷢ������.�ٴ����ó�ʼֵ...
			_type = _init._type==undefined ? "wrong" : _init._type;
			_type = _type.toLowerCase();
			_event = _init._event==undefined ? "click" : _init._event;
			_event = _event.toLowerCase();

			var _Html = "<div id=\"popHintTop\" class=\"popLeftTopAngle\"><span></span></div>" +
									"<div id=\"popHeader\">" +
									"    <div id=\"popHintLeft\" class=\"popLeft\"></div>" +
									"    <div id=\"popHintText\"></div>" +
									"    <div id=\"popHintRight\" class=\"popRight\"></div>" +
									"</div>"+
									"<div id=\"popHintFoot\" class=\"popLeftAngle\"><span></span></div>";

			if(_objHint==null) {
				_objHint = appendElement("div", {"id" : "popHint"}, _Html, _obj.ownerDocument);
				_objHint.style.display = "none";
				_objHint.style.zIndex = 1000;
				_objHint.style.width = "100%";
				_objText = _obj.ownerDocument.getElementById("popHintText");
			}
			show();
		};
		// ��ʾ
		show = function() {
			_objHint.style.display = "";
			_msg = "<span class=\"popIcon "+ _type +"\"></span>"+ _msg;
			_objText.innerHTML = _msg;
			//_marTop = _objHint.offsetHeight;
			//var textW = _obj.ownerDocument.getElementById("popHintText").offsetWidth;
			//2014��3��31�ա�·��Զ�������ȡoffsetWidth̫С���ѽ�����ҵ�bug
			var textW = Math.max(_obj.ownerDocument.getElementById("popHintText").offsetWidth , _obj.ownerDocument.getElementById("popHintText").scrollWidth);
			if(textW == 0) {
				textW = 150 + (_msg.length - 45) * 10;
			}
			var textH = _obj.ownerDocument.getElementById("popHintText").offsetHeight;
			if(textH == 0) {
				textH = 24;
			}
			var footH = _obj.ownerDocument.getElementById("popHintFoot").offsetHeight;
			if(footH == 0) {
				footH = 10;
			}
			_marTop = textH + footH;
			_marWidth = textW + 20;
			//_marTop = _obj.ownerDocument.getElementById("popHintText").offsetHeight + _obj.ownerDocument.getElementById("popHintFoot").offsetHeight;
			//_marWidth = _obj.ownerDocument.getElementById("popHintText").offsetWidth + 20;
			if ((_obj.ownerDocument.body.clientWidth-_place.x) >= _marWidth) {
				_obj.ownerDocument.getElementById("popHintFoot").className = "popLeftAngle";
				_obj.ownerDocument.getElementById("popHintTop").className = "popLeftTopAngle";
				_objHint.style.left = (_place.x) +"px";
			} else {
				_obj.ownerDocument.getElementById("popHintFoot").className = "popRightAngle";
				_obj.ownerDocument.getElementById("popHintTop").className = "popRightTopAngle";
				_objHint.style.left = (_place.x-_marWidth + 30) +"px";
			}
			//�ж���������Ϸ�/�·���ʾ
			if (_place.y-_marTop >= 0 ) {
				_objHint.style.top = (_place.y-_marTop) +"px";
				document.getElementById("popHintTop").style.display="none";
				document.getElementById("popHintFoot").style.display="";
			} else {
				_objHint.style.top = _obj.offsetHeight +"px";
				document.getElementById("popHintTop").style.display="";
				document.getElementById("popHintFoot").style.display="none";
			}
			_objHint.style.width = _marWidth + 20 + "px";
			
			// �رմ����¼�
			switch(_event) {
			case "blur" :
				myAddEventListener(_obj, 'blur', hide);
				break;
			//default :
			case "click" :
				myAddEventListener(_obj.ownerDocument, 'mousedown', hide);
				break;
			//��������Լ���չ�ܶ��¼�...
			}

		};
		// �ر�
		hide = function() {
			_objHint.style.display = "none";
			_objText.innerHTML = "";
//			_obj.style.filter = "";
			_obj.className = _obj.oldCalss;
			// �Ƴ��رմ����¼�
			myRemoveEventListener(_obj, 'blur', hide);
			myRemoveEventListener(_obj.ownerDocument, 'mousedown', hide);
		};

		appendElement= function(tagName, Attribute, strHtml, dom) {
			var cEle = dom.createElement(tagName);
			// ����ֵ
			for (var i in Attribute){
				cEle.setAttribute(i, Attribute[i]);
			}
			cEle.innerHTML = strHtml;
			dom.body.appendChild(cEle);
			return cEle;
		};

		var eventListeners= [];

		findEventListener= function(node, event, handler){
			var i;
			for (i in eventListeners){
				if (eventListeners[i].node == node && eventListeners[i].event == event && eventListeners[i].handler == handler){
					return i;
				}
			}
			return null;
		};

		myAddEventListener = function(node, event, handler){
			if (findEventListener(node, event, handler) != null){
				return;
			}
			if (!node.addEventListener){
				node.attachEvent('on' + event, handler);
			}else{
				node.addEventListener(event, handler, false);
			}
			eventListeners.push({node: node, event: event, handler: handler});
		};

		removeEventListenerIndex = function(index){
			var eventListener = eventListeners[index];
			delete eventListeners[index];
			if (!eventListener.node.removeEventListener){
				eventListener.node.detachEvent('on' + eventListener.event,
				eventListener.handler);
			}else{
				eventListener.node.removeEventListener(eventListener.event,
				eventListener.handler, false);
			}
		};

		myRemoveEventListener = function(node, event, handler){
			var index =findEventListener(node, event, handler);
			if (index == null) {
				return;
			}
			removeEventListenerIndex(index);
		};

		cleanupEventListeners = function(){
			var i;
			for (i = eventListeners.length; i > 0; i--){
				if (eventListeners[i] != undefined){
					removeEventListenerIndex(i);
				}
			}
		};

	init();
	}
};