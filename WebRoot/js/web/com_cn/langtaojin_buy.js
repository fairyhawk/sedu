$(document).ready(function(){
	var indexPageUrl = getCookie('indexURL');
	indexPageUrl = indexPageUrl? indexPageUrl: '';
	window.langtaojin_client_id  = '';
	var default_client_id = '8523';
	var client_id_map = {
		sf: '8660', 
		cpa: '8661',
		rl: '8662',
		kjz: '8663',
		zq: '8664',
		jz: '8659',
		gk: '8665',
		gwy: '8666'
	};
	for(var p in client_id_map){
		if(indexPageUrl.indexOf('/'+p) >= 0){
			langtaojin_client_id = client_id_map[p];
			break;
		}
	}
	if(!langtaojin_client_id){
		langtaojin_client_id = default_client_id;
	}
	if('http://highso.com.cn'==window.location.href || 'http://highso.com.cn/'==window.location.href){
		langtaojin_client_id = default_client_id;
	}
	if (typeof(window.ltj_phone) == 'undefined') {window.ltj_phone = '';}
	if (typeof(window.showorder) == 'undefined') {window.showorder = '';}
	var referer = encodeURIComponent(document.referrer)
	var uctrackUrl = 'http://t.uctrac.com/js/t.js?client_id='+langtaojin_client_id+'&referrer='+referer+'&rand='+Math.random();
	var langtaojin_convert=2;//1 means registration, while 2 means payment
	var langtaojin_convert_note='ÉÐµÂ×¢²á';
	langtaojin_convert_note = encodeURIComponent(langtaojin_convert_note);
	langtaojin_convert = encodeURIComponent(langtaojin_convert+'');
	var _langtaojin_order_id=typeof langtaojin_order_id == 'undefined'? '': langtaojin_order_id;
	var requestUrl = 'http://track.langtaojin.com/tracking/recvdata.js?client_id=' + langtaojin_client_id + '&convert=' + langtaojin_convert + '&orderid=' + _langtaojin_order_id + '&convert_note=' + langtaojin_convert_note + '&rand=' + Math.random();
	$.getScript(requestUrl);
	$.getScript(uctrackUrl);
});