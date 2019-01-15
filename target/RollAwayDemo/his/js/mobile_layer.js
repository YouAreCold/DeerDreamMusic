	var layer;
!function () {

	function msgBox(content,opt){
		var styleSheet=document.styleSheets,msg={sele:".layer-msg",rule:"position:absolute;word-break: break-all;word-wrap: break-word;z-index:999999;"};
		var useCss=opt.clearDefaultCss||false;
		if(!checkRule(styleSheet[styleSheet.length-1],msg.sele)){
			inserCss(styleSheet[styleSheet.length-1],msg,++layer.rulesIndex);
		}
		this.d=createEle("div","layer layer-msg",content,document.body,useCss?null:"color:#fff;max-width:60%;min-width:50%;max-height:600px;overflow:auto;padding:4%;background:#000;opacity:0.5;filter:alpha(opacity=50);border-radius:4px");
	}
	function alertBox(opt){
		var useCss=opt.clearDefaultCss||false;
		var styleSheet=document.styleSheets,alert={sele:".layer-alert",rule:"position:absolute;word-break: break-all;word-wrap:break-word;z-index:999999;"};
		
		if(!checkRule(styleSheet[styleSheet.length-1],alert.sele)){
			inserCss(styleSheet[styleSheet.length-1],alert,++layer.rulesIndex);	
		}
		this.d=createEle("div","layer layer-alert",null,document.body,useCss?null:"background:#fff;min-width:600px;max-height:460px;overflow:auto;padding:4px 2%;border-radius:4px");
		this.titleEle=createEle("div","layer-title",opt.titleText,this.d,useCss?null:"color:#328AFD;");
		this.closeEle=createEle("div","layer-close fa fa-remove fa-2x",opt.closeBtnText,this.titleEle,useCss?null:"color:red;float:right;cursor: pointer;");
		this.contentEle=createEle("div","layer-content",opt.contentText,this.d,useCss?null:"color:#333");
		this.yesBtnEle=createEle("div","layer-yesBtn fa fa-save",opt.yesBtnText,this.d,useCss?null:"display: inline-block;width:20%;text-align:center;cursor: pointer;");
		this.noBtnEle=createEle("div","layer-noBtn fa fa-times-rectangle",opt.noBtnText,this.d,useCss?null:"display: inline-block;width:20%;text-align:center;cursor: pointer;float: right;");
		addEvent(this.closeEle,"ontouchstart" in window?"touchstart":"click",closeBox);
		addEvent(this.noBtnEle,"ontouchstart" in window?"touchstart":"click",closeBox);
		addEvent(this.yesBtnEle,"ontouchstart" in window?"touchstart":"click",function (e) {
			opt.yesCall&&opt.yesCall();
			closeBox(e);
		});
		function closeBox(e) {
			var e=e||window.event;
			if(e.preventDefault){e.preventDefault();}else{e.returnValue=false;}
			layer._del(layer.d,"show");
			layer._del(layer.wrap,"wrapShow");
		}
	}

	function Layer() {
		var self=this;
		createStyle();
		initCss(this);
		this.show=false;
		this.timer=null;
	}

	Layer.prototype._popUp=function () {
		this._initDoc();
		this.show=true;
	};
	Layer.prototype._del=function(d,flag){
		this[flag]=false;
		if(d.remove){
			d.remove();
		}else if (d.removeNode) {
			empty(d);
			d.removeNode();
		}else{
			d.parentNode.removeChild(d);
		}
	};
	Layer.prototype._initDoc=function(){
		var top=left=0;
		this.d.style.top=0+"px";//要重置数值
		this.d.style.left=0+"px";
		this.h=document.documentElement.clientHeight;
		this.w=document.documentElement.clientWidth ;
		this.offsetH=this.d.offsetHeight;
		this.offsetW=this.d.offsetWidth;
		var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
		var scrollLeft=document.body.scrollLeft||document.documentElement.scrollLeft;
		if(scrollTop>0){
			top=scrollTop+(this.h/2-this.offsetH/2);
		}else{
			top=(this.h/2-this.offsetH/2);
		}
		if(scrollLeft>0){
			left=scrollLeft+(this.w/2-this.offsetW/2);
		}else{
			left=(this.w/2-this.offsetW/2);
		}

		this.d.style.top=top+"px";
		this.d.style.left=left+"px";
	};

	Layer.prototype.msg=function (s,opt) {
		var self=this;
		if(this.show){
			this._del(this.d,"show");
		}
		this.d=new msgBox(s,opt||{}).d;
		addEvent(this.d,"ontouchstart" in window ?"touchstart":"click",function(e){
		var e=e||window.event;
		if(e.preventDefault){e.preventDefault();}else{e.returnValue=false;}
			self._del(self.d,'show');
		});
		this._popUp();
		delayClose(this);
	};
	Layer.prototype.boxresize=function () {this._initDoc();}
	Layer.prototype._initWrap=function(){
		var self=this;
		var styleSheet=document.styleSheets,wrap={sele:".layer-wrap",rule:"background:#000;opacity:0.5;filter:alpha(opacity=50);width:100%;height:100%;position:absolute;"};
		if(!checkRule(styleSheet[styleSheet.length-1],wrap.sele)){
			inserCss(styleSheet[styleSheet.length-1],wrap,++this.rulesIndex);	
		}
		if(!this.wrapShow){
			this.wrap=createEle("div","layer-wrap",null,document.body);
		}
		var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
		var scrollLeft=document.body.scrollLeft||document.documentElement.scrollLeft;
		this.wrap.style.top=scrollTop+"px";
		this.wrap.style.left=scrollLeft+"px";
	};
	Layer.prototype.alert=function (opt) {
		var self=this;
		clearTimeout(this.timer);
		if(this.show){
			this._del(this.d,"show");
			this._del(this.wrap,"wrapShow");
		}
		this._initWrap();
		this.wrapShow=true;
		this.d=new alertBox(opt||{}).d;
		this._popUp();
	};
	function initCss(t) {
		t.rulesIndex=0;
		var styleSheet=document.styleSheets;
		var keyfarmes="@-webkit-keyframes layer-show {0% {  -webkit-transform: scale(0.8);}100% { -webkit-transform: scale(1); }}";
		var aniCss=".layer{-webkit-perspective:1000px;perspective: 1000px;-webkit-transform:translateZ(0);transform:translateZ(0);-webkit-animation: layer-show 0.5s ease;animation: layer-show 0.5s ease;}";
		!ie()&&styleSheet[styleSheet.length-1].insertRule(keyfarmes,t.rulesIndex);//ie8,9 不加动画
		!ie()&&styleSheet[styleSheet.length-1].insertRule(aniCss,++t.rulesIndex);//ie8,9 不加动画
	}
	function inserCss(styleSheet,css,i){
		if(!ie()){styleSheet.insertRule(css.sele+"{"+css.rule+"}",i);}else{styleSheet.addRule(css.sele,css.rule,i);}	
	}
	function ie(){
		var ie=!!window.ActiveXObject,isMode=document.documentMode,
		isIE9 = ie &&  isMode== 9,isIE8 = ie && isMode == 8; 
		if (isIE9||isIE8) { return true;}
		return false;
	}
	function createStyle(){
		var style=document.createElement("style");
		document.getElementsByTagName('head')[0].appendChild(style);
	}
	function empty(d){
		var list=d.childNodes;
		for (var i = list.length - 1; i >= 0; i--) {d.removeChild(list[i]);}
	}
	function delayClose(t){
		clearTimeout(t.timer);
		t.timer=setTimeout(function () {
				t._del(t.d,"show");
			},1000);
		}
		function addEvent(o,event,fn){
			if(o.addEventListener){
				o.addEventListener(event,fn,false);
			}else if(o.attachEvent){
				o.attachEvent("on"+event,fn);
			}
		}
		function checkRule(styleSheet,s) {
			var r=styleSheet.rules||styleSheet.cssRules;
			for (var i = r.length - 1; i >= 0; i--) {
			var selector= r[i].name||r[i].selectorText;
				if(selector.match(s)!==null){return true;}
			} return false;
	}
	function createEle(tagName,cssName,text,parent,css){
		var ele=document.createElement(tagName);
		if(cssName)ele.className=cssName;
		if(text)ele.innerHTML=text;
		if(css)ele.style.cssText=css;
		if(parent)parent.appendChild(ele);
		return ele;
	}

	layer=new Layer();

	window.onscroll=function () {
		if(layer.show){
			layer._initDoc();
			layer.wrapShow&&layer._initWrap();
		}
	};

	window.onresize=function(){
		if(layer.show){
			layer._initDoc();
		}
	};
}(window,document);
