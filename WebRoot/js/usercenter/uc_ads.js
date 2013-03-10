         	
/**
         	1	人力资源管理师	10	高级会计师
			2	心理咨询师	11	公务员
			3	会计资格证	12	经济师考试课程
			5	司法考试		13	研究生统一入学考
			6	迷你课程		14	初级会计职称
			7	注册会计师	15	中级会计职称
			8	证券从业		16	二级建造师
			9	建造师考试课程	17	监理工程师
			18	造价工程师	25	报关员考试
			19	GCT			26	地方公务员
			20	自考课程		27	职称英语
			21	临床执业医师	28	期货从业资格
			22	企业法律顾问	29	内部学员课程分类
			23	成考课程		
			24	注册税务师		
         	*/

$().ready(
	function adsInit(){
				/**中间begin**/
	         	if(subjectId==23 ){
	         		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=232&tId=1/grzy");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_11_gwy.jpg");
	         	}else if(subjectId==3 ){//会计证
	         		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=252&tId=1/grzy");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_11_gwy.jpg");
	         	}else if(subjectId==24 ){
	         		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=255/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_15_kjs.jpg");
	         	}else if( subjectId==25){
	         		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=233/grzy");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_5_sf.jpg");
	         	}else if( subjectId==18 ){
	         		$("#flashcontent").attr("href","http://highso.cn/static/web/column/651/index_1.shtml");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_16_jz02.jpg");
	         	}else if(subjectId==1 ){
	         		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=247/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_12_jjs.jpg");
	         	}else if(subjectId==9){
	         		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=238/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_9_jz01.jpg");
         		}else if(subjectId == 14){
         			$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=252/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_14_zlkjs.jpg");
            	}else if(subjectId == 15){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=254/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_15_kjs.jpg");
            	}else if(subjectId == 11){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=232/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_11_gwy.jpg");
            	}else if(subjectId == 5){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=277&tId=4");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_5_sf05.jpg");
            	}else if(subjectId == 12){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=247/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_12_jjs.jpg");
            	}else if(subjectId == 16){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=248/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_16_jz02.jpg");
            	}else if(subjectId == 17){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=248/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_16_jz02.jpg");
            	}else if(subjectId == 10){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=255/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_15_kjs.jpg");
            	}else if(subjectId == 7){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=255/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_15_kjs.jpg");
            	}else if(subjectId == 13){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=232&tId=1/grzy");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_11_gwy.jpg");
            	}else if(subjectId == 20){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=232&tId=1/grzy");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_11_gwy.jpg");
            	}else if(subjectId == 19){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=232&tId=1/grzy");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_16_jz02.jpg");
            	}else if(subjectId == 27){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=252/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_14_zlkjs.jpg");
            	}else if(subjectId == 28){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=252/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_14_zlkjs.jpg");
            	}else if(subjectId == 8){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=247/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_12_jjs.jpg");
            	}else if(subjectId == 2){
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=247/grys");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHomess_12_jjs.jpg");
            	}else if(subjectId == 22){//企业法律顾问
            		$("#flashcontent").attr("href","http://haixue.com/goods/goods_details.do?id=247&tId=4");
	         		$("#flashcontentimg").attr("src",importURL + "/images/usercenter/ss_ad/ssHome_5_sf06.jpg");
            	}
	         	/**中间end**/
	/*
	var subjectId =getCookie("subjectId");
	if(subjectId!=null && subjectId!=''){
	   if(  subjectId==20 || subjectId==23){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/gwy/gw01?Webfrom=cus");
    	}else if( subjectId==7 || subjectId==24 || subjectId==10){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/kjs/kjs01?Webfrom=cus");
    	}else if( subjectId==22 || subjectId==25){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/sf/sf03?Webfrom=cus");
    	}else if(subjectId==16 ||  subjectId==18 || subjectId==19){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/651/index_1.shtml");
    	}else if(subjectId==1 || subjectId==2 || subjectId==8 ){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/669/index_1.shtml");
    	}else if(subjectId==9){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/676/index_1.shtml");
    	}else if(subjectId==3 ||  subjectId==27 || subjectId==28){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/zlkjs/zlkjs01?Webfrom=cus");
    	}else if(subjectId == 13){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/gwy/gw01?Webfrom=cus");
    	}else if(subjectId == 14){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/682/index_1.shtml");
    	}else if(subjectId == 15){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/683/index_1.shtml");
    	}else if(subjectId == 11){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/691/index_1.shtml");
    	}else if(subjectId == 5){
    		$("#StudyStoreadlink").attr("href","http://highso.cn/static/web/column/695/index_1.shtml");
    	}else if(subjectId == 12){
    		$("#StudyStoreadlink").attr("href","http://haixue.com/goods/goods_details.do?id=247");
    	}
	}
	*/
	    /**右上begin**/     	
    	$("#picAdvert").html("");
	    if( subjectId==22){//22企业法律顾问
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=272&tId=7' ><img src='"+importURL+"/images/usercenter/ss_ad/ss_5_sf08.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==11 ){
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=232/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(  subjectId==23){//11公务员  20公务员 23成考 13考研
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if( subjectId==18 ){// 17造价  18监理
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=248/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_02.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=253/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_03.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=258/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId== 3 ){//3会计证 
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==15){//中级会计
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=254/grys'><img src='"+importURL+"/images/usercenter/ss_15_kjs01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=255/grys'><img src='"+importURL+"/images/usercenter/ss_15_kjs03.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==10){//高级会计
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=241/grys'><img src='"+importURL+"/images/usercenter/ss_5_sf04.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==24){//注册税务师
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=254/grys'><img src='"+importURL+"/images/usercenter/ss_15_kjs01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==25){//报关员
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=245/grys'><img src='"+importURL+"/images/usercenter/ss_5_sf03.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==12){//经济师
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=244/grys'><img src='"+importURL+"/images/usercenter/ss_12_jjs_01.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=247/grys'><img src='"+importURL+"/images/usercenter/ss_12_jjs_02.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=260&tId=1'><img src='"+importURL+"/images/usercenter/ss_12_jjs_03.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==19){//GCT
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_02.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=254/grys'><img src='"+importURL+"/images/usercenter/ss_15_kjs01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==9){//9一建
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=246/grys'><img src='"+importURL+"/images/usercenter/ss_9_jz01_01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=238/grys'><img src='"+importURL+"/images/usercenter/ss_9_jz01_02.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=243/grys'><img src='"+importURL+"/images/usercenter/ss_9_jz01_03.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=242/grys'><img src='"+importURL+"/images/usercenter/ss_9_jz01_04.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==16){//16二建
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=248&tId=1/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_02.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=257/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_05.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=256/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_04.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=253/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_03.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==14){//14初级会计
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=251/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs04.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==17){//17监理师
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=248/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_02.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=253/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_03.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=258/grys'><img src='"+importURL+"/images/usercenter/ss_16_jz02_01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==5){//5司法
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=272&tId=7' ><img src='"+importURL+"/images/usercenter/ss_ad/ss_5_sf08.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==7){//注会
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==13){//考研
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==20){//自考
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=231/grys'><img src='"+importURL+"/images/usercenter/ss_11_gw01.jpg' style='padding-bottom:10px;' /></a><a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==27){//自考
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==28){//期货
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==8){//期货
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==2){//期货
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else if(subjectId==1){//期货
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=249/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs03.jpg' style='padding-bottom:10px;' /></a> ");
	   		$("#picAdvert").append("<a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=250/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs01.jpg' style='padding-bottom:10px;' /></a> <a  target='_blank' href='http://haixue.com/goods/goods_details.do?id=252/grys'><img src='"+importURL+"/images/usercenter/ss_14_zlkjs02.jpg' style='padding-bottom:10px;' /></a>");
	   	}else{
	   		$("#picAdvert").hide();
	   	}
	    /**右上end**/     
	    /**右下浮动*
	 	if(subjectId==23){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=231/grys");
	 	}else if( subjectId==7 || subjectId==24 ){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=305&tId=1");
	 	}else if( subjectId==25){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=233/grys");
	 	}else if(  subjectId==19){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=297&tId=1");
	 	}else if( subjectId==2 ){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=247/grys");
	 	}else if(subjectId==28){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=297&tId=1");
	 	}else if(subjectId==16){//二建
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_lists.do?pId=8&catg_id=295&parId=293&ca_id=293");
	 	}else if(subjectId==9){//一建
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_lists.do?pId=8&catg_id=294&parId=293&ca_id=293");
	 	}else if(subjectId==14){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=262&tId=1/grys");
	 	}else if(subjectId==15){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=254/grys");
	 	}else if(subjectId==11){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=232/grys");
	 	}else if(subjectId==5){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=245&tId=5");
	 	}else if(subjectId==12){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=247/grys");
	 	}else if(subjectId==17){//监理
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_lists.do?pId=8&catg_id=295&parId=293&ca_id=293");
	 	}else if(subjectId==18){//造价
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_lists.do?pId=8&catg_id=295&parId=293&ca_id=293");
	 	}else if(subjectId==22){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=245&tId=5");
	 	}else if(subjectId==10){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=305&tId=1");
	 	}else if(subjectId==24){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=254/grys");
	 	}else if(subjectId==20){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=231/grys");
	 	}else if(subjectId==27){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=297&tId=1");
	 	}else if(subjectId==8){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=244/grys");
	 	}else if(subjectId==2){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=244/grys");
	 	}else if(subjectId==1){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=244/grys");
	 	}else if(subjectId==3){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=297&tId=1");
	 	}else if(subjectId==13){
	 		$("#floatBoxlink").attr("href","http://haixue.com/goods/goods_details.do?id=231/grfb");
	     	}
	     	*/
	}
);