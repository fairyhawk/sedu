function cleanWhitespace(element) {
	for (var i = 0;i<element.childNodes.length;i++) {
		var node = element.childNodes[i]
		if (node.nodeType ==3 && !/\S/.test(node.nodeValue))
		node.parentNode.removeChild(node);
	}
	}
	
	function SohuMenuGlobal(_tab, _content, evt,_startNum,classNames){
		this.tabs = [];
		this.contents = [];
		this.currentNum;
		this.init(_tab,_content,evt,_startNum,classNames);
	}
	
	
	SohuMenuGlobal.prototype.init = function(_tab, _content, evt,_startNum,classNames){
		var old
		var obj = document.getElementById(_tab)
		var cbj = document.getElementById(_content)
		if (!obj) return false
		if (!cbj) return false
		cleanWhitespace(obj)
		cleanWhitespace(cbj)
		this.tabs = obj.getElementsByTagName("LI");
		if (!this.tabs) return false
		
		for(var i=0; i<this.tabs.length; i++){
			this.tabs[i].flag = i;
		}
	
		var contstemp = cbj.childNodes;
		for(var i=0; i<contstemp.length; i++){
			if(contstemp[i].nodeType == 1)	 this.contents.push(contstemp[i]);
		}
		
		if (_startNum>=this.tabs.length) {_startNum=0}
		if (!_startNum){_startNum=0}
		if (!classNames) {classNames="active"}
		if (!evt) {evt=2}
		
		this.contents[_startNum].className="dispshow"
		this.tabs[_startNum].className=classNames
		this.contents[_startNum].className="dispshow"
		this.currentNum = _startNum
		this.addEvent(evt,classNames);
	}
	
	SohuMenuGlobal.prototype.addEvent = function(evt,classNames){
		for(var i=0; i<this.tabs.length; i++){
			this.tabs[i].reflect = this;
			if (evt==2) {
				this.tabs[i].onclick = function(){
					var last = this.reflect.currentNum;

					this.reflect.contents[last].className="dispnone"
					this.reflect.contents[this.flag].className="dispshow"

					if (this.reflect.tabs[last].className!="active") {
						this.reflect.tabs[last].className=old
					}else {
						this.reflect.tabs[last].className=""
					}
					
					old = this.reflect.tabs[this.flag].className
					if (!old) {
					this.reflect.tabs[this.flag].className=classNames
					} else {
					this.reflect.tabs[this.flag].className=old+"_1"
					}
					
					
					this.reflect.currentNum = this.flag;
					this.blur();
					return false;
				}
			} else {
				this.tabs[i].onclick = function(){
					var last = this.reflect.currentNum;

					this.reflect.contents[last].className="dispnone"
					this.reflect.contents[this.flag].className="dispshow"


					if (this.reflect.tabs[last].className!="active") {
						this.reflect.tabs[last].className=old
					}else {
						this.reflect.tabs[last].className=""
					}
					
					old = this.reflect.tabs[this.flag].className
					if (!old) {
					this.reflect.tabs[this.flag].className=classNames
					} else {
					this.reflect.tabs[this.flag].className=old+"_1"
					}
					this.reflect.currentNum = this.flag;
					this.blur();
					return false;
				}
			}
				
		}
	}
	
	$().ready(function() {
		var sMenu=new SohuMenuGlobal("title1","sContent");
	});
